package Core.CoreTests;

import Core.Snake;
import Geometry.Ponto;
import Geometry.Square;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SnakeTest {

    @Test
    public void testMove() {
        // Configurando o ambiente de teste
        Ponto starter = new Ponto(10, 10);
        int headDimensions = 20;
        Snake snake = new Snake(starter, headDimensions);
        snake.getSnake().addLast(new Square("10 10 30 10 30 30 10 30")); 

        // Movendo para a direita
        snake.move(0);
        String expectedAfterRight = "11 10 31 10 31 30 11 30\n11 10 31 10 31 30 11 30\n";
        assertEquals(expectedAfterRight, snake.toString());

        // Movendo para baixo
        snake.move(90);
        String expectedAfterDown = "11 11 31 11 31 31 11 31\n11 11 31 11 31 31 11 31\n";
        assertEquals(expectedAfterDown, snake.toString());

        // Movendo para a esquerda
        snake.move(180);
        String expectedAfterLeft = "10 11 30 11 30 31 10 31\n10 11 30 11 30 31 10 31\n";
        assertEquals(expectedAfterLeft, snake.toString());

        // Movendo para cima
        snake.move(270);
        String expectedAfterUp = "10 10 30 10 30 30 10 30\n10 10 30 10 30 30 10 30\n";
        assertEquals(expectedAfterUp, snake.toString());
    }

    public void testGrow() {
        // Direções: direita, baixo, esquerda, cima
        int[] directions = {0, 90, 180, 270};
        Ponto starter = new Ponto(10, 10);
        int headDimensions = 20;

        Snake snake = new Snake(starter, headDimensions);
        for (int direction : directions) {
            // Configurando a cobra de cabeça

            snake.getSnake().clear();
            snake.getSnake().addFirst(new Square("10 10 30 10 30 30 10 30"));
            snake.setDirection(direction); 
            snake.grow();
           
    
            // Verificando a representação da cobra como uma string após o crescimento
            String expectedSnake = "";
            switch (direction) {
                case 0:
                    expectedSnake = "11 10 31 10 31 30 11 30\n10 10 30 10 30 30 10 30\n";
                    break;
                case 90:
                    expectedSnake = "10 11 30 11 30 31 10 31\n10 10 30 10 30 30 10 30\n";
                    break;
                case 180:
                    expectedSnake = "10 10 30 10 30 30 10 30\n10 11 30 11 30 31 10 31\n";
                    break;
                case 270:
                    expectedSnake = "10 10 30 10 30 30 10 30\n10 10 30 10 30 30 10 30\n";
                    break;
            }
            
            assertEquals(expectedSnake, snake.toString());
        }
    }
    

}
