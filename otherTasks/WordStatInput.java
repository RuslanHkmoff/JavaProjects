import java.io.*;
import java.util.*;
import java.util.Arrays;

public class WordStatInput {

    public static boolean WordSymbol(char symbol) {
        if (Character.isLetter(symbol)) {
            return true;
        }
        if (Character.DASH_PUNCTUATION == Character.getType(symbol)) {
            return true;
        }
        if (symbol == '\'') {
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(args[0]),
                            "UTF-8"
                    )
            );

            ArrayList<String> words = new ArrayList<>();

            int[] quantity = new int[2];
            try {
                while (true) {
                    String str = reader.readLine();
                    if (str == null) {
                        break;
                    }

                    int len = 0, first = 0, last = 0;
                    for (int i = 0; i < str.length(); i++) {
                        if (WordSymbol(str.charAt(i))) {
                            if (i == 0 || !WordSymbol(str.charAt(i - 1))) {
                                first = i;
                            }
                            if (i == str.length() - 1 || !WordSymbol(str.charAt(i + 1))) {
                                last = i + 1;
                                words.add(str.substring(first, last).toLowerCase());

                                last = 0;
                                first = 0;
                            }
                        }
                    }
                }
                quantity = Arrays.copyOf(quantity, words.size());
                int temp;
                for (int i = 0; i < words.size() - 1; i++) {
                    for (int j = i + 1; j < words.size(); j++) {
                        if (!(words.get(i).equals(words.get(j)))) {
                            continue;
                        } else {
                            quantity[i]++;
                            temp = j - 1;
                            words.remove(j);
                            j = temp;
                        }
                    }
                }

            } finally {
                System.out.println("Input closed");
                reader.close();
            }
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream(args[1]),
                            "UTF-8"
                    )
            );
            try {
                for (int j = 0; j < words.size(); j++) {
                    if (words.get(j) == null) {
                        continue;
                    } else {
                        writer.write(words.get(j) + " " + (quantity[j] + 1));
                        writer.newLine();
                    }
                }
            } catch (IOException e) {
                System.out.println("Problems... :" + e.getMessage());
            } finally {
                System.out.println("Writer close");
                writer.close();
            }
        } catch (
                FileNotFoundException e) {
            System.out.println("Cannot found File" + e.getMessage());
        } catch (
                IOException e) {
            System.out.println("Cannot write" + e.getMessage());
        }
    }
}