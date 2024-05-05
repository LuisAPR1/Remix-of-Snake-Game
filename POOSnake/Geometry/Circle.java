package Geometry;

import java.util.ArrayList;
import java.util.List;

import Core.Shape;

public class Circle implements Shape {
    Ponto centro;
    int raio;

    public Circle(Ponto centro, int raio) {
        setCentro(centro);
        setRaio(raio);
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

        // Encontra os limites horizontais e verticais do retângulo circunscrito ao
        // círculo
        int minX = centroX - raio;
        int maxX = centroX + raio;
        int minY = centroY - raio;
        int maxY = centroY + raio;

        // Itera sobre todas as coordenadas inteiras dentro dos limites do retângulo
        // circunscrito
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
        if (raio <= 0)
            System.exit(0);
        this.raio = raio;
    }

    public boolean isContainedInPolygon(Poligono poligono) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isContainedInPolygon'");
    }

    public boolean isContainedInCircle(Circle largerCircle) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isContainedInCircle'");
    }

    public boolean intersects(Poligono poligono) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'intersects'");
    }

}
