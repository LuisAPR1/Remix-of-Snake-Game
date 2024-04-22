package Core;

import java.awt.Color;

import Geometry.Ponto;

public final class Food {
    private Ponto position;
    private Color color;
    private FoodType type;

    public enum FoodType {
        C,
        S,
        CS
    }

    public Food(Ponto position, Color color, FoodType type) {
        this.position = position;
        this.color = color;
        this.type = type;
    }

    public Ponto getPosition() {
        return position;
    }

    public void setPosition(Ponto position) {
        this.position = position;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public FoodType getType() {
        return type;
    }

    public void setType(FoodType type) {
        this.type = type;
    }
}
