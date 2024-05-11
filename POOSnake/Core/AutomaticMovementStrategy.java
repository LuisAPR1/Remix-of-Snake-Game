package Core;

import java.util.Timer;
import java.util.TimerTask;

import Geometry.Ponto;

/**
 * Estratégia de movimento automático para a cobra no jogo.
 * 
 * Esta estratégia permite que a cobra se mova automaticamente em direção à
 * comida mais próxima.
 * 
 * @version Versão 1.0 10/05/2024
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

                // Verifica se há colisão da cobra consigo mesma
                if (arena.getS().checkSnakeSelfCollision() && (bestDirection != 0 && bestDirection != 180)) {
                    bestDirection = 90; // Muda para baixo
                } else if (arena.getS().checkSnakeSelfCollision() && (bestDirection != 90 && bestDirection != 270)) {
                    bestDirection = 0; // Muda para cima
                }

                // Define a direção da cobra como a melhor direção calculada
                arena.getS().setDirection(bestDirection);
                // Atualiza o frame da arena
                arena.Frame();
            }
        }, 0, 1000); // Executa a cada 1 segundo (1000 milissegundos)
    }

    /**
     * Calcula a melhor direção para a cobra se mover com base na posição da comida.
     * 
     * @param currentDirection A direção atual da cobra.
     * @return A melhor direção para a cobra se mover.
     */
    public int calculateBestDirection(int currentDirection) {
        // Obtém a posição da cabeça da cobra
        Ponto headPosition = arena.getS().getSnake().get(0).calcularCentro();
        // Obtém a posição da comida
        Ponto foodPosition = arena.getFruit().getShape().getPosition();

        // Calcula a distância horizontal e vertical entre a cabeça da cobra e a comida
        double distanceX = Math.abs(headPosition.getX() - foodPosition.getX());
        double distanceY = Math.abs(headPosition.getY() - foodPosition.getY());

        // Inicializa a melhor direção como a direção atual da cobra
        int bestDirection = currentDirection;

        // Verifica se a distância horizontal é menor do que a distância vertical
        if (distanceX < distanceY) {
            // Move-se horizontalmente (esquerda ou direita)
            if (headPosition.getX() < foodPosition.getX()) {
                // Comida está à direita da cabeça da cobra
                if (currentDirection != 90) {
                    // Se a direção atual não for esquerda, muda para a direita
                    bestDirection = 270; // Direita
                }
            } else {
                // Comida está à esquerda da cabeça da cobra
                if (currentDirection != 270) {
                    // Se a direção atual não for direita, muda para a esquerda
                    bestDirection = 90; // Esquerda
                }
            }
        } else {
            // Move-se verticalmente (cima ou baixo)
            if (headPosition.getY() < foodPosition.getY()) {
                // Comida está abaixo da cabeça da cobra
                if (currentDirection != 0) {
                    // Se a direção atual não for para cima, muda para baixo
                    bestDirection = 180; // Baixo
                }
            } else {
                // Comida está acima da cabeça da cobra
                if (currentDirection != 180) {
                    // Se a direção atual não for para baixo, muda para cima
                    bestDirection = 0; // Cima
                }
            }
        }

        return bestDirection;
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
