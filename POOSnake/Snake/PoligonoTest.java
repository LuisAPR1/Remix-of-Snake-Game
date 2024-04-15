package Snake;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


class PoligonoTest {


    @Test
    public void testConstructorWithValidInput() {
        String input = "4 1 1 3 1 3 3 1 3";
        Poligono p = new Poligono(input);
        assertNotNull(p);
    }


    @Test
    public void testTranslacaoSemPonto() {
        List<Ponto> pontos = new ArrayList<>();
        pontos.add(new Ponto(1, 1));
        pontos.add(new Ponto(2, 3));
        pontos.add(new Ponto(3, 2));

        Poligono poligono = new Poligono(pontos);
        Poligono poligonoTransladado = poligono.translacaoSemPonto(2, 2);

        List<Ponto> pontosEsperados = new ArrayList<>();
        pontosEsperados.add(new Ponto(3, 3));
        pontosEsperados.add(new Ponto(4, 5));
        pontosEsperados.add(new Ponto(5, 4));

        assertEquals(pontosEsperados, poligonoTransladado.getPontos());
    }

    @Test
    public void testRotate() {
        String input = "3 2 1 4 1 3 4";
        Poligono p = new Poligono(input);
        assertEquals("Poligono de 3 vertices: [(4,3), (2,3), (3,0)]", p.rotacionar(180, p.calcularCentro()).toString());
    }

    @Test
    public void testTranslacaoCentroide() {
        String input = "4 1 1 3 1 3 3 1 3";
        Poligono p1 = new Poligono(input);
        assertEquals("Poligono de 4 vertices: [(5,5), (7,5), (7,7), (5,7)]", p1.translacao(6, 6).toString());
    }

    @Test
    public void testToString() {
        String input = "5 1 1 3 1 4 3 2 4 1 3";
        Poligono pentagono = new Poligono(input);
        assertEquals("Poligono de 5 vertices: [(1,1), (3,1), (4,3), (2,4), (1,3)]", pentagono.toString());
    }

    @Test
    public void testEquals() {
        String input1 = "3 2 1 4 1 3 4";
        String input2 = "3 2 1 4 1 3 4";
        Poligono p1 = new Poligono(input1);
        Poligono p2 = new Poligono(input2);
        assertEquals(p1, p2);
    }

    @Test
    public void testHashCode() {
        String input1 = "3 2 1 4 1 3 4";
        String input2 = "3 2 1 4 1 3 4";
        Poligono p1 = new Poligono(input1);
        Poligono p2 = new Poligono(input2);
        assertEquals(p1.hashCode(), p2.hashCode());
    }

    @Test
    public void testCalculateCenter() {
        String input = "3 2 1 4 1 3 4";
        Poligono p = new Poligono(input);
        assertEquals(new Ponto(0, 0), p.calcularCentro());
    }
}