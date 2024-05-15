package UI;

import Core.Cell;
import Core.RasterizationStrategy;

import javax.swing.*;
import java.awt.*;

/**
 * Classe que representa a interface gráfica do usuário (UI) para renderização gráfica.
 */
public class GraphicalUI implements UI {
    private RasterizationStrategy rasterizationStrategy;
    private JFrame frame;
    private JPanel panel;

    public GraphicalUI(RasterizationStrategy rasterizationStrategy) {
        this.rasterizationStrategy = rasterizationStrategy;
        initializeUI();
    }

    private void initializeUI() {
        frame = new JFrame("POOSNAKE"); // Título da janela
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600); // Tamanho da janela

        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                render(g);
            }
        };

        frame.add(panel);
        frame.setVisible(true);
    }

    private void render(Graphics g) {
        Cell[][] grid = rasterizationStrategy.getGrid();
        int cellSize = 20; // Tamanho de cada célula

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                switch (grid[i][j]) {
                    case HEAD:
                        g.setColor(Color.RED);
                        break;
                    case TAIL:
                        g.setColor(Color.GREEN);
                        break;
                    case FOOD:
                        g.setColor(Color.YELLOW);
                        break;
                    case OBSTACLE:
                        g.setColor(Color.BLUE);
                        break;
                    default:
                        g.setColor(Color.WHITE);
                        break;
                }
                g.fillRect(i * cellSize, j * cellSize, cellSize, cellSize);
            }
        }
        
    }

    @Override
    public void render() {
        panel.repaint();
    }
}