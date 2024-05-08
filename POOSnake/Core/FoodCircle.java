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

    protected Ponto generatePosition(int head) {
        int[] arenaDimensions = arena.getArenaDimensions();
        int radius = diametro / 2;

        // Calcula os limites dentro dos quais o círculo completo deve se encaixar
        int minX = radius + 1; // Menor valor possível para a coordenada X, garantindo que seja maior que 0
        int maxX = arenaDimensions[0] - radius; // Maior valor possível para a coordenada X
        int minY = radius + 1; // Menor valor possível para a coordenada Y, garantindo que seja maior que 0
        int maxY = arenaDimensions[1] - radius; // Maior valor possível para a coordenada Y

        // Ajusta os limites para garantir que a comida seja colocada em uma posição
        // onde a cabeça da cobra possa alcançá-la
        // por exemplo, se a cabeça da cobra for 2x2, a comida não deve ser colocada em
        // uma posição onde a cobra só pode passar
        // parcialmente sobre ela
        int headSize = head; // Tamanho da cabeça da cobra
        minX += headSize / 2; // Adiciona metade do tamanho da cabeça à posição mínima X
        maxX -= headSize / 2; // Subtrai metade do tamanho da cabeça à posição máxima X
        minY += headSize / 2; // Adiciona metade do tamanho da cabeça à posição mínima Y
        maxY -= headSize / 2; // Subtrai metade do tamanho da cabeça à posição máxima Y

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

        System.out.println(squareVertices.toString());

        return squareVertices;
    }

    @Override
    public Circle getShape() {
        return shape;
    }

    

}