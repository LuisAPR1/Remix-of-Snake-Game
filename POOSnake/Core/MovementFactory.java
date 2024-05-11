package Core;

import java.util.Scanner;

/**
 * Fábrica para criar instâncias de diferentes estratégias de movimento.
 * 
 * @version Versão 1.0 10/05/2024
 * @author Luís Rosa, José Lima, Pedro Ferreira e Pedro Ferreira
 */
public class MovementFactory {
    
    /**
     * Método estático para obter uma estratégia de movimento com base no tipo especificado.
     * 
     * @param type    O tipo de estratégia de movimento ("automatic" para movimento automático, "manual" para movimento manual).
     * @param scanner O scanner para entrada do usuário (usado apenas para movimento manual).
     * @param arena   A arena do jogo.
     * @return A estratégia de movimento criada.
     * @throws IllegalArgumentException Se o tipo de estratégia de movimento for desconhecido.
     */
    public static MovementStrategy getMovementStrategy(String type, Scanner scanner, Arena arena) {
        if (type.equals("automatic")) {
            return new AutomaticMovementStrategy(arena);
        } else if (type.equals("manual")) {
            return new ManualMovementStrategy(scanner, arena);
        }
        throw new IllegalArgumentException("Unknown movement strategy type");
    }
}
