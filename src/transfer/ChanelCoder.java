package transfer;
import utils.Utils;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public abstract class ChanelCoder {

    public static void transferToCoder(String text){
        String binaryString = Utils.convertToBinaryString(text);

        Path serviceFilePath = Path.of("service.txt");

        Coder.transferToTransferChanel(binaryString);
    }

}
