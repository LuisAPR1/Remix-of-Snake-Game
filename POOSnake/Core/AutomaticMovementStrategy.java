package Core;

import java.util.Timer;
import java.util.TimerTask;

public class AutomaticMovementStrategy implements MovementStrategy {
    private Arena arena;
    private Timer timer;
    int counter;

    public AutomaticMovementStrategy(Arena arena) {
        this.arena = arena;
        this.timer = new Timer();
        startTimer();
    }

    private void startTimer() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                arena.Frame();
                arena.calculateBestDirection();
                System.out.println("AAAAAAAA" + counter++);
            }
        }, 0, 1000); // Executa a cada 1 segundo (1000 milissegundos)
    }

    @Override
    public void input() {
       
    }
}
                

