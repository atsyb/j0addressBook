package dao;

import java.sql.*;

import static dao.QueryName.*;


public class MySQLdb {
    private static final String mySQL_url = "jdbc:mysql://localhost:3306/";
    private static final String mySQL_db = "db_address_book";
    private static final String mySQL_param = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String mySQL_user = "root";
    private static final String mySQL_pass = "root";
    private static final String mySQL_conn = mySQL_url + mySQL_db + mySQL_param;

    private static QuerySQL query = new QuerySQL();

    public static void initDB() {
        try {
            Connection conn = DriverManager.getConnection(mySQL_url + mySQL_param, mySQL_user, mySQL_pass);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query.getQuery(SELECT_TEST_1));
            int connectionTest = rs.next() ? rs.getInt(1) : 0;
            if (connectionTest == 1) {
                //System.out.println("CREATE SCHEMA MySQL");
                //PreparedStatement ps = conn.prepareStatement(query.getQuery(CREATE_SCHEMA));
                //ps.setString(1, mySQL_db);
                stmt.execute("CREATE SCHEMA IF NOT EXISTS " + mySQL_db + " DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci");
            }
            conn.close();
            conn = getConnectionMyDB();
            stmt = conn.createStatement();
            stmt.execute(query.getQuery(CREATE_TABLE_PERSON));
            conn.close();
        } catch (SQLException e) {
            System.out.println("ERROR_SQL!");
            e.printStackTrace();
        }
    }


    public static Connection getConnectionMyDB() throws SQLException {
        return DriverManager.getConnection(mySQL_conn, mySQL_user, mySQL_pass);
    }


}
