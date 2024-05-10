package Core;

import java.awt.Color;
import java.util.List; // Importe List do pacote java.util

import Geometry.Circle;
import Geometry.Poligono;
import Geometry.Ponto;
import Geometry.Square;

public abstract class AbstractFood<T extends Shape> {
    protected Color color;
    protected Core.FoodType type;
    protected Arena arena;

    public enum FoodType {
        C,
        S
    }

    public AbstractFood(Color color, Core.FoodType type, Arena arena) {
        this.color = color;
        this.type = type;
        this.arena = arena;
    }

    public abstract void spawnFood(Arena arena);


    public boolean isContainedIn(Poligono poligono) {
        T shape = getShape();

        // Verifica se a forma está contida no polígono
        if (shape instanceof Circle) {

            return poligono.contains((Circle) shape);
            
        } else{

            return poligono.contains((Poligono) shape);
        }
    }
    public abstract List<Ponto> SquareVertices();

    public boolean intersect(Poligono poligono) {
        T shape = getShape();

        // Verifica se a forma está contida no polígono
        if (shape instanceof Circle) {
            return poligono.intersect((Circle) shape);
            
        } else if (shape instanceof Square) {
            return poligono.intersect((Square) shape);
        } else {
            // Caso a forma não seja um círculo ou quadrado, não podemos verificar a contenção
            return false;
        }
    }

    

    // Getters and Setters
    

    public Color getColor() {
        return color;
    }

    public Core.FoodType getType() {
        return type;
    }

    public abstract T getShape(); // Tipo genérico parametrizado com a interface Shape

}
