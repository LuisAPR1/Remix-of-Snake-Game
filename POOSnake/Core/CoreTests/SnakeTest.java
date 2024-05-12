package Core.CoreTests;

import Core.AbstractFood;
import Core.Arena;
import Core.FoodFactory;
import Core.FoodType;
import Core.Obstacle;
import Core.Obstacle.ObstacleType;
import Core.RasterizationType;
import Core.Snake;
<<<<<<< Updated upstream
import Geometry.Poligono;
import Geometry.Ponto;
=======
>>>>>>> Stashed changes
import Geometry.Square;

import java.awt.Color;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class SnakeTest {

    @Test
    public void testMove() {
<<<<<<< Updated upstream
        Snake snake = new Snake(new int[]{20,20}, 1);
        List<Ponto> A = snake.getHead().getPontos(); 

        snake.setDirection(0);
        snake.move();

        A.set(0, A.get(0).translacionar(1,0));
        A.set(1, A.get(1).translacionar(1,0));
        A.set(2, A.get(2).translacionar(1,0));
        A.set(3, A.get(3).translacionar(1,0));
        assertEquals(snake.getHead().getPontos(), A);

        snake.setDirection(90);
        snake.move();

        A.set(0, A.get(0).translacionar(0,1));
        A.set(1, A.get(1).translacionar(0,1));
        A.set(2, A.get(2).translacionar(0,1));
        A.set(3, A.get(3).translacionar(0,1));
        assertEquals(snake.getHead().getPontos(), A);

        snake.setDirection(180);
        snake.move();

        A.set(0, A.get(0).translacionar(-1,0));
        A.set(1, A.get(1).translacionar(-1,0));
        A.set(2, A.get(2).translacionar(-1,0));
        A.set(3, A.get(3).translacionar(-1,0));
        assertEquals(snake.getHead().getPontos(), A);

        snake.setDirection(270);
        snake.move();

        A.set(0, A.get(0).translacionar(0,-1));
        A.set(1, A.get(1).translacionar(0,-1));
        A.set(2, A.get(2).translacionar(0,-1));
        A.set(3, A.get(3).translacionar(0,-1));
        assertEquals(snake.getHead().getPontos(), A);
    }

    @Test
    public void testGrow1() {
        Snake snake = new Snake(new int[]{20,20}, 1);
        snake.setDirection(90);
        List<Ponto> A = snake.getHead().getPontos();
        snake.move();
        snake.grow();
        List<Ponto> B = snake.getTailCoordinates().getLast().getPontos();
        assertEquals(A,B);
    }
=======
        int[] starter = new int[2];
        starter[0]=10;
        starter[0]=10;
        int headDimensions = 20;
        Snake snake = new Snake(starter, headDimensions);
        snake.getSnake().addLast(new Square("10 10 30 10 30 30 10 30")); 

        snake.setDirection(0);
        snake.move();
        String expectedAfterRight = "11 10 31 10 31 30 11 30\n11 10 31 10 31 30 11 30\n";
        assertEquals(expectedAfterRight, snake.toString());

        snake.setDirection(90);

        snake.move();
        String expectedAfterDown = "11 11 31 11 31 31 11 31\n11 11 31 11 31 31 11 31\n";
        assertEquals(expectedAfterDown, snake.toString());

        snake.setDirection(180);

        snake.move();
        String expectedAfterLeft = "10 11 30 11 30 31 10 31\n10 11 30 11 30 31 10 31\n";
        assertEquals(expectedAfterLeft, snake.toString());

        snake.setDirection(270);

        snake.move();
        String expectedAfterUp = "10 10 30 10 30 30 10 30\n10 10 30 10 30 30 10 30\n";
        assertEquals(expectedAfterUp, snake.toString());
    }

    public void testGrow() {
        int[] directions = {0, 90, 180, 270};
        int[] starter = new int[2];
        starter[0]=10;
        starter[0]=10;
        int headDimensions = 20;

        Snake snake = new Snake(starter, headDimensions);
        for (int direction : directions) {

            snake.getSnake().clear();
            snake.getSnake().addFirst(new Square("10 10 30 10 30 30 10 30"));
            snake.setDirection(direction); 
            snake.grow();
           
>>>>>>> Stashed changes
    
    
    @Test
    public void testGrow2() {
        Snake snake = new Snake(new int[]{20,20}, 1);
        snake.setDirection(180);

        snake.move();
        snake.grow();
        snake.move();
        snake.grow();
        snake.move();
        snake.grow();

        List<Ponto> A = snake.getTailCoordinates().getLast().getPontos();

        snake.move();
        snake.grow();

        List<Ponto> B = snake.getTailCoordinates().getLast().getPontos();
        assertEquals(A,B);
    }


    @Test
    public void testCheckSnakeInsideArena1() {
        Snake snake = new Snake(new int[]{20,20}, 1);
        snake.setDirection(180);

        for(int i = 0; i <= 20; i++){
            snake.move();
        }

        assertTrue(snake.checkSnakeInsideArena(new int[]{20,20}));

    }

    @Test
    public void testCheckSnakeInsideArena2() {
        Snake snake = new Snake(new int[]{20,20}, 1);
        snake.setDirection(180);
        
        assertFalse(snake.checkSnakeInsideArena(new int[]{20,20}));

    }

    @Test
    public void testSnakeSelfCollision() {
        Snake snake = new Snake(new int[]{20,20}, 1);
        snake.setDirection(180);
        snake.move();
        snake.grow();
        snake.setDirection(90);
        snake.move();
        snake.grow();
        snake.setDirection(0);
        snake.move();
        snake.grow();
        snake.setDirection(270);
        snake.move();
        snake.grow();
        assertTrue(snake.checkSnakeSelfCollision());

    }

    @Test
    public void testCheckFoodEaten() {
        Arena arena = new Arena(10, 10, 1, RasterizationType.F, 1, FoodType.S, 0, ObstacleType.S, null, 'T', "Player", null, 'M', 0);
        Snake snake = new Snake(new int[]{10,10}, 1);
        AbstractFood<?> food = FoodFactory.createFood(Color.RED, Core.FoodType.C, arena, 1);

        assertTrue(snake.CheckFoodEaten(food));

    }

    @Test
    public void testcheckSnakeObstacleColision() {
        Arena arena = new Arena(10, 10, 1, RasterizationType.F, 1, FoodType.S, 0, ObstacleType.S, null, 'T', "Player", null, 'M', 0);
        Snake snake = new Snake(new int[]{10,10}, 1);
        Obstacle obstacle = new Obstacle(ObstacleType.S, new Poligono("0 0 0 10 10 10 10 0"), null);

        assertTrue(snake.checkSnakeObstacleColision(snake, obstacle));

    }
}
