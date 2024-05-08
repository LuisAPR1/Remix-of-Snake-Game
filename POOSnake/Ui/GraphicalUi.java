package UI;

import Core.RasterizationStrategy;

public class GraphicalUI implements UI {
    private RasterizationStrategy rasterizationStrategy;

    public GraphicalUI(RasterizationStrategy rasterizationStrategy) {
        this.rasterizationStrategy = rasterizationStrategy;
    }

    @Override
    public void render() {
        rasterizationStrategy.render();
        // Implementação adicional para renderização gráfica
    }

    @Override
    public void getInput() {
        throw new UnsupportedOperationException("Unimplemented method 'getInput'");
    }
}