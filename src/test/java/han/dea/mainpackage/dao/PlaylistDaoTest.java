package han.dea.mainpackage.dao;

import han.dea.mainpackage.dto.playlist.PlaylistRequestDTO;
import han.dea.mainpackage.dto.track.TrackDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlaylistDaoTest
{
    public static final int USER_FROM_DATABASE = 2;
    public static final int ID_OF_NEW_PLAYLIST = 26;
    public static final int LENGTH_OF_ALL_PLAYLISTS = 3500;
    private ConnectionDAO connectionDAO;
    private PlaylistDAO playlistDAO;
    private PlaylistRequestDTO playlistRequestDTO;
    private TrackDTO trackDTO;
    private UserDAO userDAO;
    private String token;

    @BeforeEach
    public void setUp()
    {

        playlistDAO = new PlaylistDAO();
        userDAO = new UserDAO();
        connectionDAO = new ConnectionDAO();
        connectionDAO.setTesting(true);
        userDAO.setConncectionDAO(connectionDAO);
        playlistDAO.setConnectionDAO(connectionDAO);
        playlistDAO.setUserDAO(userDAO);
        connectionDAO.startConnection();
        connectionDAO.runQuery(connectionDAO.getInitialDatabaseStructureSql());
        trackDTO = new TrackDTO();
        trackDTO.setId(50);
        trackDTO.setOfflineAvailable(true);
        playlistRequestDTO = new PlaylistRequestDTO();
        playlistRequestDTO.setName("newPlaylist");
        playlistRequestDTO.setId(25);
        token = "1234-1234-123";
    }

    @Test
    public void testInsertPlaylist()
    {
        playlistDAO.insertPlaylist(USER_FROM_DATABASE,playlistRequestDTO);

        Assertions.assertEquals(USER_FROM_DATABASE,playlistDAO.getOwnerOfPlaylist(ID_OF_NEW_PLAYLIST));

        connectionDAO.closeConnections();
    }

    @Test
    public void testGetLengthOfAllPlaylists()
    {
        int length = playlistDAO.getLengthOfAllPlaylists();

        Assertions.assertEquals(LENGTH_OF_ALL_PLAYLISTS,length);

        connectionDAO.closeConnections();
    }
}
