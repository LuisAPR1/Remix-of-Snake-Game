package Core;

import java.util.Timer;
import java.util.TimerTask;

import Geometry.Ponto;

public class AutomaticMovementStrategy implements MovementStrategy {
    private Arena arena;
    private Timer timer;

    public AutomaticMovementStrategy(Arena arena) {
        this.arena = arena;
        this.timer = new Timer();
        startTimer();
    }

    private void startTimer() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                arena.getS().direction=calculateBestDirection(arena.getS().getDirection());

                arena.Frame();
            }
        }, 0, 1000); // Executa a cada 1 segundo (1000 milissegundos)
    }

    public int calculateBestDirection(int currentDirection) {
        // Obtém a posição da cabeça da cobra
        Ponto headPosition = arena.getS().getSnake().get(0).calcularCentro();

        // Obtém a posição da comida
        Ponto foodPosition = arena.getFruit().getShape().getPosition();

        // Calcula a distância entre a cabeça da cobra e a comida
        double distanceX = Math.abs(headPosition.getX() - foodPosition.getX());
        double distanceY = Math.abs(headPosition.getY() - foodPosition.getY());

        // Inicializa a melhor direção como a direção oposta à direção atual (para
        // garantir que seja alterada)
        int bestDirection = currentDirection;

        // Verifica se a comida está mais próxima no eixo X ou no eixo Y
        if (distanceX < distanceY) {
            // Se a comida estiver mais próxima no eixo X, mova-se horizontalmente (esquerda
            // ou direita)
            if (headPosition.getX() < foodPosition.getX()) {
                // Comida está à direita da cabeça da cobra
                if (currentDirection != 90) {
                    // Se a direção atual não for esquerda, mova-se para a direita
                    bestDirection = 270; // Direita
                } else {
                    // Se não for possível mover para a direita, mova-se verticalmente
                    if (headPosition.getY() < foodPosition.getY()) {
                        // Comida está abaixo da cabeça da cobra
                        bestDirection = 180; // Baixo
                    } else {
                        // Comida está acima da cabeça da cobra
                        bestDirection = 0; // Cima
                    }
                }
            } else {
                // Comida está à esquerda da cabeça da cobra
                if (currentDirection != 270) {
                    // Se a direção atual não for direita, mova-se para a esquerda
                    bestDirection = 90; // Esquerda
                } else {
                    // Se não for possível mover para a esquerda, mova-se verticalmente
                    if (headPosition.getY() < foodPosition.getY()) {
                        // Comida está abaixo da cabeça da cobra
                        bestDirection = 180; // Baixo
                    } else {
                        // Comida está acima da cabeça da cobra
                        bestDirection = 0; // Cima
                    }
                }
            }
        } else {
            // Se a comida estiver mais próxima no eixo Y, mova-se verticalmente (cima ou
            // baixo)
            if (headPosition.getY() < foodPosition.getY()) {
                // Comida está abaixo da cabeça da cobra
                if (currentDirection != 0) {
                    // Se a direção atual não for para cima, mova-se para baixo
                    bestDirection = 180; // Baixo
                } else {
                    // Se não for possível mover para baixo, mova-se horizontalmente
                    if (headPosition.getX() < foodPosition.getX()) {
                        // Comida está à direita da cabeça da cobra
                        bestDirection = 270; // Direita
                    } else {
                        // Comida está à esquerda da cabeça da cobra
                        bestDirection = 90; // Esquerda
                    }
                }
            } else {
                // Comida está acima da cabeça da cobra
                if (currentDirection != 180) {
                    // Se a direção atual não for para baixo, mova-se para cima
                    bestDirection = 0; // Cima
                } else {
                    // Se não for possível mover para cima, mova-se horizontalmente
                    if (headPosition.getX() < foodPosition.getX()) {
                        // Comida está à direita da cabeça da cobra
                        bestDirection = 270; // Direita
                    } else {
                        // Comida está à esquerda da cabeça da cobra
                        bestDirection = 90; // Esquerda
                    }
                }
            }
        }

        return bestDirection;
    }


    @Override
    public void input() {
        // Nada a ser feito aqui, pois o timer já está em execução
    }
}
