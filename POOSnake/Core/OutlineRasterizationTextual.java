package Core;

import java.util.List;
import java.util.LinkedList;

import Geometry.Poligono;
import Geometry.Ponto;
import Geometry.Square;

/**
 * Classe responsável por realizar a rasterização do contorno dos objetos na
 * arena.
 * 
 * Utiliza uma estratégia de rasterização para desenhar o contorno dos objetos,
 * incluindo a cabeça e a cauda da cobra,
 * obstáculos e a fruta, na grade da arena.
 * 
 * @author Luís Rosa, José Lima, Pedro Ferreira
 * @version Versão 1.0 10/05/2024
 */
public class OutlineRasterizationTextual implements RasterizationStrategy {

    // Grid que representa a arena
    Cell[][] grid;
    // Referência para a arena
    Arena arena;

    /**
     * Construtor que inicializa a classe de rasterização do contorno.
     * 
     * @param arena A arena onde a rasterização será realizada.
     */
    public OutlineRasterizationTextual(Arena arena) {
        this.grid = new Cell[arena.getArenaDimensions()[0]][arena.getArenaDimensions()[1]];
        this.arena = arena;
        initializeArena();
    }

    public Arena getArena() {
        return arena;
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
            Poligono fruitPolygon = new Poligono(arena.getFruit().getShape().getAllCoordinates());
            drawObject(fruitPolygon, "FOOD");
        }
    }

    /**
     * Desenha o contorno de um objeto na arena.
     * 
     * @param object   O objeto cujo contorno será desenhado.
     * @param cellType O tipo de célula que será desenhada para representar o
     *                 contorno.
     */
    private void drawObject(Poligono object, String cellType) {
        List<Ponto> vertices = object.getPontos();
        int numVertices = vertices.size();
        if (numVertices < 2) {
            return; // Não pode desenhar um polígono com menos de dois pontos
        }
    
        for (int i = 0; i < numVertices; i++) {
            Ponto v1 = vertices.get(i);
            Ponto v2 = vertices.get((i + 1) % numVertices); // Conecta o último ponto ao primeiro
            drawLine((int) v1.getX(), (int) v1.getY(), (int) v2.getX(), (int) v2.getY(), cellType);
        }
    }
    

    /**
     * Desenha uma linha usando o algoritmo de Bresenham.
     * 
     * @param x1       A coordenada x do primeiro ponto.
     * @param y1       A coordenada y do primeiro ponto.
     * @param x2       A coordenada x do segundo ponto.
     * @param y2       A coordenada y do segundo ponto.
     * @param cellType O tipo de célula que será desenhada para representar a linha.
     */
    private void drawLine(int x1, int y1, int x2, int y2, String cellType) {
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int sx = x1 < x2 ? 1 : -1;
        int sy = y1 < y2 ? 1 : -1;
        int err = (dx > dy ? dx : -dy) / 2, e2;
    
        while (true) {
            if (x1 >= 0 && x1 < grid.length && y1 >= 0 && y1 < grid[0].length) {
                grid[x1][y1] = Cell.valueOf(cellType);
            }
            if (x1 == x2 && y1 == y2) {
                break;
            }
            e2 = err;
            if (e2 > -dx) {
                err -= dy;
                x1 += sx;
            }
            if (e2 < dy) {
                err += dx;
                y1 += sy;
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
