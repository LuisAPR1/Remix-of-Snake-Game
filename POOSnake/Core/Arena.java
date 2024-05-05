package Core;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;
import java.util.function.BooleanSupplier;

import Geometry.Poligono;
import Geometry.Ponto;
import Geometry.Square;

public class Arena {

    Cell[][] grid;
    Snake s;
    AbstractFood<?> fruit;
    ArrayList<Obstacle> obstacles;

    @SuppressWarnings("unused")
    private int[] arenaDimensions;
    @SuppressWarnings("unused")
    private FoodType food;
    @SuppressWarnings("unused")
    private int headDimensions;
    @SuppressWarnings("unused")
    private RasterizationType rasterization;
    @SuppressWarnings("unused")
    private int score;
    @SuppressWarnings("unused")
    private InterfaceMode interfaceMode;

    public Arena(int arenaDimensionsX, int arenaDimensionsY, int headDimensions, String rasterizationType,
            int foodDimensions, FoodType foodType, int numObstacles, Obstacle.ObstacleType obstacleType,
            char interfaceMode) {
        this.grid = new Cell[arenaDimensionsX][arenaDimensionsY];
        this.headDimensions = headDimensions;
        this.rasterization = RasterizationType.valueOf(rasterizationType);
        this.food = foodType;

        this.obstacles = new ArrayList<>();
        this.s = new Snake(this, headDimensions);
        this.fruit = FoodFactory.createFood(Color.YELLOW, foodType, this, foodDimensions/2);
        createObstacles(numObstacles, obstacleType, arenaDimensionsX, arenaDimensionsY, headDimensions);

        initializeArena();
        updateArena();
        toString();
    }

    private void createObstacles(int numObstacles, Obstacle.ObstacleType obstacleType, int arenaDimensionsX,
            int arenaDimensionsY, int headDimensions) {
        obstacles = new ArrayList<>();
        for (int i = 0; i < numObstacles; i++) {
            int posX = generateRandomWithinRange(0, arenaDimensionsX - headDimensions);
            int posY = generateRandomWithinRange(0, arenaDimensionsY - headDimensions);
            ArrayList<Ponto> pontos = new ArrayList<>();
            pontos.add(new Ponto(posX, posY));
            pontos.add(new Ponto(posX + headDimensions, posY));
            pontos.add(new Ponto(posX + headDimensions, posY + headDimensions));
            pontos.add(new Ponto(posX, posY + headDimensions));
            Poligono obstacleShape = new Poligono(pontos);
            Obstacle obstacle = new Obstacle(obstacleType, obstacleShape, null);
            obstacles.add(obstacle);
        }
    }

    private int generateRandomWithinRange(int min, int max) {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }

    public int[] getArenaDimensions() {
        int[] x = new int[2];
        x[0] = grid[0].length;
        x[1] = grid.length;
        return x;
    }

    public void initializeArena() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = Cell.EMPTY;
            }
        }
    }

    public void updateArena() {
        initializeArena(); // Limpa a arena

        // Desenha a cabeça da cobra
        for (Ponto p : s.getHead().getAllCoordinates()) {
            grid[p.getX()][p.getY()] = Cell.HEAD;
        }

        // Desenha a cauda da cobra
        LinkedList<Square> tail = s.getTailCoordinates();
        for (Square square : tail) {
            for (Ponto p : square.getAllCoordinates()) {
                grid[p.getX()][p.getY()] = Cell.TAIL;
            }
        }

        // Desenha os obstáculos
        for (Obstacle obstacle : obstacles) {
            for (Ponto p : obstacle.getObstacle().getAllCoordinates()) {
                grid[p.getX()][p.getY()] = Cell.OBSTACLE;
            }
        }

        // Desenha a fruta
        if (fruit != null) {
            for (Ponto p : fruit.getShape().getAllCoordinates()) {
                grid[p.getX()][p.getY()] = Cell.FOOD;
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                sb.append(grid[j][i].getSymbol()).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public BooleanSupplier snakeIntersectObstacle(Snake snake) {
        throw new UnsupportedOperationException("Unimplemented method 'snakeIntersectObstacle'");
    }

    public BooleanSupplier foodContainedOnSnakeHead(Snake snake) {
        throw new UnsupportedOperationException("Unimplemented method 'foodContainedOnSnakeHead'");
    }

    public BooleanSupplier snakeLeftTheBoard(Snake snake) {
        throw new UnsupportedOperationException("Unimplemented method 'snakeLeftTheBoard'");
    }

    public void Start( Scanner scanner) {

        // Mantém o loop principal do jogo50
        while (true) {
            // Atualiza a arena e imprime seu estado atual10

            System.out.println(toString());

            // Solicita ao jogador que escolha uma direção
            System.out.println("Digite uma direção (w, a, s ou d):");
            String input = scanner.nextLine().toLowerCase(); // Converte a entrada para minúsculas para facilitar a
                                                             // comparação

            // Verifica qual tecla foi pressionada e atualiza a direção da cobra
            switch (input) {
                case "w":
                    s.setDirection(270);
                    break;
                case "a":
                    s.setDirection(180);
                    break;
                case "s":
                    s.setDirection(90);
                    break;
                case "d":
                    s.setDirection(0);
                    break;
                default:
                    // Se a entrada não for uma direção válida, exibe uma mensagem de erro
                    System.out.println("Entrada inválida. Por favor, digite w, a, s ou d para mover a cobra.");
                    continue; // Retorna ao início do loop para solicitar outra entrada válida
            }

            // Move a cobra
            s.move();
            updateArena();
            //checkStatus
            System.out.println(toString());
            

            
        }
    }

}
