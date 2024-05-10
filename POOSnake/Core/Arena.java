package Core;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import Geometry.Poligono;
import Geometry.Ponto;
import Geometry.Square;
import UI.UI;
import UI.UIFactory;

public class Arena {

    Snake s;

    AbstractFood<?> fruit;
    ArrayList<Obstacle> obstacles;

    private int[] arenaDimensions = new int[2];
    private FoodType foodtype;
    private int headDimensions;
    private int foodDimensions;
    @SuppressWarnings("unused")
    private RasterizationType rasterization;
    @SuppressWarnings("unused")
    private int score;
    Core.Obstacle.ObstacleType obstacletype;
    Ponto rotacao;
    int points;
    List<Player> players;
    String namePlayer;
    Rank rank;

    public int getHeadDimensions() {
        return headDimensions;
    }

    @SuppressWarnings("unused")
    private InterfaceMode interfaceMode;
    private MovementStrategy movementStrategy;
    UI ui;

    public Arena(int arenaDimensionsX, int arenaDimensionsY, int headDimensions, RasterizationType rasterizationType,
            int foodDimensions, FoodType foodType, int numObstacles, Core.Obstacle.ObstacleType obstacleType,
            Ponto rotacao,
            char interfaceMode, String namePlayer, Scanner scanner, Character movement) {
        // this.grid = new Cell[arenaDimensionsX][arenaDimensionsY];

        this.rotacao = rotacao;
        this.obstacletype = obstacleType;
        this.arenaDimensions[0] = arenaDimensionsX;
        this.arenaDimensions[1] = arenaDimensionsY;
        this.foodDimensions = foodDimensions;
        this.headDimensions = headDimensions;
        this.rasterization = rasterizationType;
        this.foodtype = foodType;
        this.namePlayer = namePlayer;

        // Cria os obstáculos
        createObstacles(numObstacles, obstacleType, arenaDimensions, headDimensions);
        // Gera a cobra
        generateSnake(arenaDimensions, headDimensions);
        // Gera a comida
        generateFood(Color.YELLOW, foodType, this, foodDimensions);
        // Inicializa a UI
        RasterizationStrategy rasterization;
        if (rasterizationType == RasterizationType.O) {
            rasterization = new OutlineRasterization(this);
        } else {
            rasterization = new FilledRasterization(this);
        }
        this.ui = UIFactory.createUI(interfaceMode, rasterization);

        // Configura a estratégia de movimento manual
        if (movement == 'M') {
            movementStrategy = new ManualMovementStrategy(scanner, this);
            setMovementStrategy(movementStrategy);

        } else {
            movementStrategy = new AutomaticMovementStrategy(this);
            setMovementStrategy(movementStrategy);
        }

        ui.render();
        startGame();
    }

    public void setMovementStrategy(MovementStrategy strategy) {
        this.movementStrategy = strategy;
    }

    public void startGame() {
        while (true) {
            // Captura a entrada do usuário e executa o movimento
            movementStrategy.input();
            // Atualiza o frame do jogo
        }
    }

