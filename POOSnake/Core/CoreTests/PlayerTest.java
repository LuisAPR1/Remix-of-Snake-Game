package Core.CoreTests;
import Core.Player;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    @Test
    public void testConstructorAndGetters() {
       
        String name = "Alice";
        int score = 100;
        Player player = new Player(name, score);

       
        assertEquals(name, player.getNome());
        assertEquals(score, player.getScore());
    }

    @Test
    public void testSetters() {
       
        String name = "Bob";
        int score = 50;
        Player player = new Player(name, score);

       
        String newName = "Charlie";
        int newScore = 75;

        player.setNome(newName);
        player.setScore(newScore);

        
        assertEquals(newName, player.getNome());
        assertEquals(newScore, player.getScore());
    }

    @Test
    public void testToString() {
        
        String name = "David";
        int score = 80;
        Player player = new Player(name, score);

       
        assertEquals("Name: David || Score: 80", player.toString());
    }
}

