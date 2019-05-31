import dao.MySQLdb;
import service.impl.ComandLineService;

public class Main {
    public static void main(String[] args) {
       // MySQLdb mySQLdb = new  MySQLdb();
        MySQLdb.initDB();
        //ComandLineService.start();
    }
}
