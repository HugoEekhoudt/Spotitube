package han.dea.mainpackage.services;

import han.dea.mainpackage.dao.LoginDAO;
import han.dea.mainpackage.dto.login.LoginRequestDTO;
import han.dea.mainpackage.dto.login.LoginResponseDTO;

import javax.inject.Inject;

public class LoginService
{
    @Inject
    LoginDAO loginDAO;

    public LoginResponseDTO getLoginResponseDTO(LoginRequestDTO loginRequestDTO)
    {
        return loginDAO.getUser(loginRequestDTO);
    }

    public void setLoginDAO(LoginDAO loginDAO)
    {
        this.loginDAO = loginDAO;
    }
}
