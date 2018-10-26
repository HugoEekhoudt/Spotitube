package han.dea.mainpackage.service;

import han.dea.mainpackage.dao.LoginDAO;
import han.dea.mainpackage.dto.login.LoginRequestDTO;
import han.dea.mainpackage.dto.login.LoginResponseDTO;
import han.dea.mainpackage.services.LoginService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class LoginServiceTester
{
    private LoginService loginService;
    private LoginDAO loginDAO;
    private LoginRequestDTO loginRequestDTO;
    private LoginResponseDTO loginResponseDTO;

    @BeforeEach
    public void setUp()
    {
        loginService = new LoginService();
        loginDAO = Mockito.mock(LoginDAO.class);
        loginService.setLoginDAO(loginDAO);

        loginRequestDTO = new LoginRequestDTO();
        loginRequestDTO.setUser("user");
        loginRequestDTO.setPassword("password");

        loginResponseDTO = new LoginResponseDTO();
        loginResponseDTO.setUser("userR");
        loginResponseDTO.setToken("token");
    }

    @Test
    public void testGetLoginResponseDTOGood()
    {
        Mockito.when(loginService.getLoginResponseDTO(loginRequestDTO)).thenReturn(loginResponseDTO);

        LoginResponseDTO response = loginService.getLoginResponseDTO(loginRequestDTO);

        Assertions.assertEquals(loginResponseDTO,response);

    }
}
