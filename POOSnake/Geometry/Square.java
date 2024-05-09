package Geometry;

import java.util.List;

/**
 * Classe que representa um quadrado, um tipo especial de retângulo.
 * 
 * @version 1.0 03/04/2024
 * @author Luís Rosa
 */
public class Square extends Retangulo {

    /**
     * Construtor da classe Quadrado a partir de uma string de entrada.
     * 
     * @param input string de entrada contendo as coordenadas dos pontos do
     *              quadrado.
     */
    public Square(String input) {
        this(toInt(input));
    }

    /**
     * Construtor da classe Quadrado a partir de uma lista de pontos.
     * 
     * @param pontos lista de pontos que definem os vértices do quadrado.
     */
    public Square(List<Ponto> pontos) {
        super(pontos);
        check(pontos);
    }

    /**
     * Verifica se a lista de pontos define um quadrado válido.
     * 
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
     * Rotaciona o quadrado em torno de um ponto (centroide) por um determinado
     * ângulo.
     * 
     * @param anguloGraus ângulo de rotação em graus.
     * @param centroide   ponto central em torno do qual o quadrado será
     *                    rotacionado.
     * @return novo quadrado rotacionado.
     */
    @Override
    public Square rotacionar(int anguloGraus, Ponto centroide) {
        Poligono p = super.rotacionar(anguloGraus, centroide);
        Square end = new Square(p.getPontos());
        return end;
    }

    public boolean distance(Poligono poligono) {
        // Obtém o centro do polígono fornecido
        Ponto centroPoligono = poligono.calcularCentro();

        // Obtém o centro do quadrado
        Ponto centroQuadrado = calcularCentro();
        // Calcula a distância entre os centros dos dois polígonos
        double distancia = centroQuadrado.dist(centroPoligono);

        // Verifica se a distância é menor que o tamanho do lado do quadrado
        return distancia < tamanhoLado();
    }

    public double tamanhoLado() {
        // Obtém dois vértices opostos do quadrado
        Ponto ponto1 = getPontos().get(0);
        Ponto ponto2 = getPontos().get(1);

        // Calcula a distância entre esses dois pontos, que é o tamanho do lado do
        // quadrado

        return ponto1.dist(ponto2);
    }

    /**
     * Realiza uma translação no quadrado.
     * 
     * @param x deslocamento horizontal.
     * @param y deslocamento vertical.
     * @return novo quadrado transladado.
     */
    @Override
    public Square translacao(int x, int y) {
        Poligono p = super.translacao(x, y);
        Square end = new Square(p.getPontos());
        return end;
    }

    public Square translacaoSemPonto(int x, int y) {
        Poligono p = super.translacaoSemPonto(x, y);
        Square end = new Square(p.getPontos());
        return end;
    }

    /**
     * Retorna a representação textual do quadrado.
     * 
     * @return string representando o quadrado.
     */
    @Override
    public String toString() {
        return "Quadrado: " + getPontos().toString();
    }
}
