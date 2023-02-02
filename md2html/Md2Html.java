package md2html;

import java.io.IOException;

public class Md2Html {
    public static void main(String[] args) throws IOException{

        Converter converter = new Converter();
        converter.convert(args[0], args[1]);
    }
}
