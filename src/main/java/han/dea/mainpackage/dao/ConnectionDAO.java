package han.dea.mainpackage.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionDAO
{
    public static final String CHARSET_NAME = "UTF-8";
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private static final Logger log = Logger.getLogger(ConnectionDAO.class.getName());
    private Boolean isTesting = false;

    public void setTesting(Boolean testing) {
        isTesting = testing;
    }

    public void startConnection()
    {
        if(isTesting)
        {
            startMySqlConnectionH2();
        }
        else
        {
            startMySqlConnection();
        }
    }

    private void startMySqlConnection()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Spotitube?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root", "");
        }
        catch (Exception e)
        {
            log.log(Level.SEVERE, "Kan geen connectie krijgen: " + e.getMessage());
        }
    }

    private void startMySqlConnectionH2()
    {
        try
        {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:mem:spotitube");
        }
        catch (Exception e)
        {
            log.log(Level.SEVERE, "Kan geen connectie krijgen: " + e.getMessage());
        }
    }

    public void closeConnections()
    {
        try {
            if(resultSet != null)
            {
                resultSet.close();
            }
            if(preparedStatement != null)
            {
                preparedStatement.close();
            }
            if(connection != null)
            {
                connection.close();
            }
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Kan niet alle connecties sluiten: " + e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public PreparedStatement getPreparedStatement() {
        return preparedStatement;
    }

    public void setPreparedStatement(PreparedStatement preparedStatement) {
        this.preparedStatement = preparedStatement;
    }

    public ResultSet getResultSet() {
        return resultSet;
    }

    public void setResultSet(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    public void runQuery(String query)
    {
        try
        {
            this.setPreparedStatement(this.getConnection().prepareStatement(query));
            this.getPreparedStatement().execute();
        } catch (SQLException e)
        {
            log.log(Level.SEVERE, "Kan query niet uitvoeren: " + e.getMessage());
        }
    }

    public String getInitialDatabaseStructureSql()
    {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("structure.sql");
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, CHARSET_NAME))) {
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
        } catch (IOException e) {
            log.log(Level.SEVERE, "Kan h2 database niet krijgen: " + e.getMessage());
        }

        return stringBuilder.toString();
    }
}
