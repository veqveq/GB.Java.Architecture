import java.sql.Connection;
import java.sql.DriverManager;

public class H2connection {
    private static final String DRIVER = "org.h2.Driver";
    private static final String URL = "jdbc:h2:file:./src/main/resources/test_db;MODE=MySQL";

    private static final String USERNAME = "sa";
    private static final String PASSWORD = "";

    @SuppressWarnings("InstantiationOfUtilityClass")
    private static final H2connection h2connection = new H2connection();

    private static Connection connection;

    private static void create() {
        try {
            Class.forName(DRIVER);
            System.out.println("Connecting...");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Connection get() {
        if (connection == null) {
            create();
        }
        return connection;
    }
}
