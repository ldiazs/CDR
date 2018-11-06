package com.totorody.cdr;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class Analyzer {

    public void analyzeToConsole(Iterable<Cdr> cdrs) {
        for (Cdr cdr : cdrs) {
            System.out.println(cdr);
        }
    }

    public void analyzeToFile(Iterable<Cdr> cdrs, File outputFile) {
        checkFileDirectory(outputFile);
        Writer writer;
        try {
            writer = Files.newBufferedWriter(outputFile.toPath(), Charset.defaultCharset(), StandardOpenOption.APPEND, StandardOpenOption.CREATE_NEW);
            for (Cdr cdr : cdrs) {
                writer.write(cdr.toString());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("[ERROR] Failed to write output file in Analyzer.");
            throw new RuntimeException(e);
        }
    }

    private void checkFileDirectory(File outputFile) {
        Path parentDir = outputFile.toPath().getParent();
        if (!Files.exists(parentDir)) {
            try {
                Files.createDirectories(parentDir);
            } catch (IOException e) {
                System.out.println("[ERROR] Failed to create parent directories.");
                throw new RuntimeException(e);
            }
        }
    }
}