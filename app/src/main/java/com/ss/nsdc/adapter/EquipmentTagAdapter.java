package com.ss.nsdc.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.ss.nsdc.R;
import com.ss.nsdc.entity.EquipmentTag;

import java.util.List;

/**
 * Created by Arjit on 23-08-2016.
 */
public class EquipmentTagAdapter extends BaseAdapter {

    Activity currentActivity;
    static List<EquipmentTag> lstEquipmentTag = null;
    private static LayoutInflater inflater = null;

    public EquipmentTagAdapter(Activity activity, List<EquipmentTag> tags) {
        this.currentActivity = activity;
        lstEquipmentTag = tags;
        inflater = (LayoutInflater) currentActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return lstEquipmentTag.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class Holder {
        CheckBox cb;
        TextView tv;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Holder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.equip_tag_dialog_item, null);
            holder = new Holder();

            holder.cb = (CheckBox) convertView.findViewById(R.id.cbTag);
            holder.tv = (TextView) convertView.findViewById(R.id.textTagName);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        holder.tv.setText(lstEquipmentTag.get(position).getEquipmentTagName());
        holder.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                lstEquipmentTag.get(position).setIsChecked(isChecked);
            }
        });


        if (lstEquipmentTag.get(position).isChecked()) {
            holder.cb.setChecked(true);
        } else {
            holder.cb.setChecked(false);
        }
        return convertView;
    }
}
