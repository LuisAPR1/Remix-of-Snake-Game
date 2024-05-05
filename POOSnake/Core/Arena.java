package Core;

import java.awt.Color;
import java.util.LinkedList;
import java.util.function.BooleanSupplier;
import Geometry.Ponto;
import Geometry.Square;

public class Arena {

    Cell[][] grid;
    private int[] arenaDimensions;
    private FoodType food;
    private int headDimensions;
    private RasterizationType rasterization;
    private int score;
    private InterfaceMode interfaceMode;

    private Ponto Snakeposition;

    public Cell[][] getGrid() {
        return grid;
    }

    public Arena(Ponto Snakeposition, int arenaDimensionsX, int arenaDimensionsY, Color foodColor, FoodType foodType, int headDimensions, RasterizationType rasterization, int score, InterfaceMode interfaceMode) {
        this.grid = new Cell[arenaDimensionsX][arenaDimensionsY];
        initializeArena();
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

    public void updateArena(Snake s) {
        // Preenche a cabeça da cobra na arena com o símbolo 'H'
        initializeArena();

        for (Ponto p : s.getHeadCoordinates()) {
            grid[p.getX()][p.getY()] = Cell.HEAD;
        }

        // Preenche a cauda da cobra na arena com o símbolo 'T'
        LinkedList<Square> tail = s.getTailCoordinates();
        for (Square square : tail) {
            for (Ponto p : square.getPontos()) {
                grid[p.getX()][p.getY()] = Cell.TAIL;
            }
        }
    }

    // public void printArena() {
    // for (int i = 0; i < grid.length; i++) {
    // for (int j = 0; j < grid[i].length; j++) {
    // System.out.print(grid[j][i].getSymbol() + " ");
    // }
    // System.out.println();
    // }
    // }

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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'snakeIntersectObstacle'");
    }

    public BooleanSupplier foodContainedOnSnakeHead(Snake snake) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'foodContainedOnSnakeHead'");
    }

    public void generateFood() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'generateFood'");
    }

    public BooleanSupplier snakeLeftTheBoard(Snake snake) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'snakeLeftTheBoard'");
    }


}
