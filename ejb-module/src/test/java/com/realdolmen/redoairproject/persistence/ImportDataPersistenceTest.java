package com.realdolmen.redoairproject.persistence;

import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Loads a DBUnit test set before every unit test.
 * Zie data.xml onder resources, hier wordt een kleine test-data-set in XML beschreven om in te voeren in de database
 */
public class ImportDataPersistenceTest extends PersistenceTest {
    private static final Logger logger = LoggerFactory.getLogger(ImportDataPersistenceTest.class);

    @Test
    @Ignore
    public void loadTestData() throws Exception {
        logger.info("Loading dataset");

        DataImporter dataImporter = new DataImporter(entityManager());
        dataImporter.importData();

        //IDataSet dataSet = new FlatXmlDataSetBuilder().build(getClass().getResource("/data.xml"));

        IDatabaseConnection connection = new DatabaseConnection(newConnection());
        connection.getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY,
            new MySqlDataTypeFactory()); // Set factorytype in dbconfig to remove warning

        //DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet);
        connection.close();
    }
}
