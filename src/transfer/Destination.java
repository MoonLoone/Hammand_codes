package transfer;

import utils.Utils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class Destination {

    public static void transferToDecoderChanel(String decodedText){
        String result = Utils.decodeFromBinaryString(decodedText);
        Path resultFilePath = Path.of("result.txt");
        try {
            Files.delete(resultFilePath);
            Files.createFile(resultFilePath);
            FileWriter writer = new FileWriter(resultFilePath.toString(),true);
            writer.append(result);
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
