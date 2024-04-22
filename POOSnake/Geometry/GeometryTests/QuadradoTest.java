package Geometry.GeometryTests;

import org.junit.jupiter.api.Test;

import Geometry.Ponto;
import Geometry.Quadrado;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class QuadradoTest {

    @Test
    void testConstrutorString() {
        Quadrado quadrado = new Quadrado("0 0 2 0 2 2 0 2");
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
        Quadrado quadrado = new Quadrado(pontos);
        assertEquals(pontos, quadrado.getPontos());
    }

    @Test
    void testRotacionar() {
        Quadrado quadrado = new Quadrado("10 10 12 10 12 12 10 12");
        Quadrado quadradoRotacionado = quadrado.rotacionar(10, new Ponto(10, 10));
        List<Ponto> pontosEsperados = new ArrayList<>();
        pontosEsperados.add(new Ponto(8, 12));
        pontosEsperados.add(new Ponto(10, 12));
        pontosEsperados.add(new Ponto(10, 14));
        pontosEsperados.add(new Ponto(8, 14));
        assertEquals(pontosEsperados, quadradoRotacionado.getPontos());
    }

    @Test
    void testTranslacaocentroide() {
        Quadrado quadrado = new Quadrado("0 0 2 0 2 2 0 2");
        Quadrado quadradoTransladado = quadrado.translacao(2, 2);
        List<Ponto> pontosEsperados = new ArrayList<>();
        pontosEsperados.add(new Ponto(1, 1));
        pontosEsperados.add(new Ponto(3, 1));
        pontosEsperados.add(new Ponto(3, 3));
        pontosEsperados.add(new Ponto(1, 3));
        assertEquals(pontosEsperados, quadradoTransladado.getPontos());
    }

    @Test
    void testTranslacaoSemPonto() {
        Quadrado quadrado = new Quadrado("0 0 2 0 2 2 0 2");
        Quadrado quadradoTransladado = quadrado.translacaoSemPonto(2, 2);
        List<Ponto> pontosEsperados = new ArrayList<>();
        pontosEsperados.add(new Ponto(2, 2));
        pontosEsperados.add(new Ponto(4, 2));
        pontosEsperados.add(new Ponto(4, 4));
        pontosEsperados.add(new Ponto(2, 4));
        assertEquals(pontosEsperados, quadradoTransladado.getPontos());
    }

}
