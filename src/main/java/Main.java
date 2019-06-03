import dao.MySQLdb;
import service.impl.ComandLineService;

public class Main {
    public static void main(String[] args) {
        MySQLdb.initDB();
        ComandLineService.start();
    }
}
