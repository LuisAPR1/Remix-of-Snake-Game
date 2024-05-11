package Core;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import Geometry.Poligono;
import Geometry.Ponto;


public class FoodSquare extends AbstractFood<Poligono> {
    private int sideLength;
    private Poligono p;
    int winner;

    public FoodSquare(Color color, Core.FoodType type, Arena arena, int sideLength) {
        super(color, type, arena);
        this.sideLength = sideLength;
        spawnFood(arena);
    }

    @Override
    public void spawnFood(Arena arena) {
        int maxTries = 10000;
        int tries = 0;
        int movementIncrement = arena.getHeadDimensions();
        while (tries < maxTries) {
            int x = (int) (Math.random() * (arena.getArenaDimensions()[0] - sideLength));
            int y = (int) (Math.random() * (arena.getArenaDimensions()[1] - sideLength));
            boolean reachableBySnake = (x % movementIncrement == 0) && (y % movementIncrement == 0);
            boolean intersectsObstacle = false; // Flag para verificar interseção com obstáculo
            if (reachableBySnake) {
                List<Ponto> pontos = new ArrayList<>();
                pontos.add(new Ponto(x, y));
                pontos.add(new Ponto(x + sideLength, y));
                pontos.add(new Ponto(x + sideLength, y + sideLength));
                pontos.add(new Ponto(x, y + sideLength));
                p = new Poligono(pontos);
                // Verificar se a comida não intersecta com nenhum obstáculo
                for (Obstacle obstacle : arena.getObstacles()) {
                    if (p.intersect(obstacle.getObstacle())) {
                        intersectsObstacle = true;
                        break;
                    }
                }
                if (!intersectsObstacle) {
                    winner=0;
                    return;
                }
            }
            winner++;
            tries++;
            System.out.println("---------------" + winner);
        }
    }
    
    @Override
    public Poligono getShape() {
        return p;
    }

    @Override
    public List<Ponto> SquareVertices() {
        return p.getPontos();
    }
}
