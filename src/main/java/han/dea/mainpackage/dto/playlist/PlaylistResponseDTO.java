package han.dea.mainpackage.dto.playlist;

import java.util.List;

public class PlaylistResponseDTO
{
    private int length;
    private List<PlaylistDTO> playlists;

    public PlaylistResponseDTO(int length, List<PlaylistDTO> playlists)
    {
        this.length = length;
        this.playlists = playlists;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public List<PlaylistDTO> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<PlaylistDTO> playlists) {
        this.playlists = playlists;
    }

}
