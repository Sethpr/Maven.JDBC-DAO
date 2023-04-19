import daos.Cars;
import models.CarDTO;

public class App {
    public static void main(String[] args) {
        Cars cars = new Cars();
        System.out.println(cars.findAll());
        //cars.create(new CarDTO(2, "volvo", "s60", 2000, "red", "idk"));
        //cars.delete(2);
        cars.update(new CarDTO(2, "volvo", "s60", 2000, "red", "idek"));
    }
}
