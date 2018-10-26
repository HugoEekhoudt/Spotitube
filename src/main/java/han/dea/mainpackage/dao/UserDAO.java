package han.dea.mainpackage.dao;

import javax.inject.Inject;
import java.util.logging.Level;
import java.util.logging.Logger;


public class UserDAO
{
    @Inject
    ConnectionDAO connectionDAO;

    private static final Logger log = Logger.getLogger(UserDAO.class.getName());

    public int getUserIdWithToken(String token)
    {
        int id = 0;
        try
        {
            connectionDAO.startConnection();
            String query = "SELECT userID FROM user WHERE token = ?";

            connectionDAO.setPreparedStatement(connectionDAO.getConnection().prepareStatement(query));
            connectionDAO.getPreparedStatement().setString(1, token);

            connectionDAO.setResultSet(connectionDAO.getPreparedStatement().executeQuery());

            while (connectionDAO.getResultSet().next())
            {
                id = connectionDAO.getResultSet().getInt(1);
            }

            return id;
        }
        catch (Exception e)
        {
            log.log(Level.SEVERE, "Kan user m.b.h token niet krijgen: " + e.getMessage());
        }
        finally
        {
            connectionDAO.closeConnections();
        }
        return id;
    }
}
