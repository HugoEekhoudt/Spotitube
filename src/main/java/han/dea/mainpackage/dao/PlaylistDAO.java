package han.dea.mainpackage.dao;

import han.dea.mainpackage.dto.playlist.PlaylistRequestDTO;
import han.dea.mainpackage.dto.track.TrackDTO;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class PlaylistDAO
{
    public static final String SELECT_ALL_FROM_PLAYLIST = "SELECT * FROM playlist";
    public static final String UPDATE_PLAYLIST_NAME_WHERE_ID = "UPDATE playlist SET name = ?  WHERE id = ?";
    public static final String INSERT_INTO_PLAYLIST = "INSERT INTO playlist (name, owner) VALUES (?,?)";
    public static final String DELETE_FROM_PLAYLIST = "DELETE FROM playlist WHERE id = ?";
    public static final String SELECT_OWNER_FROM_PLAYLIST_ON_ID = "SELECT owner FROM playlist WHERE id = ?";
    public static final String INSERT_INTO_TRACKINPLAYLIST = "INSERT INTO trackinplaylist(trackID, playlistID, offlineavailable) VALUES (?,?,?)";
    public static final String DELETE_FROM_TRACKINPLAYLIST = "DELETE FROM trackinplaylist WHERE trackID = ? AND playlistID = ?";
    @Inject
    UserDAO userDAO;

    @Inject
    ConnectionDAO connectionDAO;

    private static final Logger log = Logger.getLogger(PlaylistDAO.class.getName());

    public List<PlaylistRequestDTO> getPlaylists(String token)
    {
        List<PlaylistRequestDTO> playlist = new ArrayList<>();

        try {
            connectionDAO.startConnection();
            String query = SELECT_ALL_FROM_PLAYLIST;

            connectionDAO.setPreparedStatement(connectionDAO.getConnection().prepareStatement(query));

            connectionDAO.setResultSet(connectionDAO.getPreparedStatement().executeQuery());

            boolean owner;
            while (connectionDAO.getResultSet().next())
            {
                owner = false;
                if(userDAO.getUserIdWithToken(token) == connectionDAO.getResultSet().getInt(3))
                {
                    owner = true;
                }

                PlaylistRequestDTO playlistRequestDTO = new PlaylistRequestDTO();
                playlistRequestDTO.setId(connectionDAO.getResultSet().getInt(1));
                playlistRequestDTO.setName(connectionDAO.getResultSet().getString(2));
                playlistRequestDTO.setOwner(owner);
                playlist.add(
                        playlistRequestDTO
                );
            }
            return playlist;
        }
        catch (Exception e)
        {
            log.log(Level.SEVERE, "Kan playlist niet krijgen: " + e.getMessage());
        }
        finally
        {
         connectionDAO.closeConnections();
        }

        return playlist;
    }

    public void updatePlaylistName(int id, String newName)
    {
        try
        {
            connectionDAO.startConnection();
            String query = UPDATE_PLAYLIST_NAME_WHERE_ID;

            connectionDAO.setPreparedStatement(connectionDAO.getConnection().prepareStatement(query));
            connectionDAO.getPreparedStatement().setString(1, newName);
            connectionDAO.getPreparedStatement().setInt(2, id);

            connectionDAO.getPreparedStatement().executeUpdate();
        }
        catch (Exception e)
        {
            log.log(Level.SEVERE, "Kan playlist niet updaten: " + e.getMessage());
        }
        finally
        {
            connectionDAO.closeConnections();
        }
    }

    public void insertPlaylist(int id, PlaylistRequestDTO playlistRequestDTO)
    {
        try
        {
            connectionDAO.startConnection();
            String query = INSERT_INTO_PLAYLIST;

            connectionDAO.setPreparedStatement(connectionDAO.getConnection().prepareStatement(query));
            connectionDAO.getPreparedStatement().setString(1, playlistRequestDTO.getName());
            connectionDAO.getPreparedStatement().setInt(2, id);

            connectionDAO.getPreparedStatement().executeUpdate();
        }
        catch (Exception e)
        {
            log.log(Level.SEVERE, "Kan playlist niet toevoegen: " + e.getMessage());
        }
        finally
        {
            connectionDAO.closeConnections();
        }
    }

    public void deletePlaylist(int id, String token)
    {
        try
        {
            connectionDAO.startConnection();
                if (getOwnerOfPlaylist(id) == userDAO.getUserIdWithToken(token))
                {

                    String query = DELETE_FROM_PLAYLIST;

                    connectionDAO.setPreparedStatement(connectionDAO.getConnection().prepareStatement(query));
                    connectionDAO.getPreparedStatement().setInt(1, id);

                    connectionDAO.getPreparedStatement().executeUpdate();
                    connectionDAO.getPreparedStatement().close();
                }
        }
        catch (Exception e)
        {
            log.log(Level.SEVERE, "Kan playlist niet verwijderen: " + e.getMessage());
        }
        finally
        {
            connectionDAO.closeConnections();
        }
    }

    public int getOwnerOfPlaylist(int id)
    {
        int ownerID = 0;
        try
        {
            connectionDAO.startConnection();
            String query = SELECT_OWNER_FROM_PLAYLIST_ON_ID;

            connectionDAO.setPreparedStatement(connectionDAO.getConnection().prepareStatement(query));
            connectionDAO.getPreparedStatement().setInt(1, id);

            connectionDAO.setResultSet(connectionDAO.getPreparedStatement().executeQuery());
            while (connectionDAO.getResultSet().next())
            {
                ownerID = connectionDAO.getResultSet().getInt(1);
            }

            return ownerID;
        }
        catch (Exception e)
        {
            log.log(Level.SEVERE, "Kan user van playlist niet krijgen: " + e.getMessage());
        }
        finally
        {
            connectionDAO.closeConnections();
        }
        return ownerID;
    }

    public int getLengthOfAllPlaylists()
    {
        int length = 0;
        try
        {
            connectionDAO.startConnection();
            String query = "SELECT SUM(track.duration) FROM track INNER JOIN trackinplaylist ON track.id = trackinplaylist.trackID";

            connectionDAO.setPreparedStatement(connectionDAO.getConnection().prepareStatement(query));

            connectionDAO.setResultSet(connectionDAO.getPreparedStatement().executeQuery());
            while (connectionDAO.getResultSet().next())
            {
                length = connectionDAO.getResultSet().getInt(1);
            }

            return length;
        }
        catch (Exception e)
        {
            log.log(Level.SEVERE, "Kan lengte van playlists niet krijgen: " + e.getMessage());
        }
        finally
        {
            connectionDAO.closeConnections();
        }
        return length;
    }

    public void addTrackIntoPlaylist(int playlistID, TrackDTO trackDTO)
    {
        try
        {
            connectionDAO.startConnection();
            String query = INSERT_INTO_TRACKINPLAYLIST;

            connectionDAO.setPreparedStatement(connectionDAO.getConnection().prepareStatement(query));
            connectionDAO.getPreparedStatement().setInt(1, trackDTO.getId());
            connectionDAO.getPreparedStatement().setInt(2, playlistID);
            connectionDAO.getPreparedStatement().setBoolean(3, trackDTO.isOfflineAvailable());

            connectionDAO.getPreparedStatement().executeUpdate();
        }
        catch (Exception e)
        {
            log.log(Level.SEVERE, "Kan track niet toevoegen aan playlist: " + e.getMessage());
        }
        finally
        {
            connectionDAO.closeConnections();
        }
    }

    public void removeTrack(int playlistID, int trackID)
    {
        try
        {
            connectionDAO.startConnection();
            String query = DELETE_FROM_TRACKINPLAYLIST;

            connectionDAO.setPreparedStatement(connectionDAO.getConnection().prepareStatement(query));
            connectionDAO.getPreparedStatement().setInt(1, trackID);
            connectionDAO.getPreparedStatement().setInt(2, playlistID);

            connectionDAO.getPreparedStatement().executeUpdate();
        }
        catch (Exception e)
        {
            log.log(Level.SEVERE, "Kan track niet verwijderen van playlist: " + e.getMessage());
        }
        finally
        {
            connectionDAO.closeConnections();
        }
    }

    public void setConnectionDAO(ConnectionDAO connectionDAO)
    {
        this.connectionDAO = connectionDAO;
    }

    public void setUserDAO(UserDAO userDAO)
    {
        this.userDAO = userDAO;
    }
}
