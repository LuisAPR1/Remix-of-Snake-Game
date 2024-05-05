package Core.CoreTests;

import Core.Player;
import Core.Rank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class RankTest {
    private Rank rank;
    private Player player1;
    private Player player2;

    @BeforeEach
    public void setUp() {
        player1 = new Player("Alice", 100);
        player2 = new Player("Bob", 200);
        ArrayList<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        rank = new Rank(players);
    }

    @Test
    public void testAddPlayer() {
        Player newPlayer = new Player("Alice", 100);
        rank.addPlayer(newPlayer);
        assertTrue(rank.getPlayers().contains(newPlayer));
    }

    @Test
    public void testUpdateRank() {
        int newScore = 200;
        rank.updateRank(player1, newScore);
        assertEquals(newScore, player1.getScore());
   }
}