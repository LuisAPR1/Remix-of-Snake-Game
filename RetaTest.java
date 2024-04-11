import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RetaTest {

    @Test
    public void testConstructor() {
        Ponto ponto1 = new Ponto(1, 1);
        Ponto ponto2 = new Ponto(3, 3);
        Reta reta = new Reta(ponto1, ponto2);
        assertNotNull(reta);
    }

    @Test
    public void testGetPonto1() {
        Ponto ponto1 = new Ponto(1, 1);
        Ponto ponto2 = new Ponto(3, 3);
        Reta reta = new Reta(ponto1, ponto2);
        assertEquals(ponto1, reta.getPonto1());
    }

    @Test
    public void testGetPonto2() {
        Ponto ponto1 = new Ponto(1, 1);
        Ponto ponto2 = new Ponto(3, 3);
        Reta reta = new Reta(ponto1, ponto2);
        assertEquals(ponto2, reta.getPonto2());
    }

    @Test
    public void testSetPonto1() {
        Ponto ponto1 = new Ponto(1, 1);
        Ponto ponto2 = new Ponto(3, 3);
        Reta reta = new Reta(ponto1, ponto2);
        Ponto novoPonto = new Ponto(2, 2);
        reta.setPonto1(novoPonto);
        assertEquals(novoPonto, reta.getPonto1());
    }

    @Test
    public void testSetPonto2() {
        Ponto ponto1 = new Ponto(1, 1);
        Ponto ponto2 = new Ponto(3, 3);
        Reta reta = new Reta(ponto1, ponto2);
        Ponto novoPonto = new Ponto(4, 4);
        reta.setPonto2(novoPonto);
        assertEquals(novoPonto, reta.getPonto2());
    }

    @Test
    public void testColinearesTrue() {
        Ponto ponto1 = new Ponto(1, 1);
        Ponto ponto2 = new Ponto(3, 3);
        Reta reta = new Reta(ponto1, ponto2);
        Ponto pontoTeste = new Ponto(2, 2);
        assertTrue(reta.colineares(pontoTeste));
    }

    @Test
    public void testColinearesFalse() {
        Ponto ponto1 = new Ponto(1, 1);
        Ponto ponto2 = new Ponto(3, 3);
        Reta reta = new Reta(ponto1, ponto2);
        Ponto pontoTeste = new Ponto(4, 2);
        assertFalse(reta.colineares(pontoTeste));
    }
}
