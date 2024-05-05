package Geometry;

public class Circle {
    Ponto centro;
    int raio;

    public Circle(Ponto centro, int raio){
        setCentro(centro);
        setRaio(raio);
    }

    public Ponto getCentro() {
        return centro;
    }

    public void setCentro(Ponto centro) {
        this.centro = centro;
    }

    public int getRaio() {
        return raio;
    }

    public void setRaio(int raio) {
        if(raio <= 0) System.exit(0);
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
