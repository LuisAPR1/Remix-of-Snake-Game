package Geometry;

import java.util.ArrayList;
import java.util.List;

import Core.Shape;

public class Circle implements Shape {
    Ponto centro;
    int raio;

    public Circle(Ponto centro, int raio) {
        setCentro(centro);
        setRaio(raio/2);
    }

    public Ponto getCentro() {
        return centro;
    }

    public void setCentro(Ponto centro) {
        this.centro = centro;
    }

    public List<Ponto> getAllCoordinates() {
        // Inicializa a lista para armazenar as coordenadas
        List<Ponto> coordenadas = new ArrayList<>();

        // Obtém as coordenadas do centro do círculo
        double centroX = this.centro.getX();
        double centroY = this.centro.getY();

        
        // Encontra os limites do quadrado que circunscreve o círculo
        int minX = (int)centroX - raio;
        int maxX = (int)centroX + raio;
        int minY = (int)centroY - raio;
        int maxY = (int)centroY + raio;

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

    public int getRaio() {
        return raio;
    }

    public void setRaio(int raio) {
        
        this.raio = raio;
    }

}
