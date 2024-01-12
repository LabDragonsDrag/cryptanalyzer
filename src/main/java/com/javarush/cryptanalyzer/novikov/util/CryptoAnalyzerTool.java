package com.javarush.cryptanalyzer.novikov.util;

public class CryptoAnalyzerTool {



    public  char[] encrypt(char[] characters, int key) {
        characters = String.valueOf(characters).toCharArray();
        int alphabet = alphabetSize(characters);
        for (int i = 0; i < characters.length; ++i) {
            if (Character.isLetter(characters[i])) {
                int offset;
                if(alphabet == 32)  offset = Character.isUpperCase(characters[i]) ? 'А' : 'а';
                else  offset = Character.isUpperCase(characters[i]) ? 'A' : 'a';
                characters[i] = (char) ((((characters[i] + key - offset) % alphabet + alphabet) % alphabet) + offset);
            }
        }
        return characters;
    }


    public  char[] decrypt(char[] characters, int key) {
        return encrypt(characters, alphabetSize(characters) - key);
    }

    private  int alphabetSize(char[] characters) {
        for (char oneChar : characters) {
            if (Character.UnicodeBlock.of(oneChar).equals(Character.UnicodeBlock.CYRILLIC))
                return 32;
        }
        return 26;
    }

    public  String[] bruteforce(char[] character, int keyStart, int keyEnd) {
        String[] decryptedLines = new String[keyEnd - keyStart + 1];
        int i = 0;
        for (int keyVariant = keyStart; keyVariant <= keyEnd; ++keyVariant) {
            decryptedLines[i++] = String.valueOf(decrypt(character, keyVariant));
        }
        return decryptedLines;
    }
}
