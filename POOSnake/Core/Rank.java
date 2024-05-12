package Core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe que gerencia o ranking dos jogadores.
 * 
 * Esta classe permite adicionar novos jogadores, atualizar o ranking com base nos pontos dos jogadores
 * e escrever o ranking atualizado em um arquivo.
 * Também fornece métodos para ler o ranking do arquivo e imprimir a leaderboard.
 * 
 * O ranking é armazenado em um arquivo de texto chamado "rank.txt".
 * Cada linha do arquivo representa um jogador no formato "nome, pontuação".
 * A pontuação dos jogadores é classificada em ordem decrescente.
 * 
 * @author Luís Rosa, José Lima, Pedro Ferreira, José Lima, Pedro Ferreira
 * @version Versão 1.0 10/05/2024
 */
public class Rank {

    // Lista de jogadores no ranking
    private List<Player> Players;
    int nleader;
    /**
     * Construtor para criar um objeto Rank com uma lista de jogadores.
     * 
     * @param p A lista de jogadores.
     */
    public Rank(List<Player> p, int nleader) {
        this.Players = p;
        this.nleader= nleader;
    }

    /**
     * Obtém a lista de jogadores do ranking.
     * 
     * @return A lista de jogadores.
     */
    public List<Player> getPlayers() {
        return Players;
    }

    /**
     * Adiciona um jogador à lista de jogadores do ranking.
     * 
     * @param p O jogador a ser adicionado.
     */
    public void addPlayer(Player p) {
        Players.add(p);
    }

    /**
     * Atualiza o ranking com o nome e os pontos do jogador.
     * 
     * @param namePlayer O nome do jogador.
     * @param points     Os pontos do jogador.
     */
    public void updateRank(String namePlayer, int points) {
        // Leitura do conteúdo do arquivo para obter os jogadores existentes
        List<Player> existingPlayers = readRankFromFile();

        // Verificação se o jogador atual já está na lista
        boolean playerExists = false;
        for (Player player : existingPlayers) {
            if (player.getName().equals(namePlayer)) {
                playerExists = true;
                // Se o jogador existir, atualizamos seu resultado se for maior
                if (points > player.getScore()) {
                    player.setScore(points);
                    System.out.println("Pontuação atualizada para o jogador " + namePlayer + ": " + points);
                    break; // Não é necessário continuar a busca
                }
            }
        }

        // Se o jogador não existir na lista, adicionamos ele
        if (!playerExists) {
            existingPlayers.add(new Player(namePlayer, points));
            System.out.println("Novo jogador adicionado: " + namePlayer + ", Pontuação: " + points);
        }

        // Escrevendo a lista atualizada no arquivo
        writeToFile(existingPlayers);

        // Imprime a leaderboard atualizada
        printLeaderboard();
    }

    /**
     * Lê o arquivo "rank.txt" e imprime a leaderboard dos jogadores.
     */
    public void printLeaderboard() {

    
        System.out.flush();
        // Lê o conteúdo do arquivo "rank.txt" e imprime a leaderboard dos jogadores
        System.out.println("-----LEADERBOARD-----");

        List<Player> leaderboard = readRankFromFile();

        for (int i = 0; i < this.nleader; i++) {
            Player player = leaderboard.get(i);
            System.out.println("TOP " + (i + 1) + " - " + player.getName() + ": " + player.getScore());
        }
    }

    /**
     * Lê o conteúdo do arquivo "rank.txt" e retorna a lista de jogadores.
     * 
     * @return A lista de jogadores lida do arquivo.
     */
    private static List<Player> readRankFromFile() {
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

    /**
     * Escreve a lista de jogadores no arquivo "rank.txt".
     * 
     * @param players A lista de jogadores a ser escrita no arquivo.
     */
    public void writeToFile(List<Player> players) {
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
