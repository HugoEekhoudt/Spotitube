package han.dea.mainpackage.dao;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionDAO
{
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
}
