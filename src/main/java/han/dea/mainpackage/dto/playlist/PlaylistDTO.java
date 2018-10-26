package han.dea.mainpackage.dto.playlist;

import han.dea.mainpackage.dto.track.TrackDTO;

import java.util.List;

public class PlaylistDTO
{
    private int id;
    private String name;
    private boolean owner;
    private List<TrackDTO> trackDTOS;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOwner() {
        return owner;
    }

    public void setOwner(boolean owner) {
        this.owner = owner;
    }

    public List<TrackDTO> getTrackDTOS() {
        return trackDTOS;
    }

    public void setTrackDTOS(List<TrackDTO> trackDTOS) {
        this.trackDTOS = trackDTOS;
    }
}
