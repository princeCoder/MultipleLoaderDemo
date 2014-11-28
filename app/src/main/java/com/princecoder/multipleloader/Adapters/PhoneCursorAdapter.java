package com.princecoder.multipleloader.Adapters;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.princecoder.multipleloader.R;

/**
 * Created by prinzlyngotoum on 11/22/14.
 */
public class PhoneCursorAdapter extends CursorAdapter{

    private LayoutInflater mInflater;

    public PhoneCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
        // TODO Auto-generated constructor stub
        mInflater=LayoutInflater.from(context);
    }


    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        PhoneViewHolder holder = (PhoneViewHolder) view.getTag();
        holder.getName().setText(cursor.getString(holder.getNameIndex()));
        holder.getPicture().setImageResource(R.drawable.ic_launcher);
    }



    @Override
    public View newView(Context context, Cursor cursor, ViewGroup
            parent) {
        View view= mInflater.inflate
                (R.layout.contacts_list_item, null);

        PhoneViewHolder holder = new PhoneViewHolder(view,cursor);

        view.setTag(holder);
        return view;
    }

}

/**
 * View holder for optimize the creation of the listview rows
 */
class PhoneViewHolder {

    private TextView mName;
    private ImageView mPicture;
    private int mNameIndex;

    public PhoneViewHolder(View view, Cursor cursor) {


        this.mName = (TextView) view.findViewById(R.id.contact_name);
        this.mPicture = (ImageView) view.findViewById(R.id.contact_picture);
        this.mNameIndex   =   cursor.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY);

    }

    public void setPicture(int resId) {
        this.mPicture.setImageResource(resId);
    }

    public void setName(String name) {
        this.mName.setText(name);
    }


    public TextView getName() {
        return mName;
    }

    public ImageView getPicture() {
        return mPicture;
    }

    public int getNameIndex() {
        return mNameIndex;
    }
}
