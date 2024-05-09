package Core;

import java.util.Timer;
import java.util.TimerTask;

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
                arena.getS().direction=arena.calculateBestDirection(arena.getS().getDirection());

                arena.Frame();
            }
        }, 0, 1000); // Executa a cada 1 segundo (1000 milissegundos)
    }

    @Override
    public void input() {
        // Nada a ser feito aqui, pois o timer já está em execução
    }
}
