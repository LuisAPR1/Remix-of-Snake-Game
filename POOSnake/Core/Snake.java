package Core;

import java.util.LinkedList;
import java.util.List;

import Geometry.Ponto;
import Geometry.Square;

public class Snake {
    private LinkedList<Square> snake = new LinkedList<>();

    public Snake(int headDimensions) {
        Square h = new Square("0 0 0 0 0 0 0 0");
        snake.add(h);
    }

    public List<Ponto> getHeadCoordinates() {
        return snake.getFirst().getPontos();
    }

    public LinkedList<Square> getTailCoordinates() {
        // Cria uma sublista a partir do índice 1 para excluir o primeiro quadrado (cabeça)
        return new LinkedList<>(snake.subList(1, snake.size()));
    }
}
