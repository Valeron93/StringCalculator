package com.valeron;

import java.util.Arrays;
import java.util.regex.Pattern;

public class StringCalculator {

    private static final Pattern pattern =
            Pattern.compile("(?://((?: |[^\\s\\d\\[\\]-])|(?:\\[(?:[^\\s\\d-\\[\\]]| )+])+)\\n)?((?:-?\\d(?:.*\\d)?\\n?)+)");


    public int add(String numbers) {

        if (numbers.isEmpty()) {
            return 0;
        }

        final var match = pattern.matcher(numbers);

        if (!match.matches()) {
            throw new IllegalArgumentException();
        }

        final var delimiterGroup = match.group(1);
        final var numsGroup = match.group(2);

        final var delimiter = delimiterGroup == null
                ? new DelimiterList().add("\n").add(",")
                : DelimiterList.parse(delimiterGroup).add(",");

        final var ints = Arrays.stream(numsGroup.split(delimiter.toString()))
                .map(Integer::parseInt).toList();

        if (ints.stream().anyMatch(x -> x < 0)) {
            throw new NegativeNumbersException(ints.stream().filter(x -> x < 0).toList());
        }

        return ints.stream()
                .filter(x -> x <= 1000)
                .reduce(0, Integer::sum);
    }
}

