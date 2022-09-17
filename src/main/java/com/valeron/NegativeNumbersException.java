package com.valeron;

import java.util.List;

public class NegativeNumbersException extends IllegalArgumentException {


    public NegativeNumbersException(List<Integer> numbers) {
        super(numbers.toString());
    }
}
