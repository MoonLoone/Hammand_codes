package utils;

public class HammingAlgorithm {

    public static String encode(String text, int[][] G) {
        StringBuilder encodedString = new StringBuilder();
        for (int i = 0; i <= text.length() - 4; i = i + 4) {
            int[] bits = new int[7];
            bits[0] = (Character.getNumericValue(text.charAt(i)) * G[0][0] + Character.getNumericValue(text.charAt(i + 1)) * G[1][0] + Character.getNumericValue(text.charAt(i + 2)) * G[2][0] + Character.getNumericValue(text.charAt(i + 3)) * G[3][0]) % 2;
            bits[1] = (Character.getNumericValue(text.charAt(i)) * G[0][1] + Character.getNumericValue(text.charAt(i + 1)) * G[1][1] + Character.getNumericValue(text.charAt(i + 2)) * G[2][1] + Character.getNumericValue(text.charAt(i + 3)) * G[3][1]) % 2;
            bits[2] = (Character.getNumericValue(text.charAt(i)) * G[0][2] + Character.getNumericValue(text.charAt(i + 1)) * G[1][2] + Character.getNumericValue(text.charAt(i + 2)) * G[2][2] + Character.getNumericValue(text.charAt(i + 3)) * G[3][2]) % 2;
            bits[3] = (Character.getNumericValue(text.charAt(i)) * G[0][3] + Character.getNumericValue(text.charAt(i + 1)) * G[1][3] + Character.getNumericValue(text.charAt(i + 2)) * G[2][3] + Character.getNumericValue(text.charAt(i + 3)) * G[3][3]) % 2;
            bits[4] = (Character.getNumericValue(text.charAt(i)) * G[0][4] + Character.getNumericValue(text.charAt(i + 1)) * G[1][4] + Character.getNumericValue(text.charAt(i + 2)) * G[2][4] + Character.getNumericValue(text.charAt(i + 3)) * G[3][4]) % 2;
            bits[5] = (Character.getNumericValue(text.charAt(i)) * G[0][5] + Character.getNumericValue(text.charAt(i + 1)) * G[1][5] + Character.getNumericValue(text.charAt(i + 2)) * G[2][5] + Character.getNumericValue(text.charAt(i + 3)) * G[3][5]) % 2;
            bits[6] = (Character.getNumericValue(text.charAt(i)) * G[0][6] + Character.getNumericValue(text.charAt(i + 1)) * G[1][6] + Character.getNumericValue(text.charAt(i + 2)) * G[2][6] + Character.getNumericValue(text.charAt(i + 3)) * G[3][6]) % 2;
            encodedString.append(bits[0]);
            encodedString.append(bits[1]);
            encodedString.append(bits[2]);
            encodedString.append(bits[3]);
            encodedString.append(bits[4]);
            encodedString.append(bits[5]);
            encodedString.append(bits[6]);
        }
        return encodedString.toString();
    }

    public static String decode(String encodedText, int[][] H) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i <= encodedText.length() - 7; i = i + 7) {
            StringBuilder substring = new StringBuilder(encodedText.substring(i, i + 7));
            int sindrom1 = (H[0][0] * Character.getNumericValue(substring.charAt(0)) + H[0][1] * Character.getNumericValue(substring.charAt(1)) + H[0][2] * Character.getNumericValue(substring.charAt(2)) + H[0][3] * Character.getNumericValue(substring.charAt(3)) + H[0][4] * Character.getNumericValue(substring.charAt(4)) + H[0][5] * Character.getNumericValue(substring.charAt(5)) + H[0][6] * Character.getNumericValue(substring.charAt(6))) % 2;
            int sindrom2 = (H[1][0] * Character.getNumericValue(substring.charAt(0)) + H[1][1] * Character.getNumericValue(substring.charAt(1)) + H[1][2] * Character.getNumericValue(substring.charAt(2)) + H[1][3] * Character.getNumericValue(substring.charAt(3)) + H[1][4] * Character.getNumericValue(substring.charAt(4)) + H[1][5] * Character.getNumericValue(substring.charAt(5)) + H[1][6] * Character.getNumericValue(substring.charAt(6))) % 2;
            int sindrom3 = (H[2][0] * Character.getNumericValue(substring.charAt(0)) + H[2][1] * Character.getNumericValue(substring.charAt(1)) + H[2][2] * Character.getNumericValue(substring.charAt(2)) + H[2][3] * Character.getNumericValue(substring.charAt(3)) + H[2][4] * Character.getNumericValue(substring.charAt(4)) + H[2][5] * Character.getNumericValue(substring.charAt(5)) + H[2][6] * Character.getNumericValue(substring.charAt(6))) % 2;
            for (int j = 0; j < 7; j++) {
                if ((H[0][j] == sindrom1) && (H[1][j] == sindrom2) && (H[2][j] == sindrom3)) {
                    if (Character.getNumericValue(substring.charAt(j)) == 1) {
                        substring.replace(j, j + 1, "0");
                    } else substring.replace(j, j + 1, "1");
                }
            }
            result.append(substring);
        }
        return result.toString();
    }

    public static String cutServiceBits(String binaryString){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i <= binaryString.length() - 7; i = i + 7) {
            String str = binaryString.substring(i, i +4);
            stringBuilder.append(str);
        }
        return stringBuilder.toString();
    }

}
