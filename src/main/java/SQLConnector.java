import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLConnector {


        private static Connection connection;
        private static Statement statement;
        // "Values" is a ENUM. Placed to .gitignore
        private static final String DB_JDBC = SecretsService.getProperty("DB_BILLING");
        private static final String USER = SecretsService.getProperty("DB_USER");
        private static final String PASS = SecretsService.getProperty("DB_PASS");;

        static void connect() {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(DB_JDBC, USER, PASS);
                statement = connection.createStatement();
            } catch (ClassNotFoundException | SQLException e) {
                throw new RuntimeException(e);
            }
        }

        static void disconnect() {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        synchronized static List<String> getCheckedList() {

            connect();

            List<String> list = new ArrayList<>();

            String query = "SELECT title FROM contract " +
                    "WHERE id NOT IN " +
                    "(SELECT cid FROM contract_limit_period) AND closesumma < 0 AND mode = 1";
            try (ResultSet set = statement.executeQuery(query)) {

                while (set.next())
                    list.add(set.getString("title"));

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            disconnect();

            return list;
        }

    }


