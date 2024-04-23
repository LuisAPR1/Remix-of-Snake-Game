package Core.CoreTests;
import Core.Player;
import Core.Rank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class RankTest {

    private Rank rank;
    private List<Player> players;

    @BeforeEach
    public void setUp() {
        // Configurando o ambiente de teste
        players = new ArrayList<>();
        players.add(new Player("Alice", 100));
        players.add(new Player("Bob", 80));
        players.add(new Player("Charlie", 120));
        rank = new Rank(players);
    }

    @Test
    public void testRankInitialization() {
        // Verificando se a lista de jogadores foi inicializada corretamente
        assertEquals(players, rank.getPlayers());
    }

    @Test
    public void testUpdateRank() {
        // Atualizando o rank de um jogador
        Player player = players.get(0);
        int newScore = 150;
        rank.updateRank(player, newScore);

        // Verificando se o score do jogador foi atualizado corretamente
        assertEquals(newScore, player.getScore());
    }

    @Test
    public void testPrintRank() {
        // Redirecionando a saída padrão para capturar a impressão do rank
        String expectedOutput = "Name: Alice || Score: 100\n" +
                                "Name: Bob || Score: 80\n" +
                                "Name: Charlie || Score: 120\n";
        assertDoesNotThrow(() -> {
            assertEquals(expectedOutput, getPrintedRank());
        });
    }

    private String getPrintedRank() {
        // Capturando a saída impressa pelo método printRank()
        try {
            return captureOutput(() -> rank.printRank());
        } catch (Exception e) {
            return "";
        }
    }

    private String captureOutput(Runnable code) {
        // Captura a saída impressa durante a execução do código
        // Redireciona a saída padrão para um StringBuilder
        StringBuilder stringBuilder = new StringBuilder();
        // Salva a saída padrão
        var oldOut = System.out;
        // Redireciona a saída padrão para o StringBuilder
        System.setOut(new java.io.PrintStream(
                new java.io.OutputStream() {
                    public void write(int b) {
                        stringBuilder.append((char) b);
                    }
                }));
        // Executa o código que imprime
        code.run();
        // Restaura a saída padrão
        System.setOut(oldOut);
        return stringBuilder.toString();
    }
}

