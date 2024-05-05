package Core;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import Geometry.Ponto;
import Geometry.Poligono;

public class FoodSquare extends AbstractFood<Poligono> {
    private int sideLength;
    private Poligono p;

    public FoodSquare(Color color, Core.FoodType type, Arena arena, int sideLength) {
        super(color, type, arena);
        this.sideLength = sideLength;
        spawnFood();
    }

    @Override
    public void spawnFood() {
        // Gera uma posição aleatória dentro da arena
        Ponto position = generatePosition();

        // Calcula os pontos para formar um quadrado com base na posição e no tamanho do lado
        List<Ponto> pontos = new ArrayList<>();
        pontos.add(new Ponto(position.getX(), position.getY()));
        pontos.add(new Ponto(position.getX() + sideLength, position.getY()));
        pontos.add(new Ponto(position.getX() + sideLength, position.getY() + sideLength));
        pontos.add(new Ponto(position.getX(), position.getY() + sideLength));

        // Cria o polígono representando o quadrado
        p = new Poligono(pontos);
    }

    @Override
    public Poligono getShape() {
        return p;
    }
}
