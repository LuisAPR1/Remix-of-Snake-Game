package Core;

import Geometry.Ponto;
import java.awt.Color;
import java.util.List;
import java.util.Scanner;

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

    // Constructor
    public Game(Ponto Snakeposition, int arenaDimensionsX, int arenaDimensionsY, Ponto foodPosition, Color foodColor,
            Food.FoodType foodType, int headDimensions, String rasterization, int score, List<Obstacle> obstacles,
            String interfaceMode) {
        this.arenaDimensions = new int[] { arenaDimensionsX, arenaDimensionsY };
        this.food = new Food(foodPosition, foodColor, foodType);
        this.headDimensions = headDimensions;
        this.rasterization = RasterizationType.valueOf(rasterization); // Convertendo a String para o enum
                                                                       // RasterizationType
        this.score = score;
        this.obstacles = obstacles;
        this.interfaceMode = InterfaceMode.valueOf(interfaceMode); // Convertendo a String para o enum InterfaceMode
        this.Snakeposition = Snakeposition;
    }

    public void Start() {
        Arena a = new Arena(arenaDimensions[0], arenaDimensions[1]);
        Snake s = new Snake(Snakeposition, headDimensions);

        // Loop infinito para permitir que a cobra se mova continuamente
        while (true) {
            // Limpa a tela
            System.out.print("\033[H\033[2J");
            System.out.flush();

            // Solicita a direção ao usuário

            // Atualiza e imprime a arena
            a.updateArena(s);
            a.printArena();

            // Move a cobra na direção especificada pelo usuário
            s.move(0);
            try {
                Thread.sleep(1000); // 1000 milissegundos = 1 segundo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
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
