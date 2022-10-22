package com.evaluation.movie.battle.util;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;

@Component
public class CalculateUtils {

    /**Convert values with problem in valid big decimal values. exemples: 777,7777,00 convert to 7777777.00*/
    public BigDecimal removeNumberFormattingIssues(String numberValue) throws ParseException {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator(',');
        symbols.setDecimalSeparator('.');
        String pattern = "#,##0.0#";
        DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
        decimalFormat.setParseBigDecimal(true);

        BigDecimal parsedStringValue = (BigDecimal) decimalFormat.parse(numberValue);
        return parsedStringValue;
    }

}
