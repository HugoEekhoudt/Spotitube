package han.dea.mainpackage.service;

import han.dea.mainpackage.dao.PlaylistDAO;
import han.dea.mainpackage.dto.playlist.PlaylistRequestDTO;
import han.dea.mainpackage.dto.playlist.PlaylistResponseDTO;
import han.dea.mainpackage.services.PlaylistService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class PlaylistServiceTest
{
    private PlaylistService playlistService;
    private PlaylistRequestDTO playlistRequestDTO;
    private List<PlaylistRequestDTO> playlistRequestDTOS;
    private PlaylistDAO playlistDAO;
    private String token;

    @BeforeEach
    public void setUp()
    {
        playlistService = new PlaylistService();
        token = "1234-1234-123";
        playlistDAO = Mockito.mock(PlaylistDAO.class);
        playlistService.setPlaylistDAO(playlistDAO);


        playlistRequestDTO = new PlaylistRequestDTO();
        playlistRequestDTO.setId(1);
        playlistRequestDTO.setName("Death metal");

        playlistRequestDTOS = new ArrayList<>();
    }

    @Test
    public void testGetPlaylistsPlaylistDAO_GetPlaylists()
    {
        Mockito.when(playlistDAO.getPlaylists(token)).thenReturn(playlistRequestDTOS);

        PlaylistResponseDTO response = playlistService.getPlaylists(token);

        Assertions.assertEquals(0, response.getPlaylists().size());

    }
}
