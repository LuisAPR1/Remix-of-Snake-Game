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
                int bestDirection = calculateBestDirection(arena.getS().getDirection());
                if (arena.getS().checkSnakeSelfCollision() && (bestDirection != 0 && bestDirection != 180)) {
                    bestDirection = 90; // Mudar para baixo
                } else if (arena.getS().checkSnakeSelfCollision() && (bestDirection != 90 && bestDirection != 270)) {
                    bestDirection = 0; // Mudar para cima
                }
                
                arena.getS().setDirection(bestDirection);
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

        // Inicializa a melhor direção como a direção atual
        int bestDirection = currentDirection;
if (distanceX < distanceY) {
    // Mova-se horizontalmente (esquerda ou direita)
    if (headPosition.getX() < foodPosition.getX()) {
        // Comida está à direita da cabeça da cobra
        if (currentDirection != 90) {
            // Se a direção atual não for esquerda, mova-se para a direita
            bestDirection = 270; // Direita
        }
    } else {
        // Comida está à esquerda da cabeça da cobra
        if (currentDirection != 270) {
            // Se a direção atual não for direita, mova-se para a esquerda
            bestDirection = 90; // Esquerda
        }
    }
} else {
    // Mova-se verticalmente (cima ou baixo)
    if (headPosition.getY() < foodPosition.getY()) {
        // Comida está abaixo da cabeça da cobra
        if (currentDirection != 0) {
            // Se a direção atual não for para cima, mova-se para baixo
            bestDirection = 180; // Baixo
        }
    } else {
        // Comida está acima da cabeça da cobra
        if (currentDirection != 180) {
            // Se a direção atual não for para baixo, mova-se para cima
            bestDirection = 0; // Cima
        }
    }
}

if (distanceX < distanceY) {
    // Mova-se horizontalmente (esquerda ou direita)
    if (headPosition.getX() < foodPosition.getX()) {
        // Comida está à direita da cabeça da cobra
        if (currentDirection != 90) {
            // Se a direção atual não for esquerda, mova-se para a direita
            bestDirection = 270; // Direita
        }
    } else {
        // Comida está à esquerda da cabeça da cobra
        if (currentDirection != 270) {
            // Se a direção atual não for direita, mova-se para a esquerda
            bestDirection = 90; // Esquerda
        }
    }
} else {
    // Mova-se verticalmente (cima ou baixo)
    if (headPosition.getY() < foodPosition.getY()) {
        // Comida está abaixo da cabeça da cobra
        if (currentDirection != 0) {
            // Se a direção atual não for para cima, mova-se para baixo
            bestDirection = 180; // Baixo
        }
    } else {
        // Comida está acima da cabeça da cobra
        if (currentDirection != 180) {
            // Se a direção atual não for para baixo, mova-se para cima
            bestDirection = 0; // Cima
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
