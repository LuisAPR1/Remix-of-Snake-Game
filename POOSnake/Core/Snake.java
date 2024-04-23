package Core;

import java.util.LinkedList;
import java.util.List;

import Geometry.Ponto;
import Geometry.Square;

public class Snake {
    private LinkedList<Square> snake = new LinkedList<>();
    int headDimensions;
    int direction;

    public Snake(Ponto starter, int headDimensions) {

        HeadInitializer(starter, headDimensions);
        
    }

    public void HeadInitializer(Ponto starter, int headDimensions) {
        this.headDimensions=headDimensions;
        String head = starter.getX() + " " + starter.getY() + " " +
                (starter.getX() + headDimensions) + " " + starter.getY() + " " +
                (starter.getX() + headDimensions) + " " + (starter.getY() + headDimensions) + " " +
                starter.getX() + " " + (starter.getY() + headDimensions);

        Square h = new Square(head);
        snake.addFirst(h);
    }

    public List<Ponto> getHeadCoordinates() {
        return snake.getFirst().getPontos();
    }

    public LinkedList<Square> getTailCoordinates() {
        return new LinkedList<>(snake.subList(1, snake.size()));
    }

    public void move(int direction) {
        this.direction = direction;

        // Calcula os deslocamentos horizontal e vertical com base na direção
        int xMove = 0;
        int yMove = 0;
        switch (direction) {
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

        snake.set(0, snake.get(0).translacaoSemPonto(xMove, yMove));
        //trocar todos os quadrados de sitio com um forech, deste modo, a cabeca mexe de posição, os restantes so avancam para a posicao seguinte
        //O primeiro T vai para o lugar da H e o resto dos T vai para o proximo T
    }

    public void grow() {
        //CORRIGIR
        //Square t = new Square(snake.getLast().getPontos());
        //snake.add(t);
    }

}
