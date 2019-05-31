package dao;


public class QuerySQL {
    protected final String[] queries = new String[QueryName.MAX_INDEX];

    QuerySQL() {
        queries[QueryName.SELECT_TEST_1] = "SELECT 1 as n";
        queries[QueryName.CREATE_SCHEMA] = "CREATE SCHEMA IF NOT EXISTS `?` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci";
        queries[QueryName.DROP_SCHEMA] = "DROP SCHEMA IF EXISTS ?";
    }

    public String getQuery(final int queryId) {
        return queries[queryId];
    }
}
