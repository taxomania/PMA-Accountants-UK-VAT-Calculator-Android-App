package pma.apps.vatcalc;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Functions {
    public static List<String> calculateVAT(final double start, final boolean vat_applied,
            final double rate) {
        final double gross;
        final double net;
        final double vat;
        if (vat_applied) {
            gross = start;
            final double percent = 100.0 + rate;
            vat = gross / percent * rate;
            net = gross - vat;
        } else {
            net = start;
            vat = net / 100.0 * rate;
            gross = vat + net;
        } // else

        final NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.UK);
        final List<String> vals = new ArrayList<String>();
        vals.add(nf.format(net));
        vals.add(nf.format(vat));
        vals.add(nf.format(gross));

        return vals;
    } // calculateVAT(double, boolean, double)
} // Functions
