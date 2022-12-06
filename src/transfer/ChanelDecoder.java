package transfer;

import utils.HammingAlgorithm;
import utils.Utils;

import java.io.FileWriter;

public abstract class ChanelDecoder {

    public static void decodeAndTransfer(String binaryString, FileWriter writer){
        String dec = HammingAlgorithm.cutServiceBits(binaryString, writer);
        String result = Utils.decodeFromBinaryString(dec);
        Destination.transferToDecoderChanel(result);
    }

}
