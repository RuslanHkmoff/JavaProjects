package md2html;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class Converter {
    private static BufferedReader reader;
    private static BufferedWriter writer;

    private final static Map<String, String> close = new HashMap<>(Map.of(
            "**", "</strong>",
            "__", "</strong>",
            "`", "</code>",
            "_", "</em>",
            "*", "</em>",
            "--", "</s>",
            "++", "</u>"
    ));
    private final static Map<String, String> open = new HashMap<>(Map.of(
            "**", "<strong>",
            "__", "<strong>",
            "`", "<code>",
            "_", "<em>",
            "*", "<em>",
            "--", "<s>",
            "++", "<u>"
    ));
    private final static Map<String, String> headersOp = new HashMap<>(Map.of(
            "#", "<h1>",
            "##", "<h2>",
            "###", "<h3>",
            "####", "<h4>",
            "#####", "<h5>",
            "######", "<h6>"
    ));
    private final static Map<Integer, String> headersCl = new HashMap<>(Map.of(
            1, "</h1>",
            2, "</h2>",
            3, "</h3>",
            4, "</h4>",
            5, "</h5>",
            6, "</h6>"
    ));

    private final static Map<Character, String> specSymbols = new HashMap<>(Map.of(
            '&', "&amp;",
            '<', "&lt;",
            '>', "&gt;"
    ));

    private static String input() throws IOException {
        StringBuilder sb = new StringBuilder();
        String s;
        do {
            if ((s = reader.readLine()) == null) {
                return "";
            }
        } while (s.isEmpty());
        do {
            sb.append(s);
            sb.append("\n");
        } while ((s = reader.readLine()) != null && !s.isEmpty());
        return sb.toString();

    }

    private static boolean isMarkSymbol(char ch) {
        return (ch == '*') || (ch == '-') || (ch == '_') || (ch == '`') || (ch == '+');
    }

    private static boolean isNormalSymbol(char ch) {
        return (!isMarkSymbol(ch) && ch != '\\' && !specSymbols.containsKey(ch)) || ch == '-'
                || ch == ' ' || ch == '\n';
    }

    void convert(String input, String output) throws IOException {
        reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(input), StandardCharsets.UTF_8));
        StringBuilder ans = new StringBuilder();
        try {
            String s;
            while ((s = input()).length() != 0) {
                boolean start = false;
                int nows = 0, temp;
                boolean[] flagIsOne = new boolean[100];
                boolean[] flagIsTwo = new boolean[100];
                for (int i = 0; i < s.length(); i++) {
                    char ch = s.charAt(i);
                    if (s.charAt(i) == '#' && !start) {
                        start = true;
                        StringBuilder curr = new StringBuilder();
                        temp = i;
                        while (s.charAt(i) == '#') {
                            curr.append(s.charAt(i));
                            i++;
                        }
                        if (Character.isWhitespace(s.charAt(i))) {
                            ans.append(headersOp.get(curr.toString()));
                            nows = curr.length();
                            i++;
                            curr.delete(0, curr.length() - 1);
                        } else {
                            ans.append("<p>").append(curr.toString());
                            i = temp;
                            continue;
                        }
                    } else if (!start) {
                        ans.append("<p>");
                        start = true;
                    }
                    while (isMarkSymbol(s.charAt(i)) && (s.charAt(i + 1) != ' ' && s.charAt(i + 1) != '\n')) {
                        StringBuilder currMd = new StringBuilder();
                        if (i < s.length() - 1 && s.charAt(i + 1) == s.charAt(i)
                                && !flagIsTwo[(int) s.charAt(i)]) {
                            currMd.append(s.charAt(i));
                            currMd.append(s.charAt(i));
                            ans.append(open.get(currMd.toString()));
                            flagIsTwo[(int) s.charAt(i)] = true;
                            i += 2;
                        } else if (s.charAt(i + 1) != s.charAt(i) && s.charAt(i) != ' '
                                && !flagIsOne[(int) s.charAt(i)]) {
                            currMd.append(s.charAt(i));
                            ans.append(open.getOrDefault(currMd.toString(), "" + s.charAt(i)));
                            flagIsOne[(int) s.charAt(i)] = true;
                            i++;

                        } else {
                            break;
                        }
                    }
                    if (isMarkSymbol(s.charAt(i)) && s.charAt(i - 1) == ' ' &&
                            (s.charAt(i + 1) == ' ' || s.charAt(i + 1) == '\n')) {
                        ans.append(s.charAt(i));
                    }
                    if (isMarkSymbol(s.charAt(i)) && isMarkSymbol(s.charAt(i + 1))
                            && flagIsTwo[(int) s.charAt(i)]) {
                        String md = "" + s.charAt(i) + s.charAt(i);
                        ans.append(close.get(md));
                        flagIsTwo[(int) s.charAt(i)] = false;
                        if (i < s.length() - 2) {
                            i++;
                            ch = s.charAt(i);
                        }
                    } else if (isNormalSymbol(s.charAt(i))) {
                        ans.append(s.charAt(i));
                    } else if (isMarkSymbol(s.charAt(i)) && s.charAt(i + 1) != s.charAt(i)
                            && flagIsOne[(int) s.charAt(i)]) {
                        String md = "" + s.charAt(i);
                        ans.append(close.getOrDefault(md, "" + s.charAt(i)));
                        flagIsOne[(int) s.charAt(i)] = false;
                    }
                    if (s.charAt(i) == '\\') {
                        i++;
                        ans.append(s.charAt(i));
                    }
                    if (specSymbols.containsKey(s.charAt(i))) {
                        ans.append(specSymbols.get(s.charAt(i)));
                    }
                }
                if (nows != 0) {
                    ans.delete(ans.length() - 1, ans.length());
                    ans.append(headersCl.get(nows));
                    ans.append("\n");
                    start = false;
                    nows = 0;
                } else {
                    ans.delete(ans.length() - 1, ans.length());
                    ans.append("</p>");
                    ans.append("\n");
                    start = false;
                }
            }
        } finally {
            reader.close();
        }

        writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(output),
                StandardCharsets.UTF_8));
        try {
            writer.write(ans.toString());
        } finally {
            writer.close();
        }
    }
}