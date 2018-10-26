package han.dea.mainpackage.dao;

import han.dea.mainpackage.dto.track.TrackDTO;
import han.dea.mainpackage.dto.track.TracksDTO;

import javax.inject.Inject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TrackDAO
{
    @Inject
    ConnectionDAO connectionDAO;

    private static final Logger log = Logger.getLogger(TrackDAO.class.getName());

    public TracksDTO getTracksOfPlaylist(int id)
    {
        List<TrackDTO> tracks = new ArrayList<>();
        try
        {
            connectionDAO.startConnection();
            String query = "SELECT * FROM track t INNER JOIN trackinplaylist tp on t.id = tp.trackID WHERE tp.playlistID = ?";

            connectionDAO.setPreparedStatement(connectionDAO.getConnection().prepareStatement(query));
            connectionDAO.getPreparedStatement().setInt(1, id);

            connectionDAO.setResultSet(connectionDAO.getPreparedStatement().executeQuery());

            while (connectionDAO.getResultSet().next())
            {
                TrackDTO trackDTO = fillTrackDtoExceptOfflineAvailable();
                trackDTO.setOfflineAvailable(connectionDAO.getResultSet().getBoolean(11));
                tracks.add(trackDTO);
            }
            return new TracksDTO(tracks);
        }
        catch (Exception e)
        {
            log.log(Level.SEVERE, "Kan tracks van playlist niet krijgen: " + e.getMessage());
        }
        finally
        {
            connectionDAO.closeConnections();
        }
        return  new TracksDTO(tracks);
    }

    public TracksDTO getAllTracksExceptForCurrentPlaylist(int playlistID)
    {
        List<TrackDTO> tracks = new ArrayList<>();

        try
        {
            connectionDAO.startConnection();
            String query = "SELECT * FROM track t WHERE NOT EXISTS(SELECT * FROM trackinplaylist tp WHERE tp.trackID = t.id AND tp.playlistID = ?)";

            connectionDAO.setPreparedStatement(connectionDAO.getConnection().prepareStatement(query));
            connectionDAO.getPreparedStatement().setInt(1, playlistID);

            connectionDAO.setResultSet(connectionDAO.getPreparedStatement().executeQuery());

            while (connectionDAO.getResultSet().next())
            {
                TrackDTO trackDTO = fillTrackDtoExceptOfflineAvailable();
                tracks.add(trackDTO);
            }

            return new TracksDTO(tracks);
        }
        catch (Exception e)
        {
            log.log(Level.SEVERE, "Kan tracks behalve in playlist niet krijgen: " + e.getMessage());
        }
        finally
        {
            connectionDAO.closeConnections();
        }
        return  new TracksDTO(tracks);
    }

    private TrackDTO fillTrackDtoExceptOfflineAvailable() throws SQLException
    {
        TrackDTO trackDTO = new TrackDTO();
        trackDTO.setId(connectionDAO.getResultSet().getInt(1));
        trackDTO.setTitle(connectionDAO.getResultSet().getString(2));
        trackDTO.setPerformer(connectionDAO.getResultSet().getString(3));
        trackDTO.setDuration(connectionDAO.getResultSet().getInt(4));
        trackDTO.setAlbum(connectionDAO.getResultSet().getString(5));
        trackDTO.setPlaycount(connectionDAO.getResultSet().getInt(6));
        trackDTO.setPublicationDate(connectionDAO.getResultSet().getString(7));
        trackDTO.setDescription(connectionDAO.getResultSet().getString(8));

        return trackDTO;
    }
}
