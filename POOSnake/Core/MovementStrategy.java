package Core;

/**
 * Interface para definir diferentes estratégias de movimento.
 * 
 * @version Versão 1.0 10/05/2024
 * @author Luís Rosa, José Lima, Pedro Ferreira e Pedro Ferreira
 */
public interface MovementStrategy {
    
    /**
     * Método para receber entrada de movimento.
     */
    void input();

    void setDirectionG(int i);

    void move();
}
