package com.simbir.util;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvWriter {
    public static void write(List<String[]> data, String filename) {
        File file = new File(filename);
        try {
            FileWriter outputfile = new FileWriter(file);
            CSVWriter writer = new CSVWriter(outputfile);

            writer.writeAll(data);

            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
