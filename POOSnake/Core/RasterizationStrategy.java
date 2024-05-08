package Core;

public interface RasterizationStrategy {
    Cell[][] getGrid();
   
    void render();

    
}
