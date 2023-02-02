import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ArrayList;
import static java.util.Map.Entry.comparingByValue;


public class Wspp {
    public static boolean WordSymbol(char symbol) {
        if (Character.isLetter(symbol) ||
                Character.DASH_PUNCTUATION == Character.getType(symbol) ||
                symbol == '\'') {
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws IOException, NullPointerException {
        HashMap<String, ArrayList<Integer>> words = new LinkedHashMap<>();
        Scanner sc = new Scanner(new File(args[0]));
        try {
            String line = sc.nextLine();
            int counterWord = 1;
            while (line != null) {
                Scanner sc2 = new Scanner(line);
                int len = 0, first = 0, last = 0;
                for (int i = 0; i < line.length(); i++) {
                    if (WordSymbol(line.charAt(i))) {
                        if (i == 0 || !WordSymbol(line.charAt(i - 1))) {
                            first = i;
                        }
                        if (i == line.length() - 1 || !WordSymbol(line.charAt(i + 1))) {
                            last = i + 1;
                            String s = line.substring(first, last).toLowerCase();
                            if (words.containsKey(s)) {
                                ArrayList<Integer> currArr = words.get(s);
                                if (currArr == null) {
                                    currArr = new ArrayList<Integer>(10);
                                }
                                currArr.add(counterWord);
                                counterWord++;
                                words.put(s, currArr);
                            } else {
                                ArrayList<Integer> currArr = new ArrayList<>();
                                currArr.add(counterWord);
                                words.put(s, currArr);
                                counterWord++;
                            }
                        }
                    }
                }
                line = sc.nextLine();
            }
        } finally {
            sc.close();
        }
        sc.close();
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(args[1]),
                        "UTF-8"
                )
        );
        try {
            for (String s : words.keySet()) {
                writer.write(s + " " + words.get(s).size());
                for (int i = 0; i < words.get(s).size(); i++) {
                    writer.write(" " + words.get(s).get(i));
                }
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Trouble with I/O:" + e.getMessage());
        } finally {
            System.out.println("Writer close");
            writer.close();
        }
    }
}

