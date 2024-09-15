package menu;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private String name;
    private List<Dish> dishes;

    public Category(String name) {
        this.name = name;
        this.dishes = new ArrayList<>();
    }

    // Getter method for 'name'
    public String getName() {
        return name;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void addDish(Dish dish) {
        dishes.add(dish);
    }


}
