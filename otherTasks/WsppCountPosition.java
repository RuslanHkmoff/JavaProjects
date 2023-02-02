import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.util.Map.Entry.comparingByValue;

public class WsppCountPosition {
    public static boolean wordSymbol(char symbol) {
        if (Character.isLetter(symbol) ||
                Character.DASH_PUNCTUATION == Character.getType(symbol) ||
                symbol == '\'') {
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws IOException, NullPointerException {
        Map<String, ArrayList<String>> words = new LinkedHashMap<>();
        Scanner sc = new Scanner(new File(args[0]));
        try {
            String line = sc.nextLine();
            int counterWord = 1, counterLines = 1;
            while (line != null) {
                Scanner sc2 = new Scanner(line);
                int len = 0, first = 0, last = 0;
                for (int i = 0; i < line.length(); i++) {
                    if (wordSymbol(line.charAt(i))) {
                        if (i == 0 || !wordSymbol(line.charAt(i - 1))) {
                            first = i;
                        }
                        if (i == line.length() - 1 || !wordSymbol(line.charAt(i + 1))) {
                            last = i + 1;
                            String s = line.substring(first, last).toLowerCase();
                            ArrayList<String> currArr = words.getOrDefault(s, new ArrayList<>(10));
                            String lineWord = Integer.toString(counterLines) + ":" + Integer.toString(counterWord);
                            currArr.add(lineWord);
                            counterWord++;
                            words.put(s, currArr);
                        }
                    }
                }
                counterWord = 1;
                counterLines++;
                line = sc.nextLine();
            }
        } finally {
            sc.close();
        }
        Map<String, Integer> temp = new LinkedHashMap<>(); // Мапа: слово - кол-во вхождений
        for (String s : words.keySet()){
            temp.put(s, words.get(s).size());
        }
        ArrayList<Map.Entry<String, Integer>> list = new ArrayList<>(temp.entrySet());// ArrayList слово - кол-во вхождений
        list.sort(comparingByValue()); // Сортируем по значению
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(args[1]),
                        "UTF-8"
                )
        );
        try {
            for (int i = 0; i < list.size(); i++){
                String str = list.get(i).getKey();
                writer.write(str + " " + list.get(i).getValue());
                for (int j = 0; j < words.get(str).size(); j++){
                    writer.write(" " + words.get(str).get(j));
                }
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Trouble with I/O" + e.getMessage());
        } finally {
            System.out.println("Writer close");
            writer.close();
        }
    }
}
