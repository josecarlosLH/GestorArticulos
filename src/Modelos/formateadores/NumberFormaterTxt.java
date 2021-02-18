package Modelos.formateadores;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.JFormattedTextField;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.InternationalFormatter;
import javax.swing.text.NumberFormatter;

public class NumberFormaterTxt {

    public NumberFormaterTxt() {}
    
    public void formatearDecimal(JFormattedTextField fmt) {
            DecimalFormatSymbols coma = new DecimalFormatSymbols(Locale.UK);
            DecimalFormat format = new DecimalFormat("####.##", coma);
            format.setMinimumFractionDigits(2);
            InternationalFormatter formatter = new InternationalFormatter(format);
            formatter.setAllowsInvalid(false);
            formatter.setMinimum(0.0);
            formatter.setMaximum(Double.MAX_VALUE);
            DefaultFormatterFactory factory = new DefaultFormatterFactory(formatter);
            fmt.setFormatterFactory(factory);  
    }
    
    public void formatearEntero(JFormattedTextField fmt) {
            NumberFormat format = NumberFormat.getInstance();
            format.setGroupingUsed(false);//Remove comma from number greater than 4 digit
            NumberFormatter formatter = new NumberFormatter(format);
            formatter.setValueClass(Integer.class);
            formatter.setMinimum(0);
            formatter.setMaximum(Integer.MAX_VALUE);
            formatter.setAllowsInvalid(false);
            formatter.setCommitsOnValidEdit(true);// committ value on each keystroke instead of focus lost

            DefaultFormatterFactory factory = new DefaultFormatterFactory(formatter);
            fmt.setFormatterFactory(factory);  
    }
}