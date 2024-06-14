package ua.edu.ukma;

@StationElement("plane")
@HasString
public class Plane extends Vehicle{
    String flight;

    public String getFlight() {
        return flight;
    }

    public void setFlight(String flight) {
        this.flight = flight;
    }
}
