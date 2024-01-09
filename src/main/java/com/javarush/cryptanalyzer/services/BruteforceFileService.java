package com.javarush.cryptanalyzer.services;


import com.javarush.cryptanalyzer.util.CryptoAnalyzerTool;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;



public class BruteforceFileService {
    private  final CryptoAnalyzerTool cryptoAnalyzerTool = new CryptoAnalyzerTool();
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
        try (BufferedWriter writer = Files.newBufferedWriter(Files.createFile(path))) {
            writer.write(decryptLine);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public String[] bruteForce(){
        char[] buffer;
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            buffer = new char[(int) Files.size(Path.of(inputFile.getAbsolutePath()))];
            reader.read(buffer);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return cryptoAnalyzerTool.bruteforce(buffer,keyStart,keyEnd);
    }

}
