package UI;

import Core.RasterizationStrategy;

public class UIFactory {
    public static UI createUI(char type, RasterizationStrategy rasterizationStrategy) {
        if (Character.toLowerCase(type) == 't') {
            return new TextUI(rasterizationStrategy);
        } else if (Character.toLowerCase(type) == 'g') {
            return new GraphicalUI(rasterizationStrategy);
        } else {
            throw new IllegalArgumentException("Tipo de UI inválido.");
        }
    }
}
