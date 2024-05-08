package Geometry;
import java.util.Objects;

/**
 * Classe que representa um segmento de reta entre dois pontos.
 * @version 1.0 03/04/2024
 * @author Luís Rosa
 */
public class Segmento {
    private Ponto ponto1; // Primeiro ponto do segmento.
    private Ponto ponto2; // Segundo ponto do segmento.

    /**
     * Construtor da classe Segmento.
     * @param ponto1 primeiro ponto do segmento.
     * @param ponto2 segundo ponto do segmento.
     */
    public Segmento(Ponto ponto1, Ponto ponto2) {
        check(ponto1, ponto2);
        this.ponto1 = ponto1;
        this.ponto2 = ponto2;
    }

    /**
     * Verifica se os pontos fornecidos para criar o segmento são iguais.
     * @param ponto1 primeiro ponto.
     * @param ponto2 segundo ponto.
     */
    private void check(Ponto ponto1, Ponto ponto2) {
        if (ponto1.getX() == ponto2.getX() && ponto1.getY() == ponto2.getY()) {
            System.out.println("Segmehhhhnto:vi");
            System.exit(0);
        }
    }

    /**
     * Obtém o primeiro ponto do segmento.
     * @return primeiro ponto.
     */
    public Ponto getPonto1() {
        return ponto1;
    }

    /**
     * Define o primeiro ponto do segmento.
     * @param ponto1 primeiro ponto.
     */
    public void setPonto1(Ponto ponto1) {
        this.ponto1 = ponto1;
    }

    /**
     * Obtém o segundo ponto do segmento.
     * @return segundo ponto.
     */
    public Ponto getPonto2() {
        return ponto2;
    }

    /**
     * Define o segundo ponto do segmento.
     * @param ponto2 segundo ponto.
     */
    public void setPonto2(Ponto ponto2) {
        this.ponto2 = ponto2;
    }

    /**
     * Calcula o comprimento do segmento.
     * @return comprimento do segmento.
     */
    public double length() {
        return getPonto1().dist(getPonto2());
    }

    /**
     * Verifica se dois segmentos de reta são iguais.
     * @param object outro objeto para comparação.
     * @return true se os segmentos são iguais, false caso contrário.
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Segmento segmento = (Segmento) object;
        return ponto1.equals(segmento.getPonto1()) && ponto2.equals(segmento.getPonto2())
                || ponto1.equals(segmento.getPonto2()) && ponto2.equals(segmento.getPonto1());
    }

    /**
     * Gera o código hash para o segmento.
     * @return código hash do segmento.
     */
    @Override
    public int hashCode() {
        return Objects.hash(ponto1, ponto2);
    }

    /**
     * Calcula o produto vetorial de três pontos.
     * @param a primeiro ponto.
     * @param b segundo ponto.
     * @param c terceiro ponto.
     * @return valor do produto vetorial.
     */
    private double produtoVetorial(Ponto a, Ponto b, Ponto c) {
        return (b.getX() - a.getX()) * (c.getY() - a.getY()) - (b.getY() - a.getY()) * (c.getX() - a.getX());
    }

    /**
     * Verifica se duas arestas se cruzam.
     * @param segmentoDeReta outro segmento de reta.
     * @return true se as arestas se cruzam, false caso contrário.
     */
    public boolean arestasCruzam(Segmento segmentoDeReta) {
        double abac = produtoVetorial(ponto2, ponto1, segmentoDeReta.getPonto1());
        double abad = produtoVetorial(ponto2, ponto1, segmentoDeReta.getPonto2());
        double cdca = produtoVetorial(segmentoDeReta.getPonto2(), segmentoDeReta.getPonto1(), ponto1);
        double cdcb = produtoVetorial(segmentoDeReta.getPonto2(), segmentoDeReta.getPonto1(), ponto2);
        return abac * abad < 0 && cdca * cdcb < 0;
    }

    /**
 * Verifica se duas arestas se cruzam ou se intersectam.
 * @param segmentoDeReta outro segmento de reta.
 * @return true se as arestas se cruzam ou se intersectam, false caso contrário.
 */
/**
 * Verifica se duas arestas se cruzam.
 * @param segmentoDeReta outro segmento de reta.
 * @return true se as arestas se cruzam, false caso contrário.
 */
public boolean arestasCruzam2(Segmento segmentoDeReta) {
    // Verifica se os segmentos compartilham um dos pontos.
    if (ponto1.equals(segmentoDeReta.getPonto1()) || ponto1.equals(segmentoDeReta.getPonto2()) ||
        ponto2.equals(segmentoDeReta.getPonto1()) || ponto2.equals(segmentoDeReta.getPonto2())) {
        return true;
    }
    
    // Calcula o produto vetorial para verificar se as arestas se cruzam.
    double abac = produtoVetorial(ponto2, ponto1, segmentoDeReta.getPonto1());
    double abad = produtoVetorial(ponto2, ponto1, segmentoDeReta.getPonto2());
    double cdca = produtoVetorial(segmentoDeReta.getPonto2(), segmentoDeReta.getPonto1(), ponto1);
    double cdcb = produtoVetorial(segmentoDeReta.getPonto2(), segmentoDeReta.getPonto1(), ponto2);
    
    // Verifica se os segmentos são paralelos.
    if (abac == 0 && abad == 0 && cdca == 0 && cdcb == 0) {
        // Se os segmentos são paralelos, verifica se há sobreposição.
        if (Math.max(ponto1.getX(), ponto2.getX()) < Math.min(segmentoDeReta.getPonto1().getX(), segmentoDeReta.getPonto2().getX()) ||
            Math.min(ponto1.getX(), ponto2.getX()) > Math.max(segmentoDeReta.getPonto1().getX(), segmentoDeReta.getPonto2().getX()) ||
            Math.max(ponto1.getY(), ponto2.getY()) < Math.min(segmentoDeReta.getPonto1().getY(), segmentoDeReta.getPonto2().getY()) ||
            Math.min(ponto1.getY(), ponto2.getY()) > Math.max(segmentoDeReta.getPonto1().getY(), segmentoDeReta.getPonto2().getY())) {
            return false; // Não há sobreposição, os segmentos não se cruzam.
        }
    }
    
    // Verifica se os segmentos se cruzam.
    return abac * abad <= 0 && cdca * cdcb <= 0;
}


}
