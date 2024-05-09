package Core;

import java.util.Scanner;

public class MovementFactory {
    public static MovementStrategy getMovementStrategy(String type, Scanner scanner, Arena arena) {
        if (type.equals("automatic")) {
            return new AutomaticMovementStrategy(arena);
        } else if (type.equals("manual")) {
            return new ManualMovementStrategy(scanner, arena);
        }
        throw new IllegalArgumentException("Unknown movement strategy type");
    }
}
