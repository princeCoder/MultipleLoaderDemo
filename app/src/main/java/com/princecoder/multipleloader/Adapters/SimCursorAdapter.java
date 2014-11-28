package com.princecoder.multipleloader.Adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.princecoder.multipleloader.R;

/**
 * Created by prinzlyngotoum on 11/27/14.
 */
public class SimCursorAdapter extends CursorAdapter {

    private LayoutInflater mInflater;

    public SimCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
        // TODO Auto-generated constructor stub
        mInflater = LayoutInflater.from(context);
    }


    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        SimViewHolder holder = (SimViewHolder) view.getTag();
        holder.getName().setText(cursor.getString(holder.getNameIndex()));
        holder.getPicture().setImageResource(R.drawable.ic_launcher);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup
            parent) {
        View view = mInflater.inflate
                (R.layout.contacts_list_item, null);

        SimViewHolder holder = new SimViewHolder(view, cursor);

        view.setTag(holder);
        return view;
    }

}


/**
 * View holder to optimize the listview elements
 */
class SimViewHolder {

    private TextView mName;
    private ImageView mPicture;
    private int mNameIndex;

    public SimViewHolder(View view, Cursor cursor) {
        this.mName = (TextView) view.findViewById(R.id.contact_name);
        this.mPicture = (ImageView) view.findViewById(R.id.contact_picture);
        this.mNameIndex = cursor.getColumnIndex("name");

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
