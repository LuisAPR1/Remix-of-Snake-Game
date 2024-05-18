package Core;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

import Geometry.Circle;
import Geometry.Poligono;
import Geometry.Ponto;
import Geometry.Square;

/**
 * Estratégia de rasterização preenchida para renderizar objetos na arena do
 * jogo.
 * 
 * @version Versão 1.0 10/05/2024
 * @author Luís Rosa, José Lima, Pedro Ferreira e Pedro Ferreira
 */
public class FilledRasterizationTextual implements RasterizationStrategy {

    Cell[][] grid;
    Arena arena;

    public Arena getArena() {
        return arena;
    }

    /**
     * Construtor para criar uma estratégia de rasterização preenchida.
     * 
     * @param arena A arena em que os objetos serão renderizados.
     */
    public FilledRasterizationTextual(Arena arena) {
        this.grid = new Cell[arena.getArenaDimensions()[0]][arena.getArenaDimensions()[1]];
        this.arena = arena;
        initializeArena();
    }

    @Override
    public void render() {
        initializeArena();


        List<Ponto> headPoints;
        List<Ponto> foodPoints;
        List<Ponto> cardinalPoints;

        // Desenha a cabeça da cobra
        headPoints=drawObject(arena.getS().getHead(), "HEAD");

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
            Poligono a = new Poligono(arena.getFruit().getShape().getAllCoordinates());
            foodPoints = drawObject(a, "FOOD");
        }else{foodPoints=null;}

        cardinalPoints=findCommonPoints(headPoints, foodPoints);

        for (Ponto point : cardinalPoints) {
            if (point.getX() >= 0 && point.getX() < grid.length &&
                point.getY() >= 0 && point.getY() < grid[0].length) {
                grid[(int)point.getX()][(int)point.getY()] = Cell.BOTH;
            }
        }

    }

    /**
     * Compara duas listas de pontos e retorna uma nova lista contendo apenas os pontos
     * que são iguais em ambas as listas.
     * 
     * @param list1 A primeira lista de pontos.
     * @param list2 A segunda lista de pontos.
     * @return Uma nova lista contendo os pontos iguais nas duas listas.
     */
    private List<Ponto> findCommonPoints(List<Ponto> list1, List<Ponto> list2) {
        List<Ponto> commonPoints = new ArrayList<>();
        for (Ponto point1 : list1) {
            for (Ponto point2 : list2) {
                if (point1.equals(point2)) {
                    commonPoints.add(point1);
                    break;
                }
            }
        }
        return commonPoints;
    }

    /**
     * Desenha um objeto na grade da arena.
     * 
     * @param object   O objeto a ser desenhado.
     * @param cellType O tipo de célula associado ao objeto.
     */
    private List<Ponto> drawObject(Poligono object, String cellType) {
        List<Ponto> filledCells = new ArrayList<>();
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
    
            // Para cada linha na faixa de Y
            for (int y = minY; y <= maxY; y++) {
                List<Integer> nodeX = new ArrayList<>();
    
                // Encontrar todas as interseções com as arestas do polígono
                int numVertices = vertices.size();
                for (int i = 0; i < numVertices; i++) {
                    Ponto v1 = vertices.get(i);
                    Ponto v2 = vertices.get((i + 1) % numVertices);
    
                    if ((v1.getY() < y && v2.getY() >= y) || (v2.getY() < y && v1.getY() >= y)) {
                        if (v1.getY() != v2.getY()) {
                            int x = (int) (v1.getX() + (y - v1.getY()) * (v2.getX() - v1.getX()) / (v2.getY() - v1.getY()));
                            nodeX.add(x);
                        }
                    }
                }
    
                // Ordenar os pontos de interseção
                nodeX.sort(Integer::compare);
    
                // Preencher os pixels entre os pares de interseção
                for (int i = 0; i < nodeX.size(); i += 2) {
                    if (i + 1 < nodeX.size()) {
                        int x1 = nodeX.get(i);
                        int x2 = nodeX.get(i + 1);
                        for (int x = x1; x <= x2; x++) {  // Inclusivo em x2
                            if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length) {
                                grid[x][y] = Cell.valueOf(cellType);
                                filledCells.add(new Ponto(x, y));
                            }
                        }
                    }
                }
            }
        }
        return filledCells;
    }
    
    
    

    /**
     * Verifica se todos os pontos de um objeto estão dentro dos limites do grid.
     * 
     * @param vertices Os vértices do objeto.
     * @return true se todos os pontos estiverem dentro dos limites, caso contrário,
     *         false.
     */
    private boolean checkIfWithinBounds(List<Ponto> vertices) {
        for (Ponto p : vertices) {
            if (p.getX() > grid.length || p.getY() > grid[0].length) {
                return false;
            }
        }
        return true;
    }

    /**
     * Verifica se um ponto está dentro de um polígono.
     * 
     * @param x        A coordenada x do ponto.
     * @param y        A coordenada y do ponto.
     * @param vertices Os vértices do polígono.
     * @return true se o ponto estiver dentro do polígono, caso contrário, false.
     */
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

    /**
     * Inicializa a grade da arena com células vazias.
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
