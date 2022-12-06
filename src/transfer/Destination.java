package transfer;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Destination {

    public static void transferToDecoderChanel(String decodedText){
        Path resultFilePath = Path.of("result.txt");
        try {
            Files.delete(resultFilePath);
            Files.createFile(resultFilePath);
            FileWriter writer = new FileWriter(resultFilePath.toString(),true);
            writer.append(decodedText);
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
