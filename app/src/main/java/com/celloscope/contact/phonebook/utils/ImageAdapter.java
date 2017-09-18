package com.celloscope.contact.phonebook.utils;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.celloscope.contact.phonebook.bean.Contact;
import com.celloscope.contact.phonebook.R;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by aney on 9/7/17.
 */

public class ImageAdapter extends ArrayAdapter<Contact> {
    private final int THUMBSIZE = 70;

    ArrayList<Contact> images = new ArrayList();




    public static class ViewHolder {
        ImageView imgIcon;
        TextView name;
        TextView phn;
    }

    public ImageAdapter(Context context,  int layoutResourceId,ArrayList<Contact> images) {
        super(context, layoutResourceId , images);
    }




    @Override public View getView(int position, View convertView,
                                  ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.list_item, parent, false);
            viewHolder.name =
                    (TextView) convertView.findViewById(R.id.title);
            viewHolder.phn =
                    (TextView) convertView.findViewById(R.id.desc);
            viewHolder.imgIcon =
                    (ImageView) convertView.findViewById(R.id.icon);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Contact image = getItem(position);

        viewHolder.name.setText(image.name);
        viewHolder.phn.setText(image.mobile_number);

        if(image.getPath() != null){
        viewHolder.imgIcon.setImageBitmap(ThumbnailUtils
                .extractThumbnail(BitmapFactory.decodeFile(image.getPath()),
                        THUMBSIZE, THUMBSIZE));}
        else{
            viewHolder.imgIcon =  (ImageView) convertView.findViewById(R.id.icon);
        }

        return convertView;
    }



    public void filter(String charText) {

        charText = charText.toLowerCase(Locale.getDefault());

        images.clear();
        if (charText.length() == 0) {
            images.addAll(images);

        } else {
            for (Contact postDetail : images) {
                if (charText.length() != 0 && postDetail.getName().toLowerCase(Locale.getDefault()).contains(charText)) {
                    images.add(postDetail);
                } else if (charText.length() != 0 && postDetail.getMobNo().toLowerCase(Locale.getDefault()).contains(charText)) {
                    images.add(postDetail);
                }
            }
        }
        notifyDataSetChanged();
    }


}
