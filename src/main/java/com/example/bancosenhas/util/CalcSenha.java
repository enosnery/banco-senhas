package com.example.bancosenhas.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalcSenha {


    private static final String REGEX_UPPERCASE = "[A-Z]+";
    private static final String REGEX_LOWERCASE = "[a-z]+";
    private static final String REGEX_NUMBER = "[0-9]+";
    private static final String REGEX_SYMBOLS = "[^A-Za-z0-9]+";
    private static final String REGEX_LETTERS_ONLY = "[A-Za-z]+";

    public static int calcForcaSenha(String senha) {
        int forca = 0;
        if (!senha.isEmpty()) {
            int n = senha.length();
            forca = (n * 4);

            forca += ((n - patternCount(REGEX_UPPERCASE, senha)) * 2 );
            forca += ((n - patternCount(REGEX_LOWERCASE, senha)) * 2 );
            forca += (patternCount(REGEX_NUMBER, senha) * 4 );
            forca += (patternCount(REGEX_SYMBOLS, senha) * 6 );
            forca += ((patternMiddleCount(REGEX_SYMBOLS, senha) + patternMiddleCount(REGEX_NUMBER, senha))* 2 );
            forca += requirementCount(senha);


            if (senha.matches(REGEX_LETTERS_ONLY)) {
                forca -= senha.length();
            }

            if (senha.matches(REGEX_NUMBER)) {
                forca -= senha.length();
            }
            forca -= (patternConsecutiveCount(REGEX_UPPERCASE, senha) * 2 );
            forca -= (patternConsecutiveCount(REGEX_LOWERCASE, senha) * 2 );
            forca -= (patternConsecutiveCount(REGEX_NUMBER, senha) * 2 );

            //TODO implement consecutive chars requirement

        }
        return forca;
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
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(senha);
        int count = 0;
        while (m.find()) {
            if(m.group().length() > 1) {
                count += m.group().length();
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
}
