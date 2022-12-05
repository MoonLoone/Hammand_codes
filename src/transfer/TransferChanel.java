package transfer;

import utils.Utils;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public abstract class TransferChanel {

    public static void transferToDecoder(String binaryString, int[][] G){
        System.out.println("Enter P: ");
        Scanner scanner = new Scanner(System.in);
        double P = scanner.nextDouble();
        String binaryTextWithNoises = Utils.makeNoises(binaryString, P);
        Path serviceFilePath = Path.of("service.txt");

        Decoder.transferToDecoderChanel(binaryTextWithNoises, G);
    }

}
