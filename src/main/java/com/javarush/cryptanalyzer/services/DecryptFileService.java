package com.javarush.cryptanalyzer.services;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

import static com.javarush.cryptanalyzer.util.CryptoAnalyzerTool.decrypt;


public class DecryptFileService {
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
        try (Reader reader = new FileReader(inputFile);
             Writer writer = new FileWriter(Files.createFile(path).toFile())) {
            char[] buffer = new char[(int) Files.size(Path.of(inputFile.getAbsolutePath()))];
            reader.read(buffer);
            writer.write(decrypt(buffer, key));
            System.out.println(Arrays.toString(buffer));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}

