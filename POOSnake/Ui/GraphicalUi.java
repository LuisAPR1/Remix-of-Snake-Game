package UI;

import Core.Cell;
import Core.MovementStrategy;
import Core.RasterizationStrategy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GraphicalUi implements UI {
    private JFrame frame;
    private JPanel panel;
    private RasterizationStrategy rasterizationStrategy;
    private MovementStrategy movementStrategy;
    private Timer timer;
    private boolean timerStarted = false;

    public GraphicalUi(RasterizationStrategy rasterizationStrategy, MovementStrategy movementStrategy) {
        this.rasterizationStrategy = rasterizationStrategy;
        this.movementStrategy = movementStrategy;
        initializeUI();
    }

    private void initializeUI() {
        frame = new JFrame("POOSNAKE");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(rasterizationStrategy.getArena().getArenaDimensions()[0] +100,
                rasterizationStrategy.getArena().getArenaDimensions()[1] +100);

        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                renderGraphics(g);
            }
        };

        frame.add(panel);
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                handleKeyPress(e);
            }
        });

        frame.setVisible(true);
    }

    private void handleKeyPress(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
            if (!timerStarted) {
                startPeriodicTask();
                timerStarted = true;
            }
                movementStrategy.setDirectionG(270);
                break;
            case KeyEvent.VK_A:
            if (!timerStarted) {
                startPeriodicTask();
                timerStarted = true;
            }
                movementStrategy.setDirectionG(180);
                break;
            case KeyEvent.VK_S:
            if (!timerStarted) {
                startPeriodicTask();
                timerStarted = true;
            }
                movementStrategy.setDirectionG(90);
                break;
            case KeyEvent.VK_D:
            if (!timerStarted) {
                startPeriodicTask();
                timerStarted = true;
            }
                movementStrategy.setDirectionG(0);
                break;
            case KeyEvent.VK_E:
                
                break;
            default:
                movementStrategy.setDirectionG(1);
                break;
        }
    }

    private void startPeriodicTask() {
        timer = new Timer(300, new ActionListener() { // 500 milliseconds = 0.5 seconds
            @Override
            public void actionPerformed(ActionEvent e) {
                performPeriodicTask();
            }
        });
        timer.start();
    }

    private void performPeriodicTask() {
        movementStrategy.move();
    }

    private void renderGraphics(Graphics g) {
        Cell[][] grid = rasterizationStrategy.getGrid();
        int numRows = grid.length;
        int numCols = grid[0].length;
        int squareSize = Math.min(panel.getWidth() / numRows, panel.getHeight() / numCols);

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                Cell cell = grid[i][j];
                Color color = getColorForCell(cell);
                g.setColor(color);
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
