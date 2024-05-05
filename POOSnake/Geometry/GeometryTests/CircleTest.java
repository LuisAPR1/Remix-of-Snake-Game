package Geometry.GeometryTests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import Geometry.Circle;
import Geometry.Poligono;
import Geometry.Ponto;

public class CircleTest {
    @Test
    public void intersectTest() {
        Circle circle = new Circle(new Ponto(3, 3), 2);
        List<Ponto> verticesPoligono = new ArrayList<>();
        verticesPoligono.add(new Ponto(0, 0));
        verticesPoligono.add(new Ponto(4, 0));
        verticesPoligono.add(new Ponto(4, 4));
        verticesPoligono.add(new Ponto(0, 4));
        Poligono poligono = new Poligono(verticesPoligono);
        assertTrue(circle.intersects(poligono));
    }

    @Test
    public void containedInCircleTest() {
        Circle largerCircle = new Circle(new Ponto(5, 5), 5);
        Circle smallerCircle = new Circle(new Ponto(7, 7), 2);
        assertTrue(smallerCircle.isContainedInCircle(largerCircle));
        Circle outsideCircle = new Circle(new Ponto(10, 10), 1);
        assertFalse(outsideCircle.isContainedInCircle(largerCircle));
        Circle intersectingEdgeCircle = new Circle(new Ponto(2, 5), 4);
        assertFalse(intersectingEdgeCircle.isContainedInCircle(largerCircle));
        Circle equalCircle = new Circle(new Ponto(5, 5), 5);
        assertTrue(equalCircle.isContainedInCircle(largerCircle));
    }

    @Test
    public void containedInPolygonTest() {
        List<Ponto> verticesPoligono = new ArrayList<>();
        verticesPoligono.add(new Ponto(0, 0));
        verticesPoligono.add(new Ponto(10, 0));
        verticesPoligono.add(new Ponto(10, 10));
        verticesPoligono.add(new Ponto(0, 10));
        Poligono poligono = new Poligono(verticesPoligono);
        Circle innerCircle = new Circle(new Ponto(5, 5), 2);
        assertTrue(innerCircle.isContainedInPolygon(poligono));
        Circle partialCircle = new Circle(new Ponto(8, 8), 4);
        assertFalse(partialCircle.isContainedInPolygon(poligono));
        Circle externalCircle = new Circle(new Ponto(15, 15), 1);
        assertFalse(externalCircle.isContainedInPolygon(poligono));
    }
}