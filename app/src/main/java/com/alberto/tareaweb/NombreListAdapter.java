package com.alberto.tareaweb;

/**
 * Created by Alberto on 04/03/15.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;




import com.alberto.tareaweb.data.Nombre;

import java.util.ArrayList;

public class NombreListAdapter extends BaseAdapter{
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private ArrayList<Nombre> mNombreArray;

    public NombreListAdapter(Context context, ArrayList<Nombre> namesList) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        mNombreArray = namesList;
    }

    @Override
    public int getCount() {
        return mNombreArray.size();
    }

    @Override
    public Object getItem(int i) {
        return mNombreArray.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //This method is called for each row on the list view letting you
        //get the references from the xml and assign the corresponding values
        //of the current list item.
        final Holder holder;
        //assign to new view
        View v = view;
        //check if the view is null, meaning there is no previous view used.
        if (v == null) {
            //new holder instance
            holder = new Holder();
            //inflate the view with custom list item view
            v = mLayoutInflater.inflate(R.layout.list_custom, null);
            //get xml references on holder variables
            holder.textView = (TextView)v.findViewById(R.id.txt_list);
            //set view tag
            v.setTag(holder);
        }else {
            //get view tag to avoid referencing again
            holder = (Holder) v.getTag();
        }

        //set values to each view inside the item according to the array list current view
        holder.textView.setText(mNombreArray.get(i).getNombre());

        //Finally return the custom view
        return v;
    }

    static class Holder{
        TextView textView;
    }

}
