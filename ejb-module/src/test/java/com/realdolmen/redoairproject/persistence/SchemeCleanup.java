package com.realdolmen.redoairproject.persistence;

import java.sql.SQLException;

/**
 * Created by EWTAX45 on 6/10/2015.
 */
public class SchemeCleanup {

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
            LOG.info("Dropped " + tableName);
            return true;
        } catch (SQLException e) {  // would be nice if there was a TableReferencedException
            // SQLException could cast too wide of a net
            LOG.info(tableName + " still referenced.");
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
