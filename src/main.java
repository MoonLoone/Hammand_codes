import java.util.ArrayList;
import java.util.Scanner;

public class main {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int rowCount = 4;
        int columnCount = 7;
        int[][] G = createMatrix();
        int[][] H = createInverseMatrix(G, rowCount, columnCount);
        /*for (int i=0 ;i<rowCount; i++){
            for (int j=0; j<columnCount; j++){
                G[i][j] = scanner.nextInt();
                System.out.print(G[i][j]+" ");
            }
            System.out.println();
        }*/

        String string = "Hello world.";
        double P = 0.5;
        //Получаем строку с двоичным представлением исходного текста
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            result.append(String.format("%8s", Integer.toBinaryString(string.charAt(i))).replace(' ', '0'));
        }

        String encoded = encode(result.toString(), G);
        encoded = makeNoises(encoded, P);
        String decoded = decode(encoded, H);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i<=decoded.length()-7;i = i+7){
            String str = decoded.substring(i+3,i+7);
            stringBuilder.append(str);
        }
        System.out.println("Texts");
        System.out.println(encoded);
        System.out.println(decoded);
        decoded = stringBuilder.toString();
        stringBuilder = new StringBuilder();
        for (int i = 0; i<decoded.length()-7;i++){
            String substring = decoded.substring(i,i+8);
            char ch = (char)Integer.parseInt(substring,2);
            stringBuilder.append(ch);
        }
        System.out.println("Cutted texts");
        System.out.println(result);
        System.out.println(decoded);
        System.out.println(stringBuilder);
    }

    private static int[][] createMatrix() {
        int rowCount = 4;
        int columnCount = 7;
        int[][] G = new int[rowCount][columnCount];

        G[0][0] = 0;
        G[0][1] = 1;
        G[0][2] = 1;
        G[0][3] = 1;

        G[0][4] = 0;
        G[0][5] = 0;
        G[0][6] = 0;

        G[1][0] = 1;
        G[1][1] = 0;
        G[1][2] = 1;
        G[1][3] = 0;
        G[1][4] = 1;
        G[1][5] = 0;
        G[1][6] = 0;

        G[2][0] = 1;
        G[2][1] = 1;
        G[2][2] = 0;
        G[2][3] = 0;
        G[2][4] = 0;
        G[2][5] = 1;
        G[2][6] = 0;

        G[3][0] = 1;
        G[3][1] = 1;
        G[3][2] = 1;
        G[3][3] = 0;
        G[3][4] = 0;
        G[3][5] = 0;
        G[3][6] = 1;

        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                System.out.print(G[i][j] + " ");
            }
            System.out.println();
        }

        return G;
    }

    private static String encode(String text, int[][] G) {
        StringBuilder encodedString = new StringBuilder();
        for (int i = 0; i <= text.length() - 4; i = i + 4) {
            int bit1 = (Character.getNumericValue(text.charAt(i)) * G[0][0] + Character.getNumericValue(text.charAt(i + 1)) * G[1][0] + Character.getNumericValue(text.charAt(i + 2)) * G[2][0] + Character.getNumericValue(text.charAt(i + 3)) * G[3][0]) % 2;
            int bit2 = (Character.getNumericValue(text.charAt(i)) * G[0][1] + Character.getNumericValue(text.charAt(i + 1)) * G[1][1] + Character.getNumericValue(text.charAt(i + 2)) * G[2][1] + Character.getNumericValue(text.charAt(i + 3)) * G[3][1]) % 2;
            int bit3 = (Character.getNumericValue(text.charAt(i)) * G[0][2] + Character.getNumericValue(text.charAt(i + 1)) * G[1][2] + Character.getNumericValue(text.charAt(i + 2)) * G[2][2] + Character.getNumericValue(text.charAt(i + 3)) * G[3][2]) % 2;
            encodedString.append(bit1);
            encodedString.append(bit2);
            encodedString.append(bit3);
            encodedString.append(text.charAt(i));
            encodedString.append(text.charAt(i + 1));
            encodedString.append(text.charAt(i + 2));
            encodedString.append(text.charAt(i + 3));
        }
        return encodedString.toString();
    }

    private static int[][] createInverseMatrix(int[][] matrix, int rowCount, int columnCount) {
        int[][] inverseMatrix = new int[rowCount - 1][columnCount];

        inverseMatrix[0][0] = matrix[0][3];
        inverseMatrix[1][0] = matrix[1][3];
        inverseMatrix[2][0] = matrix[2][3];

        inverseMatrix[0][1] = matrix[0][4];
        inverseMatrix[1][1] = matrix[1][4];
        inverseMatrix[2][1] = matrix[2][4];

        inverseMatrix[0][2] = matrix[0][5];
        inverseMatrix[1][2] = matrix[1][5];
        inverseMatrix[2][2] = matrix[2][5];

        inverseMatrix[0][3] = matrix[0][0];
        inverseMatrix[1][3] = matrix[1][0];
        inverseMatrix[2][3] = matrix[2][0];

        inverseMatrix[0][4] = matrix[1][0];
        inverseMatrix[1][4] = matrix[1][1];
        inverseMatrix[2][4] = matrix[1][2];

        inverseMatrix[0][5] = matrix[2][0];
        inverseMatrix[1][5] = matrix[2][1];
        inverseMatrix[2][5] = matrix[2][2];

        inverseMatrix[0][6] = matrix[3][0];
        inverseMatrix[1][6] = matrix[3][1];
        inverseMatrix[2][6] = matrix[3][2];

        System.out.println();
        for (int i = 0; i < rowCount - 1; i++) {
            for (int j = 0; j < columnCount; j++) {
                System.out.print(inverseMatrix[i][j] + " ");
            }
            System.out.println();
        }

        return inverseMatrix;
    }

    private static String decode(String encodedText, int[][] H) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < encodedText.length() - 6; i = i + 7) {
            StringBuilder substring = new StringBuilder(encodedText.substring(i, i + 7));
            System.out.println(substring);
            int sindrom1 = (H[0][0] * Character.getNumericValue(substring.charAt(0)) + H[0][1] * Character.getNumericValue(substring.charAt(1)) + H[0][2] * Character.getNumericValue(substring.charAt(2)) + H[0][3] * Character.getNumericValue(substring.charAt(3)) + H[0][4] * Character.getNumericValue(substring.charAt(4)) + H[0][5] * Character.getNumericValue(substring.charAt(5)) + H[0][6] * Character.getNumericValue(substring.charAt(6))) % 2;
            int sindrom2 = (H[1][0] * Character.getNumericValue(substring.charAt(0)) + H[1][1] * Character.getNumericValue(substring.charAt(1)) + H[1][2] * Character.getNumericValue(substring.charAt(2)) + H[1][3] * Character.getNumericValue(substring.charAt(3)) + H[1][4] * Character.getNumericValue(substring.charAt(4)) + H[1][5] * Character.getNumericValue(substring.charAt(5)) + H[1][6] * Character.getNumericValue(substring.charAt(6))) % 2;
            int sindrom3 = (H[2][0] * Character.getNumericValue(substring.charAt(0)) + H[2][1] * Character.getNumericValue(substring.charAt(1)) + H[2][2] * Character.getNumericValue(substring.charAt(2)) + H[2][3] * Character.getNumericValue(substring.charAt(3)) + H[2][4] * Character.getNumericValue(substring.charAt(4)) + H[2][5] * Character.getNumericValue(substring.charAt(5)) + H[2][6] * Character.getNumericValue(substring.charAt(6))) % 2;
            while ((sindrom3+sindrom1+sindrom2) !=0) {
                for (int j = 0; j < 7; j++) {
                    if ((H[0][j] == sindrom1) && (H[1][j] == sindrom2) && (H[2][j] == sindrom3)) {
                        if (Character.getNumericValue(substring.charAt(j)) == 1) {
                            substring.replace(j, j + 1, "0");
                        }
                        else substring.replace(j, j + 1, "1");
                    }
                }
                sindrom1 = (H[0][0] * Character.getNumericValue(substring.charAt(0)) + H[0][1] * Character.getNumericValue(substring.charAt(1)) + H[0][2] * Character.getNumericValue(substring.charAt(2)) + H[0][3] * Character.getNumericValue(substring.charAt(3)) + H[0][4] * Character.getNumericValue(substring.charAt(4)) + H[0][5] * Character.getNumericValue(substring.charAt(5)) + H[0][6] * Character.getNumericValue(substring.charAt(6))) % 2;
                sindrom2 = (H[1][0] * Character.getNumericValue(substring.charAt(0)) + H[1][1] * Character.getNumericValue(substring.charAt(1)) + H[1][2] * Character.getNumericValue(substring.charAt(2)) + H[1][3] * Character.getNumericValue(substring.charAt(3)) + H[1][4] * Character.getNumericValue(substring.charAt(4)) + H[1][5] * Character.getNumericValue(substring.charAt(5)) + H[1][6] * Character.getNumericValue(substring.charAt(6))) % 2;
                sindrom3 = (H[2][0] * Character.getNumericValue(substring.charAt(0)) + H[2][1] * Character.getNumericValue(substring.charAt(1)) + H[2][2] * Character.getNumericValue(substring.charAt(2)) + H[2][3] * Character.getNumericValue(substring.charAt(3)) + H[2][4] * Character.getNumericValue(substring.charAt(4)) + H[2][5] * Character.getNumericValue(substring.charAt(5)) + H[2][6] * Character.getNumericValue(substring.charAt(6))) % 2;
            }
            result.append(substring);
        }
        return result.toString();
    }

    private static String makeNoises(String encodedText, double P) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < encodedText.length(); i++) {
            if (Math.random() <= P) {
                if (Character.getNumericValue(encodedText.charAt(i)) == 1) stringBuilder.append("0");
                else stringBuilder.append("1");
            } else stringBuilder.append(Character.getNumericValue(encodedText.charAt(i)));
        }
        return stringBuilder.toString();
    }

}
