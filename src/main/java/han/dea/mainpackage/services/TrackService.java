package han.dea.mainpackage.services;

import han.dea.mainpackage.dao.TrackDAO;
import han.dea.mainpackage.dto.track.TracksDTO;

import javax.inject.Inject;

public class TrackService
{
    @Inject
    TrackDAO trackDAO;

    public TracksDTO getTracksForPlaylist(int playlistID)
    {
        return trackDAO.getTracksOfPlaylist(playlistID);
    }

    public TracksDTO getTracksExceptForCurrentPlaylist(int playlistID)
    {
        return trackDAO.getAllTracksExceptForCurrentPlaylist(playlistID);
    }

    public void setTrackDAO(TrackDAO trackDAO)
    {
        this.trackDAO = trackDAO;
    }
}
