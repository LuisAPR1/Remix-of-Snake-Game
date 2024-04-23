package Core.CoreTests;
import Core.Snake;
import Geometry.Ponto;
import Geometry.Square;
import org.junit.jupiter.api.Test;
import java.util.LinkedList;
import static org.junit.jupiter.api.Assertions.*;

public class SnakeTest {

    @Test
    public void testMove() {
        // Configurando o ambiente de teste
        Ponto starter = new Ponto(10, 10);
        int headDimensions = 20;
        Snake snake = new Snake(starter, headDimensions);

        // Movendo para a direita
        snake.move(0);
        LinkedList<Square> snakeSegments = snake.getSnake();
        assertEquals(new Ponto(11, 10), snakeSegments.getFirst().getPontos().get(0));
        assertEquals(new Ponto(31, 10), snakeSegments.getFirst().getPontos().get(1));
        assertEquals(new Ponto(31, 30), snakeSegments.getFirst().getPontos().get(2));
        assertEquals(new Ponto(11, 30), snakeSegments.getFirst().getPontos().get(3));

        // Movendo para baixo
        snake.move(90);
        snakeSegments = snake.getSnake();
        assertEquals(new Ponto(11, 11), snakeSegments.getFirst().getPontos().get(0));
        assertEquals(new Ponto(31, 11), snakeSegments.getFirst().getPontos().get(1));
        assertEquals(new Ponto(31, 31), snakeSegments.getFirst().getPontos().get(2));
        assertEquals(new Ponto(11, 31), snakeSegments.getFirst().getPontos().get(3));

        // Movendo para a esquerda
        snake.move(180);
        snakeSegments = snake.getSnake();
        assertEquals(new Ponto(10, 11), snakeSegments.getFirst().getPontos().get(0));
        assertEquals(new Ponto(30, 11), snakeSegments.getFirst().getPontos().get(1));
        assertEquals(new Ponto(30, 31), snakeSegments.getFirst().getPontos().get(2));
        assertEquals(new Ponto(10, 31), snakeSegments.getFirst().getPontos().get(3));

        // Movendo para cima
        snake.move(270);
        snakeSegments = snake.getSnake();
        assertEquals(new Ponto(10, 10), snakeSegments.getFirst().getPontos().get(0));
        assertEquals(new Ponto(30, 10), snakeSegments.getFirst().getPontos().get(1));
        assertEquals(new Ponto(30, 30), snakeSegments.getFirst().getPontos().get(2));
        assertEquals(new Ponto(10, 30), snakeSegments.getFirst().getPontos().get(3));
    }
}
