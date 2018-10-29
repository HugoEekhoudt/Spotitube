package han.dea.mainpackage.dao;

import han.dea.mainpackage.dto.login.LoginRequestDTO;
import han.dea.mainpackage.dto.login.LoginResponseDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LoginDaoTest
{
    private ConnectionDAO connectionDAO;
    private LoginDAO loginDAO;
    private LoginRequestDTO loginRequestDTO;
    private LoginResponseDTO loginResponseDTO;

    @BeforeEach
    public void setUp()
    {

        loginDAO = new LoginDAO();
        connectionDAO = new ConnectionDAO();
        connectionDAO.setTesting(true);
        loginDAO.setConnectionDAO(connectionDAO);
        connectionDAO.startConnection();
        connectionDAO.runQuery(connectionDAO.getInitialDatabaseStructureSql());

        loginRequestDTO = new LoginRequestDTO();
        loginRequestDTO.setUser("hugotest");
        loginRequestDTO.setPassword("323");

        loginResponseDTO = new LoginResponseDTO();
        loginResponseDTO.setUser("userR");
        loginResponseDTO.setToken("1234-1234-123");
    }

    @Test
    public void testGetUserGood()
    {
        LoginResponseDTO response = loginDAO.getUser(loginRequestDTO);

        Assertions.assertEquals(loginResponseDTO.getToken(),response.getToken());

        connectionDAO.closeConnections();
    }
}
