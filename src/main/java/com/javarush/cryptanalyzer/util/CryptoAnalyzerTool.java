package com.javarush.cryptanalyzer.util;

public class CryptoAnalyzerTool {


    public static char[] encrypt(char[] characters, int key) {
        for (int i = 0; i < characters.length; ++i) {
            if (Character.isLetter(characters[i])) {
                int offset = Character.isUpperCase(characters[i]) ? 'A' : 'a';
                characters[i] = (char) ((((characters[i] + key - offset) % 26 + 26) % 26) + offset);
            } else {
                characters[i] = (char) (((characters[i] - 32 + key) % (224) + 32));
            }
        }
        return characters;
    }

    public static char[] decrypt(char[] characters, int key) {
        return encrypt(characters, 26 - key);
    }

    public static String[] bruteforce(char[] character, int keyStart, int keyEnd) {
           String[] decryptedLines = new String[keyEnd - keyStart + 1];
           int i = 0;
        for (int keyVariant = keyStart; keyVariant <= keyEnd; ++keyVariant) {
            decryptedLines[i++] = String.valueOf(decrypt(character, keyVariant));
        }
        return decryptedLines;
    }
}
