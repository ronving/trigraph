package com.olkhovyi;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


public class Parser {
    public static void main() {
        System.out.println("\t\t\t TRIGRAPH\n");
        String content = getText();
        List<String> trigraphs = parse(content);
        Map<String, Long> sortedTrigraphs = getNumberOfRepetitions(trigraphs);
        sortByValue(sortedTrigraphs);
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

    private static List<String> parse(String content) {
        String[] words = content.toUpperCase().split("\\s*(\\s|,|!|\\.)\\s*");

        List<String> trigraphs = Arrays.stream(words)
                .filter(s -> s.length() > 2)
                .map(s -> splitUp(s))
                .flatMap(arr -> Arrays.stream(arr))
                .collect(Collectors.toList());
        return trigraphs;
    }

    private static String[] splitUp(String s) {
        int count = s.length()-2;
        String[] trigraphs = new String[count];
        for (int i = 0; i < count; i++) {
            trigraphs[i] = s.substring(i, i+3);
        }
        return trigraphs;
    }

    private static Map<String, Long> getNumberOfRepetitions(List<String> trigraphs) {
        Map<String, Long> uniqTrigraphs = trigraphs.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        return uniqTrigraphs;
    }

    private static Map<String, Long> sortByValue(Map<String, Long> trigraphs) {
        trigraphs.entrySet().stream()
                .sorted(Map.Entry.comparingByKey()).forEach(System.out::println);
        return trigraphs;
    }
}
