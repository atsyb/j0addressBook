package dao;

import java.util.HashMap;

public class QuerySQL extends QueryName {
//    private static final String[] queries = new String[QueryName.MAX_INDEX];
    private static final HashMap<Integer, String> sqlQueries = new HashMap<>(QueryName.MAX_INDEX);
    public QuerySQL() {
        sqlQueries.put(SELECT_TEST_1, "SELECT 1 as n");
        sqlQueries.put(CREATE_SCHEMA, "CREATE SCHEMA IF NOT EXISTS ? DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci");
        sqlQueries.put(DROP_SCHEMA, "DROP SCHEMA IF EXISTS ?");
        sqlQueries.put(CREATE_TABLE_PERSON, "CREATE TABLE IF NOT EXISTS person (id INT AUTO_INCREMENT, name VARCHAR(45), surname VARCHAR(45), phonenumber VARCHAR(45), age INT NULL, height DECIMAL(5,2), married TINYINT, createdat DATE NOT NULL DEFAULT (curdate()), PRIMARY KEY (id))");
        sqlQueries.put(INSERT_PERSON, "INSERT INTO person (id, name, surname, phonenumber, age, height, married, createdat) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
        sqlQueries.put(SELECT_PERSON_BY_ID, "SELECT id, name, surname, phonenumber, age, height, married, createdat FROM person WHERE id = ?");
        sqlQueries.put(SELECT_PERSON_BY_NAME, "SELECT id, name, surname, phonenumber, age, height, married, createdat FROM person WHERE name = ?");
        sqlQueries.put(UPDATE_PERSON_BY_ID, "UPDATE person SET name=?, surname=?, phonenumber=?, age=?, height=?, married=?, createdat=?  WHERE id = ?");
        sqlQueries.put(DELETE_PERSON_BY_ID, "DELETE FROM person WHERE id = ?");

//        queries[SELECT_TEST_1] = "SELECT 1 as n";
//        queries[CREATE_SCHEMA] = "CREATE SCHEMA IF NOT EXISTS ? DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci";
//        queries[DROP_SCHEMA] = "DROP SCHEMA IF EXISTS ?";
//        queries[CREATE_TABLE_PERSON] = "CREATE TABLE IF NOT EXISTS person (id INT AUTO_INCREMENT, name VARCHAR(45), surname VARCHAR(45), phonenumber VARCHAR(45), age INT NULL, height DECIMAL(5,2), married TINYINT, createdat DATE NOT NULL DEFAULT (curdate()), PRIMARY KEY (id))";
//        queries[INSERT_PERSON] = "INSERT INTO person (id, name, surname, phonenumber, age, height, married, createdat) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
//        queries[SELECT_PERSON_BY_ID] = "SELECT id, name, surname, phonenumber, age, height, married, createdat FROM person WHERE id = ?";
//        queries[SELECT_PERSON_BY_NAME] = "SELECT id, name, surname, phonenumber, age, height, married, createdat FROM person WHERE name = ?";
//        queries[UPDATE_PERSON_BY_ID] = "UPDATE person SET name=?, surname=?, phonenumber=?, age=?, height=?, married=?, createdat=?  WHERE id = ?";
//        queries[DELETE_PERSON_BY_ID] = "DELETE FROM person WHERE id = ?";

    }

    public static String getQuery(final int queryId) {
        return sqlQueries.get(queryId);
//        return queries[queryId];
    }
}
