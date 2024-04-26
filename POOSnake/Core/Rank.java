package Core;

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
        // Exemplo de como o arquivo poderia ser escrito (pseudocódigo)
        // String content = Players.stream()
        //                         .sorted((p1, p2) -> p2.getScore() - p1.getScore())
        //                         .map(p -> p.getName() + ", " + p.getScore())
        //                         .collect(Collectors.joining("\n"));
        // Simulação de escrita em arquivo
        // System.out.println("Writing to file: \n" + content);
    }
}