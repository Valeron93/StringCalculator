package com.valeron;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringCalculatorTest {

    final StringCalculator calc = new StringCalculator();


    // Крок 1
    @Test
    void addEmptyString() {
        assertEquals(0, calc.add(""));
    }

    // Крок 1
    @Test
    void addTwoNumbers() {
        assertEquals(3, calc.add("1,2"));
        assertEquals(3, calc.add("2,1"));
        assertEquals(12, calc.add("5,7"));
    }

    // Крок 2
    @Test
    void addMoreNumbers() {
        assertEquals(6, calc.add("1,2,3"));
        assertEquals(16, calc.add("5,6,2,3"));
        assertEquals(16, calc.add("6,2,3,2,3"));
        assertEquals(55, calc.add("1,2,3,4,5,6,7,8,9,10"));
        assertEquals(648, calc.add("63,5,3,8,21,412,123,6,4,3"));
    }

    // Крок 3
    @Test
    void addWithNewline() {
        assertEquals(3, calc.add("1\n2"));
        assertEquals(15, calc.add("1\n2,3,4,5"));
    }

    // Крок 3, неправильні дані
    @Test
    void newlineIncorrectData() {
        assertThrows(IllegalArgumentException.class, () -> calc.add("1,\n"));
        assertThrows(IllegalArgumentException.class, () -> calc.add("1,2,3\n2,3,"));
        assertThrows(IllegalArgumentException.class, () -> calc.add("1,2,3\n6,5,"));
    }

    // Крок 4
    @Test
    void userDelimiters() {
        assertEquals(6, calc.add("//;\n1;2;3"));
        assertEquals(6, calc.add("//;\n1;2\n3"));
        assertEquals(311, calc.add("//!\n5!6!300"));
        assertEquals(311, calc.add("//!\n5!6\n300"));
        assertThrows(IllegalArgumentException.class, () -> calc.add("//!\n5!6\n300!"));
    }

    // Крок 5
    @Test
    void denyNegativeNumbers() {
        assertThrows(NegativeNumbersException.class, () -> calc.add("-1,-2,-3"));
        assertThrows(NegativeNumbersException.class, () -> calc.add("1,2\n-3"));
        assertThrows(NegativeNumbersException.class, () -> calc.add("//;\n-1;-2;-3"));
    }


    // Крок 6
    @Test
    void ignoreGreaterThan1000() {
        assertEquals(1000, calc.add("1,1001,999,10000"));
        assertEquals(1000, calc.add("1000"));
    }

    // Крок 7
    @Test
    void delimiterOfArbitraryLength() {
        assertEquals(6, calc.add("//[***]\n1***2***3"));
        assertEquals(10, calc.add("//[bb]\n1bb2bb3bb4"));
        assertEquals(36, calc.add("//[i]\n1i2i3i4i5i6i7i8"));
    }

    // Крок 8
    @Test
    void severalDelimiters() {
        assertEquals(22, calc.add("//[a][b]\n1b2a3b4b5b7"));
        assertEquals(15, calc.add("//[i][j][k]\n2i3j6k4"));
    }

    // Крок 9
    @Test
    void severalDelimitersOfArbitraryLength() {
        assertEquals(1060, calc.add("//[**][%%]\n200%%300%%400%%100**10**20%%30"));
        assertEquals(3, calc.add("//[firstdelimiter][seconddelimiter]\n1firstdelimiter1seconddelimiter1"));
    }

}
