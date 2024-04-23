package Core;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import Geometry.Ponto;
import Geometry.Square;

public class Snake {
    private LinkedList<Square> snake = new LinkedList<>();
    int direction;
    public Snake(Ponto starter, int headDimensions) {
        List<Ponto> squarePoints = new ArrayList<>();
        squarePoints.add(starter); // Canto superior esquerdo
        squarePoints.add(new Ponto(starter.getX() + headDimensions, starter.getY())); // Canto superior direito
        squarePoints.add(new Ponto(starter.getX() + headDimensions, starter.getY() + headDimensions)); // Canto inferior direito
        squarePoints.add(new Ponto(starter.getX(), starter.getY() + headDimensions)); // Canto inferior esquerdo

        // Cria a cabeça da cobra com os pontos calculados e adiciona à lista de
        // segmentos da cobra
        Square h = new Square(squarePoints);
        snake.add(h);
    }

    public List<Ponto> getHeadCoordinates() {
        return snake.getFirst().getPontos();
    }

    public LinkedList<Square> getTailCoordinates() {
        // Cria uma sublista a partir do índice 1 para excluir o primeiro quadrado
        // (cabeça)
        return new LinkedList<>(snake.subList(1, snake.size()));
    }

    public void move(int direction) {
        this.direction = direction;
        // Move a cabeça da cobra na direção especificada
    
        // Calcula os deslocamentos horizontal e vertical com base na direção
        int xMove = 0;
        int yMove = 0;
        if (direction == 0) {
            xMove = 1; // Movimento para a direita
        } else if (direction == 90) {
            yMove = 1; // Movimento para baixo
        } else if (direction == 180) {
            xMove = -1; // Movimento para a esquerda
        } else if (direction == 270) {
            yMove = -1; // Movimento para cima
        }
        System.out.println(snake.getFirst().toString());
        snake.set(0, snake.get(0).translacaoSemPonto(xMove, yMove));
        System.out.println(snake.getFirst().toString());
        
        
    }

    public LinkedList<Square> getSnake() {
        return snake;
    }
    
}
