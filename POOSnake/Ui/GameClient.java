package Ui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Core.Food;
import Core.Game;
import Core.Obstacle;
import Geometry.Ponto;

public class GameClient {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite a largura da arena:");
        int arenaDimensionsX = scanner.nextInt();
        System.out.println("Digite a altura da arena:");
        int arenaDimensionsY = scanner.nextInt();

        System.out.println("Digite onde deve começar a sua Snake (coordenada x):");
        int xCoord = scanner.nextInt();
        System.out.println("Digite onde deve começar a sua Snake (coordenada y):");
        int yCoord = scanner.nextInt();

        Ponto snakePosition = new Ponto(xCoord, yCoord);

        System.out.println("Digite a posição X do alimento:");
        int foodPositionX = scanner.nextInt();
        System.out.println("Digite a posição Y do alimento:");
        int foodPositionY = scanner.nextInt();
        Ponto foodPosition = new Ponto(foodPositionX, foodPositionY);

        System.out.println("Digite o código da cor do alimento (ex: 255,0,0 para vermelho):");
        String colorCode = scanner.next();
        String[] colorValues = colorCode.split(",");
        Color foodColor = new Color(Integer.parseInt(colorValues[0]), Integer.parseInt(colorValues[1]),
                Integer.parseInt(colorValues[2]));

        System.out.println("Escolha o tipo de alimento (C para comida comum, S para supercomida):");
        String foodTypeString = scanner.next();
        Food.FoodType foodType = Food.FoodType.valueOf(foodTypeString);

        System.out.println("Digite as dimensões da cabeça:");
        int headDimensions = scanner.nextInt();

        System.out.println("Escolha o tipo de rasterização (O para contorno, F para preenchido):");
        String rasterizationType = scanner.next();

        System.out.println("Digite o placar inicial:");
        int score = scanner.nextInt();

        List<Obstacle> obstacles = new ArrayList<>(); // Você pode adicionar lógica para obter obstáculos aqui, se
                                                      // necessário

        System.out.println("Escolha o modo de interface (G para gráfico, T para textual):");
        String interfaceModeString = scanner.next();
        scanner.close();
        // Criação do objeto Game com os valores inseridos
        Game game = new Game(snakePosition, arenaDimensionsX, arenaDimensionsY, foodPosition, foodColor, foodType,
                headDimensions, rasterizationType, score, obstacles, interfaceModeString);

        game.Start();

        // Agora você tem um objeto Game pronto para uso!
    }
}
