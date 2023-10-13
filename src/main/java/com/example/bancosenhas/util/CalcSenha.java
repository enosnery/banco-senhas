package com.example.bancosenhas.util;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class CalcSenha {


    private static final String REGEX_UPPERCASE = "[A-Z]+";
    private static final String REGEX_LOWERCASE = "[a-z]+";
    private static final String REGEX_NUMBER = "[0-9]+";
    private static final String REGEX_SYMBOLS = "[^A-Za-z0-9]+";
    private static final String REGEX_LETTERS_ONLY = "[A-Za-z]+";

    public static int calcForcaSenha(String senha) {
        int forca = 0;
        if (!senha.isEmpty()) {
            String reversedString = new StringBuilder(senha).reverse().toString();
            int n = senha.length();
            forca = (n * 4);

            if(patternCount(REGEX_UPPERCASE, senha) > 0){
                forca += ((n - patternCount(REGEX_UPPERCASE, senha)) * 2 );
            }
            if(patternCount(REGEX_LOWERCASE, senha) > 0){
                forca += ((n - patternCount(REGEX_LOWERCASE, senha)) * 2 );
            }
            if(patternCount(REGEX_NUMBER, senha) > 0){
                forca += (patternCount(REGEX_NUMBER, senha) * 4 );
            }
            forca += (patternCount(REGEX_SYMBOLS, senha) * 6 );
            forca += ((patternMiddleCount(REGEX_SYMBOLS, senha) + patternMiddleCount(REGEX_NUMBER, senha))* 2 );
            int requirementCount = requirementCount(senha);
            forca += requirementCount > 3 ? requirementCount : 0;


            if (senha.matches(REGEX_LETTERS_ONLY)) {
                forca -= senha.length();
            }

            if (senha.matches(REGEX_NUMBER)) {
                forca -= senha.length();
            }
            forca -= repeatCharacters(REGEX_UPPERCASE, senha);
            forca -= (patternConsecutiveCount(REGEX_UPPERCASE, senha) * 2 );
            forca -= (patternConsecutiveCount(REGEX_LOWERCASE, senha) * 2 );
            forca -= (patternConsecutiveCount(REGEX_NUMBER, senha) * 2 );

            forca -= (sequentialCount(senha) * 3 );
            forca -= (sequentialCount(reversedString) * 3 );
        }
        return forca;
    }

    private static int repeatCharacters(String regexUppercase, String senha) {
       char[] chars = senha.toCharArray();
        HashMap<String, Integer> map = new HashMap<>();
       for (int i = 0; i < chars.length; i++) {
           for(int j = 0; j < chars.length; j++) {
               if(chars[i] == chars[j] && i != j) {
                   map.merge(String.valueOf(chars[i]), 1, Integer::sum);
               }
           }
       }
       int count = 1;
       if(map.isEmpty()) {
           return 0;
       }
       for (Integer value : map.values()) {
           if(value > 2) {
               count += value;
           }
       }
       return count;
    }


    private static int patternCount(String regex, String senha) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(senha);
        int count = 0;
        while (m.find()) {
            count += m.group().length();
        }
        return count;
    }

    private static int patternConsecutiveCount(String regex, String senha) {
        char[] chars = senha.toCharArray();
        int count = 0;
        if(chars.length >= 2) {
            for (int i = 0; i < chars.length - 1; i++) {
                boolean p1 = Pattern.matches(regex, String.valueOf(chars[i]));
                boolean p2 = Pattern.matches(regex, String.valueOf(chars[i+1]));
                if (p1) {
                    if(p2) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    private static int patternMiddleCount(String regex, String senha) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(senha.substring(1, senha.length() - 1));
        int count = 0;
        while (m.find()) {
            count += m.group().length();
        }
        return count;
    }

    private static int requirementCount(String senha) {
        int count = 0;
        if(senha.length() > 7){
            count += 1;
        }

        Pattern p1 = Pattern.compile(REGEX_UPPERCASE);
        Matcher m1 = p1.matcher(senha);
        if (m1.find()) {
            count += 1;
        }

        Pattern p2 = Pattern.compile(REGEX_LOWERCASE);
        Matcher m2 = p2.matcher(senha);
        if (m2.find()) {
            count += 1;
        }
        Pattern p3 = Pattern.compile(REGEX_NUMBER);
        Matcher m3 = p3.matcher(senha);
        if (m3.find()) {
            count += 1;
        }
        Pattern p4 = Pattern.compile(REGEX_SYMBOLS);
        Matcher m4 = p4.matcher(senha);
        if (m4.find()) {
            count += 1;
        }
        if(count > 3) {
            count = count * 2;
        }

        return count;
    }

    private static int sequentialCount(String senha) {
        int count = 0;
        char[] chars = senha.toCharArray();
        HashMap<String, Integer> map = new HashMap<>();
        if(chars.length >= 3) {
            for (int i = 0; i < chars.length - 2; i++) {
                int a1 = chars[i];
                int a2 = chars[i + 1];
                int a3 = chars[i + 2];
                if ((a1 + 1 == a2)
                    && ( a2 + 1 == a3) ){
                    map.putIfAbsent(String.valueOf(chars[i] + chars[i + 1] + chars[i + 2]), 1);
                }
            }
        }
        for (Integer value : map.values()) {
                count += value;
        }
        return count;
    }
}
