
package Geometry.GeometryTests;
import org.junit.jupiter.api.Test;

import Geometry.Ponto;
import Geometry.Segmento;

import static org.junit.jupiter.api.Assertions.*;

class SegmentoTest {

    @Test
    public void testConstructor() {
        Ponto ponto1 = new Ponto(0, 0);
        Ponto ponto2 = new Ponto(3, 4);
        Segmento segmento = new Segmento(ponto1, ponto2);
        assertNotNull(segmento);
    }

    @Test
    public void testLength() {
        Ponto ponto1 = new Ponto(0, 0);
        Ponto ponto2 = new Ponto(3, 4);
        Segmento segmento = new Segmento(ponto1, ponto2);
        assertEquals(5.0, segmento.length());
    }

    @Test
    public void testEquals() {
        Ponto ponto1 = new Ponto(0, 0);
        Ponto ponto2 = new Ponto(3, 4);
        Segmento segmento1 = new Segmento(ponto1, ponto2);
        Segmento segmento2 = new Segmento(ponto2, ponto1);
        assertEquals(segmento1, segmento2);
    }

    @Test
    public void testNotEquals() {
        Ponto ponto1 = new Ponto(0, 0);
        Ponto ponto2 = new Ponto(3, 4);
        Ponto ponto3 = new Ponto(6, 8);
        Segmento segmento1 = new Segmento(ponto1, ponto2);
        Segmento segmento2 = new Segmento(ponto2, ponto3);
        assertNotEquals(segmento1, segmento2);
    }


    @Test
    public void testArestasCruzam() {
        Ponto ponto1 = new Ponto(0, 0);
        Ponto ponto2 = new Ponto(3, 3);
        Ponto ponto3 = new Ponto(3, 0);
        Ponto ponto4 = new Ponto(0, 3);
        Segmento segmento1 = new Segmento(ponto1, ponto2);
        Segmento segmento2 = new Segmento(ponto3, ponto4);
        assertTrue(segmento1.arestasCruzam(segmento2));
    }


}
