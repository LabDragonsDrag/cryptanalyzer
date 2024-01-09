package com.javarush.cryptanalyzer.services;

import com.javarush.cryptanalyzer.util.CryptoAnalyzerTool;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;




public class DecryptFileService {
    private  final CryptoAnalyzerTool cryptoAnalyzerTool = new CryptoAnalyzerTool();
    private final File outputFile;
    private final File inputFile;
    private final int key;

    public DecryptFileService(File inputFile, File outputFile, int key) {
        this.outputFile = outputFile;
        this.inputFile = inputFile;
        this.key = key;
    }

    public void decryptFile()  {
        String newPath = outputFile.getAbsolutePath() + File.separator + "decrypt" + inputFile.getName();
        Path path = Path.of(newPath);
        try {
            if(Files.exists(path)) Files.delete(path);
        }catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = Files.newBufferedWriter(Files.createFile(path))) {
            char[] buffer = new char[(int) Files.size(Path.of(inputFile.getAbsolutePath()))];
            reader.read(buffer);
            buffer = cryptoAnalyzerTool.decrypt(buffer, key);
            writer.write(buffer);

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}

