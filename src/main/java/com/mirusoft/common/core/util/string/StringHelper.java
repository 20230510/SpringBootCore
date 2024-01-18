package com.mirusoft.common.core.util.string;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringHelper {

    //문자개수 세기
    public static Map<Character, Long> countDuplicateCharacters(String str) {

        if (str == null || str.isBlank()) {
            // or throw IllegalArgumentException
            return Collections.emptyMap();
        }

        Map<Character, Long> result = str.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()));

        return result;
    }

    //숫자만 포함하는 문자열인지 검사
    public static boolean containsOnlyDigits(String str) {

        if (str == null || str.isBlank()) {
            // or throw IllegalArgumentException
            return false;
        }

        return !str.chars()
                .anyMatch(n -> !Character.isDigit(n));
    }


    //구분자로 여러 문자열 합치기
    public static String joinByDelimiter(char delimiter, String... args) {

        if (args == null || args.length == 0) {
            // or throw IllegalArgumentException
            return "";
        }

        return Arrays.stream(args, 0, args.length)
                .collect(Collectors.joining(String.valueOf(delimiter)));
    }

    //문자열 공백제거
    public static String removeWhitespaces(String str) {

        if (str == null || str.isEmpty()) {
            // or throw IllegalArgumentException
            return "";
        }

        return str.replaceAll("\\s", "");
    }

    //중복문자제거
    public static String removeDuplicates(String str) {

        if (str == null || str.isEmpty()) {
            // or throw IllegalArgumentException
            return "";
        }

        char[] chArray = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        Set<Character> chHashSet = new HashSet<>();
        for (char c : chArray) {
            if (chHashSet.add(c)) {
                sb.append(c);
            }

        }
        return sb.toString();
    }

    //특수문자 제거
    public static String removeCharacterV2(String str, char ch) {

        if (str == null || str.isEmpty()) {
            // or throw IllegalArgumentException
            return "";
        }

        return str.replaceAll(Pattern.quote(String.valueOf(ch)), "");
    }


}
