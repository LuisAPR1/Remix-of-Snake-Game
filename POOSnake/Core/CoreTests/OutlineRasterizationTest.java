package Core.CoreTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import Core.Arena;
import Core.Cell;
import Core.Obstacle;
import Core.OutlineRasterizationTextual;
import Core.RasterizationType;
import Geometry.Poligono;
import Geometry.Ponto;
import Geometry.Square;

class OutlineRasterizationTest {

    @Test
    void testGetGrid() {
        Arena arena = new Arena(10, 10, 1, RasterizationType.F, 1, null, 0, Core.Obstacle.ObstacleType.S, null, 'T', "Player", null, 'M', 0, 0, 0);
        OutlineRasterizationTextual rasterization = new OutlineRasterizationTextual(arena);
        rasterization.render();
        assertNotNull(rasterization.getGrid());
    }

    @Test
    void testRender() {
        Arena arena = new Arena(10, 10, 1, RasterizationType.F, 1, null, 0, Core.Obstacle.ObstacleType.S, null, 'T', "Player", null, 'M', 0, 0, 0);
        OutlineRasterizationTextual rasterization = new OutlineRasterizationTextual(arena);
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

        for (Obstacle obstacle : arena.getObstacles()) {
            assertObjectRenderedCorrectly(obstacle.getObstacle(), rasterization.getGrid());
        }
    }

    private void assertObjectRenderedCorrectly(Poligono object, Cell[][] grid) {
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

