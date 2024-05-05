package Core;
import java.awt.Color;

public class FoodFactory {
    public static AbstractFood<?> createFood(Color color, Core.FoodType foodType, Arena arena, int size) {
        if (foodType == FoodType.C) {
            return new FoodCircle(color, foodType, arena, size);
        } else if (foodType == FoodType.S) {
            return new FoodSquare(color, foodType, arena, size);
        }
        return null;
    }
}
