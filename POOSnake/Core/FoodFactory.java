package Core;

import java.awt.Color;

import Core.AbstractFood.FoodType;
import Geometry.Ponto;

public class FoodFactory {
    public static AbstractFood createFood(String shape, Color color, FoodType type, Arena arena, int size) {
        if (shape.equalsIgnoreCase("circle")) {
            return new FoodCircle(color, type, arena, size);
        } else if (shape.equalsIgnoreCase("square")) {
            return new FoodSquare(color, type, arena, size);
        }
        return null;
    }
}