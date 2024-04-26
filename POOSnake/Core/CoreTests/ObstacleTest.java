package Core.CoreTests;

import Core.Arena;
import Core.Food;
import Core.Food.FoodType;
import Core.Obstacle;
import Core.Obstacle.ObstacleType;
import Core.Snake;
import Geometry.Poligono;
import Geometry.Ponto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Color;


public class ObstacleTest {

    @Test
    public void testObstacleSpawn() {
        // Configurando a arena
        int arenaWidth = 200;
        int arenaHeight = 200;
        Arena arena = new Arena(arenaWidth, arenaHeight);

        // Configurando a cobra
        Ponto starter = new Ponto(10, 10);
        int headDimensions = 5;
        Snake snake = new Snake(starter, headDimensions);

        Food food = new Food(Color.RED, FoodType.C, arena, headDimensions);
        // Configurando o obstáculo
        Poligono obstacleShape = new Poligono("0 0 0 1 1 1 1 0");
        Obstacle obstacle = new Obstacle(Obstacle.ObstacleType.S, obstacleShape,null);

        // Verificando se o obstáculo está dentro da arena
        Ponto obstaclePosition = obstacle.getPosition();
        assertTrue(obstaclePosition.getX() >= 0 && obstaclePosition.getX() < arenaWidth);
        assertTrue(obstaclePosition.getY() >= 0 && obstaclePosition.getY() < arenaHeight);

        // Verificando se a posição do obstáculo corresponde a uma célula vazia na arena
        assertEquals('E', arena.getGrid()[obstaclePosition.getX()][obstaclePosition.getY()].getSymbol());
    }

    @Test
    public void testRotateObstacleWithoutPoint() {

        // Configurando o ambiente de teste
        Ponto startPoint = new Ponto(10, 10);
        Poligono obstacleShape = new Poligono("0 0 0 1 1 1 1 0"); 
        Obstacle obstacle = new Obstacle(ObstacleType.D, obstacleShape, null);

       
        // Rotacionando o obstáculo em 90 graus
        obstacle.rotate(90);

        // Obtendo as coordenadas do obstáculo depois da rotação
        Poligono obstacleAfterRotation = obstacle.getObstacle();

        Poligono expected = new Poligono("1 0 0 0 0 1 1 1"); 

        // Verificando se as coordenadas do obstáculo depois da rotação coincidem com as coordenadas esperadas
        assertArrayEquals(expected.getPontos().toArray(), obstacleAfterRotation.getPontos().toArray());
    }

    @Test
    public void testRotateDynamicObstacleWithRotationPoint() {
        // Configurando o ambiente de teste
        Ponto rotationPoint = new Ponto(15, 15); // Ponto de rotação
        Poligono obstacleShape = new Poligono(new Ponto(0, 0), 20, 4); // Quadrado de lado 20
        Obstacle obstacle = new Obstacle(ObstacleType.D, obstacleShape, rotationPoint);

        // Obtendo as coordenadas do obstáculo antes da rotação
        Poligono obstacleBeforeRotation = obstacle.getObstacle();

        // Rotacionando o obstáculo em 90 graus
        obstacle.rotate(90);

        // Obtendo as coordenadas do obstáculo depois da rotação
        Poligono obstacleAfterRotation = obstacle.getObstacle();

        // Verificando se as coordenadas do obstáculo depois da rotação coincidem com as coordenadas esperadas
        // Note que, como o ponto de rotação não é o centro do polígono, as coordenadas não devem mudar
        assertArrayEquals(obstacleBeforeRotation.getPontos().toArray(), obstacleAfterRotation.getPontos().toArray());
    }


    
}
