package Core;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import Geometry.Poligono;
import Geometry.Ponto;
import Geometry.Square;
import Geometry.Triangulo;
import UI.UI;
import UI.UIFactory;

/**
 * Classe que representa a arena do jogo POOSnake.
 * 
 * @version Versão 1.0 10/05/2024
 * @author Luís Rosa, José Lima, Pedro Ferreira e Pedro Ferreira
 */
public class Arena {

    Snake s;

    AbstractFood<?> fruit;
    ArrayList<Obstacle> obstacles;

    private int[] arenaDimensions = new int[2];
    private FoodType foodtype;
    private int headDimensions;
    private int foodDimensions;
    private Core.Obstacle.ObstacleType obstacletype;
    Ponto rotacao;
    int pointsadder;
    int points;
    int angle = 0;
    List<Player> players;
    String namePlayer;
    Rank rank;
    private MovementStrategy movementStrategy;
    UI ui;
    int tryyyy;
    int t = 0;
    int nleader = 1;
    char interfaceMode;

    public char getInterfaceMode() {
        return interfaceMode;
    }

    /**
     * Construtor da classe Arena.
     * 
     * @param arenaDimensionsX  largura da arena.
     * @param arenaDimensionsY  altura da arena.
     * @param headDimensions    dimensões da cabeça da cobra.
     * @param rasterizationType tipo de rasterização para a UI.
     * @param foodDimensions    dimensões da comida.
     * @param foodType          tipo de comida.
     * @param numObstacles      número de obstáculos.
     * @param obstacleType      tipo de obstáculo.
     * @param rotacao           ponto de rotação.
     * @param interfaceMode     modo de interface.
     * @param namePlayer        nome do jogador.
     * @param scanner           scanner para entrada.
     * @param movement          tipo de movimento.
     */
    public Arena(int arenaDimensionsX, int arenaDimensionsY, int headDimensions, RasterizationType rasterizationType,
            int foodDimensions, FoodType foodType, int numObstacles, Core.Obstacle.ObstacleType obstacleType,
            Ponto rotacao, char interfaceMode, String namePlayer, Scanner scanner, Character movement, int angle,
            int pointsadder, int nleader) {
        // Configurações iniciais da arena
        this.angle = angle;
        this.rotacao = rotacao;
        this.obstacletype = obstacleType;
        this.arenaDimensions[0] = arenaDimensionsX;
        this.arenaDimensions[1] = arenaDimensionsY;
        if (foodDimensions >= headDimensions && headDimensions > 1) {
            this.foodDimensions = headDimensions - 1;
        } else if (headDimensions == 1) {
            this.foodDimensions = 1;
        }
        this.interfaceMode = interfaceMode;
        this.foodDimensions = foodDimensions;
        this.headDimensions = headDimensions;
        this.foodtype = foodType;
        this.namePlayer = namePlayer;
        this.rank = new Rank(players, nleader);
        this.pointsadder = pointsadder;

        if (arenaDimensionsX % headDimensions != 0 || arenaDimensionsY % headDimensions != 0) {
            // Calcula as dimensões ajustadas da arena para serem múltiplos do tamanho da
            // cabeça
            this.arenaDimensions[0] = adjustArenaDimension(arenaDimensionsX, headDimensions);
            this.arenaDimensions[1] = adjustArenaDimension(arenaDimensionsY, headDimensions);
            System.out.println("O tamanho da arena foi ajustado para uma melhor experiência!");
            System.out.println("Ajustado em função do tamanho da cabeça escolhido");

        }

        // Cria os obstáculos
        createObstacles(numObstacles, obstacleType, arenaDimensions, headDimensions);
        // Gera a cobra
        generateSnake(arenaDimensions, headDimensions);
        // Gera a comida
        generateFood(Color.YELLOW, foodType, this, foodDimensions);
        // Inicializa a UI
        RasterizationStrategy rasterization;
        if (rasterizationType == RasterizationType.O) {
            rasterization = new OutlineRasterizationTextual(this);
        } else {
            rasterization = new FilledRasterizationTextual(this);
        }
        if (movement == 'M') {
            movementStrategy = new ManualMovementStrategy(scanner, this);
            setMovementStrategy(movementStrategy);

        } else {
            movementStrategy = new AutomaticMovementStrategy(this);
            setMovementStrategy(movementStrategy);
        }

        this.ui = UIFactory.createUI(interfaceMode, rasterization, movementStrategy);

        // Configura a estratégia de movimento manual

        System.out.print("\033[H\033[2J");
        System.out.flush();
        ui.render();

        startGame();
    }

    /**
     * Inicia o jogo.
     */
    public void startGame() {
        while (true) {
            // Captura a entrada do usuário e executa o movimento
            movementStrategy.input();
            // Atualiza o frame do jogo
        }
    }

