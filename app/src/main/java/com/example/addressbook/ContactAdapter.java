package com.example.addressbook;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ContactAdapter extends BaseAdapter {

    Activity mActivity;
    AddressBook ab;

    public ContactAdapter(Activity mActivity, AddressBook ab){
        this.mActivity = mActivity;
        this.ab = ab;
    }

    @Override
    public int getCount() {
        return ab.getContactList().size();
    }

    @Override
    public Object getItem(int position) {
        return ab.getContactList().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View oneContactLine;

        LayoutInflater inflater = (LayoutInflater)mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        oneContactLine = inflater.inflate(R.layout.one_contact_view,parent,false);

        TextView tv_type = oneContactLine.findViewById(R.id.tv_typeOfContact);
        TextView tv_name = oneContactLine.findViewById(R.id.tv_contactName);
        TextView tv_phone = oneContactLine.findViewById(R.id.tv_contactPhone);
        ImageView iv_photo = oneContactLine.findViewById(R.id.iv_photo);

        BaseContact b = (BaseContact) this.getItem(position);

        tv_type.setText(b.getClass().getSimpleName());
        tv_name.setText(b.getName());
        tv_phone.setText(b.getPhone());
        iv_photo.setImageResource(R.drawable.no_photo);

        return oneContactLine;

    }
}
