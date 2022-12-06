package transfer;

import utils.HammingAlgorithm;
import utils.Utils;

import java.io.FileWriter;

public abstract class Decoder {

    public static void transferToDecoderChanel(String binaryString, int[][] G, FileWriter writer){
        int[][] H = Utils.createCheckMatrix(G, writer);
        String decoded = HammingAlgorithm.decode(binaryString, H);
        ChanelDecoder.decodeAndTransfer(decoded, writer);

    }
}
