package Core;

public class AutomaticMovementStrategy implements MovementStrategy {
    @SuppressWarnings("unused")
    private Arena arena;

    public AutomaticMovementStrategy(Arena arena) {
        this.arena = arena;
    }

    @Override
    public void input() {
    }
}
