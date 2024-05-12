package Geometry.GeometryTests;

import org.junit.jupiter.api.Test;

import Geometry.Poligono;
import Geometry.Ponto;
import Geometry.Retangulo;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class RetanguloTest {

    @Test
    void testConstrutorString() {
        Retangulo retangulo = new Retangulo("0 0 4 0 4 2 0 2");
        List<Ponto> pontosEsperados = new ArrayList<>();
        pontosEsperados.add(new Ponto(0, 0));
        pontosEsperados.add(new Ponto(4, 0));
        pontosEsperados.add(new Ponto(4, 2));
        pontosEsperados.add(new Ponto(0, 2));
        assertEquals(pontosEsperados, retangulo.getPontos());
    }

    @Test
    void testConstrutorLista() {
        List<Ponto> pontos = new ArrayList<>();
        pontos.add(new Ponto(1, 1));
        pontos.add(new Ponto(3, 1));
        pontos.add(new Ponto(3, 3));
        pontos.add(new Ponto(1, 3));
        Retangulo retangulo = new Retangulo(pontos);
        assertEquals(pontos, retangulo.getPontos());
    }

    @Test
    void testRotacionar() {
        Retangulo retangulo = new Retangulo("10 10 14 10 14 12 10 12");
        Retangulo retanguloRotacionado = retangulo.rotacionar(10, new Ponto(12, 11));
        List<Ponto> pontosEsperados = new ArrayList<>();
        pontosEsperados.add(new Ponto(8, 12));
        pontosEsperados.add(new Ponto(12, 12));
        pontosEsperados.add(new Ponto(12, 14));
        pontosEsperados.add(new Ponto(8, 14));
        assertEquals(pontosEsperados, retanguloRotacionado.getPontos());
    }

    @Test
    void testTranslacaocentroide() {
        Retangulo retangulo = new Retangulo("0 0 4 0 4 2 0 2");
        Retangulo retanguloTransladado = retangulo.translacao(2, 2);
        List<Ponto> pontosEsperados = new ArrayList<>();
        pontosEsperados.add(new Ponto(0, 1));
        pontosEsperados.add(new Ponto(4, 1));
        pontosEsperados.add(new Ponto(4, 3));
        pontosEsperados.add(new Ponto(0, 3));
        assertEquals(pontosEsperados, retanguloTransladado.getPontos());
    }

    @Test
    void testTranslacaoSemPontoRetangulo() {
        Poligono retangulo = new Poligono("0 0 4 0 4 2 0 2");
        Poligono retanguloTransladado = retangulo.translacaoSemPonto(2, 2);
        List<Ponto> pontosEsperados = new ArrayList<>();
        pontosEsperados.add(new Ponto(2, 2));
        pontosEsperados.add(new Ponto(6, 2));
        pontosEsperados.add(new Ponto(6, 4));
        pontosEsperados.add(new Ponto(2, 4));
        assertEquals(pontosEsperados, retanguloTransladado.getPontos());
    }

}
