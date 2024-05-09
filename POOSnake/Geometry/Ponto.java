package Geometry;

import java.util.Objects;

/**
 * Classe que representa um ponto no plano cartesiano.
 * 
 * @version 1.0 03/04/2024
 * @author Luís Rosa
 */
public class Ponto {
    private int x, y; // Coordenadas inteiras do ponto.
    private double xDouble, yDouble; // Coordenadas em ponto flutuante do ponto.

    /**
     * Construtor padrão que inicializa um ponto na origem.
     */
    public Ponto() {
        this.x = 0;
        this.y = 0;
    }

    /**
     * Construtor que recebe coordenadas inteiras para criar um ponto.
     * 
     * @param x coordenada x do ponto.
     * @param y coordenada y do ponto.
     */
    public Ponto(int x, int y) {
        // check(x, y);
        setX(x);
        setY(y);
    }

    /**
     * Construtor que recebe coordenadas em ponto flutuante para criar um ponto.
     * 
     * @param xDouble coordenada x do ponto.
     * @param yDouble coordenada y do ponto.
     */
    public Ponto(double xDouble, double yDouble) {
        // check(xDouble, yDouble);
        setX(xDouble);
        setY(yDouble);
    }

    /**
     * Verifica se as coordenadas em ponto flutuante são próximas o suficiente para
     * serem consideradas iguais.
     * 
     * @param x coordenada x.
     * @param y coordenada y.
     */
    // private void check(double x, double y) {
    //     if (Math.abs(x - y) < Math.pow(10, -9))
    //         x = y;
    // }

    /**
     * Calcula a distância entre este ponto e outro ponto.
     * 
     * @param p ponto para o qual deseja calcular a distância.
     * @return distância entre os pontos.
     */
    public int dist(Ponto p) {
        double dx = x - p.x;
        double dy = y - p.y;
        return (int) Math.sqrt(dx * dx + dy * dy);
    }
    public double distDouble(Ponto p) {
        double dx = xDouble - p.x;
        double dy = yDouble - p.y;
        return Math.sqrt(dx * dx + dy * dy);
    }
    /**
     * Obtém a coordenada x do ponto.
     * 
     * @return coordenada x.
     */
    public int getX() {
        
        return x;
    }

    /**
     * Obtém a coordenada y do ponto.
     * 
     * @return coordenada y.
     */
    public int getY() {
        return y;
    }

    /**
     * Define a coordenada x do ponto.
     * 
     * @param x coordenada x.
     */
    public void setX(int x) {
        
        
        this.x = x;
    }

    /**
     * Define a coordenada x em ponto flutuante do ponto.
     * 
     * @param xDouble coordenada x em ponto flutuante.
     */
    public void setX(double xDouble) {
        
        
        this.xDouble = xDouble;
    }

    /**
     * Define a coordenada y do ponto.
     * 
     * @param y coordenada y.
     */
    public void setY(int y) {
       
        this.y = y;
    }

    /**
     * Define a coordenada y em ponto flutuante do ponto.
     * 
     * @param yDouble coordenada y em ponto flutuante.
     */
    public void setY(double yDouble) {
        
        
        this.yDouble = yDouble;
    }

    /**
     * Verifica se este ponto é igual a outro objeto.
     * 
     * @param obj objeto a ser comparado.
     * @return true se os pontos forem iguais, false caso contrário.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Ponto that = (Ponto) obj;

        return this.x == that.x && this.y == that.y;
    }

    /**
     * Calcula o código de hash para este ponto.
     * 
     * @return código de hash do ponto.
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.x, this.y);
    }

    /**
     * Retorna uma representação textual deste ponto.
     * 
     * @return string representando o ponto.
     */
    @Override
    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }

    /**
     * Obtém a coordenada x em ponto flutuante do ponto.
     * 
     * @return coordenada x em ponto flutuante.
     */
    public double getxDouble() {
        return xDouble;
    }

    /**
     * Obtém a coordenada y em ponto flutuante do ponto.
     * 
     * @return coordenada y em ponto flutuante.
     */
    public double getyDouble() {
        return yDouble;
    }

    /**
     * Rotaciona o ponto em torno de um ponto fixo por um determinado ângulo.
     * 
     * @param anguloGraus ângulo de rotação em graus.
     * @param fixo        ponto fixo em torno do qual o ponto será rotacionado.
     * @return novo ponto rotacionado.
     */
    public Ponto rotacionar(int anguloGraus, Ponto fixo) {

        double x = Math.round((fixo.getxDouble() + (this.x - fixo.getxDouble()) * Math.cos(Math.toRadians(anguloGraus))
                - (this.y - fixo.getyDouble()) * Math.sin(Math.toRadians(anguloGraus))));
        double y = Math.round(fixo.getyDouble() + ((this.x - fixo.getxDouble()) * Math.sin(Math.toRadians(anguloGraus))
                + (this.y - fixo.getyDouble()) * Math.cos(Math.toRadians(anguloGraus))));
        return new Ponto( x,  y);
    }

    /**
     * Realiza uma translação no ponto.
     * 
     * @param xmove deslocamento horizontal.
     * @param ymove deslocamento vertical.
     * @return novo ponto transladado.
     */
    public Ponto translacionar(int xmove, int ymove) {
        return new Ponto(this.x + xmove, this.y + ymove);
    }

}
