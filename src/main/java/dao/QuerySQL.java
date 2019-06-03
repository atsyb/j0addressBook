package dao;


public class QuerySQL {
    protected final String[] queries = new String[QueryName.MAX_INDEX];

    QuerySQL() {
        queries[QueryName.SELECT_TEST_1] = "SELECT 1 as n";
        queries[QueryName.CREATE_SCHEMA] = "CREATE SCHEMA IF NOT EXISTS ? DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci";
        queries[QueryName.DROP_SCHEMA] = "DROP SCHEMA IF EXISTS ?";
        queries[QueryName.CREATE_TABLE_PERSON] = "CREATE TABLE IF NOT EXISTS person (id INT AUTO_INCREMENT, name VARCHAR(45), surname VARCHAR(45), phonenumber VARCHAR(45), age INT NULL, height DECIMAL(5,2), married TINYINT, createdat DATE NOT NULL DEFAULT (curdate()), PRIMARY KEY (id))";
        queries[QueryName.INSERT_PERSON] = "INSERT INTO person (id, name, surname, phonenumber, age, height, married, createdat) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        queries[QueryName.SELECT_PERSON_BY_ID] = "SELECT id, name, surname, phonenumber, age, height, married, createdat FROM person WHERE id = ?";
        queries[QueryName.SELECT_PERSON_BY_NAME] = "SELECT id, name, surname, phonenumber, age, height, married, createdat FROM person WHERE name = ?";
        queries[QueryName.UPDATE_PERSON_BY_ID] = "UPDATE person SET name=?, surname=?, phonenumber=?, age=?, height=?, married=?, createdat=?  WHERE id = ?";
        queries[QueryName.DELITE_PERSON_BY_ID] = "DELETE FROM person WHERE id = ?";

    }

    public String getQuery(final int queryId) {
        return queries[queryId];
    }
}
