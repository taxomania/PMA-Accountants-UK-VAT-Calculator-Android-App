package pma.apps.vatcalc;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class Information extends Constants {
    private TextView regV, deregV, accV, cashV, vatV, redV;
    private static final String REG_PREF = "73,000";
    private static final String DEREG_PREF = "71,000";
    private static final String ACC_PREF = "1,350,000";
    private static final String CASH_PREF = "1,350,000";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info);
        getData();
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        final MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.info, menu);
        return true;
    }

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
        }
    }

    private void getData() {
        regV = (TextView) findViewById(R.id.reg);
        deregV = (TextView) findViewById(R.id.dereg);
        accV = (TextView) findViewById(R.id.acc);
        cashV = (TextView) findViewById(R.id.cash);
        vatV = (TextView) findViewById(R.id.standard);
        redV = (TextView) findViewById(R.id.red);

        regV.setText("£" + REG_PREF);
        deregV.setText("£" + DEREG_PREF);
        accV.setText("£" + ACC_PREF);
        cashV.setText("£" + CASH_PREF);
        vatV.setText(VAT_RATE + "%");
        redV.setText(REDUCED_VAT_RATE + "%");
    }

}
