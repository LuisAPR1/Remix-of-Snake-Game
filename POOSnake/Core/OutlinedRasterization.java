package Core;

import java.util.List;
import java.util.LinkedList;

import Geometry.Poligono;
import Geometry.Ponto;
import Geometry.Square;

class OutlineRasterization implements RasterizationStrategy {

    Cell[][] grid;
    Arena arena;

    public OutlineRasterization(Arena arena) {
        this.grid = new Cell[arena.getArenaDimensions()[0]][arena.getArenaDimensions()[1]];
        this.arena = arena;
        initializeArena();
    }

    @Override
    public void render() {
        initializeArena();

        // Desenha o contorno da cabeça da cobra
        drawOutline(arena.getS().getHead(), "HEAD");

        // Desenha o contorno da cauda da cobra
        LinkedList<Square> tail = arena.getS().getTailCoordinates();
        for (Square square : tail) {
            drawOutline(square, "TAIL");
        }

        // Desenha o contorno dos obstáculos
        for (Obstacle obstacle : arena.getObstacles()) {
            drawOutline(obstacle.getObstacle(), "OBSTACLE");
        }

        // Desenha o contorno da fruta
        if (arena.getFruit() != null) {
            Square a = new Square(arena.getFruit().SquareVertices());
            drawOutline(a, "FOOD");
        }
    }

    private void drawOutline(Poligono object, String cellType) {
        List<Ponto> vertices = object.getPontos();

        // Verifica se todos os pontos do objeto estão dentro dos limites do grid
        if (checkIfWithinBounds(vertices)) {
            // Encontra os limites do objeto
            int minX = Integer.MAX_VALUE;
            int minY = Integer.MAX_VALUE;
            int maxX = Integer.MIN_VALUE;
            int maxY = Integer.MIN_VALUE;
            for (Ponto p : vertices) {
                minX = (int) Math.min(minX, p.getX());
                minY = (int) Math.min(minY, p.getY());
                maxX = (int) Math.max(maxX, p.getX());
                maxY = (int) Math.max(maxY, p.getY());
            }

            // Desenha o contorno do objeto
            for (int x = minX; x < maxX; x++) {
                if (x >= 0 && x < grid.length) {
                    if (minY >= 0 && minY < grid[0].length) {
                        grid[x][minY] = Cell.valueOf(cellType);
                    }
                    if (maxY - 1 >= 0 && maxY - 1 < grid[0].length) {
                        grid[x][maxY - 1] = Cell.valueOf(cellType);
                    }
                }
            }
            for (int y = minY; y < maxY; y++) {
                if (y >= 0 && y < grid[0].length) {
                    if (minX >= 0 && minX < grid.length) {
                        grid[minX][y] = Cell.valueOf(cellType);
                    }
                    if (maxX - 1 >= 0 && maxX - 1 < grid.length) {
                        grid[maxX - 1][y] = Cell.valueOf(cellType);
                    }
                }
            }
        }
    }

    private boolean checkIfWithinBounds(List<Ponto> vertices) {
        for (Ponto p : vertices) {
            if (p.getX() >= grid.length || p.getY() >= grid[0].length || p.getX() < 0 || p.getY() < 0) {
                return false;
            }
        }
        return true;
    }

    private void initializeArena() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = Cell.EMPTY;
            }
        }
    }

    @Override
    public Cell[][] getGrid() {
        return grid;
    }
}
