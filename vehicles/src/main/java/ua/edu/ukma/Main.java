package ua.edu.ukma;

public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        Station station = new Station("car");
        Car car = new Car();
        car.setSpeedType("km/h");
        SpeedTypeValidator.validateSpeedType(car);
        car.setSpeedType("km/s");
        SpeedTypeValidator.validateSpeedType(car);
    }
}