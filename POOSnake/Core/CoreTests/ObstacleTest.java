package Core.CoreTests;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import Geometry.Poligono;
import Geometry.Ponto;
import Core.Obstacle;

public class ObstacleTest {

    @Test
    public void testRotateDynamicObstacle1() {
        // Definir um polígono para representar o obstáculo
        Poligono obstaclePolygon = new Poligono("0 0 0 4 4 4 4 0");
        // Definir um ponto de rotação
        Ponto rotationPoint = new Ponto(2, 2);

        // Criar um obstáculo dinâmico com o polígono e o ponto de rotação definidos
        Obstacle obstacle = new Obstacle(Obstacle.ObstacleType.D, obstaclePolygon, rotationPoint);

        // Rotacionar o obstáculo em 90 graus
        Poligono rotatedObstacle = obstacle.rotate(90);

        // Verificar se a rotação foi aplicada corretamente
        assertEquals(4, rotatedObstacle.getPontos().size());
        assertEquals(new Ponto(4, 0), rotatedObstacle.getPontos().get(0));
        assertEquals(new Ponto(4, 4), rotatedObstacle.getPontos().get(1));
        assertEquals(new Ponto(0, 4), rotatedObstacle.getPontos().get(2));
        assertEquals(new Ponto(0, 0), rotatedObstacle.getPontos().get(3));
    }

    @Test
    public void testRotateDynamicObstacle2() {
        // Definir um polígono diferente para representar o obstáculo
        Poligono obstaclePolygon = new Poligono("0 0 0 3 3 3 3 0");

        // Definir um ponto de rotação
        Ponto rotationPoint = new Ponto(1.5, 1.5);

        // Criar um obstáculo dinâmico com o polígono e o ponto de rotação definidos
        Obstacle obstacle = new Obstacle(Obstacle.ObstacleType.D, obstaclePolygon, rotationPoint);

        // Rotacionar o obstáculo em 90 graus
        Poligono rotatedObstacle = obstacle.rotate(90);

        // Verificar se a rotação foi aplicada corretamente
        assertEquals(4, rotatedObstacle.getPontos().size());
        assertEquals(new Ponto(3, 0), rotatedObstacle.getPontos().get(0));
        assertEquals(new Ponto(3, 3), rotatedObstacle.getPontos().get(1));
        assertEquals(new Ponto(0, 3), rotatedObstacle.getPontos().get(2));
        assertEquals(new Ponto(0, 0), rotatedObstacle.getPontos().get(3));
    }
}
