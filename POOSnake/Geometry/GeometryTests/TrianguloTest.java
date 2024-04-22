package Geometry.GeometryTests;

import org.junit.jupiter.api.Test;

import Geometry.Ponto;
import Geometry.Triangulo;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class TrianguloTest {

    @Test
    void testConstrutorString() {
        Triangulo triangulo = new Triangulo("0 0 1 1 2 0");
        List<Ponto> pontosEsperados = new ArrayList<>();
        pontosEsperados.add(new Ponto(0, 0));
        pontosEsperados.add(new Ponto(1, 1));
        pontosEsperados.add(new Ponto(2, 0));
        assertEquals(pontosEsperados, triangulo.getPontos());
    }

    @Test
    void testConstrutorLista() {
        List<Ponto> pontos = new ArrayList<>();
        pontos.add(new Ponto(1, 1));
        pontos.add(new Ponto(2, 2));
        pontos.add(new Ponto(3, 1));
        Triangulo triangulo = new Triangulo(pontos);
        assertEquals(pontos, triangulo.getPontos());
    }

    @Test
    void testRotacionar() {
        Triangulo triangulo = new Triangulo("10 10 11 11 12 10");
        Triangulo trianguloRotacionado = triangulo.rotacionar(10, new Ponto(10, 10));
        List<Ponto> pontosEsperados = new ArrayList<>();
        pontosEsperados.add(new Ponto(8, 12));
        pontosEsperados.add(new Ponto(9, 13));
        pontosEsperados.add(new Ponto(10, 12));
        assertEquals(pontosEsperados, trianguloRotacionado.getPontos());
    }

    @Test
    void testTranslacaocentroide() {
        Triangulo triangulo = new Triangulo("0 0 1 1 2 0");
        Triangulo trianguloTransladado = triangulo.translacao(2, 2);
        List<Ponto> pontosEsperados = new ArrayList<>();
        pontosEsperados.add(new Ponto(1, 1));
        pontosEsperados.add(new Ponto(2, 2));
        pontosEsperados.add(new Ponto(3, 1));
        assertEquals(pontosEsperados, trianguloTransladado.getPontos());
    }

    @Test
    void testTranslacaoSemPontoTriangulo() {
        Triangulo triangulo = new Triangulo("0 0 4 0 2 2");
        Triangulo trianguloTransladado = triangulo.translacaoSemPonto(2, 2);
        List<Ponto> pontosEsperados = new ArrayList<>();
        pontosEsperados.add(new Ponto(2, 2));
        pontosEsperados.add(new Ponto(6, 2));
        pontosEsperados.add(new Ponto(4, 4));
        assertEquals(pontosEsperados, trianguloTransladado.getPontos());
    }

}
