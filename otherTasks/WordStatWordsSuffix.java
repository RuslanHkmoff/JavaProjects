import java.io.*;
import java.util.*;

public class WordStatWordsSuffix {

    public static String Suffix(String s) {
        if (s.length() <= 3) {
            return s;
        } else {
            return s.substring(s.length() - 3, s.length());
        }
    }


    public static boolean isWordSymbol(char symbol) {
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
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(args[0]), "UTF-8"));
            ArrayList<String> words = new ArrayList<>();
            ArrayList<String> suff = new ArrayList<>();
            int[] quantity = new int[2];
            try {
                while (true) {
                    String str = reader.readLine();
                    if (str == null) {
                        break;
                    }

                    int len = 0, first = 0, last = 0;
                    for (int i = 0; i < str.length(); i++) {
                        if (isWordSymbol(str.charAt(i))) {
                            if (i == 0 || !isWordSymbol(str.charAt(i - 1))) {
                                first = i;
                            }
                            if (i == str.length() - 1 || !isWordSymbol(str.charAt(i + 1))) {
                                last = i + 1;
                                words.add(str.substring(first, last).toLowerCase());

                                last = 0;
                                first = 0;
                            }
                        }
                    }
                }
                for (int k = 0; k < words.size(); k++) {
                    System.out.print(words.get(k) + " ");
                    if (words.get(k) != null) {
                        suff.add(Suffix(words.get(k)));
                    }
                }
                for (int i = 0; i < suff.size(); i++) {
                    //System.out.print(suff.get(i) + " ");

                }
                System.out.println();
                Collections.sort(suff);
                int temp;

                quantity = Arrays.copyOf(quantity, suff.size());
                for (int i = 0; i < suff.size() - 1; i++) {
                    for (int j = i + 1; j < suff.size(); j++) {
                        if (!(suff.get(i).equals(suff.get(j)))) {
                            continue;
                        } else {
                            quantity[i]++;
                            temp = j - 1;
                            suff.remove(j);
                            j = temp;
                        }
                    }
                }

            } finally {
                System.out.println("Input closed");
                reader.close();
            }
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1]), "UTF-8"));
            try {
                for (int j = 0; j < suff.size(); j++) {
                    if (suff.get(j) == null) {
                        continue;
                    } else {
                        writer.write(suff.get(j) + " " + (1 + quantity[j]));
                        writer.newLine();
                    }
                }
            } catch (IOException e) {
                System.out.println("Problems... :" + e.getMessage());
            } finally {
                System.out.println("Writer close");
                writer.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Cannot found File" + e.getMessage());
        } catch (IOException e) {
            System.out.println("Cannot write" + e.getMessage());
        }
    }
}