package Core;
import java.util.List;

public class Game {
    private int[] arenaDimensions;
    private int[] foodDimensions;
    private FoodType foodType;
    private int headDimensions;
    private RasterizationType rasterization;
    private int score;
    private List <Obstacle> obstacles;
    private InterfaceMode interfaceMode;

    // Constructor
    public Game(int arenaDimensionsX, int arenaDimensionsY, int foodDimensionsX, int foodDimensionsY, String foodType, int headDimensions, String rasterization, int score, List<Obstacle> obstacles, String interfaceMode) {
        this.arenaDimensions = new int[] { arenaDimensionsX, arenaDimensionsY };
        this.foodDimensions = new int[] { foodDimensionsX, foodDimensionsY };
        this.foodType = FoodType.valueOf(foodType); // Convertendo a String para o enum FoodType
        this.headDimensions = headDimensions;
        this.rasterization = RasterizationType.valueOf(rasterization); // Convertendo a String para o enum RasterizationType
        this.score = score;
        this.obstacles = obstacles;
        this.interfaceMode = InterfaceMode.valueOf(interfaceMode); // Convertendo a String para o enum InterfaceMode
    }

    // Getter and setters here

    // Enum for Food Type
    public enum FoodType {
        C, // Circles
        S // Squares
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
