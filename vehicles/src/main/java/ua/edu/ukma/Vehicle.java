package ua.edu.ukma;

@StationFactory
public class Vehicle {
    int speed;

    public String getSpeedType() {
        return speedType;
    }

    public void setSpeedType(String speedType) {
        this.speedType = speedType;
    }

    @ValidSpeed
    String speedType;

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
