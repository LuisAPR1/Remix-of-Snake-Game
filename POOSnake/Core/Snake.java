package Core;

import java.util.LinkedList;

import Geometry.Ponto;
import Geometry.Square;

public class Snake {
    private LinkedList<Square> snake = new LinkedList<>();
    int headDimensions;
    int direction;
    Ponto lastTailPosition;

    public LinkedList<Square> getSnake() {
        return snake;
    }

    public void setSnake(LinkedList<Square> snake) {
        this.snake = snake;
    }

    public Snake(int[] arenaDimensions, int headDimensions) {
        this.headDimensions = headDimensions;
        HeadInitializer(arenaDimensions, headDimensions);
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void HeadInitializer(int[] arenaDimensions, int headDimensions) {
        // Gera coordenadas aleatórias para a cabeça dentro da arena
        int randomX = 1 + (int) (Math.random() * (arenaDimensions[0] - headDimensions - 1));
        int randomY = 1 + (int) (Math.random() * (arenaDimensions[1] - headDimensions - 1));

        // Define a direção aleatória da cobra (0, 90, 180, 270 graus)
        int randomDirection = (int) (Math.random() * 4) * 90;

        // Calcula as coordenadas da cabeça com base na direção
        int headX = randomX;
        int headY = randomY;
        switch (randomDirection) {
            case 90: // Baixo
                headY = arenaDimensions[1] - headDimensions;
                break;
            case 180: // Esquerda
                headX = arenaDimensions[0] - headDimensions;
                break;
            case 270: // Cima
                headY = 1;
                break;
            default:
                break;
        }

        // Monta a representação da cabeça como uma string
        String headRepresentation = headX + " " + headY + " " +
                (headX + headDimensions) + " " + headY + " " +
                (headX + headDimensions) + " " + (headY + headDimensions) + " " +
                headX + " " + (headY + headDimensions);

        // Cria o objeto Square representando a cabeça e adiciona à lista de quadrados
        // da cobra
        Square h = new Square(headRepresentation);
        snake.addFirst(h);

        // Define a direção da cobra como a direção aleatória gerada
        this.direction = randomDirection;
    }

    public Square getHead() {
        return snake.getFirst();
    }

    public LinkedList<Square> getTailCoordinates() {
        return new LinkedList<>(snake.subList(1, snake.size()));
    }

    public void move() {
        // Guarda a posição da última cauda antes de movê-la
        lastTailPosition = snake.getLast().getPontos().get(0);

        // Calcula os deslocamentos horizontal e vertical com base na direção
        int xMove = 0;
        int yMove = 0;
        switch (this.direction) {
            case 0:
                xMove = headDimensions; // direita
                break;
            case 90:
                yMove = headDimensions; // baixo
                break;
            case 180:
                xMove = -headDimensions; // esquerda
                break;
            case 270:
                yMove = -headDimensions; // cima
                break;
            default:
                break;
        }

        // Move o restante da cobra
        for (int i = snake.size() - 1; i > 0; i--) {
            // Obtém o quadrado anterior
            Square previous = snake.get(i - 1);
            // Move o quadrado atual para a posição do quadrado anterior
            snake.set(i, previous);
        }

        // Move a cabeça da cobra na nova posição
        Square head = snake.getFirst();
        snake.set(0, head.translacaoSemPonto(xMove, yMove));

        // Chama o método grow passando a posição da última cauda
        
    }

    public void grow() {
        // Calcula as coordenadas do novo quadrado com base na posição da última cauda
        int xNewSquare = (int)lastTailPosition.getX(); // Mantém a mesma coordenada X da última cauda
        int yNewSquare = (int)lastTailPosition.getY(); // Mantém a mesma coordenada Y da última cauda

        // Cria um novo quadrado com as coordenadas calculadas e o mesmo tamanho da
        // cabeça
        String newSquareCoordinates = xNewSquare + " " + yNewSquare + " " +
                (xNewSquare + headDimensions) + " " + yNewSquare + " " +
                (xNewSquare + headDimensions) + " " + (yNewSquare + headDimensions) + " " +
                xNewSquare + " " + (yNewSquare + headDimensions);

        // Adiciona o novo quadrado após o último quadrado da lista
        snake.add(new Square(newSquareCoordinates));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Square square : snake) {
            sb.append(square.toString()).append("\n");
        }
        return sb.toString();
    }

    public int getDirection() {
        return direction;
    }

}
