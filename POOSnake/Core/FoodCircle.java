package Core;

import java.awt.Color;
import Geometry.Circle;

public class FoodCircle extends AbstractFood {
    private int size;

    public FoodCircle(Color color, FoodType type, Arena arena, int size) {
        super(color, type, arena);
        this.size = size;
        spawnFood();
    }

    @Override
    public void spawnFood() {
        // logic to ensure food does not spawn on obstacles or outside arena boundaries
    }
}