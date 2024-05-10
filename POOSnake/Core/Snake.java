package Core;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
        // Determina um fator de deslocamento aleatório relativo ao tamanho da cabeça
        int offsetFactorX = (int) (Math.random() * (arenaDimensions[0] / headDimensions - 2)) + 1;
        int offsetFactorY = (int) (Math.random() * (arenaDimensions[1] / headDimensions - 2)) + 1;
    
        // Calcula as coordenadas da cabeça da cobra baseadas no fator de deslocamento
        int headX = offsetFactorX * headDimensions;
        int headY = offsetFactorY * headDimensions;
    
        // Monta a representação da cabeça como uma string
        String headRepresentation = headX + " " + headY + " " +
                (headX + headDimensions) + " " + headY + " " +
                (headX + headDimensions) + " " + (headY + headDimensions) + " " +
                headX + " " + (headY + headDimensions);
    
        // Cria o objeto Square representando a cabeça e adiciona à lista de quadrados da cobra
        Square h = new Square(headRepresentation);
        snake.addFirst(h);
    
        // Define uma direção inicial aleatória para a cobra (em graus)
        this.direction = (int) (Math.random() * 360);
    }
    

    public boolean checkSnakeSelfCollision() {
        // Obtém os quadrados da cobra

        // Obtém a cabeça da cobra (primeiro quadrado)

        // Verifica se a cabeça da cobra intersecta com algum outro quadrado do corpo
        for (int i = 1; i < snake.size(); i++) {
            if (snake.get(0).intersect2(snake.get(i))) {
                // Se houver interseção, significa que a cabeça da cobra bateu em alguma parte

                return true;
            }

            // Verifica se os 4 pontos da cabeça são iguais aos 4 pontos de qualquer outra
            // parte do corpo
            Square bodyPart = snake.get(i);
            List<Ponto> headPoints = snake.get(0).getPontos();
            List<Ponto> bodyPartPoints = bodyPart.getPontos();

            if (headPoints.containsAll(bodyPartPoints) && bodyPartPoints.containsAll(headPoints)) {
                // Se todos os pontos são iguais, houve colisão

                return true;
            }
        }

        // Se não houve interseção, significa que não houve colisão com o próprio corpo
        return false;
    }

    public boolean checkSnakeInsideArena(int[] arenaDimensions) {
        // Obtém os quadrados da cobra

        // Obtém as dimensões da arena
        int arenaWidth = arenaDimensions[0];
        int arenaHeight = arenaDimensions[1];

        // Itera sobre os quadrados da cobra
        for (Square square : snake) {
            // Obtém os pontos do quadrado
            List<Ponto> squareCoordinates = square.getAllCoordinates();

            // Verifica cada ponto do quadrado
            for (Ponto point : squareCoordinates) {
                // Verifica se o ponto está fora dos limites da arena
                if (point.getX() < 0 || point.getX() > arenaWidth || point.getY() < 0
                        || point.getY() > arenaHeight) {
                    // Se algum ponto estiver fora dos limites, a cobra saiu da arena
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkSnakeObstacleColision(Snake s, ArrayList<Obstacle> obstacles) {
        // Obtém os quadrados da cobra
        List<Square> squares = s.getSnake();

        // Itera sobre os quadrados da cobra
        for (Square square : squares) {
            // Verifica se algum quadrado intersecta os polígonos dos obstáculos
            for (Obstacle obstacle : obstacles) {

                if (square.intersect(obstacle.getObstacle()) || square.contains(obstacle.getObstacle())
                        || square.distance(obstacle.getObstacle())) {
                    // Se houver interseção, a cobra colidiu com um obstáculo
                    System.out.println("Colisao snake com objeto");

                    // Retorna verdadeiro indicando que houve colisão
                    return true;
                }
            }
        }

        // Se não houve colisão, retorna falso
        return false;
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
        int xNewSquare = (int) lastTailPosition.getX(); // Mantém a mesma coordenada X da última cauda
        int yNewSquare = (int) lastTailPosition.getY(); // Mantém a mesma coordenada Y da última cauda

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