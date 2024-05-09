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

        // Desenha a cabeça da cobra
        drawObject(arena.getS().getHead(), "HEAD");

        // Desenha a cauda da cobra
        LinkedList<Square> tail = arena.getS().getTailCoordinates();
        for (Square square : tail) {
            drawObject(square, "TAIL");
        }

        // Desenha os obstáculos
        for (Obstacle obstacle : arena.getObstacles()) {
            drawObject(obstacle.getObstacle(), "OBSTACLE");
        }

        // Desenha a fruta
        if (arena.getFruit() != null) {
            Square a = new Square(arena.getFruit().SquareVertices());
            drawObject(a, "FOOD");
        }
    }

    private void drawObject(Poligono object, String cellType) {
        List<Ponto> vertices = object.getPontos();
    
        // Desenha uma linha entre cada par de vértices adjacentes
        for (int i = 0; i < vertices.size(); i++) {
            Ponto currentVertex = vertices.get(i);
            Ponto nextVertex = vertices.get((i + 1) % vertices.size()); // O próximo vértice é o primeiro se estivermos no último vértice
            drawLine(currentVertex, nextVertex, cellType);
        }
    }
    
    private void drawLine(Ponto start, Ponto end, String cellType) {
        int x0 = start.getX();
        int y0 = start.getY();
        int x1 = end.getX();
        int y1 = end.getY();
    
        // Calcula as diferenças entre as coordenadas
        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);
    
        // Determina a direção do incremento para x e y
        int sx = x0 < x1 ? 1 : -1;
        int sy = y0 < y1 ? 1 : -1;
    
        // Inicializa os pontos de decisão para a primeira coordenada
        int err = dx - dy;
        int x = x0;
        int y = y0;
    
        // Percorre a linha
        while (true) {
            // Desenha a célula correspondente
            grid[x - 1][y - 1] = Cell.valueOf(cellType);
    
            // Verifica se chegamos ao fim da linha
            if (x == x1 && y == y1) {
                break;
            }
    
            // Calcula os pontos de decisão para a próxima coordenada
            int e2 = 2 * err;
            if (e2 > -dy) {
                err -= dy;
                x += sx;
            }
            if (e2 < dx) {
                err += dx;
                y += sy;
            }
        }
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
