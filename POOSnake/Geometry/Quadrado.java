package Geometry;

import java.util.List;

/**
 * Classe que representa um quadrado, um tipo especial de retângulo.
 * @version 1.0 03/04/2024
 * @author Luís Rosa
 */
public class Quadrado extends Retangulo {

    /**
     * Construtor da classe Quadrado a partir de uma string de entrada.
     * @param input string de entrada contendo as coordenadas dos pontos do quadrado.
     */
    public Quadrado(String input) {
        this(toInt(input));
    }

    /**
     * Construtor da classe Quadrado a partir de uma lista de pontos.
     * @param pontos lista de pontos que definem os vértices do quadrado.
     */
    public Quadrado(List<Ponto> pontos) {
        super(pontos);
        check(pontos);
    }

    /**
     * Verifica se a lista de pontos define um quadrado válido.
     * @param pontos lista de pontos que definem os vértices do quadrado.
     */
    private void check(List<Ponto> pontos) {
        // Verifica se a quantidade de pontos é igual a 4.
        if (pontos.size() != 4) {
            System.out.println("Quadrado:vi");
            System.exit(0);
        }
        // Verifica se os lados opostos têm o mesmo comprimento.
        double lado = pontos.get(0).dist(pontos.get(1));
        for (int i = 1; i < pontos.size(); i++) {
            if (lado != pontos.get(i).dist(pontos.get((i + 1) % pontos.size()))) {
                System.out.println("Quadrado:vi");
                System.exit(0);
            }
        }
    }

    /**
     * Rotaciona o quadrado em torno de um ponto (centroide) por um determinado ângulo.
     * @param anguloGraus ângulo de rotação em graus.
     * @param centroide ponto central em torno do qual o quadrado será rotacionado.
     * @return novo quadrado rotacionado.
     */
    @Override
    public Quadrado rotacionar(int anguloGraus, Ponto centroide) {
        Poligono p = super.rotacionar(anguloGraus, centroide);
        Quadrado end = new Quadrado(p.getPontos());
        return end;
    }

    /**
     * Realiza uma translação no quadrado.
     * @param x deslocamento horizontal.
     * @param y deslocamento vertical.
     * @return novo quadrado transladado.
     */
    @Override
    public Quadrado translacao(int x, int y) {
        Poligono p = super.translacao(x, y);
        Quadrado end = new Quadrado(p.getPontos());
        return end;
    }

    public Quadrado translacaoSemPonto(int x, int y) {
        Poligono p = super.translacaoSemPonto(x, y);
        Quadrado end = new Quadrado(p.getPontos());
        return end;
    }
    /**
     * Retorna a representação textual do quadrado.
     * @return string representando o quadrado.
     */
    @Override
    public String toString() {
        return "Quadrado: " + getPontos().toString();
    }
}
