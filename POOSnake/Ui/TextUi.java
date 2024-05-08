package UI;

import Core.RasterizationStrategy;

public class TextUI implements UI {
    private RasterizationStrategy rasterizationStrategy;

    public TextUI(RasterizationStrategy rasterizationStrategy) {
        this.rasterizationStrategy = rasterizationStrategy;
    }

    @Override
    public void render() {
        rasterizationStrategy.render();
        System.out.println(toString());
        System.out.println();
        // Implementação adicional para renderização textual
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rasterizationStrategy.getGrid().length; i++) {
            for (int j = 0; j < rasterizationStrategy.getGrid()[i].length; j++) {
                sb.append(rasterizationStrategy.getGrid()[i][j].getSymbol()).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public void getInput() {
       
    }
}
