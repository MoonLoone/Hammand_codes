package transfer;

import utils.HammingAlgorithm;
import utils.Utils;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public abstract class Coder {

    public static void transferToTransferChanel(String binaryString, FileWriter writer){
        int[][] G = Utils.createMatrix(writer);
        String encoded = HammingAlgorithm.encode(binaryString, G);

        TransferChanel.transferToDecoder(encoded,G, writer);
    }

}