    public int calculateBestDirection(int currentDirection) {
        // Obtém a posição da cabeça da cobra
        Ponto headPosition = s.getSnake().get(0).calcularCentro();

        // Obtém a posição da comida
        Ponto foodPosition = fruit.getShape().getPosition();

        // Calcula a distância entre a cabeça da cobra e a comida
        double distanceX = Math.abs(headPosition.getX() - foodPosition.getX());
        double distanceY = Math.abs(headPosition.getY() - foodPosition.getY());

        // Inicializa a melhor direção como a direção oposta à direção atual (para
        // garantir que seja alterada)
        int bestDirection = currentDirection;

        // Verifica se a comida está mais próxima no eixo X ou no eixo Y
        if (distanceX < distanceY) {
            // Se a comida estiver mais próxima no eixo X, mova-se horizontalmente (esquerda
            // ou direita)
            if (headPosition.getX() < foodPosition.getX()) {
                // Comida está à direita da cabeça da cobra
                if (currentDirection != 90) {
                    // Se a direção atual não for esquerda, mova-se para a direita
                    bestDirection = 270; // Direita
                } else {
                    // Se não for possível mover para a direita, mova-se verticalmente
                    if (headPosition.getY() < foodPosition.getY()) {
                        // Comida está abaixo da cabeça da cobra
                        bestDirection = 180; // Baixo
                    } else {
                        // Comida está acima da cabeça da cobra
                        bestDirection = 0; // Cima
                    }
                }
            } else {
                // Comida está à esquerda da cabeça da cobra
                if (currentDirection != 270) {
                    // Se a direção atual não for direita, mova-se para a esquerda
                    bestDirection = 90; // Esquerda
                } else {
                    // Se não for possível mover para a esquerda, mova-se verticalmente
                    if (headPosition.getY() < foodPosition.getY()) {
                        // Comida está abaixo da cabeça da cobra
                        bestDirection = 180; // Baixo
                    } else {
                        // Comida está acima da cabeça da cobra
                        bestDirection = 0; // Cima
                    }
                }
            }
        } else {
            // Se a comida estiver mais próxima no eixo Y, mova-se verticalmente (cima ou
            // baixo)
            if (headPosition.getY() < foodPosition.getY()) {
                // Comida está abaixo da cabeça da cobra
                if (currentDirection != 0) {
                    // Se a direção atual não for para cima, mova-se para baixo
                    bestDirection = 180; // Baixo
                } else {
                    // Se não for possível mover para baixo, mova-se horizontalmente
                    if (headPosition.getX() < foodPosition.getX()) {
                        // Comida está à direita da cabeça da cobra
                        bestDirection = 270; // Direita
                    } else {
                        // Comida está à esquerda da cabeça da cobra
                        bestDirection = 90; // Esquerda
                    }
                }
            } else {
                // Comida está acima da cabeça da cobra
                if (currentDirection != 180) {
                    // Se a direção atual não for para baixo, mova-se para cima
                    bestDirection = 0; // Cima
                } else {
                    // Se não for possível mover para cima, mova-se horizontalmente
                    if (headPosition.getX() < foodPosition.getX()) {
                        // Comida está à direita da cabeça da cobra
                        bestDirection = 270; // Direita
                    } else {
                        // Comida está à esquerda da cabeça da cobra
                        bestDirection = 90; // Esquerda
                    }
                }
            }
        }

        return bestDirection;
    }

