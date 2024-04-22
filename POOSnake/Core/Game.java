package Core;

import Geometry.Ponto;
import java.awt.Color;
import java.util.List;

public class Game {
    private int[] arenaDimensions;
    @SuppressWarnings("unused")
    private Food food;
    private int headDimensions;
    @SuppressWarnings("unused")
    private RasterizationType rasterization;
    @SuppressWarnings("unused")
    private int score;
    @SuppressWarnings("unused")
    private List<Obstacle> obstacles;
    @SuppressWarnings("unused")
    private InterfaceMode interfaceMode;

    // Constructor
    public Game(int arenaDimensionsX, int arenaDimensionsY, Ponto foodPosition, Color foodColor, Food.FoodType foodType, int headDimensions, String rasterization, int score, List<Obstacle> obstacles, String interfaceMode) {
        this.arenaDimensions = new int[] { arenaDimensionsX, arenaDimensionsY };
        this.food = new Food(foodPosition, foodColor, foodType);
        this.headDimensions = headDimensions;
        this.rasterization = RasterizationType.valueOf(rasterization); // Convertendo a String para o enum RasterizationType
        this.score = score;
        this.obstacles = obstacles;
        this.interfaceMode = InterfaceMode.valueOf(interfaceMode); // Convertendo a String para o enum InterfaceMode
        }

    public void Start() {
       Snake s = new Snake(headDimensions);
       Arena a = new Arena(arenaDimensions[0], arenaDimensions[1]);
       a.updateArena(s);
       a.printArena();
       
    }
    
    // Getter and setters here

    // Enum for Rasterization Type
    public enum RasterizationType {
        O, // Outlined
        F // Filled
    }

    // Enum for Interface Mode
    public enum InterfaceMode {
        G, // Graphic
        T // Textual
    }
}
