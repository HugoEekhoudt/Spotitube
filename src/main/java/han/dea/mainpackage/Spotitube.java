package han.dea.mainpackage;

import han.dea.mainpackage.dao.ConnectionDAO;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/")
public class Spotitube
{
    @Inject
    ConnectionDAO connectionDAO;

    @GET
    public String main()
    {
        connectionDAO.startConnection();
        return "http://ci.icaprojecten.nl/spotitube/";
    }

}
