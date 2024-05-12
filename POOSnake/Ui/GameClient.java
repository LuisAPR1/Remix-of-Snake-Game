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
        System.out.flush();
        int angle = 0;
        int points = 1;
        int nleader = 0;
        System.out.println(
                "\033[0;31mBem-vindo ao jogo POOSNAKE - Desenvolvido por Pedro Ferreira, Luís Rosa e José Lima \033[0m\n\n");

        // Solicita ao usuário que escolha o modo de jogo
        char movement;
        do {
            System.out.println("Digite o modo de jogo (A para Automático, M para Manual):");
            movement = scanner.next().charAt(0);
            if (movement != 'A' && movement != 'M') {
                System.out.println("Modo de jogo inválido. Por favor, insira 'A' para Automático ou 'M' para Manual.");
            }
        } while (movement != 'A' && movement != 'M');

        // Solicita ao usuário as dimensões da arena
        int arenaDimensionsX, arenaDimensionsY;
        do {
            System.out.println("Digite a largura da arena:");
            arenaDimensionsX = scanner.nextInt();
            System.out.println("Digite a altura da arena:");
            arenaDimensionsY = scanner.nextInt();
            if (arenaDimensionsX <= 0 || arenaDimensionsY <= 0) {
                System.out.println("As dimensões da arena devem ser maiores que zero.");
            }
        } while (arenaDimensionsX <= 0 || arenaDimensionsY <= 0);

        // Solicita ao usuário as dimensões da cabeça do jogador
        int headDimensions;
        
            System.out.println("Digite as dimensões da cabeça:");
            headDimensions = scanner.nextInt();
            if (headDimensions < 3) {
                System.out.println("O tamanho da cabeça deve ser no mínimo 3 para acomodar alimentos circulares.");
                System.out.println("Por favor, escolha o tipo de comida como quadrado (S).");
            }
        

        // Solicita ao usuário que escolha o tipo de comida
        char foodTypeString;
        do {
            System.out.println("Escolha o tipo de comida (C para círculo, S para quadrado):");
            foodTypeString = scanner.next().charAt(0);
            if (foodTypeString != 'C' && foodTypeString != 'S') {
                System.out.println("Tipo de comida inválido. Por favor, insira 'C' para círculo ou 'S' para quadrado.");
            } else if (foodTypeString == 'C' && headDimensions < 3) {
                System.out.println("O tamanho da cabeça é muito pequeno para acomodar alimentos circulares.");
                System.out.println("Por favor, escolha o tipo de comida como quadrado (S).");
            }
        } while ((foodTypeString != 'C' && foodTypeString != 'S') || (foodTypeString == 'C' && headDimensions < 3));
        FoodType foodType = FoodType.valueOf(Character.toString(foodTypeString));

        // Solicita ao usuário o tipo de rasterização
        char rasterization;
        do {
            System.out.println("Escolha o tipo de rasterização (O para contorno, F para preenchido):");
            rasterization = scanner.next().charAt(0);
            if (rasterization != 'O' && rasterization != 'F') {
                System.out.println(
                        "Tipo de rasterização inválido. Por favor, insira 'O' para contorno ou 'F' para preenchido.");
            }
        } while (rasterization != 'O' && rasterization != 'F');
        RasterizationType rasterizationType = RasterizationType.valueOf(Character.toString(rasterization));

        // Solicita ao usuário a dimensão da comida
        int foodDimensions;
        do {
            System.out.println("Digite a dimensão da comida:");
            foodDimensions = scanner.nextInt();
            if (foodDimensions <= 0) {
                System.out.println("A dimensão da comida deve ser maior que zero.");
            }
            System.out.println("Quantos pontos vale cada comida?:");
            points = scanner.nextInt();
        } while (foodDimensions <= 0);

        // Solicita ao usuário o número de obstáculos
        int numObstacles;
        do {
            System.out.println("Digite quantos obstáculos devem existir:");
            numObstacles = scanner.nextInt();
            if (numObstacles < 0) {
                System.out.println("O número de obstáculos deve ser maior ou igual a zero.");
            }
        } while (numObstacles < 0);

        // Solicita ao usuário que escolha o tipo de obstáculos
        char obstacleTypeString;
        do {
            System.out.println("Escolha o tipo de obstáculos (D para dinâmicos, S para estáticos):");
            obstacleTypeString = scanner.next().charAt(0);
            if (obstacleTypeString != 'D' && obstacleTypeString != 'S') {
                System.out.println(
                        "Tipo de obstáculo inválido. Por favor, insira 'D' para dinâmicos ou 'S' para estáticos.");
            }
        } while (obstacleTypeString != 'D' && obstacleTypeString != 'S');
        ObstacleType obstacleType = ObstacleType.valueOf(Character.toString(obstacleTypeString));

        Ponto rotation = null;
        // Se os obstáculos são dinâmicos, solicita as coordenadas do ponto de rotação
        if (obstacleType == ObstacleType.D) {
            System.out.println("Digite o ângulo de rotação:");
            angle = scanner.nextInt();
            System.out.println("Deseja definir um ponto de rotação para os obstáculos dinâmicos? (S/N):");
            char rotationChoice = scanner.next().charAt(0);
            if (rotationChoice == 'S' || rotationChoice == 's') {
                System.out.println("Digite x do ponto de rotação:");
                int pointX = scanner.nextInt();
                System.out.println("Digite y do ponto de rotação:");
                int pointY = scanner.nextInt();
                rotation = new Ponto(pointX, pointY);
            }
        }

        // Solicita ao usuário o nome do jogador
        System.out.println("Digite o nome do Jogador:");
        String namePlayer = scanner.next();

        // Solicita ao usuário que escolha o modo de interface (gráfico ou textual)
        char interfaceMode;
        do {
            System.out.println("Escolha o modo de interface (G para gráfico, T para textual):");
            interfaceMode = scanner.next().charAt(0);
            if (interfaceMode != 'G' && interfaceMode != 'T') {
                System.out
                        .println("Modo de interface inválido. Por favor, insira 'G' para gráfico ou 'T' para textual.");
            }
        } while (interfaceMode != 'G' && interfaceMode != 'T');

        System.out.println("Quantos jogadores da LeaderBoard deseja imprimir no fim do jogo?:");
        nleader = scanner.nextInt();

        // Criação do objeto Arena com os valores inseridos
        @SuppressWarnings("unused")
        Arena game = new Arena(arenaDimensionsX, arenaDimensionsY, headDimensions, rasterizationType, foodDimensions,
                foodType, numObstacles, obstacleType, rotation, interfaceMode, namePlayer, scanner, movement, angle,
                points, nleader);

        // Agora você tem um objeto Arena pronto para uso!
    }
}
