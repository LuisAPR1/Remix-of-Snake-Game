package Core;

import java.awt.Color;
import java.util.List;
import Geometry.Circle;
import Geometry.Poligono;
import Geometry.Ponto;
import Geometry.Square;

/**
 * Classe abstrata que representa um alimento no jogo.
 * 
 * Alimentos podem ter diferentes formas e cores.
 * 
 * @author Luís Rosa, José Lima, Pedro Ferreira, José Lima, Pedro Ferreira
 * @version Versão 1.0 10/05/2024
 * 
 * @param <T> O tipo genérico que estende a interface Shape.
 */
public abstract class AbstractFood<T extends Shape> {
    protected Ponto position;
    protected Color color;
    protected Core.FoodType type;
    protected Arena arena;

    /**
     * Construtor para criar um alimento com cor, tipo e arena específicos.
     * 
     * @param color A cor do alimento.
     * @param type  O tipo de alimento.
     * @param arena A arena em que o alimento será gerado.
     */
    public AbstractFood(Color color, Core.FoodType type, Arena arena) {
        this.color = color;
        this.type = type;
        this.arena = arena;
    }

    /**
     * Método abstrato para gerar o alimento na arena.
     * 
     * @param arena A arena em que o alimento será gerado.
     */
    public abstract void spawnFood(Arena arena);

    /**
     * Verifica se o alimento está contido em um polígono.
     * 
     * @param poligono O polígono no qual verificar a contenção.
     * @return true se o alimento estiver contido no polígono, caso contrário,
     *         false.
     */
    public boolean isContainedIn(Poligono poligono) {
        T shape = getShape();

        // Verifica se a forma está contida no polígono
        if (shape instanceof Circle) {
            return poligono.contains((Circle) shape);
        } else {
            return poligono.contains((Poligono) shape);
        }
    }

    /**
     * Método abstrato para obter os vértices de um quadrado.
     * 
     * @return Uma lista de pontos que representam os vértices do quadrado.
     */
    public abstract List<Ponto> SquareVertices();

    /**
     * Verifica se o alimento intersecta um polígono.
     * 
     * @param poligono O polígono com o qual verificar a interseção.
     * @return true se houver interseção, caso contrário, false.
     */
    public boolean intersect(Poligono poligono) {
        T shape = getShape();

        // Verifica se a forma está contida no polígono
        if (shape instanceof Circle) {
            return poligono.intersect((Circle) shape);
        } else if (shape instanceof Square) {
            return poligono.intersect((Square) shape);
        } else {
            // Caso a forma não seja um círculo ou quadrado, não podemos verificar a
            // contenção
            return false;
        }
    }

    /**
     * Verifica se o alimento compartilha pontos com um polígono.
     * 
     * @param poligono O polígono com o qual verificar a compartilhamento de pontos.
     * @return true se houver compartilhamento de pontos, caso contrário, false.
     */
    public boolean sharePoints(Poligono poligono) {
        T shape = getShape();

        // Verifica se a forma compartilha pontos com o polígono
        if (shape instanceof Circle) {
            return poligono.sharePoints((Circle) shape);
        } else if (shape instanceof Square) {
            return poligono.sharePoints((Square) shape);
        } else {
            // Caso a forma não seja um círculo ou quadrado, não podemos verificar o
            // compartilhamento de pontos
            return false;
        }
    }

    /**
     * Verifica se o alimento está completamente contido em um polígono.
     * 
     * @param poligono O polígono com o qual verificar a contenção.
     * @return true se o alimento estiver completamente contido no polígono, caso
     *         contrário, false.
     */
    public boolean contains(Poligono poligono) {
        T shape = getShape();

        // Verifica se a forma está contida no polígono
        if (shape instanceof Circle) {
            return poligono.contains((Circle) shape);
        } else if (shape instanceof Square) {
            return poligono.contains((Square) shape);
        } else {
            // Caso a forma não seja um círculo ou quadrado, não podemos verificar a
            // contenção
            return false;
        }
    }

    /**
     * Verifica a distância entre o alimento e um polígono.
     * 
     * @param poligono O polígono com o qual verificar a distância.
     * @return true se a distância for satisfatória, caso contrário, false.
     */
    public boolean distance(Poligono poligono) {
        T shape = getShape();

        // Verifica se a forma está contida no polígono
        if (shape instanceof Circle) {
            return poligono.distance((Circle) shape);
        } else if (shape instanceof Square) {
            return poligono.distance((Square) shape);
        } else {
            // Caso a forma não seja um círculo ou quadrado, não podemos verificar a
            // distância
            return false;
        }
    }

    /**
     * Obtém a posição do alimento.
     * 
     * @return A posição do alimento como um ponto.
     */
    public Ponto getPosition() {
        return position;
    }

    /**
     * Obtém a cor do alimento.
     * 
     * @return A cor do alimento.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Obtém o tipo de alimento.
     * 
     * @return O tipo de alimento.
     */
    public Core.FoodType getType() {
        return type;
    }

    /**
     * Obtém a forma do alimento.
     * 
     * @return A forma do alimento.
     */
    public abstract T getShape(); // Tipo genérico parametrizado com a interface Shape

}
