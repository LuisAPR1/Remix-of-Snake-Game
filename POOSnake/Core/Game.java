package Core;

import Geometry.Ponto;
import java.awt.Color;
import java.util.List;
import java.util.NoSuchElementException;
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
        
        // Inicialização do scanner
        try (Scanner scanner = new Scanner(System.in)) {
            // Loop para permitir que a cobra se mova quando solicitado
            while (true) {
                // Limpa a tela
                System.out.print("\033[H\033[2J");
                System.out.flush();
    
                // Atualiza e imprime a arena
                a.updateArena(s);
                a.printArena();
    
                // Solicita a direção ao usuário
                System.out.println("Use as teclas WASD para mover a cobra (W: cima, A: esquerda, S: baixo, D: direita).");
                
                // Aguarda até que o usuário pressione Enter para mover a cobra
                System.out.println("Pressione Enter para mover a cobra...");
                scanner.nextLine(); // Aguarda a entrada do usuário
    
                // Captura a entrada do usuário
                char input;
                try {
                    input = scanner.next().charAt(0); // Lê o primeiro caractere da entrada do usuário
                } catch (NoSuchElementException e) {
                    System.out.println("Nenhuma entrada encontrada. Encerrando o jogo.");
                    break; // Sai do loop
                }
                
                int direction = 0; // Direção padrão (direita)
                switch (Character.toUpperCase(input)) {
                    case 'W':
                        direction = 270; // Mover para cima
                        break;
                    case 'S':
                        direction = 90; // Mover para baixo
                        break;
                    case 'A':
                        direction = 180; // Mover para esquerda
                        break;
                    case 'D':
                        direction = 0; // Mover para direita
                        break;
                    default:
                        System.out.println("Tecla inválida. Use as teclas WASD para mover a cobra.");
                }
    
                // Move a cobra na direção especificada pelo usuário
                s.move(direction);
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
