package Interface;

import java.util.List;

public class Rank {
    private List<Player> Players; // Tipo - Jogador (int scoreAtual, String nome, rank)

    public Rank(List<Player> p) {
        this.Players = p;
    }

    private void updateRank(Player p, int score) {

        //escrever no documento de texto (ha de ser um foreach com os players e um imprimir rank a mandar para o ficheiro)
        //atualizar no objeto jogador
        p.setScore(score);
    }

    private void showRank(Player p) {
        // sout p.showRank();

    }

    private void imprimirRank() {

    }

}
