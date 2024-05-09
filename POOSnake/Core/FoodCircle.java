package Core;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import Geometry.Circle;
import Geometry.Ponto;

public class FoodCircle extends AbstractFood<Circle> {
    private int diametro;
    Circle shape;
    protected Ponto position;

    public FoodCircle(Color color, Core.FoodType type, Arena arena, int diametro) {
        super(color, type, arena);
        this.diametro = diametro;
        spawnFood(arena);
    }

    @Override
    public void spawnFood(Arena arena) {
        this.position = generatePosition(arena.getHeadDimensions());
        this.shape = new Circle(position, diametro);
    }

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
    
        return new Ponto(x, y);
    }
    

    @Override
    public List<Ponto> SquareVertices() {
        List<Ponto> squareVertices = new ArrayList<>();

        // Calcula os limites do quadrado que envolve o círculo
        int minX = position.getX() - (diametro/2);
        int maxX = position.getX() + (diametro/2);
        int minY = position.getY() - (diametro/2);
        int maxY = position.getY() + (diametro/2);



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