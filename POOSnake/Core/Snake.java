package Core;

import java.util.LinkedList;

import Geometry.Ponto;
import Geometry.Square;

public class Snake {
    private LinkedList<Square> snake = new LinkedList<>();

    public LinkedList<Square> getSnake() {
        return snake;
    }

    public void setSnake(LinkedList<Square> snake) {
        this.snake = snake;
    }

    int headDimensions;
    int direction;

    public Snake(Arena a, int headDimensions) {

        this.headDimensions=headDimensions;
        HeadInitializer(a.getArenaDimensions(), headDimensions);

    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void HeadInitializer(int[] arenaDimensions, int headDimensions) {

        Ponto starter = new Ponto(Math.random() % arenaDimensions[0], Math.random() % arenaDimensions[1]);
        
        String head = starter.getX() + " " + starter.getY() + " " +
                (starter.getX() + headDimensions) + " " + starter.getY() + " " +
                (starter.getX() + headDimensions) + " " + (starter.getY() + headDimensions) + " " +
                starter.getX() + " " + (starter.getY() + headDimensions);

        Square h = new Square(head);
        snake.addFirst(h);
    }

    public Square getHead() {
        return snake.getFirst();
    }

    public LinkedList<Square> getTailCoordinates() {
        return new LinkedList<>(snake.subList(1, snake.size()));
    }

    public void move() {
        // Calcula os deslocamentos horizontal e vertical com base na direção
        int xMove = 0;
        int yMove = 0;
        switch (this.direction) {
            case 0:
                xMove = 1; // direita
                break;
            case 90:
                yMove = 1; // baixo
                break;
            case 180:
                xMove = -1; // esquerda
                break;
            case 270:
                yMove = -1; // cima
                break;
            default:
                break;
        }
    
        // Move a cabeça da cobra na nova posição
        Square head = snake.getFirst();
        snake.set(0, head.translacaoSemPonto(xMove, yMove));
    
        // Move o restante da cobra
        for (int i = 1; i < snake.size(); i++) {
            // Obtém o quadrado atual da cobra
            Square current = snake.get(i);
            // Move o quadrado atual para a posição do quadrado anterior
            snake.set(i, head);
            // Atualiza o quadrado anterior para o quadrado atual, para ser usado na próxima iteração
            head = current;
        }
    }
    
    

    public void grow() {
        // CORRIGIR
        // Square t = new Square(snake.getLast().getPontos());
        // snake.add(t);
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
