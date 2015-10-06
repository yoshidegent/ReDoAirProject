package com.realdolmen.redoairproject.persistence;

import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;
import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.sql.*;
import java.util.*;

public class PersistenceTest{

    public static final String DRIVER = "javax.persistence.jdbc.driver";
    public static final String URL = "javax.persistence.jdbc.url";
    public static final String USER = "javax.persistence.jdbc.user";
    public static final String PASSWORD = "javax.persistence.jdbc.password";

    private static final Logger logger = LoggerFactory.getLogger(PersistenceTest.class);
    public static final String DATABASE_ENGINE_SYSTEM_PARAMETER = "databaseEngine";

    private static EntityManagerFactory entityManagerFactory;

    private EntityManager entityManager;
    private EntityTransaction transaction;

    @BeforeClass
    public static void initializeEntityManagerFactory() {
        logger.info("Creating EntityManagerFactory");
        entityManagerFactory = Persistence.createEntityManagerFactory("MyTestPersistenceUnit", properties());
    }


    @Before
    public void loadTestData() throws Exception {
        logger.info("Clearing data");
        entityManager().createQuery("DELETE from Address a").executeUpdate();
        entityManager().createQuery("DELETE from Address a").executeUpdate();
        entityManager().createQuery("DELETE from Address a").executeUpdate();


        logger.info("Loading dataset");
        //IDataSet dataSet = new FlatXmlDataSetBuilder().build(getClass().getResource("/data.xml"));

        DataImporter dataImporter = new DataImporter(entityManager());
        dataImporter.importData();

        IDatabaseConnection connection = new DatabaseConnection(newConnection());
        connection.getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY,
            new MySqlDataTypeFactory()); // Set factorytype in dbconfig to remove warning

        //DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet);
        connection.close();
    }

    /**
     * Provides connection settings for the database.
     * @return Map of JPA properties.
     */
    protected static Map<String, String> properties() {
        DatabaseEngine databaseEngine = databaseEngine();
        HashMap<String, String> properties = new HashMap<>();
        properties.put(DRIVER, databaseEngine.driverClass);
        properties.put(URL, databaseEngine.url);
        properties.put(USER, databaseEngine.username);
        properties.put(PASSWORD, databaseEngine.password);
        return Collections.unmodifiableMap(properties);
    }

    private static DatabaseEngine databaseEngine() {
        String databaseEngine = System.getProperty(DATABASE_ENGINE_SYSTEM_PARAMETER);
        DatabaseEngine engine = databaseEngine != null ? DatabaseEngine.valueOf(databaseEngine) : DatabaseEngine.mysql;
        logger.info("Using database enigine: " + engine);
        return engine;
    }

    @Before
    public void initialize() {
        logger.info("Creating transacted EntityManager");
        entityManager = entityManagerFactory.createEntityManager();
        transaction = entityManager.getTransaction();
        transaction.begin();
    }

    @After
    public void destroy() {
        logger.info("Committing and closing transacted EntityManager");
        if(transaction != null) {
            if(transaction.getRollbackOnly()) {
                transaction.rollback();
            } else {
                transaction.commit();
            }
        }

        if(entityManager != null) {
            entityManager.close();
        }

    }

    @AfterClass
    public static void destroyEntityManagerFactory() {
        logger.info("Closing EntityManagerFactory");
        if(entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }

    /**
     * Obtains the current EntityManager. Use this to write tests against.
     */
    protected EntityManager entityManager() {
        return this.entityManager;
    }

    /**
     * Obtains a <strong>new</strong> JDBC connection using connection settings defined in {@link #properties()}
     * @return A new JDBC connection. Callsite is responsible for closing.
     * @throws SQLException When the shit hits the fan.
     */
    protected Connection newConnection() throws SQLException {
        Map<String, String> properties = properties();
        return DriverManager
            .getConnection(properties.get(URL), properties.get(USER), properties.get(PASSWORD));
    }












    /**
     * SQL Statement to get all table names from the database. Here our naming
     * convention is actually useful. This also enforces compliance to that
     * convention.
     */
    private static final String QUERY_FOR_TABLE_NAMES =
            "SELECT [name] FROM sys.tables WHERE [type] = 'U' AND [name] LIKE 'tbl_%'";



    /**
     * Drops the existing (possibly out of date) schema and uses hibernate tools
     * to create a new one based on the current object model.
     *
     * @throws SQLException
     */
    protected void freshDatabase() throws SQLException {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            dropSchema(connection);
            createSchema(connection);
        } catch (SQLException e) {
            throw e;
        } finally  {
            if (connection != null)  {
                connection.close();
            }
        }
    }


    /**
     * Runs a query to pull all the table names, and attempts to drop them one
     * by one until none exist. This may take several passes due to foreign key
     * constraints.
     *
     * @param connection an active connection which is left open
     * @throws SQLException
     */
    private void dropSchema(Connection connection) throws SQLException  {
        Set<String> tables = queryForTableNames(connection);
        while (! tables.isEmpty())  {
            Iterator<String> tablesIterator = tables.iterator();
            while(tablesIterator.hasNext()) {
                if (dropTable(connection, tablesIterator.next()))  {
                    tablesIterator.remove();
                }
            }
        }
    }


    /**
     * Runs a query to pull all table names (tbl_%) and adds them to a set.
     *
     * @param connection
     *            an active connection which is left open
     * @return a set containing the table names
     * @throws SQLException
     */
    private Set<String> queryForTableNames(Connection connection) throws SQLException {
        Set<String> tableNames = new HashSet<String>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(QUERY_FOR_TABLE_NAMES);
        while (resultSet.next())  {
            tableNames.add(resultSet.getString(1));
        }
        resultSet.close();
        statement.close();
        return tableNames;
    }


    /**
     * Tries to drop a given table name. Returns whether or not it succeeded.
     *
     * @param connection
     *            an active connection which is left open
     * @param tableName
     *            the name of the table to drop
     * @return whether or not the table was able to be dropped
     * @throws SQLException
     */
    private boolean dropTable(Connection connection, String tableName) throws SQLException  {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.execute("DROP TABLE " + tableName);
            logger.info("Dropped " + tableName);
            return true;
        } catch (SQLException e) {  // would be nice if there was a TableReferencedException
            // SQLException could cast too wide of a net
            logger.info(tableName + " still referenced.");
            return false;
        } finally  {
            if (statement != null)  {
                statement.close();
            }
        }
    }


    /**
     * Uses hibernate tools to create a new schema. Logs the script to stdout
     * and exports it to the database.
     *
     * @param connection
     *            an active connection which is left open
     */
    private void createSchema(Connection connection) {
        new SchemaExport(hibernateConfig, connection)
                .execute(true, true, false, true);
    }





}
