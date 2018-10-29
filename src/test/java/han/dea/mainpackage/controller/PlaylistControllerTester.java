package han.dea.mainpackage.controller;

import han.dea.mainpackage.dto.playlist.PlaylistRequestDTO;
import han.dea.mainpackage.dto.playlist.PlaylistResponseDTO;
import han.dea.mainpackage.dto.track.TracksDTO;
import han.dea.mainpackage.services.PlaylistService;
import han.dea.mainpackage.services.TrackService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;

public class PlaylistControllerTester
{
    private PlayListController playListController;
    private PlaylistService playlistService;
    private TrackService trackService;
    private PlaylistRequestDTO playlistRequestDTO;
    private PlaylistResponseDTO playlistResponseDTO;
    private TracksDTO tracksDTO;
    private String token;

    @BeforeEach
    public void setUp()
    {
        playListController = new PlayListController();
        playlistService = Mockito.mock(PlaylistService.class);
        trackService = Mockito.mock(TrackService.class);
        playListController.setPlaylistService(playlistService);
        playListController.setTrackService(trackService);
        token = "1234-1234-123";

        playlistRequestDTO = new PlaylistRequestDTO();
        playlistRequestDTO.setId(1);
        playlistRequestDTO.setName("Death metal");

        playlistResponseDTO = new PlaylistResponseDTO(123445, null);

        tracksDTO = new TracksDTO(null);
    }

    @Test
    public void testGetPlayLists()
    {
        Mockito.when(playlistService.getPlaylists(token)).thenReturn(playlistResponseDTO);

        Response response = playListController.getPlayLists(token);

        Assertions.assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

    }

    @Test
    public void testGetTracks()
    {
        Mockito.when(trackService.getTracksForPlaylist(playlistRequestDTO.getId())).thenReturn(tracksDTO);

        Response response = playListController.getTracks(playlistRequestDTO.getId(), token);

        Assertions.assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

    }
}
