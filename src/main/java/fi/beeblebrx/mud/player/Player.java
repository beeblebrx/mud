package fi.beeblebrx.mud.player;

public class Player {
    private int id;
    private int location;

    public Player(final int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }
}
