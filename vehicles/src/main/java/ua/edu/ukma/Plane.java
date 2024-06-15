package ua.edu.ukma;

@StationElement("plane")
public class Plane extends Vehicle{
    String flight;
    String options;

    public String getFlight() {
        return flight;
    }

    public void setFlight(String flight) {
        this.flight = flight;
    }
}
