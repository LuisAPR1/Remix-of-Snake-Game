package Snake;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa um triângulo, que é um tipo especial de polígono.
 * @version 1.0 03/04/2024
 * @author Luís Rosa
 * @inv Um triângulo deve sempre ter exatamente 3 pontos.
 */
public class Triangulo extends Poligono {

    /**
     * Construtor da classe Triangulo a partir de uma string de entrada.
     * @param input string de entrada contendo as coordenadas dos pontos do triângulo.
     */
    public Triangulo(String input) {
        this(toInt(input));
    }

    /**
     * Construtor da classe Triangulo a partir de uma lista de pontos.
     * @param pontos lista de pontos que definem os vértices do triângulo.
     */
    public Triangulo(List<Ponto> pontos) {
        super(pontos);
        // Verifica se a quantidade de pontos é diferente de 3.
        if (pontos.size() != 3){
            System.out.println("Triângulo:vi");
            System.exit(0);
        }
    }

    /**
     * Converte uma string de entrada em uma lista de pontos.
     * @param input string de entrada contendo as coordenadas dos pontos do triângulo.
     * @return lista de pontos que definem os vértices do triângulo.
     */
    private static List<Ponto> toInt (String input) {
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
     * Rotaciona o triângulo em torno de um ponto (centroide) por um determinado ângulo.
     * @param anguloGraus ângulo de rotação em graus.
     * @param centroide ponto central em torno do qual o triângulo será rotacionado.
     * @return novo triângulo rotacionado.
     */
    @Override
    public Triangulo rotacionar(int anguloGraus, Ponto centroide) {
        Poligono p = super.rotacionar(anguloGraus, centroide);
        Triangulo end = new Triangulo(p.getPontos());
        return end;
    }

    /**
     * Realiza uma translação no triângulo.
     * @param x deslocamento horizontal.
     * @param y deslocamento vertical.
     * @return novo triângulo transladado.
     */
    @Override
    public Triangulo translacao(int x, int y) {
        Poligono p = super.translacao(x, y);
        Triangulo end = new Triangulo(p.getPontos());
        return end;
    }

    public Triangulo translacaoSemPonto(int x, int y) {
        Poligono p = super.translacaoSemPonto(x, y);
        Triangulo end = new Triangulo(p.getPontos());
        return end;
    }
    /**
     * Retorna a representação textual do triângulo.
     * @return string representando o triângulo.
     */
    @Override
    public String toString() {
        return "Triangulo: " + getPontos().toString();
    }
}
