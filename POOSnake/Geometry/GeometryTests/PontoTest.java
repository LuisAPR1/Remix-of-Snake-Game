package Geometry.GeometryTests;

import org.junit.jupiter.api.Test;

import Geometry.Ponto;

import static org.junit.jupiter.api.Assertions.*;

class PontoTest {

    @Test
    public void testConstructorWithIntCoordinates() {
        Ponto ponto = new Ponto(3, 4);
        assertEquals(3, ponto.getX());
        assertEquals(4, ponto.getY());
    }

    @Test
    public void testConstructorWithDoubleCoordinates() {
        Ponto ponto = new Ponto(3.5, 4.5);
        assertEquals(3.5, ponto.getX());
        assertEquals(4.5, ponto.getY());
    }

    @Test
    public void testDistance() {
        Ponto ponto1 = new Ponto(0, 0);
        Ponto ponto2 = new Ponto(3, 4);
        assertEquals(5, ponto1.dist(ponto2));
    }

    @Test
    public void testTranslate() {
        Ponto ponto = new Ponto(3, 4);
        Ponto translated = ponto.translacionar(2, 3);
        assertEquals(5, translated.getX());
        assertEquals(7, translated.getY());
    }

    @Test
    public void testRotate() {
        Ponto ponto = new Ponto(1, 0);
        Ponto fixedPoint = new Ponto(0, 0);
        Ponto rotated = ponto.rotacionar(90, fixedPoint);
        assertEquals(0, rotated.getX());
        assertEquals(1, rotated.getY());
    }

    @Test
    public void testEquals() {
        Ponto ponto1 = new Ponto(3, 4);
        Ponto ponto2 = new Ponto(3, 4);
        assertEquals(ponto1, ponto2);
    }

    @Test
    public void testNotEquals() {
        Ponto ponto1 = new Ponto(3, 4);
        Ponto ponto2 = new Ponto(4, 3);
        assertNotEquals(ponto1, ponto2);
    }

    @Test
    public void testHashCode() {
        Ponto ponto1 = new Ponto(3, 4);
        Ponto ponto2 = new Ponto(3, 4);
        assertEquals(ponto1.hashCode(), ponto2.hashCode());
    }

    @Test
    public void testToString() {
        Ponto ponto = new Ponto(3, 4);
        assertEquals("(3,4)", ponto.toString());
    }
}
