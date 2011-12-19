package pma.apps.vatcalc;

import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class VATCalcActivity extends Constants {
    private EditText et;
    private boolean vat;
    private TextView net,vatv, gross, vatAt;
    private static double rate;
    private static String sRate;
    private static boolean decimal = false, decComp = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        net = (TextView) findViewById(R.id.net);
        vatv = (TextView) findViewById(R.id.vat);
        gross = (TextView) findViewById(R.id.gross);
        TextView.OnEditorActionListener editListener = new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId,
                    KeyEvent event) {
                if (actionId == EditorInfo.IME_NULL) {
                    if (TextUtils.isEmpty(v.getText().toString()))
                        v.setText("0.00");
                    else if (!decimal)
                        v.setText(v.getText().toString() + ".00");
                    else if (!decComp)
                        v.setText(v.getText().toString() + "0");
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    if (TextUtils.isEmpty(v.getText().toString())) reset(v);
                    else submit(v);
                }
                return true;
            }
        };
        et = (EditText) findViewById(R.id.edittext);
        et.setOnEditorActionListener(editListener);
        TextWatcher watcher = new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                    int after) {}

            @Override
            public void afterTextChanged(Editable s) {
                String str = s.toString();
                if (str.contains("."))	decimal = true;
                else decimal = false;

                if (!decimal){
                    if (str.length() == 8){
                        if (str.charAt(str.length()-1) == '.') return;
                        final String st = str.substring(0, str.length()-1);
                        s.clear();
                        s.append(st);
                    }
                }
                else {
                    String a = str.substring(str.lastIndexOf(".")+1);
                        if (a.length() != 2)
                            decComp = false;
                        if (a.length()==3){
                            a = a.substring(0, 2);
                            str = str.substring(0, str.length()-1);
                            str.concat(a);
                            s.clear();
                            s.append(str);
                            decComp = true;
                        }
                        if (str.length() == 11){
                            String st = str.substring(0, str.length()-1);
                            s.clear();
                            s.append(st);
                        }
                }
            }
        };
        et.addTextChangedListener(watcher);

        rate = Double.parseDouble(VAT_RATE);
        sRate = Double.toString(rate);
        if (sRate.endsWith(".0"))
            sRate = sRate.substring(0, sRate.length()-2);
        vatAt = (TextView) findViewById(R.id.vatAt);
        vatAt.setText("VAT at " + sRate + "%");
        final RadioButton standard = (RadioButton) findViewById(R.id.yesRadioVAT);
        final RadioButton reduced = (RadioButton) findViewById(R.id.noRadioVAT);
        standard.setText(VAT_RATE + "%");
        reduced.setText(REDUCED_VAT_RATE + "%");
    }

    public void toggleRadio(View view) {
        RadioButton rb;
        if (view.getId() == R.id.noRadio) {
            rb = (RadioButton) findViewById(R.id.yesRadio);
            vat = false;
        } else {
            rb = (RadioButton) findViewById(R.id.noRadio);
            vat = true;
        }
        rb.setChecked(false);
        ((RadioButton) view).setChecked(true);
        submit(view);
    }

    public void toggleVATRadio(View view) {
        RadioButton rb;
        if (view.getId() == R.id.noRadioVAT) {
            rb = (RadioButton) findViewById(R.id.yesRadioVAT);
            rate = Double.parseDouble(REDUCED_VAT_RATE);
        } else {
            rb = (RadioButton) findViewById(R.id.noRadioVAT);
            rate = Double.parseDouble(VAT_RATE);
        }
        rb.setChecked(false);
        ((RadioButton) view).setChecked(true);
        sRate = Double.toString(rate);
        if (sRate.endsWith(".0"))
            sRate = sRate.substring(0, sRate.length()-2);
        vatAt.setText("VAT at " + sRate + "%");
        submit(view);
    }

    public void reset(final View view) {
        et.setText("");
        net.setText("");
        vatv.setText("");
        gross.setText("");
        toggleRadio(findViewById(R.id.noRadio));
    }

    public void submit(final View view) {
        final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(findViewById(R.id.edittext)
                .getWindowToken(), 0);
        if (TextUtils.isEmpty(et.getText().toString())) return;
        final List<String> vals = Functions.calculateVAT(
                Double.parseDouble(et.getText().toString()), vat, rate);
        net.setText(vals.get(0));
        vatv.setText(vals.get(1));
        gross.setText(vals.get(2));
    }
}