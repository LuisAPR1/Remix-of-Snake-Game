package Core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
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

    public void updateRank() {
    // Atualiza o rank dos jogadores ao final do jogo
    // Este método já está atualizando o rank dos jogadores e escrevendo no arquivo

    // Após atualizar o rank, imprime a leaderboard
    printLeaderboard();
}

private void printLeaderboard() {
    // Lê o conteúdo do arquivo "rank.txt" e imprime a leaderboard dos jogadores
    System.out.println("-----LEADERBOARD-----");

    List<Player> leaderboard = readRankFromFile();

    for (int i = 0; i < leaderboard.size(); i++) {
        Player player = leaderboard.get(i);
        System.out.println("TOP " + (i + 1) + " - " + player.getName() + ": " + player.getScore());
    }
}

private List<Player> readRankFromFile() {
    List<Player> leaderboard = new ArrayList<>();

    try (BufferedReader reader = new BufferedReader(new FileReader("rank.txt"))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(", ");
            String playerName = parts[0];
            int playerScore = Integer.parseInt(parts[1]);
            leaderboard.add(new Player(playerName, playerScore));
        }
    } catch (IOException e) {
        System.out.println("Erro ao ler o arquivo: " + e.getMessage());
    }

    // Ordena a leaderboard pelo score em ordem decrescente
    leaderboard.sort(Comparator.comparingInt(Player::getScore).reversed());

    return leaderboard;
}

public void writeToFile() {
    // Obtém a lista de jogadores da classe Rank
    List<Player> players = getPlayers();

    // Ordena os jogadores pelo score em ordem decrescente
    List<Player> sortedPlayers = players.stream()
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