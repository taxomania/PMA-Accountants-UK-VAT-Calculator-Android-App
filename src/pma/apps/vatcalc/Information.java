package pma.apps.vatcalc;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class Information extends Constants {
    private static final String REG_PREF = "73,000";
    private static final String DEREG_PREF = "71,000";
    private static final String ACC_PREF = "1,350,000";
    private static final String CASH_PREF = "1,350,000";

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info);
        ((TextView) findViewById(R.id.reg)).setText("£" + REG_PREF);
        ((TextView) findViewById(R.id.dereg)).setText("£" + DEREG_PREF);
        ((TextView) findViewById(R.id.acc)).setText("£" + ACC_PREF);
        ((TextView) findViewById(R.id.cash)).setText("£" + CASH_PREF);
        ((TextView) findViewById(R.id.standard)).setText(VAT_RATE + "%");
        ((TextView) findViewById(R.id.red)).setText(REDUCED_VAT_RATE + "%");
    } // onCreate(Bundle)

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        final MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.info, menu);
        return true;
    } // onCreateOptionsMenu(Menu)

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                finish();
                return true;
            case R.id.call2:
                openDialer();
                return true;
            case R.id.email2:
                openEmail();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        } // switch
    } // onOptionsItemSelected(MenuItem)
} // Information
