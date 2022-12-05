import transfer.ChanelCoder;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

public class Main {


    public static void main(String[] args) {

        Path textFilePath = Path.of("myFile.txt");
        String string;
        try {
            File file = new File(textFilePath.toString());
            InputStreamReader reader = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(reader);
            string = bufferedReader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ChanelCoder.transferToCoder(string);
    }
}
