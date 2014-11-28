package com.princecoder.multipleloader;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;

import com.princecoder.multipleloader.Adapters.PhoneCursorAdapter;
import com.princecoder.multipleloader.Adapters.SimCursorAdapter;

/**
 * @author prinzlyngotoum
 */
public class Home extends ActionBarActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    //Phone and Sim card Listviews
    ListView mListviewSim;
    ListView mListviewPhone;

    //Loaders
    private final int mLoaderSim = 1;
    private final int mLoaderPhone = 2;

    //Cursor Adapters
    PhoneCursorAdapter mAdapterPhone;
    SimCursorAdapter mAdapterSim;

    //Selection for the phone loader
    private static final String SELECTION =
            ContactsContract.Contacts.HAS_PHONE_NUMBER + "=1";

    //Column for the Sim card loader
    private static final String NAME = "name";
    private static final String NUMBER = "number";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mListviewPhone = (ListView) findViewById(R.id.listview_phone);
        mListviewSim = (ListView) findViewById(R.id.listview_sim);

        mAdapterPhone = new PhoneCursorAdapter(getApplicationContext(), null, 0);
        mAdapterSim = new SimCursorAdapter(getApplicationContext(), null, 0);

        //Initialize loaders
        getLoaderManager().initLoader(mLoaderSim, null, this);
        getLoaderManager().initLoader(mLoaderPhone, null, this);

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        switch (id) {
            case mLoaderPhone:
                return new CursorLoader(
                        this,
                        ContactsContract.Contacts.CONTENT_URI,
                        new String[]{ContactsContract.Contacts._ID,
                                ContactsContract.Contacts.DISPLAY_NAME_PRIMARY, ContactsContract.Contacts.HAS_PHONE_NUMBER},
                        SELECTION, null,
                        ContactsContract.Contacts.DISPLAY_NAME_PRIMARY);


            case mLoaderSim:
                Uri simUri = Uri.parse("content://icc/adn");
                return new CursorLoader(
                        this,
                        simUri,
                        new String[]{NAME, NUMBER},
                        null,
                        null,
                        NAME);
            default:
                break;
        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        switch (loader.getId()) {
            case mLoaderPhone:
                mAdapterPhone = new PhoneCursorAdapter(getApplicationContext(), data, 0);
                mListviewPhone.setAdapter(mAdapterPhone);
                //mAdapterPhone.notifyDataSetChanged();
                break;
            case mLoaderSim:

                mAdapterSim = new SimCursorAdapter(getApplicationContext(), data, 0);
                mListviewSim.setAdapter(mAdapterSim);
                //mAdapterSim.notifyDataSetChanged();
                break;
            default:
                break;
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        switch (loader.getId()) {
            case mLoaderPhone:
                mAdapterPhone.swapCursor(null);
                break;
            case mLoaderSim:
                mAdapterSim.swapCursor(null);
                break;
            default:
                break;
        }
    }

}


