package Core;

import java.awt.Color;
import java.util.List;
import Geometry.Circle;
import Geometry.Poligono;
import Geometry.Ponto;
import Geometry.Segmento;
import Geometry.Square;

public abstract class AbstractFood<T extends Shape> {
    protected Ponto position;
    protected Color color;
    protected Core.FoodType type;
    protected Arena arena;

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
    

    public boolean intersect4(Poligono poligono) {
        T shape = getShape();

        // Verifica se a forma está contida no polígono
        if (shape instanceof Circle) {
            return poligono.intersect4((Circle) shape);
            
        } else if (shape instanceof Square) {
            return poligono.intersect4((Square) shape);
        } else {
            // Caso a forma não seja um círculo ou quadrado, não podemos verificar a contenção
            return false;
        }
    }

    public boolean sharePoints(Poligono poligono) {
        T shape = getShape();

        // Verifica se a forma está contida no polígono
        if (shape instanceof Circle) {
            return poligono.sharePoints((Circle) shape);
            
        } else if (shape instanceof Square) {
            return poligono.sharePoints((Square) shape);
        } else {
            // Caso a forma não seja um círculo ou quadrado, não podemos verificar a contenção
            return false;
        }
    }

    public boolean contains(Poligono poligono) {
        T shape = getShape();

        // Verifica se a forma está contida no polígono
        if (shape instanceof Circle) {
            return poligono.contains((Circle) shape);
            
        } else if (shape instanceof Square) {
            return poligono.contains((Square) shape);
        } else {
            // Caso a forma não seja um círculo ou quadrado, não podemos verificar a contenção
            return false;
        }
    }

    public boolean distance(Poligono poligono) {
        T shape = getShape();

        // Verifica se a forma está contida no polígono
        if (shape instanceof Circle) {
            return poligono.distance((Circle) shape);
            
        } else if (shape instanceof Square) {
            return poligono.distance((Square) shape);
        } else {
            // Caso a forma não seja um círculo ou quadrado, não podemos verificar a contenção
            return false;
        }
    }


    // Getters and Setters
    public Ponto getPosition() {
        return position;
    }

    public Color getColor() {
        return color;
    }

    public Core.FoodType getType() {
        return type;
    }

    public abstract T getShape(); // Tipo genérico parametrizado com a interface Shape

}
