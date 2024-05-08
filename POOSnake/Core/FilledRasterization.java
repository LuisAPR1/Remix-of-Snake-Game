package Core;

import java.util.List;
import java.util.LinkedList;

import Geometry.Poligono;
import Geometry.Ponto;
import Geometry.Square;

class FilledRasterization implements RasterizationStrategy {

    Cell[][] grid;
    Arena arena;

    public FilledRasterization(Arena arena) {
        this.grid = new Cell[arena.getArenaDimensions()[0]][arena.getArenaDimensions()[1]];
        this.arena = arena;
        initializeArena();
    }

    @Override
    public void render() {
        initializeArena();

        // Desenha a cabeça da cobra
        drawObject(arena.getS().getHead(),"HEAD");

        // Desenha a cauda da cobra
        LinkedList<Square> tail = arena.getS().getTailCoordinates();
        for (Square square : tail) {
            drawObject(square,"TAIL");
        }

        // Desenha os obstáculos
        for (Obstacle obstacle : arena.getObstacles()) {
            drawObject(obstacle.getObstacle(),"OBSTACLE");
        }

        // Desenha a fruta
        if (arena.getFruit() != null) {
            Square a = new Square(arena.getFruit().SquareVertices());
            drawObject(a,"FOOD");
        }
    }

    private void drawObject(Poligono object, String cellType) {
        List<Ponto> vertices = object.getPontos();
    
        // Encontra os limites do objeto
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;
        for (Ponto p : vertices) {
            minX = Math.min(minX, p.getX());
            minY = Math.min(minY, p.getY());
            maxX = Math.max(maxX, p.getX());
            maxY = Math.max(maxY, p.getY());
        }
    
        // Preenche as células dentro do polígono definido pelos vértices
        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                if (isInsidePolygon(x, y, vertices)) {
                    grid[x - 1][y - 1] = Cell.valueOf(String.valueOf(cellType)); // Ajusta as coordenadas para o índice da matriz
                }
            }
        }
    }
    
    private boolean isInsidePolygon(int x, int y, List<Ponto> vertices) {
        int intersectCount = 0;
        int numVertices = vertices.size();
        for (int i = 0; i < numVertices; i++) {
            Ponto v1 = vertices.get(i);
            Ponto v2 = vertices.get((i + 1) % numVertices);
            if ((v1.getY() > y) != (v2.getY() > y) &&
                x < (v2.getX() - v1.getX()) * (y - v1.getY()) / (v2.getY() - v1.getY()) + v1.getX()) {
                intersectCount++;
            }
        }
        return intersectCount % 2 == 1;
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
