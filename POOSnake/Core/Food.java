package Core;

import java.awt.Color;
import java.util.Random;

import Geometry.Ponto;

public final class Food {
    private Ponto position;
    private Color color;
    private FoodType type;
    int[] arenaDimensions;

    public enum FoodType {
        C,
        S
    }

    public Food(Color color, FoodType type, int[] arenaDimensions) {
        this.color = color;
        this.type = type;
        this.arenaDimensions= arenaDimensions;
        this.position = setPosition(arenaDimensions);
        spawnFood(position, type);
    }

    public void spawnFood(Ponto position, FoodType type)
    {

    }

    public Ponto setPosition(int[] arenaDimensions) {
        Random random = new Random();
        int x = random.nextInt(arenaDimensions[0] + 1); 
        int y = random.nextInt(arenaDimensions[1] + 1); 
        return new Ponto(x, y);
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
