package Core;

import java.awt.Color;
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

        this.rank = new Rank(players);
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
        while (true) 
            movementStrategy.input();
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

// Dentro do método generateFood na classe Arena
private void generateFood(Color color, FoodType foodType, Arena arena, int foodDimensions) {
    boolean foodIntersects = true;
    while (foodIntersects) {
        fruit = FoodFactory.createFood(color, foodType, arena, foodDimensions);
        // Verificar se a comida está dentro de um obstáculo
        foodIntersects = checkFoodObstacleCollision(fruit);
        if (!foodIntersects) {
            // Verificar se a comida está dentro da cobra
            foodIntersects = checkFoodSnakeCollision(fruit);
        }
        // Se ainda houver interseção, gerar uma nova posição
        if (foodIntersects) {
            fruit.spawnFood(arena);
        }
    }
}

// Dentro do método checkFoodObstacleCollision na classe Arena
private boolean checkFoodObstacleCollision(AbstractFood<?> food) {
    for (Obstacle obstacle : obstacles) {
        if (food.intersect(obstacle.getObstacle())) {
            return true;
        }
    }
    return false;
}

// Dentro do método checkFoodSnakeCollision na classe Arena
private boolean checkFoodSnakeCollision(AbstractFood<?> food) {
    List<Square> squares = s.getSnake();
    for (Square square : squares) {
        if (food.intersect(square)) {
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
            rank.updateRank(namePlayer, points);
            ui.render();
            Rank.printLeaderboard();
            System.exit(0);
        }

        if (s.checkSnakeInsideArena(arenaDimensions)==true) {
            System.out.println("snake saiu da arena");
            rank.updateRank(namePlayer, points);
                    ui.render();
                    Rank.printLeaderboard();
                    System.exit(0);
        }
        
        if ( s.checkSnakeSelfCollision()==true) {
            rank.updateRank(namePlayer, points);
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

    

}