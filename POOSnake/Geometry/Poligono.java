package Geometry;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import Core.Shape;

/**
 * Classe que representa um polígono.
 * 
 * @version Versão 1.0 10/05/2024
 * @author Luís Rosa, José Lima, Pedro Ferreira e Pedro Ferreira
 * @inv O polígono tem de ter pelo m pontos.
 */
public class Poligono implements Shape {
    protected final List<Ponto> pontos; // Lista de pontos que definem os vértices do polígono.
    protected final List<Segmento> segmentoDeRetas = new ArrayList<>(); // Lista de segmentos de reta do polígono.

    /**
     * Construtor da classe Poligono que recebe uma string de entrada.
     * 
     * @param input string de entrada contendo as coordenadas dos pontos.
     */
    public Poligono(String input) {
        this(toInt(input));
    }

    /**
     * Construtor da classe Poligono que recebe uma lista de pontos.
     * 
     * @param pontos Lista de pontos que definem os vértices do polígono.
     *               Deve conter pelo menos 3 pontos para formar um polígono válido.
     */
    public Poligono(List<Ponto> pontos) {
        // Verifica se o polígono tem menos de 3 pontos.
        if (pontos.size() < 3) {
            System.out.println("false");
            System.exit(0);
        }

        // Cria segmentos de reta entre cada par de pontos consecutivos.
        for (int i = 0; i < pontos.size(); i++) {
            segmentoDeRetas.add(new Segmento(pontos.get(i), pontos.get((i + 1) % pontos.size())));
        }

        // Verifica se os pontos são colineares e se as arestas se cruzam.
        for (int i = 0; i < pontos.size(); i++) {
            Reta reta = new Reta(pontos.get(i), pontos.get((i + 1) % pontos.size()));
            if (reta.colineares(pontos.get((i + 2) % pontos.size()))) {
                System.out.println("Poligono:vi");
                System.exit(0);
            }
            if (segmentoDeRetas.get(i).arestasCruzam(segmentoDeRetas.get((i + 2) % pontos.size()))) {
                System.out.println("Poligono:vi");
                System.exit(0);
            }
        }
        this.pontos = pontos;
    }
    public static boolean saoColineares(Ponto p1, Ponto p2, Ponto p3) {
        // Calcula a área do triângulo formado pelos três pontos
        double area = 0.5 * ((p2.getX() - p1.getX()) * (p3.getY() - p1.getY()) - (p3.getX() - p1.getX()) * (p2.getY() - p1.getY()));
        
        // Se a área for próxima de zero, os pontos são colineares
        return Math.abs(area) < 1e-9; // 1e-9 é uma tolerância pequena para lidar com imprecisões de ponto flutuante
    }

    
    public double calcularArea() {
        double area = 0.0;
    
        // Itera sobre todos os vértices do polígono
        for (int i = 0; i < pontos.size(); i++) {
            // Obtém os pontos consecutivos
            Ponto pontoAtual = pontos.get(i);
            Ponto proximoPonto = pontos.get((i + 1) % pontos.size());
    
            // Calcula o produto cruzado entre os pontos
            area += pontoAtual.getX() * proximoPonto.getY();
            area -= pontoAtual.getY() * proximoPonto.getX();
        }
    
        // Divide o resultado por 2 para obter a área final
        area = Math.abs(area) / 2.0;
    
        return area;
    }
    

    public boolean intersect2(Poligono otherPolygon) {
        // Verifica se os polígonos têm os 4 pontos iguais
        if (this.equals(otherPolygon)) {
            return true; // Se tiverem, considera-se como intersectados
        }

        // Obtém todas as coordenadas do polígono recebido por argumento
        List<Ponto> coordenadasOutroPoligono = otherPolygon.getAllCoordinates();

        // Itera sobre todos os pontos do polígono atual
        for (Ponto ponto : pontos) {
            // Verifica se o ponto do polígono atual está dentro do polígono recebido por
            // argumento
            if (otherPolygon.isPointInsidePolygon(ponto, coordenadasOutroPoligono)) {
                return true; // Se o ponto estiver dentro, retorna verdadeiro
            }
        }

        return false; // Se nenhum ponto estiver dentro, retorna falso
    }


