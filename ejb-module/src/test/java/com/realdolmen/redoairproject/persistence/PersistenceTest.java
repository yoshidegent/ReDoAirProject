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
        initialize();
        entityManager().createQuery("DELETE from Trip t").executeUpdate();
        entityManager().createQuery("DELETE from Flight f").executeUpdate();
        entityManager().createQuery("DELETE from Airline a").executeUpdate();
        entityManager().createQuery("DELETE from Airport a").executeUpdate();
        entityManager().createQuery("DELETE from Address a").executeUpdate();
        entityManager().createQuery("DELETE from Country c").executeUpdate();
        entityManager().createQuery("DELETE from Partner p").executeUpdate();
        entityManager().createQuery("DELETE from Passenger p").executeUpdate();
        entityManager().createQuery("DELETE from ReDoEmployees r").executeUpdate();
        entityManager().createQuery("DELETE from Partner p").executeUpdate();

        logger.info("Loading dataset");
        //IDataSet dataSet = new FlatXmlDataSetBuilder().build(getClass().getResource("/data.xml"));

        EntityManager entityManager1 = entityManagerFactory.createEntityManager();

        DataImporter dataImporter = new DataImporter(entityManager1);
        dataImporter.importData();

        entityManager1.flush();
        entityManager1.close();

        /*IDatabaseConnection connection = new DatabaseConnection(newConnection());
        connection.getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY,
            new MySqlDataTypeFactory()); // Set factorytype in dbconfig to remove warning

        //DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet);
        connection.close();*/
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

      /*  if(entityManager != null) {
            entityManager.close();
        }*/

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



}
