package Core;

import java.awt.Color;
import Geometry.Square;
import Geometry.Ponto;

public class FoodSquare extends AbstractFood {
    private int sideLength;

    public FoodSquare(Color color, Core.FoodType s, Arena arena, int sideLength) {
        super(color, s, arena);
        this.sideLength = sideLength;
        spawnFood();
    }

    @Override
    public void spawnFood() {
        // logic to ensure food does not spawn on obstacles or outside arena boundaries
    }
}