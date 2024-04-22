package Core.CoreTests;
import Core.Player;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    @Test
    public void testConstructorAndGetters() {
        // Criando uma instância de Player
        String name = "Alice";
        int score = 100;
        Player player = new Player(name, score);

        // Verificando se os valores estão corretos após a criação
        assertEquals(name, player.getNome());
        assertEquals(score, player.getScore());
    }

    @Test
    public void testSetters() {
        // Criando uma instância de Player
        String name = "Bob";
        int score = 50;
        Player player = new Player(name, score);

        // Modificando os valores
        String newName = "Charlie";
        int newScore = 75;

        player.setNome(newName);
        player.setScore(newScore);

        // Verificando se os valores foram modificados corretamente
        assertEquals(newName, player.getNome());
        assertEquals(newScore, player.getScore());
    }

    @Test
    public void testToString() {
        // Criando uma instância de Player
        String name = "David";
        int score = 80;
        Player player = new Player(name, score);

        // Verificando se o método toString() retorna o formato esperado
        assertEquals("Name: David || Score: 80", player.toString());
    }
}

