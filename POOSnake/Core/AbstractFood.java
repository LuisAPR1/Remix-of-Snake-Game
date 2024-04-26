package Core;

import java.awt.Color;
import Geometry.Ponto;

public abstract class AbstractFood {
    protected Ponto position;
    protected Color color;
    protected FoodType type;
    protected Arena arena;

    public enum FoodType {
        C,
        S
    }

    public AbstractFood(Color color, FoodType type, Arena arena) {
        this.color = color;
        this.type = type;
        this.arena = arena;
        this.position = generatePosition();
    }

    public abstract void spawnFood();

    protected Ponto generatePosition() {
        int[] arenaDimensions = arena.getArenaDimensions();
        int x = (int) (Math.random() * arenaDimensions[0]);
        int y = (int) (Math.random() * arenaDimensions[1]);
        return new Ponto(x, y);
    }

    // Getters and Setters
    public Ponto getPosition() {
        return position;
    }

    public Color getColor() {
        return color;
    }

    public FoodType getType() {
        return type;
    }
}