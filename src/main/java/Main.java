import dao.MySQLdb;
import dao.QuerySQL;
import service.impl.ComandLineService;

public class Main {
    public static void main(String[] args) {
        new QuerySQL();
        if (MySQLdb.initDB()) {
            ComandLineService.start();
        }
    }
}
