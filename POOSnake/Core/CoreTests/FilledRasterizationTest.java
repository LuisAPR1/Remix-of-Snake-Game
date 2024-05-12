package Core.CoreTests;
import Core.*;
import Geometry.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

public class FilledRasterizationTest {
    @Test
    void testGetGrid() {

    }

    @Test
    public void testRender() {

        Snake snake = new Snake(new int[]{10, 10}, 1);
        Arena arena = new Arena(10, 10, 1, RasterizationType.F, 1, FoodType.S, 0, ObstacleType.S, null, 'T', "Player", null, 'M', 0);

        FilledRasterization rasterization = new FilledRasterization(arena);
        
        // Renderiza os objetos na grade da arena
        rasterization.render();
        
        // Verifica se os objetos foram renderizados corretamente
        
        // Verifica se a cabeça da cobra foi desenhada corretamente
        Square head = snake.getHead();
        assertObjectRenderedCorrectly(head, rasterization.getGrid());
        
        // Verifica se a cauda da cobra foi desenhada corretamente
        for (Square tailSegment : snake.getTailCoordinates()) {
            assertObjectRenderedCorrectly(tailSegment, rasterization.getGrid());
        }
        
        // Verifica se os obstáculos foram desenhados corretamente
        for (Square obstacle : arena.getObstaclesAsSquares()) {
            assertObjectRenderedCorrectly(obstacle, rasterization.getGrid());
        }
        
        // Verifica se a fruta foi desenhada corretamente
        if (arena.getFruit() != null) {
            Square fruit = new Square(arena.getFruit().SquareVertices());
            assertObjectRenderedCorrectly(fruit, rasterization.getGrid());
        }

    }

    // Função auxiliar para verificar se um objeto foi renderizado corretamente na grade
    private void assertObjectRenderedCorrectly(Square object, Cell[][] grid) {
        List<Ponto> vertices = object.getPontos();
        for (Ponto vertex : vertices) {
            int x = (int) vertex.getX();
            int y = (int) vertex.getY();
            // Verifica se o ponto está dentro dos limites da grade
            if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length) {
                // Verifica se a célula correspondente na grade está preenchida
                assertEquals(Cell.BOTH, grid[x][y]);
            }
        }
    }
}
