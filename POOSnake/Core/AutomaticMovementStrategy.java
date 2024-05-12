package Core;

import java.util.Timer;
import java.util.TimerTask;

import Geometry.Ponto;

/**
 * Estratégia de movimento automático para a cobra no jogo.
 * 
 * Esta estratégia permite que a cobra se mova automaticamente em direção à
 * comida mais próxima, evitando as bordas da arena, obstáculos e a própria cobra.
 * 
 * @version Versão 1.1 11/05/2024
 * @author Luís Rosa, José Lima, Pedro Ferreira e Pedro Ferreira
 */
public class AutomaticMovementStrategy implements MovementStrategy {
    private Arena arena;
    private Timer timer;

    /**
     * Construtor para criar uma estratégia de movimento automático para a cobra.
     * 
     * @param arena A arena em que a cobra está se movendo.
     */
    public AutomaticMovementStrategy(Arena arena) {
        this.arena = arena;
        this.timer = new Timer();
        startTimer();
    }

    /**
     * Inicia o temporizador para atualizar o movimento da cobra periodicamente.
     */
    private void startTimer() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // Calcula a melhor direção para a cobra se mover
                int bestDirection = calculateBestDirection(arena.getS().getDirection());

                // Define a direção da cobra como a melhor direção calculada
                arena.getS().setDirection(bestDirection);
                // Atualiza o frame da arena
                arena.Frame();
            }
        }, 0, 1000); // Executa a cada 1 segundo (1000 milissegundos)
    }

    /**
     * Calcula a melhor direção para a cobra se mover com base na posição da comida,
     * evitando as bordas da arena, obstáculos e a própria cobra.
     * 
     * @param currentDirection A direção atual da cobra.
     * @return A melhor direção para a cobra se mover.
     */
    public int calculateBestDirection(int currentDirection) {
        Ponto headPosition = arena.getS().getSnake().get(0).calcularCentro();
        Ponto foodPosition = arena.getFruit().getShape().getPosition();
    
        int bestDirection = currentDirection;
    
        double distanceX = Math.abs(headPosition.getX() - foodPosition.getX());
        double distanceY = Math.abs(headPosition.getY() - foodPosition.getY());
    
        if (distanceX < distanceY) {
            // Prioriza o movimento horizontal em direção à comida
            if (headPosition.getX() < foodPosition.getX()) {
                if (currentDirection != 270 && !isCollision(headPosition, 270)) {
                    bestDirection = 90; // Direita
                }
            } else {
                if (currentDirection != 90 && !isCollision(headPosition, 90)) {
                    bestDirection = 270; // Esquerda
                }
            }
        } else {
            // Prioriza o movimento vertical em direção à comida
            if (headPosition.getY() < foodPosition.getY()) {
                if (currentDirection != 180 && !isCollision(headPosition, 180)) {
                    bestDirection = 0; // Baixo
                }
            } else {
                if (currentDirection != 0 && !isCollision(headPosition, 0)) {
                    bestDirection = 180; // Cima
                }
            }
        }
    
        // Evitar bordas da arena
        if (headPosition.getX() == 0 && bestDirection == 270) {
            bestDirection = 0; // Evita a borda esquerda, indo para cima
        } else if (headPosition.getX() == arena.getArenaDimensions()[0] - 1 && bestDirection == 90) {
            bestDirection = 180; // Evita a borda direita, indo para baixo
        } else if (headPosition.getY() == 0 && bestDirection == 0) {
            bestDirection = 270; // Evita a borda superior, indo para a esquerda
        } else if (headPosition.getY() == arena.getArenaDimensions()[1] - 1 && bestDirection == 180) {
            bestDirection = 90; // Evita a borda inferior, indo para a direita
        }
    
        return bestDirection;
    }
    
    // Método para verificar colisão em uma direção específica
    private boolean isCollision(Ponto headPosition, int direction) {
        // Calcula a próxima posição da cabeça da cobra na direção especificada
        Ponto nextPosition = calculateNextPosition(headPosition, direction);
        // Verifica se a próxima posição é uma colisão (com obstáculos ou com a própria cobra)
        return isObstacle(nextPosition) || isSnakeCollision(nextPosition);
    }

    // Método para calcular a próxima posição da cabeça da cobra em uma direção específica
    private Ponto calculateNextPosition(Ponto currentPosition, int direction) {
        switch (direction) {
            case 0: // Cima
                return new Ponto(currentPosition.getX(), currentPosition.getY() - 1);
            case 90: // Direita
                return new Ponto(currentPosition.getX() + 1, currentPosition.getY());
            case 180: // Baixo
                return new Ponto(currentPosition.getX(), currentPosition.getY() + 1);
            case 270: // Esquerda
                return new Ponto(currentPosition.getX() - 1, currentPosition.getY());
            default:
                return currentPosition;
        }
    }

    // Método para verificar se uma posição contém um obstáculo
    private boolean isObstacle(Ponto position) {
        // Implemente a lógica para verificar se a posição contém um obstáculo
        return false; // Exemplo: sempre retorna falso por enquanto
    }

    // Método para verificar se uma posição está em colisão com a própria cobra
    private boolean isSnakeCollision(Ponto position) {
        // Implemente a lógica para verificar se a posição está em colisão com a própria cobra
        return false; // Exemplo: sempre retorna falso por enquanto
    }

    /**
     * Não Utilizada na estratégia automática
     * 
     */
    @Override
    public void input() {
        // Entrada não é necessária na estratégia de movimento automático
    }
}
