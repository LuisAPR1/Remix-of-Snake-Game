package UI;

import java.util.Scanner;

import Core.Arena;
import Core.FoodType;
import Core.Obstacle.ObstacleType;
import Core.RasterizationType;
import Geometry.Ponto;

public class GameClient {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o modo de jogo (A para Automático, M para Manual):");
        char movement = scanner.next().charAt(0);
        System.out.println("Digite a largura da arena:");
        int arenaDimensionsX = scanner.nextInt();
        System.out.println("Digite a altura da arena:");
        int arenaDimensionsY = scanner.nextInt();

        System.out.println("Digite as dimensões da cabeça:");
        int headDimensions = scanner.nextInt();

        System.out.println("Escolha o tipo de rasterização (O para contorno, F para preenchido):");
        char rasterization = scanner.next().charAt(0);
        RasterizationType rasterizationType = RasterizationType.valueOf(Character.toString(rasterization));

        System.out.println("Digite a dimensão da comida:");
        int foodDimensions = scanner.nextInt();

        System.out.println("Escolha o tipo de comida (C para círculo, S para quadrado):");
        char foodTypeString = scanner.next().charAt(0);
        FoodType foodType = FoodType.valueOf(Character.toString(foodTypeString));

        System.out.println("Digite quantos obstáculos devem existir:");
        int numObstacles = scanner.nextInt();

        System.out.println("Escolha o tipo de obstáculos (D para dinâmicos, S para estáticos):");
        char obstacleTypeString = scanner.next().charAt(0);
        ObstacleType obstacleType = ObstacleType.valueOf(Character.toString(obstacleTypeString));

        System.out.println("Digite o nome do Jogador:");
        String namePlayer = scanner.next();

        Ponto rotation;
        if (obstacleType == ObstacleType.S) {
            rotation = null;
        } else {
            System.out.println("Digite x do ponto de rotação:");
            int pointX = scanner.nextInt();
            System.out.println("Digite y do ponto de rotação:");
            int pointY = scanner.nextInt();
            rotation = new Ponto(pointX, pointY);
        }

        System.out.println("Escolha o modo de interface (G para gráfico, T para textual):");
        char interfaceMode = scanner.next().charAt(0);

        // Criação do objeto Arena com os valores inseridos
        @SuppressWarnings("unused")
        Arena game = new Arena(arenaDimensionsX, arenaDimensionsY, headDimensions, rasterizationType, foodDimensions,
                foodType, numObstacles, obstacleType, rotation, interfaceMode, namePlayer, scanner, movement);

        // Agora você tem um objeto Arena pronto para uso!
    }
}
