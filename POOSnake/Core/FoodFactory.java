package Core;

import java.awt.Color;

import Core.AbstractFood.FoodType;

public class FoodFactory {
    public static AbstractFood createFood(Color color, Core.FoodType c, Arena arena, int size) {
        if (c==FoodType.C) {
            return new FoodCircle(color, c, arena, size);
        } else if (c==FoodType.S) {
            return new FoodSquare(color, c, arena, size);
        }
        return null;
    }
}