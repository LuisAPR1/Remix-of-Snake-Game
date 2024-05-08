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
        int centroX = this.centro.getX();
        int centroY = this.centro.getY();

        
        // Encontra os limites do quadrado que circunscreve o círculo
        int minX = centroX - raio;
        int maxX = centroX + raio;
        int minY = centroY - raio;
        int maxY = centroY + raio;

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
        if (raio <= 0) {
            System.out.println("raio:vi");
            System.exit(0);
        }
        this.raio = raio;
    }

}
