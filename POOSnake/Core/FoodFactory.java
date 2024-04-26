package Core;

import java.awt.Color;

import Core.AbstractFood.FoodType;

public class FoodFactory {
    public static AbstractFood createFood(Color color, FoodType type, Arena arena, int size) {
        if (type==FoodType.C) {
            return new FoodCircle(color, type, arena, size);
        } else if (type==FoodType.S) {
            return new FoodSquare(color, type, arena, size);
        }
        return null;
    }
}