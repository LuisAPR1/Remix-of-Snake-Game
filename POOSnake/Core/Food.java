package Core;

import java.awt.Color;
import java.util.Random;

import Geometry.Ponto;

public final class Food {
    private Ponto position;
    private Color color;
    private FoodType type;
    private int maxFoodSize;
    Arena arena;

    public enum FoodType {
        C,
        S
    }

    public Food(Color color, FoodType type, Arena a, int headSize) {
        this.color = color;
        this.type = type;
        this.arena = a;
        this.maxFoodSize=headSize;
        this.position = setPosition(a.getArenaDimensions());

        spawnFood(position, type);
    }

    public void spawnFood(Ponto position, FoodType type) {
        // invariancia nao pode spawnar fora da arena, dentro da cobra nem dentro do
        // obstaculo e mais pequeno do que a cabeça da cobra

        //TAMANHO DA FRUTA , (int) Math.random() % maxFoodSize

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
