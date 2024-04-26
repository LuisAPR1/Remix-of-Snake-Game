package Core;

public class MovementFactory {
    public static MovementStrategy getMovementStrategy(String type, int direction) {
        if (type.equals("automatic")) {
            return new AutomaticMovementStrategy();
        } else if (type.equals("manual")) {
            return new ManualMovementStrategy(direction);
        }
        throw new IllegalArgumentException("Unknown movement strategy type");
    }
}