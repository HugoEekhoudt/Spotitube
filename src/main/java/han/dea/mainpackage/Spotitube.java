package han.dea.mainpackage;

import han.dea.mainpackage.dao.ConnectionDAO;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/")
public class Spotitube
{
    public static final String LINK_TO_WEBSITE = "http://ci.icaprojecten.nl/spotitube/";
    @Inject
    ConnectionDAO connectionDAO;

    @GET
    public String main()
    {
        connectionDAO.startConnection();
        return LINK_TO_WEBSITE;
    }

}
