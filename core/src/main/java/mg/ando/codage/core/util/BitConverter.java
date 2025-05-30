package mg.ando.codage.core.util;

public class BitConverter {

    // Convertit une String de bits (ex: "10101000") en byte[]
    public static byte[] toByteArray(String bitString) {
        if (bitString == null || !bitString.matches("[01]+")) {
            throw new IllegalArgumentException("Chaîne de bits invalide");
        }

        int bitLength = bitString.length();
        int numBytes = (bitLength + 7) / 8;
        byte[] bytes = new byte[numBytes];

        for (int i = 0; i < bitLength; i++) {
            if (bitString.charAt(i) == '1') {
                int byteIndex = i / 8;
                int bitPosition = 7 - (i % 8);
                bytes[byteIndex] |= (1 << bitPosition);
            }
        }

        return bytes;
    }

    // Convertit un byte[] en String de bits (ex: "10101000")
    public static String fromByteArray(byte[] bytes) {
        StringBuilder bitString = new StringBuilder();

        for (byte b : bytes) {
            for (int i = 7; i >= 0; i--) {
                int bit = (b >> i) & 1;
                bitString.append(bit == 1 ? '1' : '0');
            }
        }

        return bitString.toString();
    }

    // (Optionnel) Supprime les bits de padding ajoutés lors de la conversion
    public static String trimPadding(String bitString, int originalLength) {
        return bitString.substring(0, originalLength);
    }
}