package Geometry;

import java.util.ArrayList;
import java.util.List;
import Core.Shape;

/**
 * Classe que representa um círculo com centro e raio.
 * 
 * @version Versão 1.0 10/05/2024
 * @author Luís Rosa, José Lima, Pedro Ferreira e Pedro Ferreira
 */

public class Circle implements Shape {
    Ponto centro; // O centro do círculo
    int raio; // O raio do círculo

    /**
     * Construtor para criar um círculo com um centro e raio específicos.
     * 
     * @param centro O ponto que representa o centro do círculo.
     * @param raio   O raio do círculo.
     */
    public Circle(Ponto centro, int raio) {
        setCentro(centro);
        setRaio(raio / 2); // O raio fornecido é dividido por 2, pois o círculo é representado pela metade
                           // do raio
    }

    /**
     * Obtém o centro do círculo.
     * 
     * @return O ponto representando o centro do círculo.
     */
    public Ponto getCentro() {
        return centro;
    }

    /**
     * Define o centro do círculo.
     * 
     * @param centro O ponto que representa o novo centro do círculo.
     */
    public void setCentro(Ponto centro) {
        this.centro = centro;
    }

    /**
     * Obtém todas as coordenadas que compõem o círculo.
     * 
     * @return Uma lista de pontos representando todas as coordenadas do círculo.
     */
    public List<Ponto> getAllCoordinates() {
        // Inicializa a lista para armazenar as coordenadas
        List<Ponto> coordenadas = new ArrayList<>();

        // Obtém as coordenadas do centro do círculo
        double centroX = this.centro.getX();
        double centroY = this.centro.getY();

        // Encontra os limites do quadrado que circunscreve o círculo
        int minX = (int) centroX - raio;
        int maxX = (int) centroX + raio;
        int minY = (int) centroY - raio;
        int maxY = (int) centroY + raio;

        // Itera sobre todas as coordenadas inteiras dentro do quadrado circunscrito
        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                // Verifica se a coordenada está dentro do círculo usando a equação do círculo
                if ((x - centroX) * (x - centroX) + (y - centroY) * (y - centroY) <= raio * raio) {
                    coordenadas.add(new Ponto(x, y));
                }
            }
        }

        // Retorna a lista de coordenadas
        return coordenadas;
    }

    /**
     * Obtém o raio do círculo.
     * 
     * @return O raio do círculo.
     */
    public int getRaio() {
        return raio;
    }

    /**
     * Define o raio do círculo.
     * 
     * @param raio O novo raio do círculo.
     */
    public void setRaio(int raio) {
        this.raio = raio;
    }

    /**
     * Obtém a posição do círculo (no caso, o centro).
     * 
     * @return O ponto representando a posição do círculo.
     */
    @Override
    public Ponto getPosition() {
        return centro;
    }

    /**
     * Verifica se o círculo intersecta com um polígono.
     * 
     * @param polygon O polígono com o qual verificar a interseção.
     * @return true se houver interseção, caso contrário false.
     */
    public boolean intersect(Poligono polygon) {
        // Obtém todas as coordenadas do polígono fornecido por argumento
        List<Ponto> polygonCoordinates = polygon.getAllCoordinates();

        // Itera sobre todas as coordenadas do polígono
        for (Ponto point : polygonCoordinates) {
            // Calcula a distância entre o centro do círculo e o ponto do polígono
            double distance = Math.sqrt(Math.pow(this.centro.getX() - point.getX(), 2) +
                    Math.pow(this.centro.getY() - point.getY(), 2));

            // Se a distância for menor ou igual ao raio do círculo, há interseção
            if (distance <= this.raio) {
                return true;
            }
        }

        // Se nenhum ponto do polígono estiver dentro do círculo, retorna falso
        return false;
    }

}
