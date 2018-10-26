package han.dea.mainpackage.dao;

import han.dea.mainpackage.dto.login.LoginRequestDTO;
import han.dea.mainpackage.dto.login.LoginResponseDTO;

import javax.inject.Inject;
import java.util.logging.Level;
import java.util.logging.Logger;


public class LoginDAO
{
    @Inject
    ConnectionDAO connectionDAO;

    private static final Logger log = Logger.getLogger(LoginDAO.class.getName());

    public LoginResponseDTO getUser(LoginRequestDTO loginRequestDTO)
    {
        try
        {
            connectionDAO.startConnection();
            String query = "SELECT * FROM user WHERE userName = ? AND password = ?";
            connectionDAO.setPreparedStatement(connectionDAO.getConnection().prepareStatement(query));
            connectionDAO.getPreparedStatement().setString(1, loginRequestDTO.getUser());
            connectionDAO.getPreparedStatement().setString(2, loginRequestDTO.getPassword());

            connectionDAO.setResultSet(connectionDAO.getPreparedStatement().executeQuery());

            LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
            while (connectionDAO.getResultSet().next())
            {
                loginResponseDTO.setUser(connectionDAO.getResultSet().getString(2));
                loginResponseDTO.setToken(connectionDAO.getResultSet().getString(4));

                return loginResponseDTO;
            }
            return null;
        }
        catch (Exception e)
        {
            log.log(Level.SEVERE, "Kan user niet krijgen: " + e.getMessage());
        }
        finally
        {
            connectionDAO.closeConnections();
        }
            return null;
    }

    public void setConnectionDAO(ConnectionDAO connectionDAO)
    {
        this.connectionDAO = connectionDAO;
    }
}
