package com.javarush.cryptanalyzer.services;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.javarush.cryptanalyzer.util.CryptoAnalyzerTool.bruteforce;


public class BruteforceFileService {
    private final File outputFile;
    private final File inputFile;

    private  final  int keyStart;
    private  final int keyEnd;

    public BruteforceFileService(File inputFile, File outputFile, int keyStart, int keyEnd) {
        this.outputFile = outputFile;
        this.inputFile = inputFile;
        this.keyStart = keyStart;
        this.keyEnd = keyEnd;
    }

    public void decryptFile(String decryptLine)  {
        String newPath = outputFile.getAbsolutePath() + File.separator + "decrypt" + inputFile.getName();
        Path path = Path.of(newPath);
        try {
            if(Files.exists(path)) Files.delete(path);
        }catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        try (Writer writer = new FileWriter(Files.createFile(path).toFile())) {
            writer.write(decryptLine);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public String[] bruteForce(){
        char[] buffer;
        try (Reader reader = new FileReader(inputFile)) {
            buffer = new char[(int) Files.size(Path.of(inputFile.getAbsolutePath()))];
            reader.read(buffer);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return bruteforce(buffer,keyStart,keyEnd);
    }

}
