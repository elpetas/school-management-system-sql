import java.sql.*;


/**
 * The Database class contains helper functions to connect and test your
 * connection with the database.
 */
public class Database {

    /**
     * Creates a connection to the database using environment variables. The following environment
     * variables must be set prior to running your program:
     *
     * <ul>
     *    <li>CS310_PORT - The database management system port
     *    <li>CS310_HOST - The database management system host
     *    <li>CS310_USERNAME - The database management system username
     *    <li>CS310_PASSWORD - The database management system user's password
     *    <li>CS310_DATABASE - The name of the database in the database management system
     * </ul>
     *
     * For more information on environment variables see:
     * <a href="https://docs.oracle.com/javase/tutorial/essential/environment/env.html">
     *  https://docs.oracle.com/javase/tutorial/essential/environment/env.html
     * </a>
     * @return java.sql.Connection
     * @throws SQLException
     */
    public static Connection getDatabaseConnection() throws SQLException {
        int databasePort = Integer.parseInt(System.getenv("CS310_PORT"));
        String databaseHost = System.getenv("CS310_HOST");
        String databaseUsername = System.getenv("CS310_USERNAME");
        String databasePassword = System.getenv("CS310_PASSWORD");
        String databaseName = System.getenv("CS310_DATABASE");
        String databaseURL = String.format(
                "jdbc:mysql://%s:%s/%s?verifyServerCertificate=false&useSSL=false&serverTimezone=UTC",
                databaseHost,
                databasePort,
                databaseName);

        try {

            return DriverManager.getConnection(databaseURL, databaseUsername, databasePassword);
        } catch (SQLException sqlException) {
            System.out.printf("SQLException was thrown while trying to connection to database: %s%n", databaseURL);
            System.out.println(sqlException.getMessage());
            throw sqlException;
        }

    }

    /**
     * Tests the connection to your database. If your connection fails, please check:
     * <ul>
     *    <li>Database is running
     *    <li>Environment variables are set and being read in properly
     *    <li>Database Driver is in the CLASSPATH.
     *    <li>SSH port forwarding is properly setup
     * </ul>
     */
    public static void testConnection() {
        System.out.println("Attempting to connect to MySQL database using:");
        System.out.printf("CS310_HOST: %s%n", System.getenv("CS310_HOST"));
        System.out.printf("CS310_PORT: %s%n", System.getenv("CS310_PORT"));
        System.out.printf("CS310_USERNAME: %s%n", System.getenv("CS310_USERNAME"));
        System.out.printf("CS310_PASSWORD: %s%n", System.getenv("CS310_PASSWORD"));
        System.out.printf("CS310_DATABASE: %s%n", System.getenv("CS310_DATABASE"));

        Connection connection = null;
        ResultSet resultSet = null;

        try{
            connection = getDatabaseConnection();
            Statement sqlStatement = connection.createStatement();
            String sql = "SELECT VERSION();";
            resultSet = sqlStatement.executeQuery(sql);
            resultSet.next();
            System.out.printf("Connected SUCCESS! Database Version: %s%n", resultSet.getString(1));
        } catch (SQLException sql){
            System.out.println("Failed to connect to database! Please make sure your Environment variables are set!");
        } finally {
            try { resultSet.close(); } catch (Exception e) { /* ignored */ }
            try { connection.close(); } catch (Exception e) { /* ignored */ }
        }
    }
}
