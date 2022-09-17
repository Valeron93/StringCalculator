package com.valeron;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class DelimiterList {

    private final List<String> list;

    public DelimiterList() {
        list = new ArrayList<>();
    }

    public DelimiterList add(String delimiter) {
        list.add(delimiter);
        return this;
    }

    public static DelimiterList parse(String delimiters) {
        var b = delimiters.split("([\\[\\]])");
        var v = new DelimiterList().add("\n");
        Arrays.stream(b).filter(s -> !s.isEmpty()).forEach(v::add);
        return v;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();

        final int size = list.size();

        for (int i = 0; i < size; i++) {

            if (i == 0) {
                sb.append('(');
            }

            sb.append("(?<=\\d)");
            sb.append(Pattern.quote(list.get(i)));
            sb.append("(?=-?\\d)");

            sb.append(i == size - 1 ? ')' : '|');
        }

        return sb.toString();
    }

}
