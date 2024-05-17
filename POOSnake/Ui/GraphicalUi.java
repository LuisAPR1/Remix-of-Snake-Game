package UI;
import Core.Cell;
import Core.RasterizationStrategy;
import javafx.scene.input.KeyEvent;

import javax.swing.*;
import java.awt.*;


public class GraphicalUi implements UI {

    private JFrame frame;
    private JPanel panel;
    private RasterizationStrategy rasterizationStrategy;

    public GraphicalUi(RasterizationStrategy rasterizationStrategy) {
        this.rasterizationStrategy = rasterizationStrategy;
        initializeUI();
        
    }

    private void initializeUI() {
        frame = new JFrame("POOSNAKE");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(rasterizationStrategy.getArena().getArenaDimensions()[0]+50,rasterizationStrategy.getArena().getArenaDimensions()[1]+50);
        

        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                renderGraphics(g);
            }
        };

        frame.add(panel);
        frame.setVisible(true);
    }

    private void renderGraphics(Graphics g) {

        Cell[][] grid = rasterizationStrategy.getGrid();

        int numRows = grid.length;
        int numCols = grid[0].length;
        

        // Calcular o tamanho dos quadrados
        int squareSize = Math.min(panel.getWidth() / numRows, panel.getHeight() / numCols);

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                Cell cell = grid[j][i];
                Color color = getColorForCell(cell);
                g.setColor(color);
                // Desenhar o quadrado na posição correta
                g.fillRect(i * squareSize, j * squareSize, squareSize, squareSize);
            }
        }
    }

    private Color getColorForCell(Cell cell) {
        switch (cell) {
            case HEAD:
                return Color.RED;
            case TAIL:
                return Color.GREEN;
            case BOTH:
                return Color.ORANGE;
            case FOOD:
                return Color.YELLOW;
            case OBSTACLE:
                return Color.BLACK;
            default:
                return Color.WHITE;
        }
    }

    
    @Override
    public void render() {
        rasterizationStrategy.render();
        panel.repaint();
    }
}
