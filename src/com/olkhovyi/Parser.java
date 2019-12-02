package com.olkhovyi;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;



public class Parser {
    public static void main() {
        System.out.println("\t\t\t TRIGRAPH\n");
        String content = getText();
        System.out.println(content);
    }

    private static String getText() {
        String fileName = "src/file.txt";
        String content = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            content = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }
    
}
