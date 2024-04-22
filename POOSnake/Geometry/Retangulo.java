package Geometry;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa um retângulo, um tipo especial de polígono.
 * @version 1.0 03/04/2024
 * @author Luís Rosa
 */
public class Retangulo extends Poligono {

    /**
     * Construtor da classe Retangulo a partir de uma string de entrada.
     * @param input string de entrada contendo as coordenadas dos pontos do retângulo.
     */
    public Retangulo(String input){
        this(toInt(input));
    }

    /**
     * Construtor da classe Retangulo a partir de uma lista de pontos.
     * @param pontos lista de pontos que definem os vértices do retângulo.
     */
    public Retangulo(List<Ponto> pontos) {
        super(pontos);
        check(pontos);
    }

    /**
     * Verifica se a lista de pontos define um retângulo válido.
     * @param pontos lista de pontos que definem os vértices do retângulo.
     */
    private void check(List<Ponto> pontos){
        // Verifica se a quantidade de pontos é igual a 4.
        if (pontos.size() != 4){
            System.out.println("Retangulo:vi");
            System.exit(0);
        }
        // Verifica se os segmentos opostos são perpendiculares.
        for (int i = 0;i<pontos.size();i++){
            if (produtoEscalar(pontos.get(i),pontos.get((i+1) % pontos.size()),pontos.get((i+2) % pontos.size())) != 0){
                System.out.println("Retangulo:vi");
                System.exit(0);
            }
        }
    }

    /**
     * Converte uma string de entrada em uma lista de pontos.
     * @param input string de entrada contendo as coordenadas dos pontos do retângulo.
     * @return lista de pontos que definem os vértices do retângulo.
     */
    protected static List<Ponto> toInt (String input) {
        String [] parts = input.split(" ");
        // Verifica a validade da entrada.
        if(parts.length-1 % 2 == 0)
            System.exit(0);
        List<Ponto> pontos = new ArrayList<>();
        for(int i = 0; i < parts.length; i+=2)
            pontos.add(new Ponto(Integer.parseInt(parts[i]),Integer.parseInt(parts[i+1])));
        return pontos;
    }

    /**
     * Calcula o produto escalar de três pontos.
     * @param a primeiro ponto.
     * @param b segundo ponto.
     * @param c terceiro ponto.
     * @return valor do produto escalar.
     */
    public double produtoEscalar(Ponto a, Ponto b, Ponto c){
        int vetor1x = b.getX()-a.getX();
        int vetor1y = b.getY()-a.getY();
        int vetor2x = c.getX()-b.getX();
        int vetor2y = c.getY()-b.getY();

        double norma1= Math.sqrt(Math.pow(vetor1x, 2) + Math.pow(vetor1y, 2));
        double norma2= Math.sqrt(Math.pow(vetor2x, 2) + Math.pow(vetor2y, 2));

        double produtoVetores = vetor1x*vetor2x + vetor1y*vetor2y;
        return produtoVetores/(norma1*norma2);
    }

    /**
     * Rotaciona o retângulo em torno de um ponto (centroide) por um determinado ângulo.
     * @param anguloGraus ângulo de rotação em graus.
     * @param centroide ponto central em torno do qual o retângulo será rotacionado.
     * @return novo retângulo rotacionado.
     */
    @Override
    public Retangulo rotacionar(int anguloGraus, Ponto centroide) {
        Poligono p = super.rotacionar(anguloGraus, centroide);
        Retangulo end = new Retangulo(p.getPontos());
        return end;
    }

    /**
     * Realiza uma translação no retângulo.
     * @param x deslocamento horizontal.
     * @param y deslocamento vertical.
     * @return novo retângulo transladado.
     */
    @Override
    public Retangulo translacao(int x, int y) {
        Poligono p = super.translacao(x, y);
        Retangulo end = new Retangulo(p.getPontos());
        return end;
    }

    public Retangulo translacaoSemPonto(int x, int y) {
        Poligono p = super.translacaoSemPonto(x, y);
        Retangulo end = new Retangulo(p.getPontos());
        return end;
    }
    /**
     * Retorna a representação textual do retângulo.
     * @return string representando o retângulo.
     */
    @Override
    public String toString() {
        return "Retangulo: " + getPontos().toString();
    }
}
