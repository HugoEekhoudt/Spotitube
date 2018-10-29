package han.dea.mainpackage.controller;

import han.dea.mainpackage.dto.track.TrackDTO;
import han.dea.mainpackage.dto.track.TracksDTO;
import han.dea.mainpackage.services.TrackService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

public class TrackControllerTest
{
    private TrackController trackController;
    private TrackService trackService;
    private int playlistID;
    private TracksDTO tracksDTO;

    @BeforeEach
    public void setUp()
    {
        trackController = new TrackController();
        trackService = Mockito.mock(TrackService.class);
        trackController.setTrackService(trackService);

        playlistID = 1;
        List<TrackDTO> list = new ArrayList<>();
        tracksDTO = new TracksDTO(list);
    }

    @Test
    public void testGetTracksForPlaylistGood()
    {
        Mockito.when(trackService.getTracksForPlaylist(playlistID)).thenReturn(tracksDTO);

        Response response = trackController.getTracks(playlistID, "token");

        Assertions.assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

    }
}
