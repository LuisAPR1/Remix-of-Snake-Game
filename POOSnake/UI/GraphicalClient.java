package UI;

import javax.swing.*;
import Core.Arena;
import Core.FoodType;
import Core.Obstacle.ObstacleType;
import Core.RasterizationType;
import Geometry.Ponto;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class GraphicalClient extends JFrame {
    private JRadioButton autoModeButton;
    private JRadioButton manualModeButton;
    private ButtonGroup gameModeGroup;
    private JRadioButton filledRasterButton;
    private JRadioButton outlinedRasterButton;
    private ButtonGroup rasterizationTypeGroup;
    private JRadioButton circleFoodButton;
    private JRadioButton squareFoodButton;
    private ButtonGroup foodTypeGroup;
    private JTextField arenaWidthField;
    private JTextField arenaHeightField;
    private JTextField headSizeField;
    private JTextField foodSizeField;
    private JTextField numObstaclesField;
    private JCheckBox dynamicObstaclesCheckBox;
    private JCheckBox rotationCheckBox;
    private JTextField rotationXField;
    private JTextField rotationYField;
    private JTextField playerNameField;
    private JTextField leaderboardSizeField;

    public GraphicalClient() {
        super("POOSNAKE - Interface Gráfica");

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Adiciona espaçamento entre os componentes

        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(new JLabel("Modo de Jogo:"), gbc);

        autoModeButton = new JRadioButton("Automático");
        manualModeButton = new JRadioButton("Manual");
        gameModeGroup = new ButtonGroup();
        gameModeGroup.add(autoModeButton);
        gameModeGroup.add(manualModeButton);

        gbc.gridx = 1;
        mainPanel.add(autoModeButton, gbc);
        gbc.gridx = 2;
        mainPanel.add(manualModeButton, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        mainPanel.add(new JLabel("Tipo de Rasterização:"), gbc);

        filledRasterButton = new JRadioButton("Filled");
        outlinedRasterButton = new JRadioButton("Outlined");
        rasterizationTypeGroup = new ButtonGroup();
        rasterizationTypeGroup.add(filledRasterButton);
        rasterizationTypeGroup.add(outlinedRasterButton);

        gbc.gridx = 1;
        mainPanel.add(filledRasterButton, gbc);
        gbc.gridx = 2;
        mainPanel.add(outlinedRasterButton, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        mainPanel.add(new JLabel("Largura da Arena:"), gbc);
        arenaWidthField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        mainPanel.add(arenaWidthField, gbc);
        gbc.gridwidth = 1;

        gbc.gridx = 0;
        gbc.gridy++;
        mainPanel.add(new JLabel("Altura da Arena:"), gbc);
        arenaHeightField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        mainPanel.add(arenaHeightField, gbc);
        gbc.gridwidth = 1;

        gbc.gridx = 0;
        gbc.gridy++;
        mainPanel.add(new JLabel("Dimensões da Cabeça:"), gbc);
        headSizeField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        mainPanel.add(headSizeField, gbc);
        gbc.gridwidth = 1;

        gbc.gridx = 0;
        gbc.gridy++;
        mainPanel.add(new JLabel("Dimensão da Comida:"), gbc);
        foodSizeField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        mainPanel.add(foodSizeField, gbc);
        gbc.gridwidth = 1;

        gbc.gridx = 0;
        gbc.gridy++;
        mainPanel.add(new JLabel("Tipo de Comida:"), gbc);

        circleFoodButton = new JRadioButton("Círculo");
        squareFoodButton = new JRadioButton("Quadrado");
        foodTypeGroup = new ButtonGroup();
        foodTypeGroup.add(circleFoodButton);
        foodTypeGroup.add(squareFoodButton);

        gbc.gridx = 1;
        mainPanel.add(circleFoodButton, gbc);
        gbc.gridx = 2;
        mainPanel.add(squareFoodButton, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        mainPanel.add(new JLabel("Número de Obstáculos:"), gbc);
        numObstaclesField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        mainPanel.add(numObstaclesField, gbc);
        gbc.gridwidth = 1;

        gbc.gridx = 0;
        gbc.gridy++;
        dynamicObstaclesCheckBox = new JCheckBox("Obstáculos Dinâmicos?");
        dynamicObstaclesCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean dynamicObstaclesSelected = dynamicObstaclesCheckBox.isSelected();
                rotationCheckBox.setEnabled(dynamicObstaclesSelected);
                rotationXField.setEnabled(dynamicObstaclesSelected && rotationCheckBox.isSelected());
                rotationYField.setEnabled(dynamicObstaclesSelected && rotationCheckBox.isSelected());
            }
        });
        gbc.gridwidth = 3;
        mainPanel.add(dynamicObstaclesCheckBox, gbc);
        gbc.gridwidth = 1;

        gbc.gridx = 0;
        gbc.gridy++;
        rotationCheckBox = new JCheckBox("Deseja incluir Ponto de rotação?");
        rotationCheckBox.setEnabled(false);
        rotationCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean rotationSelected = rotationCheckBox.isSelected();
                rotationXField.setEnabled(rotationSelected && dynamicObstaclesCheckBox.isSelected());
                rotationYField.setEnabled(rotationSelected && dynamicObstaclesCheckBox.isSelected());
            }
        });
        gbc.gridwidth = 3;
        mainPanel.add(rotationCheckBox, gbc);
        gbc.gridwidth = 1;

        gbc.gridx = 0;
        gbc.gridy++;
        mainPanel.add(new JLabel("Ponto de Rotação (X):"), gbc);
        rotationXField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        mainPanel.add(rotationXField, gbc);
        rotationXField.setEnabled(false);
        gbc.gridwidth = 1;

        gbc.gridx = 0;
        gbc.gridy++;
        mainPanel.add(new JLabel("Ponto de Rotação (Y):"), gbc);
        rotationYField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        mainPanel.add(rotationYField, gbc);
        rotationYField.setEnabled(false);
        gbc.gridwidth = 1;

        gbc.gridx = 0;
        gbc.gridy++;
        mainPanel.add(new JLabel("Nome do Jogador:"), gbc);
        playerNameField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        mainPanel.add(playerNameField, gbc);
        gbc.gridwidth = 1;

        gbc.gridx = 0;
        gbc.gridy++;
        mainPanel.add(new JLabel("Quantos jogadores na Leaderboard?"), gbc);
        leaderboardSizeField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        mainPanel.add(leaderboardSizeField, gbc);
        gbc.gridwidth = 1;

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 3;
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String gameMode = autoModeButton.isSelected() ? "A" : "M";
                    String rasterizationTypeStr = filledRasterButton.isSelected() ? "F" : "O";
                    String foodTypeStr = circleFoodButton.isSelected() ? "C" : "S";
        
                    int arenaWidth = Integer.parseInt(arenaWidthField.getText());
                    int arenaHeight = Integer.parseInt(arenaHeightField.getText());
                    int headSize = Integer.parseInt(headSizeField.getText());
                    int foodSize = Integer.parseInt(foodSizeField.getText());
                    int numObstacles = Integer.parseInt(numObstaclesField.getText());
                    boolean dynamicObstacles = dynamicObstaclesCheckBox.isSelected();
                    boolean includeRotation = rotationCheckBox.isSelected();
                    int rotationX = includeRotation ? Integer.parseInt(rotationXField.getText()) : 0;
                    int rotationY = includeRotation ? Integer.parseInt(rotationYField.getText()) : 0;
                    String playerName = playerNameField.getText();
                    int leaderboardSize = Integer.parseInt(leaderboardSizeField.getText());
        
                    RasterizationType rasterizationType = RasterizationType.valueOf(rasterizationTypeStr);
                    FoodType foodType = FoodType.valueOf(foodTypeStr);
        
                    Ponto rotation = includeRotation ? new Ponto(rotationX, rotationY) : null;
        
                    // Fechar a janela inicial
                    dispose();
        
                    // Criar e iniciar a arena
                    new Arena(arenaWidth, arenaHeight, headSize, rasterizationType, foodSize,
                            foodType, numObstacles, dynamicObstacles ? ObstacleType.D : ObstacleType.S, rotation,
                            'G', playerName, new Scanner(System.in), gameMode.charAt(0), 0, 1, leaderboardSize);
        
                    System.out.println("Arena created successfully with the given parameters.");
        
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(GraphicalClient.this, "Please enter valid numbers.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(GraphicalClient.this, ex.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        mainPanel.add(submitButton, gbc);

        add(mainPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GraphicalClient();
            }
        });
    }
}
