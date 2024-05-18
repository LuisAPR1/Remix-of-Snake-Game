package Core;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import Geometry.Poligono;
import Geometry.Ponto;
import Geometry.Square;

/**
 * Classe que representa uma cobra no jogo Snake.
 * 
 * A cobra é composta por uma lista encadeada de quadrados, onde cada quadrado
 * representa uma parte do corpo da cobra.
 * 
 * Esta classe é responsável por gerenciar o movimento da cobra, detectar
 * colisões
 * e crescer quando come um alimento.
 * 
 * @author Luís Rosa, José Lima, Pedro Ferreira, José Lima, Pedro Ferreira
 * @version Versão 1.0 10/05/2024
 */
public class Snake {
    private LinkedList<Square> snake = new LinkedList<>();
    int headDimensions;
    int direction;
    Ponto lastTailPosition;

    /**
     * Obtém a lista encadeada de quadrados que representam a cobra.
     * 
     * @return A lista encadeada de quadrados que representam a cobra.
     */
    public LinkedList<Square> getSnake() {
        return snake;
    }

    /**
     * Define a lista encadeada de quadrados que representam a cobra.
     * 
     * @param snake A lista encadeada de quadrados que representam a cobra.
     */
    public void setSnake(LinkedList<Square> snake) {
        this.snake = snake;
    }

    /**
     * Construtor para criar uma cobra dentro da arena com dimensões especificadas.
     * 
     * @param arenaDimensions As dimensões da arena onde a cobra irá se mover.
     * @param headDimensions  As dimensões da cabeça da cobra.
     */
    public Snake(int[] arenaDimensions, int headDimensions) {
        this.headDimensions = headDimensions;
        HeadInitializer(arenaDimensions, headDimensions);
    }

    /**
     * Define a direção da cobra.
     * 
     * @param direction A direção da cobra em graus (0 a 359).
     */
    public void setDirection(int direction) {
        this.direction = direction;
    }

    /**
     * Inicializa a cabeça da cobra dentro da arena.
     * 
     * @param arenaDimensions As dimensões da arena onde a cobra irá se mover.
     * @param headDimensions  As dimensões da cabeça da cobra.
     */
    public void HeadInitializer(int[] arenaDimensions, int headDimensions) {
        // Calcula as coordenadas X e Y da cabeça da cobra de forma aleatória dentro da
        // arena
        int headX = (int) (Math.random() * (arenaDimensions[0] - headDimensions));
        int headY = (int) (Math.random() * (arenaDimensions[1] - headDimensions));

        // Ajusta as coordenadas X e Y para garantir que a cabeça da cobra esteja
        // próxima ao canto da arena
        headX -= headX % headDimensions;
        headY -= headY % headDimensions;

        // Monta a representação da cabeça como uma string
        String headRepresentation = headX + " " + headY + " " +
                (headX + headDimensions) + " " + headY + " " +
                (headX + headDimensions) + " " + (headY + headDimensions) + " " +
                headX + " " + (headY + headDimensions);

        // Cria o objeto Square representando a cabeça e adiciona à lista de quadrados
        // da cobra
        Square h = new Square(headRepresentation);
        snake.addFirst(h);

        // Define uma direção inicial aleatória para a cobra (em graus)
        this.direction = (int) (Math.random() * 360);
    }

    /**
     * Verifica se a cobra comeu o alimento.
     * 
     * @param fruit O alimento a ser verificado.
     * @return true se a cobra comeu o alimento, caso contrário false.
     */
    public boolean CheckFoodEaten(AbstractFood<?> fruit) {
        Poligono square;
        if (snake.size() >= 2) {
            // Se a cobra tem pelo menos dois quadrados, combina os quadrados 0 e 1
            Poligono square0 = snake.get(0);
            Poligono square1 = snake.get(1);
            square = square0.combine(square1);
        } else {
            // Se a cobra tem apenas um quadrado, usa apenas esse quadrado
            square = snake.get(0);
        }

        // Verifica se a comida está contida no quadrado
        boolean isContained = fruit.isContainedIn(square);

        // Retorna true se a comida foi comida, caso contrário false
        return isContained;
    }

    /**
     * Verifica se houve colisão da cobra com o próprio corpo.
     * 
     * @return true se houve colisão, caso contrário false.
     */
    public boolean checkSnakeSelfCollision() {
        // Itera sobre os quadrados do corpo da cobra
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

    /**
     * Verifica se a cobra saiu da arena.
     * 
     * @param arenaDimensions As dimensões da arena.
     * @return true se a cobra saiu da arena, caso contrário false.
     */
    public boolean checkSnakeInsideArena(int[] arenaDimensions) {
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

    /**
     * Verifica se a cobra colidiu com algum obstáculo.
     * 
     * @param s         A cobra.
     * @param obstacles A lista de obstáculos.
     * @return true se houve colisão com algum obstáculo, caso contrário false.
     */
    public boolean checkSnakeObstacleColision(Snake s, ArrayList<Obstacle> obstacles) {
        // Obtém os quadrados da cobra
        List<Square> squares = s.getSnake();

        // Itera sobre os quadrados da cobra
        for (Square square : squares) {
            // Verifica se algum quadrado intersecta os polígonos dos obstáculos
            for (Obstacle obstacle : obstacles) {
                if (square.contains(obstacle.getObstacle()) || square.distance(obstacle.getObstacle())) {
                    // Se houver interseção, a cobra colidiu com um obstáculo
                    return true;
                }
            }
        }

        // Se não houve colisão, retorna false
        return false;
    }

    /**
     * Obtém a cabeça da cobra.
     * 
     * @return A cabeça da cobra.
     */
    public Square getHead() {
        return snake.getFirst();
    }

    /**
     * Obtém as coordenadas da cauda da cobra.
     * 
     * @return A lista encadeada de quadrados representando a cauda da cobra.
     */
    public LinkedList<Square> getTailCoordinates() {
        return new LinkedList<>(snake.subList(1, snake.size()));
    }

    /**
     * Move a cobra em direção à direção atual.
     */
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

    }

    /**
     * Faz a cobra crescer adicionando um novo quadrado à cauda.
     */
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

    /**
     * Retorna uma representação da cobra em forma de string.
     * 
     * @return Uma representação da cobra em forma de string.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Square square : snake) {
            sb.append(square.toString()).append("\n");
        }
        return sb.toString();
    }

    /**
     * Obtém a direção atual da cobra.
     * 
     * @return A direção atual da cobra em graus.
     */
    public int getDirection() {
        return direction;
    }

}
