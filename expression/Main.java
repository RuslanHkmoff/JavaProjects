package expression;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = Integer.parseInt(sc.nextLine());
        int ans = new Multiply(
                new Subtract(
                        new Variable("x"), new Const(1)),
                new Subtract(
                        new Variable("x"), new Const(1)
                )).evaluate(x);
        System.out.println(ans);
    }
}
