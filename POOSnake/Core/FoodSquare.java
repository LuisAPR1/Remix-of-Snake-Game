package Core;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import Geometry.Poligono;
import Geometry.Ponto;

public class FoodSquare extends AbstractFood<Poligono> {
    private int sideLength;
    private Poligono p;
    private Ponto position;

    public FoodSquare(Color color, Core.FoodType type, Arena arena, int sideLength) {
        super(color, type, arena);
        this.sideLength = sideLength;
        spawnFood(arena);
    }

    @Override
    public void spawnFood(Arena arena) {
        // Gera uma posição aleatória dentro da arena
        this.position = generatePosition(arena.getHeadDimensions());

        // Calcula os pontos para formar um quadrado com base na posição e no tamanho do lado
        List<Ponto> pontos = new ArrayList<>();
        pontos.add(new Ponto(position.getX(), position.getY()));
        pontos.add(new Ponto(position.getX() + sideLength, position.getY()));
        pontos.add(new Ponto(position.getX() + sideLength, position.getY() + sideLength));
        pontos.add(new Ponto(position.getX(), position.getY() + sideLength));

        // Cria o polígono representando o quadrado
        p = new Poligono(pontos);
    }

    protected Ponto generatePosition(int headSize) {
        int[] arenaDimensions = arena.getArenaDimensions();
        
        // Calcula os limites dentro dos quais a comida deve se encaixar, considerando o tamanho da comida
        int minX = 1; // Menor valor possível para a coordenada X
        int maxX = arenaDimensions[0] - sideLength - 1; // Maior valor possível para a coordenada X, considerando o tamanho da comida
        int minY = 1; // Menor valor possível para a coordenada Y
        int maxY = arenaDimensions[1] - sideLength - 1; // Maior valor possível para a coordenada Y, considerando o tamanho da comida
    
        // Gera aleatoriamente uma posição dentro desses limites
        int x = (int) (Math.random() * (maxX - minX + 1)) + minX; // Gera um valor aleatório para X dentro dos limites
        int y = (int) (Math.random() * (maxY - minY + 1)) + minY; // Gera um valor aleatório para Y dentro dos limites
    
        return new Ponto(x, y);
    }
    

    @Override
    public Poligono getShape() {
        return p;
    }

    @Override
    public List<Ponto> SquareVertices() {
        return p.getPontos();
    }
}
