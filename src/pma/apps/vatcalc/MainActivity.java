package pma.apps.vatcalc;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Constants {
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        final TextView webLink = (TextView) findViewById(R.id.website);
        Linkify.addLinks(webLink, Linkify.WEB_URLS);
        final TextView telLink = (TextView) findViewById(R.id.tel);
        Linkify.addLinks(telLink, Linkify.PHONE_NUMBERS);
        final TextView faxLink = (TextView) findViewById(R.id.fax);
        Linkify.addLinks(faxLink, Linkify.PHONE_NUMBERS);
        final TextView mailLink = (TextView) findViewById(R.id.email);
        Linkify.addLinks(mailLink, Linkify.EMAIL_ADDRESSES);
    } // onCreate(Bundle)

    public void VAT(final View view) {
        startActivity(new Intent(this, VATCalcActivity.class));
    } // VAT(View)

    public void email(final View view) {
        openEmail();
    } // email(View)

    public void call(final View view) {
        openDialer();
    } // call(View)

    public void info(final View view) {
        startActivity(new Intent(this, Information.class));
    } // info(View)
} // MainActivity
