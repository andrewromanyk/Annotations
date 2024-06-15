package ua.edu.ukma;

@StationElement("train")
public class Train extends Vehicle{
    String route;

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }
}