    public boolean intersect(Poligono otherPolygon) {
        // Verifica se os polígonos têm os mesmos pontos
        if (this.equals(otherPolygon)) {
            return true; // Se tiverem, considera-se como intersectados
        }
    
        // Obtém todas as coordenadas do polígono recebido por argumento
        List<Ponto> coordenadasOutroPoligono = otherPolygon.getAllCoordinates();
    
        // Itera sobre todos os pontos do polígono atual
        for (Ponto ponto : pontos) {
            // Verifica se o ponto do polígono atual está dentro do polígono recebido por argumento
            if (otherPolygon.isPointInsidePolygon(ponto, coordenadasOutroPoligono)) {

                return true; // Se o ponto estiver dentro, retorna verdadeiro
            }
        }
    
        // Itera sobre todos os pontos do polígono recebido por argumento
        for (Ponto ponto : otherPolygon.getPontos()) {
            // Verifica se o ponto do polígono recebido por argumento está dentro do polígono atual
            if (isPointInsidePolygon(ponto, getAllCoordinates())) {

                return true; // Se o ponto estiver dentro, retorna verdadeiro
            }
        }
    
        return false; // Se nenhum ponto estiver dentro, retorna falso
    }
    
    public boolean intersect(Circle circle) {
        // Obtém todas as coordenadas do polígono
        List<Ponto> coordenadasPoligono = getAllCoordinates();

        // Itera sobre todas as coordenadas do polígono
        for (Ponto ponto : coordenadasPoligono) {
            // Calcula a distância entre o ponto do polígono e o centro do círculo
            double distancia = ponto.dist(circle.getCentro());

            // Se a distância for menor ou igual ao raio do círculo, então há interseção
            if (distancia <= circle.getRaio()) {
                return true;
            }
        }

        // Se nenhum ponto do polígono estiver dentro do círculo, não há interseção
        return false;
    }
    public boolean distance(Poligono poligono) {
        // Obtém o centro do polígono fornecido
        Ponto centroPoligono = poligono.calcularCentro();

        // Obtém o centro do quadrado
        Ponto centroQuadrado = calcularCentro();
        // Calcula a distância entre os centros dos dois polígonos
        double distancia = centroQuadrado.dist(centroPoligono);

        // Verifica se a distância é menor que o tamanho do lado do quadrado
        return distancia < tamanhoLado();
    }
    public boolean distance(Circle poligono) {
        // Obtém o centro do polígono fornecido
        Ponto centroPoligono = poligono.getPosition();

        // Obtém o centro do quadrado
        Ponto centroQuadrado = calcularCentro();
        // Calcula a distância entre os centros dos dois polígonos
        double distancia = centroQuadrado.dist(centroPoligono);

        // Verifica se a distância é menor que o tamanho do lado do quadrado
        return distancia < tamanhoLado();
    }
    
    public double tamanhoLado() {
        double maiorDistancia = 0;
    
        // Itera sobre todos os segmentos de reta do polígono
        for (Segmento segmento : segmentoDeRetas) {
            // Calcula a distância entre os pontos inicial e final do segmento
            double distancia = segmento.getPonto1().dist(segmento.getPonto2());
            
            // Atualiza a maior distância, se necessário
            if (distancia > maiorDistancia) {
                maiorDistancia = distancia;
            }
        }
    
        return maiorDistancia;
    }
    

    public boolean contains(Circle shape) {
        // Obtém o centro do círculo e o raio
        Ponto centroCirculo = shape.getCentro();
        double raioCirculo = shape.getRaio();

        // Calcula os limites do quadrado definido pelos vértices do polígono
        int minX = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxY = Integer.MIN_VALUE;
        for (Ponto ponto : pontos) {
            double x = ponto.getX();
            double y = ponto.getY();
            minX = (int)Math.min(minX, x);
            maxX = (int)Math.max(maxX, x);
            minY = (int)Math.min(minY, y);
            maxY = (int)Math.max(maxY, y);
        }

        // Verifica se o centro do círculo, somado ao raio, fica dentro do quadrado
        if (centroCirculo.getX() + raioCirculo < minX || centroCirculo.getX() - raioCirculo > maxX ||
                centroCirculo.getY() + raioCirculo < minY || centroCirculo.getY() - raioCirculo > maxY) {
            return false; // Se não estiver dentro do quadrado, o círculo não está contido
        }

        // Verifica se o centro do círculo está dentro do polígono
        if (!isPointInsidePolygon(centroCirculo, pontos)) {
            return false; // Se não estiver dentro do polígono, o círculo não está contido
        }

        // Se passar por ambas as verificações, o círculo está contido
        return true;
    }

