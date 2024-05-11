package UI;

import java.util.Scanner;

import Core.Arena;
import Core.FoodType;
import Core.Obstacle.ObstacleType;
import Core.RasterizationType;
import Geometry.Ponto;

/**
 *
 * Classe que representa o cliente do jogo,responsável por interagir com o usuário para configurar o jogo.
 *  
 * @version Versão 1.0 10/05/2024
 * @author Luís Rosa, José Lima, Pedro Ferreira, José Lima, Pedro Ferreira
 */
public class GameClient {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Solicita ao usuário que escolha o modo de jogo
        System.out.println("Digite o modo de jogo (A para Automático, M para Manual):");
        char movement = scanner.next().charAt(0);
        // Solicita ao usuário as dimensões da arena
        System.out.println("Digite a largura da arena:");
        int arenaDimensionsX = scanner.nextInt();
        System.out.println("Digite a altura da arena:");
        int arenaDimensionsY = scanner.nextInt();

        // Solicita ao usuário as dimensões da cabeça do jogador
        System.out.println("Digite as dimensões da cabeça:");
        int headDimensions = scanner.nextInt();

        // Solicita ao usuário que escolha o tipo de rasterização
        System.out.println("Escolha o tipo de rasterização (O para contorno, F para preenchido):");
        char rasterization = scanner.next().charAt(0);
        RasterizationType rasterizationType = RasterizationType.valueOf(Character.toString(rasterization));

        // Solicita ao usuário a dimensão da comida
        System.out.println("Digite a dimensão da comida:");
        int foodDimensions = scanner.nextInt();

        // Solicita ao usuário que escolha o tipo de comida
        System.out.println("Escolha o tipo de comida (C para círculo, S para quadrado):");
        char foodTypeString = scanner.next().charAt(0);
        FoodType foodType = FoodType.valueOf(Character.toString(foodTypeString));

        // Solicita ao usuário o número de obstáculos
        System.out.println("Digite quantos obstáculos devem existir:");
        int numObstacles = scanner.nextInt();

        // Solicita ao usuário que escolha o tipo de obstáculos
        System.out.println("Escolha o tipo de obstáculos (D para dinâmicos, S para estáticos):");
        char obstacleTypeString = scanner.next().charAt(0);
        ObstacleType obstacleType = ObstacleType.valueOf(Character.toString(obstacleTypeString));

        Ponto rotation;
        // Se os obstáculos são estáticos, não há ponto de rotação
        if (obstacleType == ObstacleType.S) {
            rotation = null;
        } else { // Caso contrário, solicita as coordenadas do ponto de rotação
            System.out.println("Digite x do ponto de rotação:");
            int pointX = scanner.nextInt();
            System.out.println("Digite y do ponto de rotação:");
            int pointY = scanner.nextInt();
            rotation = new Ponto(pointX, pointY);
        }

        // Solicita ao usuário o nome do jogador
        System.out.println("Digite o nome do Jogador:");
        String namePlayer = scanner.next();

        // Solicita ao usuário que escolha o modo de interface (gráfico ou textual)
        System.out.println("Escolha o modo de interface (G para gráfico, T para textual):");
        char interfaceMode = scanner.next().charAt(0);

        // Criação do objeto Arena com os valores inseridos
        @SuppressWarnings("unused")
        Arena game = new Arena(arenaDimensionsX, arenaDimensionsY, headDimensions, rasterizationType, foodDimensions,
                foodType, numObstacles, obstacleType, rotation, interfaceMode, namePlayer, scanner, movement);

        // Agora você tem um objeto Arena pronto para uso!
    }
}
