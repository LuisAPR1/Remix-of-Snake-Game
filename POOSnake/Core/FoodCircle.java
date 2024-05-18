package Core;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import Geometry.Circle;
import Geometry.Ponto;

/**
 * Classe para representar uma comida na forma de um círculo na arena do jogo.
 * 
 * @version Versão 1.0 10/05/2024
 * @author Luís Rosa, José Lima, Pedro Ferreira e Pedro Ferreira
 */
public class FoodCircle extends AbstractFood<Circle> {
    private int diametro;
    private Circle shape;
    public Ponto position;

    /**
     * Construtor para criar uma comida na forma de um círculo.
     * 
     * @param color    A cor da comida.
     * @param type     O tipo de comida.
     * @param arena    A arena em que a comida será gerada.
     * @param diametro O diâmetro do círculo.
     */
    public FoodCircle(Color color, Core.FoodType type, Arena arena, int diametro) {
        super(color, type, arena);
        this.diametro = diametro;
        spawnFood(arena);
        System.out.println("FRUTA NO CENTRO - " + position);
    }

    /**
     * Gera uma posição aleatória para a comida.
     * 
     * @param headSize O tamanho da cabeça da cobra.
     * @return A posição gerada.
     */
    protected Ponto generatePosition(int headSize) {
        int[] arenaDimensions = arena.getArenaDimensions();
        int radius = diametro / 2;

        // Determina o salto máximo que a cobra pode fazer em todas as direções
        int maxJump = headSize;

        // Calcula os limites dentro dos quais a comida pode ser gerada
        int minX = radius + 1; // Menor valor possível para a coordenada X, garantindo que seja maior que 0
        int maxX = arenaDimensions[0] - radius; // Maior valor possível para a coordenada X
        int minY = radius + 1; // Menor valor possível para a coordenada Y, garantindo que seja maior que 0
        int maxY = arenaDimensions[1] - radius; // Maior valor possível para a coordenada Y

        // Ajusta os limites para garantir que a comida seja colocada em uma posição
        // onde a cobra possa alcançá-la
        minX += maxJump; // Adiciona o salto máximo à posição mínima X
        maxX -= maxJump; // Subtrai o salto máximo à posição máxima X
        minY += maxJump; // Adiciona o salto máximo à posição mínima Y
        maxY -= maxJump; // Subtrai o salto máximo à posição máxima Y

        // Gera aleatoriamente uma posição dentro desses limites ajustados
        int x = (int) (Math.random() * (maxX - minX + 1)) + minX; // Gera um valor aleatório para X dentro dos limites
        int y = (int) (Math.random() * (maxY - minY + 1)) + minY; // Gera um valor aleatório para Y dentro dos limites
        x += diametro;
        y += diametro;

        return new Ponto(x, y);
    }

    @Override
    public void spawnFood(Arena arena) {
        int maxTries = 10000;
        int tries = 0;
        int movementIncrement = arena.getHeadDimensions();
        while (tries < maxTries) {
            int x = (int) (Math.random() * (arena.getArenaDimensions()[0] - diametro)) + diametro/2;
            int y = (int) (Math.random() * (arena.getArenaDimensions()[1] - diametro)) + diametro/2;
            boolean reachableBySnake = (x % movementIncrement == 0) && (y % movementIncrement == 0);
            boolean intersectsObstacle = false; // Flag para verificar interseção com obstáculo
            x += diametro/2;
            y += diametro/2;
            if (reachableBySnake) {
                Ponto a = new Ponto(x, y);
                this.shape = new Circle(a, diametro);
                this.position = a;

                // Verificar se a comida não intersecta com nenhum obstáculo
                for (Obstacle obstacle : arena.getObstacles()) {
                    if (this.shape.intersect(obstacle.getObstacle())) {
                        intersectsObstacle = true;
                        break;
                    }
                }
                if (!intersectsObstacle) {
                    return;
                }
            }
            tries++;

        }

        // Se não encontrar uma posição válida, gerar uma posição aleatória fora dos
        // obstáculos
        this.position = generatePosition(arena.getHeadDimensions());
        this.shape = new Circle(position, diametro);
    }

    @Override
    public List<Ponto> SquareVertices() {
        List<Ponto> squareVertices = new ArrayList<>();

        // Calcula os limites do quadrado que envolve o círculo
        int minX = (int) position.getX() - (diametro / 2);
        int maxX = (int) position.getX() + (diametro / 2);
        int minY = (int) position.getY() - (diametro / 2);
        int maxY = (int) position.getY() + (diametro / 2);

        // Adiciona os vértices do quadrado à lista de vértices
        squareVertices.add(new Ponto(minX, minY)); // Vértice inferior esquerdo
        squareVertices.add(new Ponto(maxX, minY)); // Vértice inferior direito
        squareVertices.add(new Ponto(maxX, maxY)); // Vértice superior esquerdo
        squareVertices.add(new Ponto(minX, maxY)); // Vértice superior direito

        return squareVertices;
    }

    @Override
    public Circle getShape() {
        return shape;
    }

}