    public boolean contains(Poligono poligono) {
        // Obtém todas as coordenadas do polígono original
        List<Ponto> coordenadasPoligono = getAllCoordinates();

        // Obtém os vértices do polígono a ser verificado
        List<Ponto> verticesPoligono = poligono.getPontos();

        // Itera sobre todos os vértices do polígono a ser verificado
        for (Ponto ponto : verticesPoligono) {
            // Verifica se o ponto do polígono está dentro ou na borda do polígono original
            if (!isPointInsidePolygon2(ponto, coordenadasPoligono)) {
                return false;
            }
        }

        // Verifica se o polígono fornecido ultrapassa o polígono original
        for (Segmento segmento : poligono.getSegmentoDeRetas()) {
            for (Segmento originalSegment : getSegmentoDeRetas()) {
                if (segmento.arestasCruzam(originalSegment)) {
                    return false;
                }
            }
        }

        // Se todos os vértices do polígono a ser verificado estiverem dentro ou na
        // borda do polígono original,
        // e o polígono a ser verificado não ultrapassar o polígono original, então o
        // polígono está contido
        return true;
    }

    // Método auxiliar para verificar se um ponto está dentro ou na borda de um
    // polígono
    private boolean isPointInsidePolygon2(Ponto ponto, List<Ponto> coordenadasPoligono) {
        int n = coordenadasPoligono.size();
        boolean inside = false;
        int j = n - 1;
        for (int i = 0; i < n; j = i++) {
            // Verifica se o ponto está na borda do polígono
            if (coordenadasPoligono.get(i).equals(ponto) || coordenadasPoligono.get(j).equals(ponto)) {
                return true;
            }
            if (((coordenadasPoligono.get(i).getY() > ponto.getY()) != (coordenadasPoligono.get(j).getY() > ponto
                    .getY())) &&
                    (ponto.getX() < (coordenadasPoligono.get(j).getX() - coordenadasPoligono.get(i).getX())
                            * (ponto.getY() - coordenadasPoligono.get(i).getY())
                            / (coordenadasPoligono.get(j).getY() - coordenadasPoligono.get(i).getY())
                            + coordenadasPoligono.get(i).getX())) {
                inside = !inside;
            }
        }
        return inside;
    }

    // Método auxiliar para verificar se um ponto está dentro de um polígono
    private boolean isPointInsidePolygon(Ponto ponto, List<Ponto> coordenadasPoligono) {
        int n = coordenadasPoligono.size();
        boolean inside = false;
        int j = n - 1;
        for (int i = 0; i < n; j = i++) {
            if (((coordenadasPoligono.get(i).getY() > ponto.getY()) != (coordenadasPoligono.get(j).getY() > ponto
                    .getY())) &&
                    (ponto.getX() < (coordenadasPoligono.get(j).getX() - coordenadasPoligono.get(i).getX())
                            * (ponto.getY() - coordenadasPoligono.get(i).getY())
                            / (coordenadasPoligono.get(j).getY() - coordenadasPoligono.get(i).getY())
                            + coordenadasPoligono.get(i).getX())) {
                inside = !inside;
            }
        }
        return inside;
    }

    public List<Ponto> getAllCoordinates() {
        // Inicializa a lista para armazenar as coordenadas
        List<Ponto> coordenadas = new ArrayList<>();

        // Obtém os vértices do quadrado
        List<Ponto> vertices = getPontos();

        // Encontra os limites horizontais e verticais do quadrado
        double minX = Double.MAX_VALUE;
        double maxX = Double.MIN_VALUE;
        double minY = Double.MAX_VALUE;
        double maxY = Double.MIN_VALUE;
        boolean inte = true;
        for (Ponto ponto : vertices) {

            minX = Math.min(minX, ponto.getX());
            maxX = Math.max(maxX, ponto.getX());
            minY = Math.min(minY, ponto.getY());
            maxY = Math.max(maxY, ponto.getY());

        }

        // Itera sobre todas as coordenadas inteiras dentro dos limites do quadrado
        if (inte == true) {
            for (int x = (int) minX; x <= maxX; x++) {
                for (int y = (int) minY; y <= maxY; y++) {
                    coordenadas.add(new Ponto(x, y));
                }
            }
        } else {
            for (double x = minX; x <= maxX; x++) {
                for (double y = minY; y <= maxY; y++) {
                    coordenadas.add(new Ponto(x, y));
                }
            }
        }

        // Retorna a lista de coordenadas
        return coordenadas;
    }