    /**
     * Ajusta a dimensão da arena para garantir que seja um múltiplo inteiro da
     * dimensão da cabeça.
     *
     * @param dimension      A dimensão original da arena.
     * @param headDimensions As dimensões da cabeça da cobra.
     * @return A dimensão ajustada da arena.
     */
    private int adjustArenaDimension(int dimension, int headDimensions) {
        // Calcula o quociente da divisão entre a dimensão original e o tamanho da
        // cabeça
        int quotient = dimension / headDimensions;
        // Incrementa o quociente para garantir que a dimensão seja múltiplo do tamanho
        // da cabeça
        if (dimension % headDimensions != 0) {
            quotient++;
        }
        // Retorna a dimensão ajustada
        return (quotient * headDimensions);
    }

    /**
     * Gera a comida na arena.
     * 
     * @param color          cor da comida.
     * @param foodType       tipo de comida.
     * @param arena          arena do jogo.
     * @param foodDimensions dimensões da comida.
     */
    private void generateFood(Color color, FoodType foodType, Arena arena, int foodDimensions) {
        boolean foodIntersects = true;

        if (foodDimensions >= headDimensions && headDimensions > 1) {
            foodDimensions = headDimensions - 1;
        } else if (headDimensions == 1) {
            foodDimensions = 1;
        }
        // Repete até que a comida não intersecte com a cobra ou obstáculos
        while (foodIntersects) {
            // Cria a comida com uma posição aleatória
            fruit = FoodFactory.createFood(color, foodType, arena, foodDimensions);

            // Verifica se a comida intersecta com a cobra
            foodIntersects = checkFoodSnakeCollision(fruit);

            // Se a comida não intersectar com a cobra, verifica se intersecta com algum
            // obstáculo
            if (!foodIntersects) {
                foodIntersects = checkFoodObstacleCollision(fruit);
            }

            // Se a comida intersectar com a cobra ou um obstáculo, gere uma nova posição
            // para ela
            if (foodIntersects) {
                fruit.spawnFood(arena);
                tryyyy++;
                if (tryyyy > 100000) {
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    ui.render();
                    System.out.println("Max Score Achieved!");
                    System.out.println("Score: " + points);
                    System.out.println();
                    rank.printLeaderboard();
                    System.exit(0);

                }
            }
        }

    }

