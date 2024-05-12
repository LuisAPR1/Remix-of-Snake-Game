package Geometry.GeometryTests;

import org.junit.jupiter.api.Test;

import Geometry.Ponto;
import Geometry.Square;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class QuadradoTest {

    @Test
    void testConstrutorString() {
        Square quadrado = new Square("0 0 2 0 2 2 0 2");
        List<Ponto> pontosEsperados = new ArrayList<>();
        pontosEsperados.add(new Ponto(0, 0));
        pontosEsperados.add(new Ponto(2, 0));
        pontosEsperados.add(new Ponto(2, 2));
        pontosEsperados.add(new Ponto(0, 2));
        assertEquals(pontosEsperados, quadrado.getPontos());
    }

    @Test
    void testConstrutorLista() {
        List<Ponto> pontos = new ArrayList<>();
        pontos.add(new Ponto(1, 1));
        pontos.add(new Ponto(3, 1));
        pontos.add(new Ponto(3, 3));
        pontos.add(new Ponto(1, 3));
        Square quadrado = new Square(pontos);
        assertEquals(pontos, quadrado.getPontos());
    }

    @Test
    void testRotacionar() {
        Square quadrado = new Square("10 10 12 10 12 12 10 12");
        Square quadradoRotacionado = quadrado.rotacionar(10, new Ponto(10, 10));
        List<Ponto> pontosEsperados = new ArrayList<>();
        pontosEsperados.add(new Ponto(8, 12));
        pontosEsperados.add(new Ponto(10, 12));
        pontosEsperados.add(new Ponto(10, 14));
        pontosEsperados.add(new Ponto(8, 14));
        assertEquals(pontosEsperados, quadradoRotacionado.getPontos());
    }

    @Test
    void testTranslacaocentroide() {
        Square quadrado = new Square("0 0 2 0 2 2 0 2");
        Square quadradoTransladado = quadrado.translacao(2, 2);
        List<Ponto> pontosEsperados = new ArrayList<>();
        pontosEsperados.add(new Ponto(1, 1));
        pontosEsperados.add(new Ponto(3, 1));
        pontosEsperados.add(new Ponto(3, 3));
        pontosEsperados.add(new Ponto(1, 3));
        assertEquals(pontosEsperados, quadradoTransladado.getPontos());
    }

    @Test
    void testTranslacaoSemPonto() {
        Square quadrado = new Square("0 0 2 0 2 2 0 2");
        Square quadradoTransladado = quadrado.translacaoSemPonto(2, 2);
        List<Ponto> pontosEsperados = new ArrayList<>();
        pontosEsperados.add(new Ponto(2, 2));
        pontosEsperados.add(new Ponto(4, 2));
        pontosEsperados.add(new Ponto(4, 4));
        pontosEsperados.add(new Ponto(2, 4));
        assertEquals(pontosEsperados, quadradoTransladado.getPontos());
    }

}
