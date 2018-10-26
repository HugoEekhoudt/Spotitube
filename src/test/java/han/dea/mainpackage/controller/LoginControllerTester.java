package han.dea.mainpackage.controller;

import han.dea.mainpackage.dto.login.LoginRequestDTO;
import han.dea.mainpackage.dto.login.LoginResponseDTO;
import han.dea.mainpackage.services.LoginService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;

public class LoginControllerTester
{
    private LoginController loginController;
    private LoginService loginService;
    private LoginRequestDTO loginRequestDTO;
    private LoginResponseDTO loginResponseDTO;

    @BeforeEach
    public void setUp()
    {
        loginController = new LoginController();
        loginService = Mockito.mock(LoginService.class);
        loginController.setLoginService(loginService);

        loginRequestDTO = new LoginRequestDTO();
        loginRequestDTO.setUser("user");
        loginRequestDTO.setPassword("password");

        loginResponseDTO = new LoginResponseDTO();
        loginResponseDTO.setUser("userR");
        loginResponseDTO.setToken("token");
    }

    @Test
    public void testLoginGood()
    {
        Mockito.when(loginService.getLoginResponseDTO(loginRequestDTO)).thenReturn(loginResponseDTO);

        Response response = loginController.login(loginRequestDTO);

        Assertions.assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

    }
}
