package Core;

import java.awt.Color;
import Geometry.Circle;

public class FoodCircle extends AbstractFood {
    private int size;

    public FoodCircle(Color color, Core.FoodType c, Arena arena, int size) {
        super(color, c, arena);
        this.size = size;
        spawnFood();
    }

    @Override
    public void spawnFood() {

    }
    
}