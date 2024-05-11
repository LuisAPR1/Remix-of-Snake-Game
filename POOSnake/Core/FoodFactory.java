package Core;

import java.awt.Color;

/**
 * Classe responsável por criar instâncias de comida com base no tipo
 * especificado.
 * 
 * @version Versão 1.0 10/05/2024
 * @author Luís Rosa, José Lima, Pedro Ferreira e Pedro Ferreira
 */
public class FoodFactory {

    /**
     * Cria uma instância de comida com base no tipo especificado.
     * 
     * @param color    A cor da comida.
     * @param foodType O tipo de comida a ser criado.
     * @param arena    A arena em que a comida será gerada.
     * @param size     O tamanho da comida.
     * @return A instância de comida criada.
     */
    public static AbstractFood<?> createFood(Color color, Core.FoodType foodType, Arena arena, int size) {
        if (foodType == FoodType.C) {
            return new FoodCircle(color, foodType, arena, size);
        } else if (foodType == FoodType.S) {
            return new FoodSquare(color, foodType, arena, size);
        }
        return null;
    }
}