    /**
     * Verifica se a comida intersecta com a cobra.
     * 
     * @param food comida a ser verificada.
     * @return true se a comida intersecta com a cobra, false caso contrário.
     */
    private boolean checkFoodSnakeCollision(AbstractFood<?> food) {
        // Obtém os quadrados da cobra
        List<Square> squares = s.getSnake();

        // Verifica se a comida intersecta com algum polígono da cobra
        for (Square square : squares) {
            if (food.intersect(square) || food.contains(square) || food.distance(square)
                    || food.isContainedIn(square)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Verifica se a comida intersecta com algum obstáculo.
     * 
     * @param food comida a ser verificada.
     * @return true se a comida intersecta com algum obstáculo, false caso
     *         contrário.
     */
    private boolean checkFoodObstacleCollision(AbstractFood<?> food) {
        // Verifica se a comida intersecta com algum obstáculo
        for (Obstacle obstacle : obstacles) {
            if (food.intersect(obstacle.getObstacle()) || food.contains(obstacle.getObstacle())
                    || food.distance(obstacle.getObstacle()) || food.isContainedIn(obstacle.getObstacle())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Cria os obstáculos na arena. Podendo estes ser qualquer tipo de polígono
     * 
     * @param numObstacles    número de obstáculos a serem criados.
     * @param obstacleType    tipo de obstáculo.
     * @param arenaDimensions dimensões da arena.
     * @param headDimensions  dimensões da cabeça da cobra.
     */
    private void createObstacles(int numObstacles, Core.Obstacle.ObstacleType obstacleType, int[] arenaDimensions,
            int headDimensions) {
        obstacles = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < numObstacles; i++) {
            // Decidindo aleatoriamente se o próximo obstáculo será um quadrado, um
            // retângulo ou um triângulo
            int obstacleShapeType = rand.nextInt(50); // 0 para quadrado, 1 para retângulo, 2 para triângulo

            int posX = rand.nextInt(arenaDimensions[0] - headDimensions) + 1;
            int posY = rand.nextInt(arenaDimensions[1] - headDimensions) + 1;

            if (obstacleShapeType < 25 || headDimensions < 4) {
                // Criação de quadrado
                ArrayList<Ponto> pontos = new ArrayList<>();
                pontos.add(new Ponto(posX, posY));
                pontos.add(new Ponto(posX + headDimensions, posY));
                pontos.add(new Ponto(posX + headDimensions, posY + headDimensions));
                pontos.add(new Ponto(posX, posY + headDimensions));
                Poligono obstacleShape = new Poligono(pontos);
                Obstacle obstacle = new Obstacle(obstacleType, obstacleShape, rotacao);
                obstacles.add(obstacle);
            } else if (obstacleShapeType == 26) {
                ArrayList<Ponto> pontos1 = new ArrayList<>();
                pontos1.add(new Ponto(posX, posY));
                pontos1.add(new Ponto(posX + headDimensions, posY));
                pontos1.add(new Ponto(posX + headDimensions, posY + headDimensions));
                pontos1.add(new Ponto(posX, posY + headDimensions));
                Poligono obstacleShape1 = new Poligono(pontos1);
                Obstacle obstacle1 = new Obstacle(obstacleType, obstacleShape1, rotacao);
                obstacles.add(obstacle1);
                ArrayList<Ponto> pontos2 = new ArrayList<>();
                pontos2.add(new Ponto(posX + headDimensions, posY));
                pontos2.add(new Ponto(posX + headDimensions * 2, posY));
                pontos2.add(new Ponto(posX + headDimensions * 2, posY + headDimensions));
                pontos2.add(new Ponto(posX + headDimensions, posY + headDimensions));
                Poligono obstacleShape2 = new Poligono(pontos2);
                Obstacle obstacle2 = new Obstacle(obstacleType, obstacleShape2, rotacao);
                obstacles.add(obstacle2);

            } else {
                // Criação de triângulo
                ArrayList<Ponto> pontos = new ArrayList<>();
                pontos.add(new Ponto(posX, posY));
                pontos.add(new Ponto(posX + headDimensions, posY));
                pontos.add(new Ponto(posX + (headDimensions / 2), posY + headDimensions));
                Triangulo obstacleShape = new Triangulo(pontos);
                Obstacle obstacle = new Obstacle(obstacleType, obstacleShape, null);
                obstacles.add(obstacle);
            }
        }
    }

    /**
     * Gera a cobra na arena.
     * 
     * @param arenaDimensions dimensões da arena.
     * @param headDimensions  dimensões da cabeça da cobra.
     */
    private void generateSnake(int[] arenaDimensions, int headDimensions) {
        boolean snakeCollidedWithObstacle = true;

        // Repete até que a cobra não colida com nenhum obstáculo
        while (snakeCollidedWithObstacle) {
            // Gera a cobra
            this.s = new Snake(arenaDimensions, headDimensions);

            // Verifica se a cobra colidiu com algum obstáculo
            snakeCollidedWithObstacle = s.checkSnakeObstacleColision(s, obstacles);
        }
    }

    /**
     * Move os obstáculos na arena.
     */
    public void obstaclesmove() {
        // Itera sobre todos os obstáculos na lista
        for (Obstacle obstacle : obstacles) {
            // Obtém o polígono do obstáculo
            Poligono obstacleShape = obstacle.rotate(this.angle);

            // Atualiza o polígono do obstáculo com a nova posição após a rotação
            obstacle.setObstacle(obstacleShape);
        }
    }

    /**
     * Atualiza o frame do jogo.
     */
    public void Frame() {

        s.move();
        System.out.println(s.toString());

        if (s.CheckFoodEaten(fruit) == true) {
            points += pointsadder;
            s.grow();
            generateFood(Color.YELLOW, foodtype, this, foodDimensions);

        }

        if (s.checkSnakeObstacleColision(s, obstacles) == true) {
            rank.updateRank(namePlayer, points);
            System.out.print("\033[H\033[2J");
            System.out.flush();
            ui.render();

            rank.printLeaderboard();
            System.exit(0);
        }

        if (s.checkSnakeInsideArena(arenaDimensions) == true) {
            rank.updateRank(namePlayer, points);
            System.out.print("\033[H\033[2J");
            System.out.flush();
            ui.render();

            rank.printLeaderboard();
            System.exit(0);
        }

        if (s.checkSnakeSelfCollision() == true) {
            rank.updateRank(namePlayer, points);
            System.out.print("\033[H\033[2J");
            System.out.flush();
            ui.render();

            rank.printLeaderboard();
            System.exit(0);
        }

        if (this.obstacletype == Obstacle.ObstacleType.D && t != 0) {
            obstaclesmove();
        }

        System.out.println();
        System.out.flush();
        ui.render();
        t++;

    }

    // GETTERS AND SETTERS

    public Snake getS() {
        return s;
    }

    public AbstractFood<?> getFruit() {
        return fruit;
    }

    public int[] getArenaDimensions() {
        return arenaDimensions;
    }

    public ArrayList<Obstacle> getObstacles() {
        return obstacles;
    }

    public int getHeadDimensions() {
        return headDimensions;
    }

    public void setMovementStrategy(MovementStrategy strategy) {
        this.movementStrategy = strategy;
    }

}
