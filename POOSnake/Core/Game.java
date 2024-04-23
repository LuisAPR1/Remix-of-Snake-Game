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

    private Ponto Snakeposition;

    public Game(Ponto Snakeposition, int arenaDimensionsX, int arenaDimensionsY, Color foodColor,Food.FoodType foodType, int headDimensions, String rasterization, int score, List<Obstacle> obstacles,String interfaceMode) {
        
        this.arenaDimensions = new int[] { arenaDimensionsX, arenaDimensionsY };
        this.food = new Food(foodColor, foodType);
        this.headDimensions = headDimensions;
        this.rasterization = RasterizationType.valueOf(rasterization);
        this.score = score;
        this.obstacles = obstacles;
        this.interfaceMode = InterfaceMode.valueOf(interfaceMode);
        this.Snakeposition = Snakeposition;

        InitializeGame();
    }

    public void InitializeGame() {
        Arena a = new Arena(arenaDimensions[0], arenaDimensions[1]);
        Snake s = new Snake(Snakeposition, headDimensions);
        
        //colocar frutas e objetos 
    }

    public void Start()
    {
        // int direction = 0;

        // // Loop infinito para permitir que a cobra se mova continuamente
        // while (true) {

        //     Clean();

        //     // Atualiza e imprime a arena
        //     a.updateArena(s);
        //     a.printArena();

        //     // Move a cobra na direção especificada pelo usuário
        //     s.move(direction);
        
        //     try {
        //         Thread.sleep(1000);
        //     } catch (InterruptedException e) {
        //         e.printStackTrace();
        //     }

        // }
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
