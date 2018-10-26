package han.dea.mainpackage.controller;

import han.dea.mainpackage.services.TrackService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/tracks")
public class TrackController
{
    @Inject
    TrackService trackService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracks(@QueryParam("forPlaylist") int forPlaylistID, @QueryParam("token") String token)
    {
        return Response.ok(trackService.getTracksExceptForCurrentPlaylist(forPlaylistID)).build();
    }
}
