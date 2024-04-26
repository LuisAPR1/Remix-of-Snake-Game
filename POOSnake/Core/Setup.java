package Core;

import Geometry.Ponto;
import java.awt.Color;
import java.util.List;

import Core.AbstractFood.FoodType;

public class Setup {
    private int[] arenaDimensions;
    private FoodType food;
    private int headDimensions;
    private RasterizationType rasterization;
    private int score;
    private InterfaceMode interfaceMode;

    private Ponto Snakeposition;

    public Setup(Ponto Snakeposition, int arenaDimensionsX, int arenaDimensionsY, Color foodColor,FoodType foodType, int headDimensions, String rasterization, int score, String interfaceMode) {
        
        this.arenaDimensions = new int[] { arenaDimensionsX, arenaDimensionsY };
        this.food = new Food(foodColor, foodType);
        this.headDimensions = headDimensions;
        this.rasterization = RasterizationType.valueOf(rasterization);
        this.score = score;
        this.interfaceMode = InterfaceMode.valueOf(interfaceMode);
        this.Snakeposition = Snakeposition;

        InitializeGame();
    }

    public void InitializeGame() {
        Arena a = new Arena(arenaDimensions[0], arenaDimensions[1]);

         
    }

    public void Start()
    {

    }

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
