package pma.apps.vatcalc;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Constants {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        final TextView webLink = (TextView) findViewById(R.id.website);
        Linkify.addLinks(webLink,Linkify.WEB_URLS);
        final TextView telLink = (TextView) findViewById(R.id.tel);
        Linkify.addLinks(telLink,Linkify.PHONE_NUMBERS);
        final TextView faxLink = (TextView) findViewById(R.id.fax);
        Linkify.addLinks(faxLink,Linkify.PHONE_NUMBERS);
        final TextView mailLink = (TextView) findViewById(R.id.email);
        Linkify.addLinks(mailLink,Linkify.EMAIL_ADDRESSES);
    }

    public void VAT(View view) {startActivity(new Intent(this, VATCalcActivity.class));}
    public void email(View view){openEmail();}
    public void call(View view){openDialer();}
    public void info(View view){startActivity(new Intent(this, Information.class));}
}
