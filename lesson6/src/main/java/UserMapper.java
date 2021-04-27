import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class UserMapper {

    private static final Map<Long, User> identityMap = new HashMap<>();

    private static User getInCache(Long id) {
        User user = identityMap.get(id);
        System.out.printf("User returned from cache: %s%n", user.toString());
        return user;
    }

    private static void addInCache(User user) {
        identityMap.put(user.getId(), user);
        System.out.printf("User added in cache: %s%n", user.toString());
    }

    private static void removeInCache(Long id) {
        if (!identityMap.containsKey(id)) return;
        User user = identityMap.remove(id);
        System.out.printf("User removed from cache: %s%n", user.toString());
    }

    public static void createTable() {
        try {
            try (Statement statement = H2connection.get()
                    .createStatement()) {
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS users_tbl (" +
                        "id_fld BIGINT AUTO_INCREMENT NOT NULL, " +
                        "name_fld VARCHAR(255), " +
                        "age_fld INT, " +
                        "PRIMARY KEY (id_fld))");
                System.out.println("Create table success");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void addUser(User user) {
        if (findById(user.getId()) != null) {
            updateUser(user);
            System.out.printf("User updated: [%s]%n", user);
            return;
        }
        createUser(user);
        System.out.printf("User created: [%s]%n", user);
    }

    private static void createUser(User user) {
        try {
            try (PreparedStatement statement = H2connection.get()
                    .prepareStatement("INSERT INTO users_tbl(name_fld,age_fld) VALUES (?,?)")) {
                statement.setString(1, user.getName());
                statement.setInt(2, user.getAge());
                statement.executeUpdate();
                addInCache(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void updateUser(User user) {
        try {
            try (PreparedStatement statement = H2connection.get()
                    .prepareStatement("UPDATE users_tbl SET name_fld = ?, age_fld = ? WHERE id_fld = ?")) {
                statement.setString(1, user.getName());
                statement.setInt(2, user.getAge());
                statement.setLong(3, user.getId());
                statement.executeUpdate();
                if (identityMap.containsKey(user.getId())&& !identityMap.containsValue(user)) addInCache(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static User findById(Long id) {
        if (identityMap.containsKey(id)) {
            return getInCache(id);
        }
        try {
            try (PreparedStatement statement = H2connection.get()
                    .prepareStatement("SELECT * FROM users_tbl WHERE (id_fld=?)")) {
                statement.setLong(1, id);
                ResultSet result = statement.executeQuery();
                while (result.next()) {
                    User user = new User();
                    user.setId(result.getLong(1));
                    user.setName(result.getString(2));
                    user.setAge(result.getInt(3));
                    System.out.printf("User detected: [%s]%n", user.toString());
                    addInCache(user);
                    return user;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.printf("User by id: %d not found%n", id);
        return null;
    }

    public static void deleteById(User user) {
        deleteById(user.getId());
    }

    public static void deleteById(Long id) {
        try {
            try (PreparedStatement statement = H2connection.get()
                    .prepareStatement("DELETE FROM users_tbl WHERE id_fld = ?")) {
                statement.setLong(1, id);
                statement.executeUpdate();
                System.out.println("User deleted");
                removeInCache(id);
                return;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
