package Core;

import java.util.Scanner;

/**
 * Estratégia de movimento manual, onde o jogador pode controlar a direção da cobra usando as teclas W, A, S e D.
 * 
 * @version Versão 1.0 10/05/2024
 * @author Luís Rosa, José Lima, Pedro Ferreira e Pedro Ferreira
 */
public class ManualMovementStrategy implements MovementStrategy {
    private Scanner scanner;
    private Arena arena;

    /**
     * Construtor para criar uma instância de `ManualMovementStrategy`.
     * 
     * @param scanner O scanner para entrada do usuário.
     * @param arena   A arena do jogo.
     */
    public ManualMovementStrategy(Scanner scanner, Arena arena) {
        this.scanner = scanner;
        this.arena = arena;
    }

    /**
     * Método para receber a entrada do usuário e ajustar a direção da cobra de acordo com a entrada.
     */
    @Override
    public void input() {

        System.out.println("Dir H: " + arena.getS().getDirection());
        System.out.println("Pontos: " + arena.points);

        System.out.println("Digite uma direção (w, a, s ou d) ou pressione Enter para manter a direção");
        String input = scanner.nextLine().toLowerCase();
    
        if (input.isEmpty()) {
            arena.Frame();
        }
    
        // Obtém a direção atual da cobra
        int currentDirection = arena.getS().getDirection();

        // Processa a entrada do usuário e ajusta a direção da cobra conforme necessário
        switch (input) {
            case "w":
                // Se a direção atual não for para baixo, permite a mudança para cima
                if (currentDirection != 0) {
                    arena.getS().setDirection(180);
                    arena.Frame();
                }
                break;
            case "a":
                // Se a direção atual não for para a direita, permite a mudança para a esquerda
                if (currentDirection != 90) {
                    arena.getS().setDirection(270);
                    arena.Frame();
                }
                break;
            case "s":
                // Se a direção atual não for para cima, permite a mudança para baixo
                if (currentDirection != 180) {
                    arena.getS().setDirection(0);
                    arena.Frame();
                }
                break;
            case "d":
                // Se a direção atual não for para a esquerda, permite a mudança para a direita
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
