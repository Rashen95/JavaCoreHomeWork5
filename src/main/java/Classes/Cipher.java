package Classes;

import java.util.HashMap;

public class Cipher {
    public static HashMap<String, Byte> cipherKey() {
        HashMap<String, Byte> myCipher = new HashMap<>();
        int key = 0;
        byte value = 1;
        while (myCipher.size() != 64) {
            String b = Integer.toString(key);
            if (!(b.contains("4") || b.contains("5") || b.contains("6") || b.contains("7") || b.contains("8") || b.contains("9"))) {
                if (b.length() == 1) {
                    b = "00" + b;
                    myCipher.put(b, value);
                    value++;
                } else if (b.length() == 2) {
                    b = "0" + b;
                    myCipher.put(b, value);
                    value++;
                } else {
                    myCipher.put(b, value);
                    value++;
                }
            }
            key++;
        }
        return myCipher;
    }

    public static byte[] encrypt(byte[][] field) {
        byte[] encryptedArray = new byte[3];
        HashMap<String, Byte> myCipherKey = cipherKey();
        StringBuilder row;
        for (int i = 0; i < field.length; i++) {
            row = new StringBuilder();
            for (int j = 0; j < field[i].length; j++) {
                row.append(field[i][j]);
            }
            encryptedArray[i] = myCipherKey.get(row.toString());
        }
        return encryptedArray;
    }

    public static byte[][] decrypt(byte[] keys) {
        byte[][] decryptedArray = new byte[3][3];
        HashMap<String, Byte> myCipherKey = cipherKey();
        for (int i = 0; i < keys.length; i++) {
            for (HashMap.Entry<String, Byte> item : myCipherKey.entrySet()) {
                if (item.getValue() == keys[i]) {
                    decryptedArray[i][0] = Byte.parseByte(String.valueOf(item.getKey().charAt(0)));
                    decryptedArray[i][1] = Byte.parseByte(String.valueOf(item.getKey().charAt(1)));
                    decryptedArray[i][2] = Byte.parseByte(String.valueOf(item.getKey().charAt(2)));
                }
            }
        }
        return decryptedArray;
    }
}