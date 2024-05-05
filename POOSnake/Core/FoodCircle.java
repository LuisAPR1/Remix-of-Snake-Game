package Core;

import java.awt.Color;

import Geometry.Circle;
import Geometry.Ponto;

public class FoodCircle extends AbstractFood<Circle> {
    private int raio;
    Circle shape;

    public FoodCircle(Color color, Core.FoodType type, Arena arena, int raio) {
        super(color, type, arena);
        this.raio=raio;
        spawnFood();
    }

    @Override
    public void spawnFood() {
        Ponto position = generatePosition(); // Gere uma posição aleatória para o círculo
        shape = new Circle(position, raio);
    }

    @Override
    public Circle getShape() {
        return shape;
    }

}