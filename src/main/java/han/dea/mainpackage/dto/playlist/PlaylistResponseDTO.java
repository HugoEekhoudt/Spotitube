package han.dea.mainpackage.dto.playlist;

import java.util.List;

public class PlaylistResponseDTO
{
    private int length;
    private List<PlaylistRequestDTO> playlists;

    public PlaylistResponseDTO(int length, List<PlaylistRequestDTO> playlists)
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

    public List<PlaylistRequestDTO> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<PlaylistRequestDTO> playlists) {
        this.playlists = playlists;
    }

}
