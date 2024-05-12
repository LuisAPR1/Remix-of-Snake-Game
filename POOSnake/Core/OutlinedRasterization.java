package Core;

import java.util.List;
import java.util.LinkedList;

import Geometry.Poligono;
import Geometry.Ponto;
import Geometry.Square;

/**
 * Classe responsável por realizar a rasterização do contorno dos objetos na arena.
 * 
 * Utiliza uma estratégia de rasterização para desenhar o contorno dos objetos, incluindo a cabeça e a cauda da cobra,
 * obstáculos e a fruta, na grade da arena.
 * 
 * @author Luís Rosa, José Lima, Pedro Ferreira
 * @version Versão 1.0 10/05/2024
 */
class OutlineRasterization implements RasterizationStrategy {

    // Grid que representa a arena
    Cell[][] grid;
    // Referência para a arena
    Arena arena;

    /**
     * Construtor que inicializa a classe de rasterização do contorno.
     * 
     * @param arena A arena onde a rasterização será realizada.
     */
    public OutlineRasterization(Arena arena) {
        this.grid = new Cell[arena.getArenaDimensions()[0]][arena.getArenaDimensions()[1]];
        this.arena = arena;
        initializeArena();
    }

    @Override
    public void render() {
        initializeArena();

        // Desenha o contorno da cabeça da cobra
        drawObject(arena.getS().getHead(), "HEAD");

        // Desenha o contorno da cauda da cobra
        LinkedList<Square> tail = arena.getS().getTailCoordinates();
        for (Square square : tail) {
            drawObject(square, "TAIL");
        }

        // Desenha o contorno dos obstáculos
        for (Obstacle obstacle : arena.getObstacles()) {
            drawObject(obstacle.getObstacle(), "OBSTACLE");
        }

        // Desenha o contorno da fruta
        if (arena.getFruit() != null) {
            Square a = new Square(arena.getFruit().SquareVertices());
            drawObject(a, "FOOD");
        }
    }

    /**
     * Desenha o contorno de um objeto na arena.
     * 
     * @param object   O objeto cujo contorno será desenhado.
     * @param cellType O tipo de célula que será desenhada para representar o contorno.
     */
    private void drawObject(Poligono object, String cellType) {
        List<Ponto> vertices = object.getPontos();
        int numVertices = vertices.size();
    
        // Desenha linhas entre todos os vértices do objeto
        for (int i = 0; i < numVertices; i++) {
            Ponto p1 = vertices.get(i);
            Ponto p2 = vertices.get((i + 1) % numVertices) ;
            drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY(), cellType);
        }
    }

    
    private void drawLine(double x0, double y0, double x1, double y1, String cellType) {
        double dx = Math.abs(x1 - x0);
        double dy = Math.abs(y1 - y0);
        double sx = x0 < x1 ? 1 : -1;
        double sy = y0 < y1 ? 1 : -1;
        double err = dx - dy;
    
        while (x0 != x1 || y0 != y1) {
            // Desenha o ponto atual
            if (x0 >= 0 && x0 < grid.length && y0 >= 0 && y0 < grid[0].length) {
                grid[(int) x0][(int) y0] = Cell.valueOf(cellType);
            }
            
            double e2 = 2 * err;
            if (e2 > -dy) {
                err -= dy;
                x0 += sx;
            }
            if (e2 < dx) {
                err += dx;
                y0 += sy;
            }
        }
    }
    

    /**
     * Inicializa a arena, preenchendo o grid com células vazias.
     */
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