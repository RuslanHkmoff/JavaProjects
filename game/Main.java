package game;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int result;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter m, n, k:");
        int m = sc.nextInt();
        int n = sc.nextInt();
        int k = sc.nextInt();
        System.out.println("Кол-во игр в матче");
        int winsNeeded = sc.nextInt();
        final Game game = new Game(true, new HumanPlayer(), new HumanPlayer());
        Match match = new Match(game, winsNeeded, m, n, k);
        match.createMatch(match);
    }
}
