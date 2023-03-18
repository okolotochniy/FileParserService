import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PostgresWriter {
    private static final String DB_URL = "jdbc:postgresql://localhost:5051/mydatabase";
    private static final String DB_USER = "superuser";
    private static final String DB_PASSWORD = "superuser";

    public void write(String data) {

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // Подключение к базе данных
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // Подготовка SQL запроса с параметром в виде местозаполнителя "?"
            String sql = "INSERT INTO mytable (column1) VALUES (?)";
            statement = connection.prepareStatement(sql);

            // Установка значения параметра местозаполнителя
            statement.setString(1, data);

            // Выполнение запроса
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Строка успешно добавлена в базу данных!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
