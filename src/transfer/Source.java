package transfer;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public abstract class Source {

    public static void startTransfer() {

        Path textFilePath = Path.of("source.txt");
        Path serviceFilePath = Path.of("service.txt");
        FileWriter writer = null;
        String string;
        try {
            Files.delete(serviceFilePath);
            Files.createFile(serviceFilePath);
            writer = new FileWriter(serviceFilePath.toString(), true);
            File file = new File(textFilePath.toString());
            InputStreamReader reader = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(reader);
            string = bufferedReader.readLine();
            writer.append("Source text is: "+string);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ChanelCoder.transferToCoder(string, writer);
    }

}
