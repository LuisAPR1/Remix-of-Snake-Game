package Core.CoreTests;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import Core.Arena;
import Core.Cell;
import Core.FilledRasterizationTextual;
import Core.FoodType;
import Core.RasterizationType;
import Geometry.Ponto;
import Geometry.Square;

public class FilledRasterizationTest {
    private Scanner scanner;

    @Test
    void testGetGrid() {
        Arena arena = new Arena(10, 10, 1, RasterizationType.F, 1, FoodType.S, 0, Core.Obstacle.ObstacleType.S, null,
                'T', "Player", scanner, 'M', 0, 0, 0);

        FilledRasterizationTextual rasterization = new FilledRasterizationTextual(arena);
        rasterization.render();

        assertNotNull(rasterization.getGrid());
    }

    @Test
    public void testRender() {

        Arena arena = new Arena(10, 10, 1, RasterizationType.F, 1, FoodType.S, 0, Core.Obstacle.ObstacleType.S, null,
                'T', "Player", scanner, 'M', 0, 0, 0);

        FilledRasterizationTextual rasterization = new FilledRasterizationTextual(arena);
        rasterization.render();

        Square head = arena.getS().getHead();
        assertObjectRenderedCorrectly(head, rasterization.getGrid());

        for (Square tailSegment : arena.getS().getTailCoordinates()) {
            assertObjectRenderedCorrectly(tailSegment, rasterization.getGrid());
        }

        if (arena.getFruit() != null) {
            Square fruit = new Square(arena.getFruit().SquareVertices());
            assertObjectRenderedCorrectly(fruit, rasterization.getGrid());
        }

    }

    private void assertObjectRenderedCorrectly(Square object, Cell[][] grid) {
        List<Ponto> vertices = object.getPontos();
        for (int i = 0; i < vertices.size(); i++) {
            int x1 = (int) vertices.get(i).getX();
            int y1 = (int) vertices.get(i).getY();
            int x2 = (int) vertices.get((i + 1) % vertices.size()).getX();
            int y2 = (int) vertices.get((i + 1) % vertices.size()).getY();

            int dx = Math.abs(x2 - x1);
            int dy = Math.abs(y2 - y1);
            int sx = x1 < x2 ? 1 : -1;
            int sy = y1 < y2 ? 1 : -1;
            int err = dx - dy;
            while (true) {

                if (x1 >= 0 && x1 < grid.length && y1 >= 0 && y1 < grid[0].length) {

                    assertEquals(Cell.BOTH, grid[x1][y1]);
                }
                if (x1 == x2 && y1 == y2)
                    break;
                int e2 = 2 * err;
                if (e2 > -dy) {
                    err -= dy;
                    x1 += sx;
                }
                if (e2 < dx) {
                    err += dx;
                    y1 += sy;
                }
            }
        }
    }

}
