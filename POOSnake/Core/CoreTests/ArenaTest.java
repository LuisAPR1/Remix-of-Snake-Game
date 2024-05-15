package Core.CoreTests;

import Core.AbstractFood;
import Core.Arena;
import Core.Cell;
import Core.FoodFactory;
import Core.FoodType;
import Core.InterfaceMode;
import Core.MovementStrategy;
import Core.RasterizationType;
import Core.Snake;
import Core.Obstacle.ObstacleType;
import Geometry.Ponto;
import Geometry.Square;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


    //Testes nao funcionais devido a visibilidade dos metodos, no momento de testar os metodos foram colocados como public.

public class ArenaTest {
    private Arena arena;
    private Snake snake;
    @BeforeEach
    public void setUp() {

        @SuppressWarnings("unused")
        Snake snake = new Snake(new int[]{10, 10}, 1);
        @SuppressWarnings("unused")
        Arena arena = new Arena(10, 10, 1, RasterizationType.F, 1, FoodType.S, 0, ObstacleType.S, null, 'T', "Player", null, 'M', 0, 0, 0);
    }

    @Test
    void testCheckFoodSnakeCollision() {
        AbstractFood<?> food = FoodFactory.createFood(Color.RED, Core.FoodType.C, arena, 1);

        if(food.contains(snake.getHead())) {
            assertTrue(arena.checkFoodSnakeCollision(food));
        }
    }

    @Test
    void testGenerateFood() {
        arena.generateFood(Color.RED, FoodType.C, arena, 1);

        assertEquals(1, arena.getFruit());
        assertEquals(arena.getFruit().getType(), FoodType.C);
    }

    @Test
    void testCreateObstacles1() {
        arena.createObstacles(2, ObstacleType.S, new int[]{10,10}, 1);

        assertEquals(2, arena.getObstacles().size());
        assertEquals(arena.getObstacles().get(0).getType(), ObstacleType.S);
    }

    @Test
    void testCreateObstacles2() {
        Arena arena1 = new Arena(10, 10, 1, RasterizationType.F, 1, FoodType.S, 0, ObstacleType.D, new Ponto(0, 0), 'T', "Player", null, 'M', 0, 0, 0);

        arena1.createObstacles(2, ObstacleType.D, new int[]{10,10}, 1);

        assertEquals(2, arena1.getObstacles().size());
        assertEquals(arena1.getObstacles().get(0).getType(), ObstacleType.S);
    }

    @Test
    void testGenerateSnake() {
        arena.generateSnake(new int[]{10,10}, 1);
        assertEquals(snake, arena.getS());
    }

    @Test
    void testAdjustArenaDimension() {
        int originalDimension = 15;
        int headDimensions = 5;

        int adjustedDimension = arena.adjustArenaDimension(originalDimension, headDimensions);

        assertEquals(20, adjustedDimension);
    }

    @Test
    void testNoAdjustmentNeeded() {
        int originalDimension = 20;
        int headDimensions = 5;

        int adjustedDimension = arena.adjustArenaDimension(originalDimension, headDimensions);

        assertEquals(20, adjustedDimension);
    }

    @Test
    void testFrame1() {
        List<Ponto> headBeforeMove = arena.getS().getHead().getPontos();
        snake.setDirection(0);
        arena.Frame();

        List<Ponto> headAfterMove = arena.getS().getHead().getPontos();

        assertNotEquals(headBeforeMove, headAfterMove);
    }

    @Test
    void testObstacleMove() {
        Ponto obstacleBeforeMove = arena.getObstacles().get(0).getPosition();

        arena.obstaclesmove();

        Ponto obstacleAfterMove = arena.getObstacles().get(0).getPosition();

        assertNotEquals(obstacleBeforeMove, obstacleAfterMove);
    }
}