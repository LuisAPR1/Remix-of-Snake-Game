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

        // Obtém a direção atual da cobra
        int currentDirection = arena.getS().getDirection();

        switch (input) {
            case "w":
                // Se a direção atual não for para baixo, permita a mudança para cima
                if (currentDirection != 0) {
                    arena.getS().setDirection(180);
                    arena.Frame();
                }
                break;
            case "a":
                // Se a direção atual não for para a direita, permita a mudança para a esquerda
                if (currentDirection != 90) {
                    arena.getS().setDirection(270);
                    arena.Frame();
                }
                break;
            case "s":
                // Se a direção atual não for para cima, permita a mudança para baixo
                if (currentDirection != 180) {
                    arena.getS().setDirection(0);
                    arena.Frame();
                }
                break;
            case "d":
                // Se a direção atual não for para a esquerda, permita a mudança para a direita
                if (currentDirection != 270) {
                    arena.getS().setDirection(90);
                    arena.Frame();
                }
                break;
            default:
                break;
        }
    }
}