    private void generateFood(Color color, FoodType foodType, Arena arena, int foodDimensions) {
        boolean foodIntersects = true;

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
            }
        }
    }

    

    private boolean checkFoodSnakeCollision(AbstractFood<?> food) {
        // Obtém os quadrados da cobra
        List<Square> squares = s.getSnake();

        // Verifica se a comida intersecta com algum polígono da cobra
        for (Square square : squares) {
            if (food.intersect(square)) {
                System.out.println("snake colidiu food");
                updateRank();
                System.exit(0);
                return true;
            }
        }
        return false;
    }

    private boolean checkFoodObstacleCollision(AbstractFood<?> food) {
        // Verifica se a comida intersecta com algum obstáculo
        for (Obstacle obstacle : obstacles) {
            if (food.intersect(obstacle.getObstacle())) {
                return true;
            }
        }
        return false;
    }

    private void createObstacles(int numObstacles, Core.Obstacle.ObstacleType obstacleType, int[] arenaDimensions,
            int headDimensions) {
        obstacles = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < numObstacles; i++) {
            int posX = rand.nextInt(arenaDimensions[0] - headDimensions) + 1; // Adiciona 1 para garantir que seja no
                                                                              // mínimo 1
            int posY = rand.nextInt(arenaDimensions[1] - headDimensions) + 1; // Adiciona 1 para garantir que seja no
                                                                              // mínimo 1
            ArrayList<Ponto> pontos = new ArrayList<>();
            pontos.add(new Ponto(posX, posY));
            pontos.add(new Ponto(posX + headDimensions, posY));
            pontos.add(new Ponto(posX + headDimensions, posY + headDimensions));
            pontos.add(new Ponto(posX, posY + headDimensions));
            Poligono obstacleShape = new Poligono(pontos);
            Obstacle obstacle = new Obstacle(obstacleType, obstacleShape, null);
            obstacles.add(obstacle);
        }
    }

    private void generateSnake(int[] arenaDimensions, int headDimensions) {
        boolean snakeCollidedWithObstacle = true;

        // Repete até que a cobra não colida com nenhum obstáculo
        while (snakeCollidedWithObstacle) {
            // Gera a cobra
            this.s = new Snake(arenaDimensions, headDimensions);

            // Verifica se a cobra colidiu com algum obstáculo
            snakeCollidedWithObstacle = s.checkSnakeObstacleColision(s,obstacles);
        }
    }

    public int[] getArenaDimensions() {
        return arenaDimensions;
    }

   
    public ArrayList<Obstacle> getObstacles() {
        return obstacles;
    }

    private void obstaclesmove() {
        // Itera sobre todos os obstáculos na lista
        for (Obstacle obstacle : obstacles) {
            // Obtém o polígono do obstáculo
            Poligono obstacleShape = obstacle.getObstacle();

            // Rotaciona o polígono em torno do ponto de rotação (0, 0)
            Poligono rotatedObstacle = obstacleShape.rotacionar(10, rotacao);

            // Atualiza o polígono do obstáculo com a nova posição após a rotação
            obstacle.setObstacle(rotatedObstacle);
        }
    }

    
    public void Frame() {
        ui.render();
        s.move();

        if (s.CheckFoodEaten(fruit) == true) {
            points++;
            s.grow();
            generateFood(Color.YELLOW, foodtype, this, foodDimensions);

        }

       

        if (s.checkSnakeObstacleColision(s,obstacles) == true) {
            updateRank();
            ui.render();
            Rank.printLeaderboard();
            System.exit(0);
        }

        if (s.checkSnakeInsideArena(arenaDimensions)==true) {
            System.out.println("snake saiu da arena");
                    updateRank();
                    ui.render();
                    Rank.printLeaderboard();
                    System.exit(0);
        }
        
        if ( s.checkSnakeSelfCollision()==true) {
            updateRank();
                ui.render();
                Rank.printLeaderboard();
                System.exit(0);
        } 

        if (this.obstacletype == Obstacle.ObstacleType.D) {
            obstaclesmove();
        }

        ui.render();

    }

    public Snake getS() {
        return s;
    }

    public AbstractFood<?> getFruit() {
        return fruit;
    }

    public void updateRank() {
        // Leitura do conteúdo do arquivo para obter os jogadores existentes
        List<Player> existingPlayers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("rank.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                String playerName = parts[0];
                int playerScore = Integer.parseInt(parts[1]);
                existingPlayers.add(new Player(playerName, playerScore));
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        // Verificação se o jogador atual já está na lista
        boolean playerExists = false;
        for (Player player : existingPlayers) {
            if (player.getName().equals(namePlayer)) {
                playerExists = true;
                // Se o jogador existir, atualizamos seu resultado se for maior
                if (points > player.getScore()) {
                    player.setScore(points);
                    System.out.println("Pontuação atualizada para o jogador " + namePlayer + ": " + points);
                    break; // Não é necessário continuar a busca
                }
            }
        }

        // Se o jogador não existir na lista, adicionamos ele
        if (!playerExists) {
            existingPlayers.add(new Player(namePlayer, points));
            System.out.println("Novo jogador adicionado: " + namePlayer + ", Pontuação: " + points);
        }

        // Escrevendo a lista atualizada no arquivo
        try (FileWriter writer = new FileWriter("rank.txt")) {
            for (Player player : existingPlayers) {
                writer.write(player.getName() + ", " + player.getScore() + "\n");
            }
            System.out.println("Rank atualizado e escrito no arquivo 'rank.txt'");
        } catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo: " + e.getMessage());
        }
    }

}