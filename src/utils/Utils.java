package utils;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Utils {

    public static int[][] createMatrix() {
        int rowCount = 4;
        int columnCount = 7;
        int[][] G = new int[rowCount][columnCount];
        Scanner scanner = new Scanner(System.in);

        System.out.println("Do you want to edit your own matrix?");
        String isEdit = scanner.nextLine();
        Path serviceFilePath = Path.of("service.txt");

        if (isEdit.equals("yes")){
            G[0][0] = scanner.nextInt();
            G[0][1] = scanner.nextInt();
            G[0][2] = scanner.nextInt();
            G[0][3] = scanner.nextInt();
            G[0][4] = scanner.nextInt();
            G[0][5] = scanner.nextInt();
            G[0][6] = scanner.nextInt();

            G[1][0] = scanner.nextInt();
            G[1][1] = scanner.nextInt();
            G[1][2] = scanner.nextInt();
            G[1][3] = scanner.nextInt();
            G[1][4] = scanner.nextInt();
            G[1][5] = scanner.nextInt();
            G[1][6] = scanner.nextInt();

            G[2][0] = scanner.nextInt();
            G[2][1] = scanner.nextInt();
            G[2][2] = scanner.nextInt();
            G[2][3] = scanner.nextInt();
            G[2][4] = scanner.nextInt();
            G[2][5] = scanner.nextInt();
            G[2][6] = scanner.nextInt();

            G[3][0] = scanner.nextInt();
            G[3][1] = scanner.nextInt();
            G[3][2] = scanner.nextInt();
            G[3][3] = scanner.nextInt();
            G[3][4] = scanner.nextInt();
            G[3][5] = scanner.nextInt();
            G[3][6] = scanner.nextInt();
        }else {
            G[0][0] = 1;
            G[0][1] = 0;
            G[0][2] = 0;
            G[0][3] = 0;
            G[0][4] = 0;
            G[0][5] = 1;
            G[0][6] = 1;

            G[1][0] = 0;
            G[1][1] = 1;
            G[1][2] = 0;
            G[1][3] = 0;
            G[1][4] = 1;
            G[1][5] = 0;
            G[1][6] = 1;

            G[2][0] = 0;
            G[2][1] = 0;
            G[2][2] = 1;
            G[2][3] = 0;
            G[2][4] = 1;
            G[2][5] = 1;
            G[2][6] = 0;

            G[3][0] = 0;
            G[3][1] = 0;
            G[3][2] = 0;
            G[3][3] = 1;
            G[3][4] = 1;
            G[3][5] = 1;
            G[3][6] = 1;
        }

        return G;
    }

    public static int[][] createCheckMatrix(int[][] matrix) {
        int[][] inverseMatrix = new int[3][7];
        Path serviceFilePath = Path.of("service.txt");

        inverseMatrix[0][0] = matrix[0][4];
        inverseMatrix[0][1] = matrix[1][4];
        inverseMatrix[0][2] = matrix[2][4];
        inverseMatrix[0][3] = matrix[3][4];

        inverseMatrix[1][0] = matrix[0][5];
        inverseMatrix[1][1] = matrix[1][5];
        inverseMatrix[1][2] = matrix[2][5];
        inverseMatrix[1][3] = matrix[3][5];

        inverseMatrix[2][0] = matrix[0][6];
        inverseMatrix[2][1] = matrix[1][6];
        inverseMatrix[2][2] = matrix[2][6];
        inverseMatrix[2][3] = matrix[3][6];

        inverseMatrix[0][4] = matrix[0][0];
        inverseMatrix[1][4] = matrix[1][0];
        inverseMatrix[2][4] = matrix[2][0];

        inverseMatrix[0][5] = matrix[0][1];
        inverseMatrix[1][5] = matrix[1][1];
        inverseMatrix[2][5] = matrix[2][1];

        inverseMatrix[0][6] = matrix[0][2];
        inverseMatrix[1][6] = matrix[1][2];
        inverseMatrix[2][6] = matrix[2][2];

        try {
            Files.delete(serviceFilePath);
            Files.createFile(serviceFilePath);
            FileWriter writer = new FileWriter(serviceFilePath.toString(),true);
            writer.append("Check matrix: \n");
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 7; j++) {
                    writer.append(""+inverseMatrix[i][j]+" ");
                }
                writer.append("\n");
            }
            writer.flush();
        }catch (IOException e) {
            throw new RuntimeException(e);
        }

        return inverseMatrix;
    }

    public static String decodeFromBinaryString(String binaryString) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < binaryString.length() - 7; i = i + 8) {
            String substring = binaryString.substring(i, i + 8);
            char ch = (char) Integer.parseInt(substring, 2);
            stringBuilder.append(ch);
        }
        return stringBuilder.toString();
    }

    public static String makeNoises(String encodedText, double P) {
        StringBuilder stringBuilder = new StringBuilder();
        int position = (int)(Math.random()*4+1);
        System.out.println(position);
        for (int i = 0; i < encodedText.length(); i++) {
            position = position+7*i;
            if (i == position) {
                int number = Character.getNumericValue(encodedText.charAt(i));
                if (number == 1) number = 0;
                else {
                    number = 1;
                }
                stringBuilder.append(number);
            } else stringBuilder.append(encodedText.charAt(i));
        }
        return stringBuilder.toString();
    }

    public static String convertToBinaryString(String text){
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            result.append(String.format("%8s", Integer.toBinaryString(text.charAt(i))).replace(' ', '0'));
        }
        return result.toString();
    }

}
