package Core;

import java.util.Scanner;

public class ManualMovementStrategy implements MovementStrategy {
    private Scanner scanner;
    private Arena arena;

    public ManualMovementStrategy(Scanner scanner, Arena arena) {
        this.scanner = scanner;
        this.arena = arena;
    }

    @Override
    public void input() {
        System.out.println("Digite uma direção (w, a, s ou d):");
        String input = scanner.nextLine().toLowerCase();

        switch (input) {
            case "w":
                arena.getS().setDirection(180);
                arena.Frame();
                break;
            case "a":
                arena.getS().setDirection(270);
                arena.Frame();
                break;
            case "s":
                arena.getS().setDirection(0);
                arena.Frame();
                break;
            case "d":
                arena.getS().setDirection(90);
                arena.Frame();
                break;
            default:
                break;
        }
    }

}
