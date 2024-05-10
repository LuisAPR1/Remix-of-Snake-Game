package Core;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import Geometry.Poligono;
import Geometry.Ponto;


public class FoodSquare extends AbstractFood<Poligono> {
    private int sideLength;
    private Poligono p;

    public FoodSquare(Color color, Core.FoodType type, Arena arena, int sideLength) {
        super(color, type, arena);
        this.sideLength = sideLength;
        spawnFood(arena);
    }

   @Override
public void spawnFood(Arena arena) {
    // Calcula o número máximo de tentativas para evitar um loop infinito
    int maxTries = 100;
    int tries = 0;

    // Tamanho do incremento em que a cobra se move
    int movementIncrement = arena.getHeadDimensions(); // Supondo que a cobra se move em incrementos do tamanho da cabeça

    // Repete até encontrar uma posição válida para a comida ou atingir o número máximo de tentativas
    while (tries < maxTries) {
        // Gera coordenadas aleatórias dentro da arena
        int x = (int) (Math.random() * (arena.getArenaDimensions()[0] - sideLength));
        int y = (int) (Math.random() * (arena.getArenaDimensions()[1] - sideLength));

        // Verifica se a posição gerada está em uma grade que a cobra pode alcançar
        boolean reachableBySnake = (x % movementIncrement == 0) && (y % movementIncrement == 0);

        // Se a posição for alcançável pela cobra, define a posição da comida e sai do loop
        if (reachableBySnake) {
            List<Ponto> pontos = new ArrayList<>();
            pontos.add(new Ponto(x, y));
            pontos.add(new Ponto(x + sideLength, y));
            pontos.add(new Ponto(x + sideLength, y + sideLength));
            pontos.add(new Ponto(x, y + sideLength));
            p = new Poligono(pontos);
            return;
        }

        tries++;
    }

    // Se após o número máximo de tentativas ainda não foi possível encontrar uma posição válida,
    // simplesmente escolhe uma posição aleatória dentro da arena
    int x = (int) (Math.random() * (arena.getArenaDimensions()[0] - sideLength));
    int y = (int) (Math.random() * (arena.getArenaDimensions()[1] - sideLength));
    List<Ponto> pontos = new ArrayList<>();
    pontos.add(new Ponto(x, y));
    pontos.add(new Ponto(x + sideLength, y));
    pontos.add(new Ponto(x + sideLength, y + sideLength));
    pontos.add(new Ponto(x, y + sideLength));
    p = new Poligono(pontos);
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
