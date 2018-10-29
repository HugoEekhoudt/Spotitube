package han.dea.mainpackage.services;

import han.dea.mainpackage.dao.PlaylistDAO;
import han.dea.mainpackage.dao.UserDAO;
import han.dea.mainpackage.dto.playlist.PlaylistRequestDTO;
import han.dea.mainpackage.dto.playlist.PlaylistResponseDTO;
import han.dea.mainpackage.dto.track.TrackDTO;

import javax.inject.Inject;

public class PlaylistService
{
    @Inject
    PlaylistDAO playlistDAO;

    @Inject
    UserDAO userDAO;

    public PlaylistResponseDTO getPlaylists(String token)
    {
        return new PlaylistResponseDTO(playlistDAO.getLengthOfAllPlaylists(), playlistDAO.getPlaylists(token));
    }

    public void updatePlaylist(int id,String newName)
    {
        playlistDAO.updatePlaylistName(id,newName);
    }

    public void insertPlaylist(String token, PlaylistRequestDTO playlistRequestDTO)
    {
        int id = userDAO.getUserIdWithToken(token);
        playlistDAO.insertPlaylist(id, playlistRequestDTO);
    }

    public void deletePlaylist(int id, String token)
    {
        playlistDAO.deletePlaylist(id, token);
    }

    public void addTrack(int playlistID, TrackDTO trackDTO)
    {
        playlistDAO.addTrackIntoPlaylist(playlistID,trackDTO);
    }

    public void removeTrackFromPlaylist(int playlistID, int trackID)
    {
        playlistDAO.removeTrack(playlistID, trackID);
    }

    public void setPlaylistDAO(PlaylistDAO playlistDAO)
    {
        this.playlistDAO = playlistDAO;
    }
}
