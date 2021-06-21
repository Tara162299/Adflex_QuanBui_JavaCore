package week3.Project3.thread;

public class Ship {
    String shipName;
    int longitude;
    int latitude;
    String currentTime;

    public Ship(String shipName) {
        this.shipName = shipName;
    }

    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {

        this.longitude = longitude;
    }

    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {

        this.latitude = latitude;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {

        this.currentTime = currentTime;
    }
}
