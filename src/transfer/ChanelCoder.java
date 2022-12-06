package transfer;
import utils.Utils;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public abstract class ChanelCoder {

    public static void transferToCoder(String text, FileWriter writer){
        String binaryString = Utils.convertToBinaryString(text, writer);

        Path serviceFilePath = Path.of("service.txt");

        Coder.transferToTransferChanel(binaryString, writer);
    }

}