    public boolean intersect4(Poligono otherPolygon) {
        // Verifica se há interseção entre as arestas dos polígonos
        for (Segmento segmento : this.getSegmentoDeRetas()) {
            for (Segmento otherSegment : otherPolygon.getSegmentoDeRetas()) {
                if (segmento.arestasCruzam2(otherSegment)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    
    public boolean sharePoints(Poligono otherPolygon) {
        // Obtém todas as coordenadas do polígono atual
        List<Ponto> coordenadasPoligonoAtual = this.getAllCoordinates();
    
        // Obtém todas as coordenadas do polígono recebido por argumento
        List<Ponto> coordenadasOutroPoligono = otherPolygon.getAllCoordinates();
    
        // Itera sobre todas as coordenadas do polígono atual
        for (Ponto ponto : coordenadasPoligonoAtual) {
            // Verifica se o ponto do polígono atual está presente no polígono recebido
            if (coordenadasOutroPoligono.contains(ponto)) {
                return true; // Se o ponto estiver presente, retorna verdadeiro
            }
        }
    
        // Se nenhum ponto do polígono atual estiver presente no polígono recebido, retorna falso
        return false;
    }

    public boolean sharePoints(Circle otherPolygon) {
        // Obtém todas as coordenadas do polígono atual
        List<Ponto> coordenadasPoligonoAtual = this.getAllCoordinates();
    
        // Obtém todas as coordenadas do polígono recebido por argumento
        List<Ponto> coordenadasOutroPoligono = otherPolygon.getAllCoordinates();
    
        // Itera sobre todas as coordenadas do polígono atual
        for (Ponto ponto : coordenadasPoligonoAtual) {
            // Verifica se o ponto do polígono atual está presente no polígono recebido
            if (coordenadasOutroPoligono.contains(ponto)) {
                return true; // Se o ponto estiver presente, retorna verdadeiro
            }
        }
    
        // Se nenhum ponto do polígono atual estiver presente no polígono recebido, retorna falso
        return false;
    }
    

    /**
     * Obtém a lista de pontos do polígono.
     * 
     * @return Lista de pontos que formam o polígono.
     */
    public List<Ponto> getPontos() {
        return pontos;
    }

    /**
     * Obtém a lista de segmentos de reta do polígono.
     * 
     * @return Lista de segmentos de reta que formam as arestas do polígono.
     */
    public List<Segmento> getSegmentoDeRetas() {
        return segmentoDeRetas;
    }

    /**
     * Converte uma string de entrada em uma lista de pontos.
     * 
     * @param input String de entrada contendo as coordenadas dos pontos.
     * @return Lista de pontos que formam o polígono.
     */
    private static List<Ponto> toInt(String input) {
        String[] parts = input.split(" ");
        if ((parts.length - 2) % 2 == 0)
            System.exit(0);

        List<Ponto> pontos = new ArrayList<>();
        for (int i = 1, count = 0; count < Integer.parseInt(parts[0]); i += 2, count++) {
            pontos.add(new Ponto(Double.parseDouble(parts[i]), Double.parseDouble(parts[i + 1])));
        }
        return pontos;
    }

    /**
     * Verifica se dois polígonos são iguais.
     * 
     * @param object Objeto a ser comparado.
     * @return true se os polígonos forem iguais, false caso contrário.
     */
    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (object == null || getClass() != object.getClass())
            return false;
        Poligono poligono = (Poligono) object;
        if (segmentoDeRetas.size() != poligono.getSegmentoDeRetas().size()) {
            return false;
        }
        List<Segmento> segmentoCopy = new ArrayList<>(poligono.getSegmentoDeRetas());
        for (Segmento segmento : segmentoDeRetas) {
            boolean found = false;
            Iterator<Segmento> iterator = segmentoCopy.iterator();
            while (iterator.hasNext()) {
                Segmento segmentoPoligono = iterator.next();
                if (segmento.equals(segmentoPoligono)) {
                    iterator.remove();
                    found = true;
                    break;
                }
            }
            if (!found) {
                return false;
            }
        }
        return segmentoCopy.isEmpty();
    }

    /**
     * Calcula o código de hash para o polígono.
     * 
     * @return Código de hash do polígono.
     */
    @Override
    public int hashCode() {
        return Objects.hash(pontos, segmentoDeRetas);
    }

    /**
     * Calcula o centro do polígono.
     * 
     * @return Ponto representando o centro do polígono.
     */
    public Ponto calcularCentro() {
        double centroX = 0;
        double centroY = 0;
        for (Ponto ponto : pontos) {
            centroX += ponto.getX();
            centroY += ponto.getY();
            if (centroX == 0 && centroY == 0) {
                centroX += ponto.getX();
                centroY += ponto.getX();
            }
        }

        centroX /= pontos.size();
        centroY /= pontos.size();
        return new Ponto(centroX, centroY);
    }

    /**
     * Rotaciona o polígono em torno de um ponto central por um determinado ângulo.
     * 
     * @param anguloGraus Ângulo de rotação em graus.
     * @param centroide   Ponto central em torno do qual o polígono será
     *                    rotacionado.
     * @return Novo polígono rotacionado.
     */
    public Poligono rotacionar(int anguloGraus, Ponto centroide) {
        List<Ponto> pontosPol = new ArrayList<>();
        for (Ponto ponto : pontos) {

            pontosPol.add(ponto.rotacionar(anguloGraus, centroide));
        }

        return new Poligono(pontosPol);
    }

   
        public Poligono combine(Poligono outroPoligono) {
            // Obtém os pontos dos polígonos
            List<Ponto> pontos1 = this.getPontos();
            List<Ponto> pontos2 = outroPoligono.getPontos();
    
            // Encontra os pontos mais distantes em cada eixo (x e y) de ambos os polígonos
            double minX = Math.min(pontos1.get(0).getX(), pontos2.get(0).getX());
            double minY = Math.min(pontos1.get(0).getY(), pontos2.get(0).getY());
            double maxX = Math.max(pontos1.get(2).getX(), pontos2.get(2).getX());
            double maxY = Math.max(pontos1.get(2).getY(), pontos2.get(2).getY());
    
            // Cria um novo polígono com os pontos mais distantes
            List<Ponto> combinedPoints = new ArrayList<>();
            combinedPoints.add(new Ponto(minX, minY));
            combinedPoints.add(new Ponto(maxX, minY));
            combinedPoints.add(new Ponto(maxX, maxY));
            combinedPoints.add(new Ponto(minX, maxY));
    
            return new Poligono(combinedPoints);
        }
    
    

    /**
     * Realiza uma translação no polígono.
     * 
     * @param x Deslocamento horizontal.
     * @param y Deslocamento vertical.
     * @return Novo polígono transladado.
     */
    public Poligono translacao(int x, int y) {
        Ponto centro = calcularCentro();
        int deltaX = (int) (x - centro.getX());
        int deltaY = (int) (y - centro.getY());
        List<Ponto> pontosPol = new ArrayList<>();
        for (Ponto ponto : pontos) {
            pontosPol.add(ponto.translacionar(deltaX, deltaY));
        }
        return new Poligono(pontosPol);
    }

    public Poligono translacaoSemPonto(int x, int y) {

        List<Ponto> pontosCopy = new ArrayList<>();
        for (Ponto ponto : pontos) {
            pontosCopy.add(ponto.translacionar(x, y));
        }
        return new Poligono(pontosCopy);
    }

    /**
     * Retorna uma representação textual do polígono.
     * 
     * @return String representando o polígono.
     */
    @Override
    public String toString() {
        return "Poligono de " + pontos.size() + " vertices: " + getPontos().toString();
    }

    @Override
    public Ponto getPosition() {
        return calcularCentro();
    }

}
