package ua.edu.ukma;

@StationElement("car")
@HasString
public class Car extends Vehicle {
    String model;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
