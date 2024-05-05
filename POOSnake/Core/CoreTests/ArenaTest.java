package Core.CoreTests;

import Core.Arena;
import Core.Cell;
import Core.FoodType;
import Core.InterfaceMode;
import Core.RasterizationType;
import Core.Snake;
import Geometry.Ponto;
import java.awt.Color;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ArenaTest {
    private Arena arena;
    private Snake snake;

    @BeforeEach
    public void setUp() {
        Ponto starter = new Ponto(5, 5);
        int arenaDimensionsX = 10;
        int arenaDimensionsY = 10;
        arena = new Arena(starter, arenaDimensionsX, arenaDimensionsY, Color.RED, FoodType.S, 1, RasterizationType.O, 0, InterfaceMode.T);
        snake = new Snake(starter, 1);
    }

    @Test
    public void testInitializeArena() {
        arena.initializeArena();
        assertNotNull(arena.getGrid());
        assertEquals(arena.getGrid()[0][0].getSymbol(), 'E'); 
    }

    @Test
    public void testUpdateArena() {
        snake.move(90);
        snake.grow();
        arena.updateArena(snake);
        assertEquals(arena.getGrid()[4][5].getSymbol(), 'H'); 
        assertEquals(arena.getGrid()[5][5].getSymbol(), 'T'); 
    }

    @Test
    public void testSnakeIntersectObstacle() {
        arena.getGrid()[3][5] = Cell.OBSTACLE; 
        snake.move(90); 
        arena.updateArena(snake);
        assertTrue(arena.snakeIntersectObstacle(snake)); 
    }

    @Test
    public void testFoodContainedOnSnakeHead() {

        arena.getGrid()[4][5] = Cell.FOOD;
        snake.move(90); 
        arena.updateArena(snake);
        assertTrue(arena.foodContainedOnSnakeHead(snake)); 
    }

    @Test
    public void testGenerateFood() {
        arena.generateFood();
        boolean foundFood = false;
        for (int i = 0; i < arena.getGrid().length; i++) {
            for (int j = 0; j < arena.getGrid()[i].length; j++) {
                if (arena.getGrid()[i][j] == Cell.FOOD) {
                    foundFood = true;
                    break;
                }
            }
        }
        assertTrue(foundFood); 
    }

    @Test
    public void testSnakeLeftTheBoard() {
       
        snake.setDirection(270);
        snake.move(90);
        snake.move(90);
        snake.move(90); 
        snake.move(90);
        snake.move(90);
        snake.move(90);
        assertTrue(arena.snakeLeftTheBoard(snake));
    }
}