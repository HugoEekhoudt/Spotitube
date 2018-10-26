package han.dea.mainpackage.dto.track;

import java.util.List;

public class TracksDTO
{
    private List<TrackDTO> tracks;

    public TracksDTO(List<TrackDTO> tracks) {
        this.tracks = tracks;
    }

    public List<TrackDTO> getTracks() {
        return tracks;
    }

    public void setTracks(List<TrackDTO> tracks) {
        this.tracks = tracks;
    }
}
