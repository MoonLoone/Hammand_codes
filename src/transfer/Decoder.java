package transfer;

import utils.HammingAlgorithm;
import utils.Utils;

public abstract class Decoder {

    public static void transferToDecoderChanel(String binaryString, int[][] G){
        int[][] H = Utils.createCheckMatrix(G);
        String decoded = HammingAlgorithm.decode(binaryString, H);
        decoded = HammingAlgorithm.cutServiceBits(decoded);
        Destination.transferToDecoderChanel(decoded);
    }
}
