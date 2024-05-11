package Core;

/**
 * Classe que representa um jogador.
 * 
 * Um jogador possui um nome e uma pontuação.
 * 
 * @author Luís Rosa, José Lima, Pedro Ferreira, José Lima, Pedro Ferreira
 * @version Versão 1.0 10/05/2024
 */
public class Player {

    // Pontuação do jogador
    private int score;
    // Nome do jogador
    private String name;

    /**
     * Obtém a pontuação do jogador.
     * 
     * @return A pontuação do jogador.
     */
    public int getScore() {
        return score;
    }

    /**
     * Define a pontuação do jogador.
     * 
     * @param score A nova pontuação do jogador.
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Obtém o nome do jogador.
     * 
     * @return O nome do jogador.
     */
    public String getNome() {
        return name;
    }

    /**
     * Define o nome do jogador.
     * 
     * @param name O novo nome do jogador.
     */
    public void setNome(String name) {
        this.name = name;
    }

    /**
     * Construtor para criar um jogador com nome e pontuação inicial.
     * 
     * @param name  O nome do jogador.
     * @param score A pontuação inicial do jogador.
     */
    public Player(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /**
     * Obtém o nome do jogador.
     * 
     * @return O nome do jogador.
     */
    public String getName() {
        return name;
    }

    /**
     * Retorna uma representação em forma de string do jogador.
     * 
     * @return Uma string representando o jogador.
     */
    @Override
    public String toString() {
        return ("Name: " + score + " || " + "Score: " + score);
    }

}
