package han.dea.mainpackage.controller;

import han.dea.mainpackage.dto.login.LoginRequestDTO;
import han.dea.mainpackage.services.LoginService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
public class LoginController
{
    @Inject
    LoginService loginService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(LoginRequestDTO loginRequestDTO)
    {
            return Response.ok(loginService.getLoginResponseDTO(loginRequestDTO)).build();
    }

    public void setLoginService(LoginService loginService)
    {
        this.loginService = loginService;
    }

}
