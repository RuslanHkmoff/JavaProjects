import java.io.*;
import java.nio.charset.StandardCharsets;

public class Scanner {
    private String separator = System.lineSeparator();
    private BufferedReader reader;

    Scanner(File filename) throws IOException {
        this.reader = new BufferedReader(new FileReader(filename, StandardCharsets.UTF_8));
    }

    Scanner(InputStream inputStream) throws IOException {
        this.reader = new BufferedReader(new InputStreamReader(inputStream));
    }

    Scanner(String s) throws IOException {
        if (s == null) {
            throw new NullPointerException();
        }
        this.reader = new BufferedReader(new StringReader(s));
    }

    public static boolean WordSymbol(char symbol) {
        if (Character.isLetter(symbol) ||
                Character.DASH_PUNCTUATION == Character.getType(symbol) ||
                symbol == '\'') {
            return true;
        }
        return false;
    }
    char[] buffer = new char[32];
    private int ended = 0;
    private int c = 0;

    public String nextLine() throws IOException {
        StringBuilder s = new StringBuilder();
        while (true) {
            if (ended == 0) {
                c = reader.read(buffer);
                if (c == -1) {
                    if (s.length() != 0) {
                        return s.toString();
                    }
                    return null;
                }
            }
            for (int i = ended; i < c; i++) {
                if (separator.length() == 2 && buffer[i] == '\r') {
                    if (i == c - 1) {
                        ended = 0;
                    }
                    continue;
                } else if (buffer[i] == '\n' || ((separator.length() == 1 && buffer[i] == '\r'))) {
                    if (i < c - 1) {
                        ended = i + 1;
                    } else {
                        ended = 0;
                    }
                    return s.toString();
                } else {
                    s.append(buffer[i]);
                }
                if (i == c - 1 && s.length() != 0) {
                    ended = 0;
                }
            }
        }
    }

    private int end = 0;
    private String currS = "";
    private String currWord = "";

    public String next() throws IOException {
        if (currS == null || currS.isEmpty()) {
            String s = nextLine();
            currS = s;
            int first = 0, last = 0;
            if (s != null) {
                for (int i = end; i < s.length(); i++) {
                    if (i == 0 || (s.charAt(i - 1) == ' ' && (s.charAt(i)) != ' ')) {
                        first = i;
                    }
                    if ((i == currS.length() - 1) || (currS.charAt(i + 1) == ' ' && currS.charAt(i) != ' ')) {
                        last = i;
                        end = i + 1;
                        return s.substring(first, last + 1);
                    }
                }
            } else {
                return null;
            }
        } else {
            int first = 0, last = 0;
            if (null != currS) {
                for (int i = end; i < currS.length(); i++) {
                    if (i == 0 || (currS.charAt(i - 1) == ' ' && (currS.charAt(i)) != ' ')) {
                        first = i;
                    }
                    if ((i == currS.length() - 1) || (currS.charAt(i + 1) == ' ' && currS.charAt(i) != ' ')) {
                        last = i;
                        end = i + 1;
                        return currS.substring(first, last + 1);
                    }
                }
            }
        }
        currS = "";
        return null;
    }

    public String nextWord() throws IOException {
        if (currWord == null || currWord.isEmpty()) {
            String s = nextLine();
            currWord = s;
            int first = 0, last = 0;
            if (s != null) {
                for (int i = end; i < s.length(); i++) {
                    if (i == 0 || (!WordSymbol(s.charAt(i)) && (WordSymbol(s.charAt(i))))) {
                        first = i;
                    }
                    if ((i == currWord.length() - 1) ||
                            (!WordSymbol(currWord.charAt(i+1)) && WordSymbol(currWord.charAt(i)))) {
                        last = i;
                        end = i + 1;
                        return s.substring(first, last + 1).toLowerCase();
                    }
                }
            } else {
                return null;
            }
        } else {
            int first = 0, last = 0;
            if (null != currWord) {
                for (int i = end; i < currWord.length(); i++) {
                    if (i == 0 || (!WordSymbol(currWord.charAt(i)) && (WordSymbol(currWord.charAt(i))))) {
                        first = i;
                    }
                    if ((i == currWord.length() - 1) ||
                            (!WordSymbol(currWord.charAt(i+1)) && WordSymbol(currWord.charAt(i)))) {
                        last = i;
                        end = i + 1;
                        return currWord.substring(first, last + 1).toLowerCase();
                    }
                }
            }
        }
        currWord = "";
        return null;
    }

    public void close() throws IOException {
        reader.close();
    }
}