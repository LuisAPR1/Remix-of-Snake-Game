package Core;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Rank {
    private List<Player> Players;

    public List<Player> getPlayers() {
        return Players;
    }
    
    public Rank(List<Player> p) {
        this.Players = p;
    }

    public void addPlayer(Player p) {
        Players.add(p);
    }

    public void updateRank(Player p, int score) {
        p.setScore(score);
        writeToFile();
    }

    private void writeToFile() {
        // Ordenando os jogadores pelo score em ordem decrescente
        List<Player> sortedPlayers = Players.stream()
                                            .sorted(Comparator.comparingInt(Player::getScore).reversed())
                                            .collect(Collectors.toList());

        // Escrevendo os jogadores no arquivo
        try (FileWriter writer = new FileWriter("rank.txt")) {
            for (Player player : sortedPlayers) {
                writer.write(player.getName() + ", " + player.getScore() + "\n");
            }
            System.out.println("Rank atualizado e escrito no arquivo 'rank.txt'");
        } catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo: " + e.getMessage());
        }
    }
}