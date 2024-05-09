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

        System.out.println("Digite a largura da arena:");
        int arenaDimensionsX = scanner.nextInt();
        System.out.println("Digite a altura da arena:");
        int arenaDimensionsY = scanner.nextInt();

        System.out.println("Digite as dimensoes da cabeça:");
        int headDimensions = scanner.nextInt();

        System.out.println("Escolha o tipo de rasterização (O para contorno, F para preenchido):");
        String rasterization = scanner.next();
        RasterizationType rasterizationtype = RasterizationType.valueOf(rasterization);

        System.out.println("Digite a dimensao da commida:");
        int foodDimensions = scanner.nextInt();

        System.out.println("Escolha o tipo de comida (C para circulo, S para quadrado):");
        String foodTypeString = scanner.next();
        FoodType foodType = FoodType.valueOf(foodTypeString);

        System.out.println("Digite quantos obstáculos devem existir:");
        int numObstacles = scanner.nextInt();

        System.out.println("Escolha o tipo de obstaculos (D para dinammicos, S para estaticos):");
        String obstacleTypeString = scanner.next();
        ObstacleType obstacleType = ObstacleType.valueOf(obstacleTypeString);

        System.out.println("Digite o nome do Jogador:");
        String namePlayer = scanner.next();


        Ponto rotacao;
        if (obstacleType==ObstacleType.S) {
            rotacao = null;
        }else{
            System.out.println("Digite x do ponto de rotacao:");
            int pontox = scanner.nextInt();
            System.out.println("Digite y do ponto de rotacao:");
            int pontoy = scanner.nextInt();
            rotacao = new Ponto(pontox,pontoy);
        }

        System.out.println("Escolha o modo de interface (G para gráfico, T para textual):");
        char interfaceMode = scanner.next().charAt(0);
       
        // Criação do objeto Game com os valores inseridos
        @SuppressWarnings("unused")
        Arena game = new Arena(arenaDimensionsX, arenaDimensionsY, headDimensions, rasterizationtype, foodDimensions,
                foodType, numObstacles, obstacleType, rotacao ,interfaceMode, namePlayer, scanner);


        // Agora você tem um objeto Game pronto para uso!
    }
}
