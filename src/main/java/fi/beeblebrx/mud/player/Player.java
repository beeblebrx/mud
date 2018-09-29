package fi.beeblebrx.mud.player;

public class Player {
    private long id;
    private long location;

    public Player(final long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public long getLocation() {
        return location;
    }

    public void setLocation(long location) {
        this.location = location;
    }
}
