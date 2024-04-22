package Geometry;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Classe que representa um polígono.
 * @version 1.0 03/04/2024
 * @author Luís Rosa e Pedro Ferreira
 * @inv O polígono tem de ter pelo m pontos.
 */
public class Poligono {
    protected final List<Ponto> pontos; // Lista de pontos que definem os vértices do polígono.
    protected final List<Segmento> segmentoDeRetas = new ArrayList<>(); // Lista de segmentos de reta do polígono.

    /**
     * Construtor da classe Poligono que recebe uma string de entrada.
     * @param input string de entrada contendo as coordenadas dos pontos.
     */
    public Poligono(String input) {
        this(toInt(input));
    }

    /**
     * Construtor da classe Poligono que recebe uma lista de pontos.
     * @param pontos Lista de pontos que definem os vértices do polígono.
     * Deve conter pelo menos 3 pontos para formar um polígono válido.
     */
    public Poligono(List<Ponto> pontos) {
        // Verifica se o polígono tem menos de 3 pontos.
        if (pontos.size() < 3) {
            System.out.println("false");
            System.exit(0);
        }

        // Cria segmentos de reta entre cada par de pontos consecutivos.
        for (int i = 0; i < pontos.size(); i++) {
            segmentoDeRetas.add(new Segmento(pontos.get(i), pontos.get((i + 1) % pontos.size())));
        }

        // Verifica se os pontos são colineares e se as arestas se cruzam.
        for (int i = 0; i < pontos.size(); i++) {
            Reta reta = new Reta(pontos.get(i), pontos.get((i + 1) % pontos.size()));
            if (reta.colineares(pontos.get((i + 2) % pontos.size()))) {
                System.out.println("Poligono:vi");
                System.exit(0);
            }
            if (segmentoDeRetas.get(i).arestasCruzam(segmentoDeRetas.get((i + 2) % pontos.size()))) {
                System.out.println("Poligono:vi");
                System.exit(0);
            }
        }
        this.pontos = pontos;
    }

    /**
     * Obtém a lista de pontos do polígono.
     * @return Lista de pontos que formam o polígono.
     */
    public List<Ponto> getPontos() {
        return pontos;
    }

    /**
     * Obtém a lista de segmentos de reta do polígono.
     * @return Lista de segmentos de reta que formam as arestas do polígono.
     */
    public List<Segmento> getSegmentoDeRetas() {
        return segmentoDeRetas;
    }

    /**
     * Converte uma string de entrada em uma lista de pontos.
     * @param input String de entrada contendo as coordenadas dos pontos.
     * @return Lista de pontos que formam o polígono.
     */
    private static List<Ponto> toInt(String input) {
        String[] parts = input.split(" ");
        if ((parts.length - 2) % 2 == 0)
            System.exit(0);

        List<Ponto> pontos = new ArrayList<>();
        for (int i = 1, count = 0; count < Integer.parseInt(parts[0]); i += 2, count++) {
            pontos.add(new Ponto(Integer.parseInt(parts[i]), Integer.parseInt(parts[i + 1])));
        }
        return pontos;
    }

    /**
     * Verifica se dois polígonos são iguais.
     * @param object Objeto a ser comparado.
     * @return true se os polígonos forem iguais, false caso contrário.
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null) return false;
        Poligono poligono = (Poligono) object;
        if (segmentoDeRetas.size() != poligono.getSegmentoDeRetas().size()) {
            return false;
        }
        List<Segmento> segmentoCopy = new ArrayList<>(poligono.getSegmentoDeRetas());
        for (int i = 0; i < segmentoDeRetas.size(); i++) {
            for (int j = 0; j < poligono.getSegmentoDeRetas().size(); j++) {
                if (segmentoDeRetas.get(i).equals(segmentoCopy.get(j))) {
                    segmentoCopy.remove(j % poligono.getSegmentoDeRetas().size());
                    break;
                }
            }
        }
        return segmentoCopy.isEmpty();
    }

    /**
     * Calcula o código de hash para o polígono.
     * @return Código de hash do polígono.
     */
    @Override
    public int hashCode() {
        return Objects.hash(pontos, segmentoDeRetas);
    }

    /**
     * Calcula o centro do polígono.
     * @return Ponto representando o centro do polígono.
     */
    public Ponto calcularCentro() {
        double centroX = 0;
        double centroY = 0;
        for (Ponto ponto : pontos) {
            centroX += ponto.getX();
            centroY += ponto.getY();
        }
        centroX /= pontos.size();
        centroY /= pontos.size();
        return new Ponto(centroX, centroY);
    }

    /**
     * Rotaciona o polígono em torno de um ponto central por um determinado ângulo.
     * @param anguloGraus Ângulo de rotação em graus.
     * @param centroide Ponto central em torno do qual o polígono será rotacionado.
     * @return Novo polígono rotacionado.
     */
    public Poligono rotacionar(int anguloGraus, Ponto centroide) {
        List<Ponto> pontosPol = new ArrayList<>();
        for (Ponto ponto : pontos) {
            pontosPol.add(ponto.rotacionar(anguloGraus, centroide));
        }
        return new Poligono(pontosPol);
    }

    /**
     * Realiza uma translação no polígono.
     * @param x Deslocamento horizontal.
     * @param y Deslocamento vertical.
     * @return Novo polígono transladado.
     */
    public Poligono translacao(int x, int y) {
        Ponto centro = calcularCentro();
        int deltaX = (int) (x - centro.getxDouble());
        int deltaY = (int) (y - centro.getyDouble());
        List<Ponto> pontosPol = new ArrayList<>();
        for (Ponto ponto : pontos) {
            pontosPol.add(ponto.translacionar(deltaX, deltaY));
        }
        return new Poligono(pontosPol);
    }

    public Poligono translacaoSemPonto(int x, int y) {

        List<Ponto> pontosCopy = new ArrayList<>();
        for (Ponto ponto : pontos) {
            pontosCopy.add(ponto.translacionar(x, y));
        }
        return new Poligono(pontosCopy);
    }

    /**
     * Retorna uma representação textual do polígono.
     * @return String representando o polígono.
     */
    @Override
    public String toString() {
        return "Poligono de " + pontos.size() + " vertices: " + getPontos().toString();
    }
}
