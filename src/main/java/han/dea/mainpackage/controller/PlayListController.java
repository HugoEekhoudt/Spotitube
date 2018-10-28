package han.dea.mainpackage.controller;

import han.dea.mainpackage.dto.playlist.PlaylistRequestDTO;
import han.dea.mainpackage.dto.track.TrackDTO;
import han.dea.mainpackage.services.PlaylistService;
import han.dea.mainpackage.services.TrackService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/playlists")
public class PlayListController
{

    @Inject
    TrackService trackService;

    @Inject
    PlaylistService playlistService;

    @GET
    public Response getPlayLists(@QueryParam("token") String token)
    {
        return Response.ok(playlistService.getPlaylists(token)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}/tracks")
    public Response getTracks(@PathParam("id") int id, @QueryParam("token") String token)
    {
        return Response.ok(trackService.getTracksForPlaylist(id)).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response editPlaylist(PlaylistRequestDTO playlistRequestDTO, @PathParam("id") int id, @QueryParam("token") String token)
    {
        playlistService.updatePlaylist(id, playlistRequestDTO.getName());
        return Response.ok(playlistService).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}/tracks")
    public Response addTrackToPlaylist(@PathParam("id") int playlistID, @QueryParam("token") String token, TrackDTO trackDTO)
    {
        playlistService.addTrack(playlistID, trackDTO);
        return Response.ok(trackService.getTracksForPlaylist(playlistID)).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertPlaylist(@QueryParam("token") String token, PlaylistRequestDTO playlistRequestDTO)
    {
        playlistService.insertPlaylist(token, playlistRequestDTO);
        return Response.ok(playlistService.getPlaylists(token)).build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response deletePlaylist(@PathParam("id") int id, @QueryParam("token") String token)
    {
        playlistService.deletePlaylist(id, token);
        return Response.ok(playlistService.getPlaylists(token)).build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{playlistID}/tracks/{trackID}")
    public Response removeTrackFromPlaylist(@PathParam("playlistID") int playlistID,@PathParam("trackID") int trackID, @QueryParam("token") String token)
    {
        playlistService.removeTrackFromPlaylist(playlistID,trackID);
        return Response.ok(trackService.getTracksForPlaylist(playlistID)).build();
    }
}
