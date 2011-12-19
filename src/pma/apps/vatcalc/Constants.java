package pma.apps.vatcalc;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class Constants extends Activity {
    private static final String EMAIL_ADDRESS = "mailto:info@pmaaccountants.co.uk";
    private static final String PHONE_NUMBER = "tel:08443573646";

    protected static final String VAT_RATE = "20";
    protected static final String REDUCED_VAT_RATE = "5";

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        final MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    } // onCreateOptionsMenu(Menu)

    protected final void openEmail() {
        final Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setType("text/plain");
        emailIntent.setData(Uri.parse(EMAIL_ADDRESS));
        startActivity(Intent.createChooser(emailIntent, "Send email..."));
    } // openEmail()

    protected final void openDialer() {
        final Intent phoneIntent = new Intent(Intent.ACTION_DIAL);
        phoneIntent.setData(Uri.parse(PHONE_NUMBER));
        startActivity(phoneIntent);
    } // openDialer()

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case R.id.info:
                startActivity(new Intent(this, Information.class));
                return true;
            case R.id.call:
                openDialer();
                return true;
            case R.id.email:
                openEmail();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        } // switch
    } // onOptionsItemSelected(MenuItem)
} // Constants
