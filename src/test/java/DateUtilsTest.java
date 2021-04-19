package br.ce.wcaquino.taskbackend.utils;

import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class DateUtilsTest {

    @Test
    public void deveRetornarTrueParaDatasFuturas() {

        LocalDate date = LocalDate.of(2025,01,01);
        boolean equalOrFutureDate = DateUtils.isEqualOrFutureDate(date);
        assertTrue(equalOrFutureDate);
    }

    @Test
    public void deveFalharParaDatasFuturas() {

        LocalDate date = LocalDate.of(2020,01,01);
        boolean equalOrFutureDate = DateUtils.isEqualOrFutureDate(date);
        assertFalse(equalOrFutureDate);
    }

    @Test
    public void deveFalharParaDataAtual() {

        LocalDate date = LocalDate.now();
        boolean equalOrFutureDate = DateUtils.isEqualOrFutureDate(date);
        assertTrue(equalOrFutureDate);
    }
}
