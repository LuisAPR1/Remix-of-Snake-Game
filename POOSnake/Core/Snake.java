package Core;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import Geometry.Ponto;
import Geometry.Square;

public class Snake {
    private LinkedList<Square> snake = new LinkedList<>();

    public Snake(Ponto starter, int headDimensions) {
        List<Ponto> squarePoints = new ArrayList<>();
        squarePoints.add(starter); // Canto superior esquerdo
        squarePoints.add(new Ponto(starter.getX() + headDimensions, starter.getY())); // Canto superior direito
        squarePoints.add(new Ponto(starter.getX() + headDimensions, starter.getY() + headDimensions)); // Canto inferior
                                                                                                       // direito
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
}
