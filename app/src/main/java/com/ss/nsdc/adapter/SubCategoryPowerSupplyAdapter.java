package com.ss.nsdc.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.ss.nsdc.R;
import com.ss.nsdc.entity.ITLab;

import java.util.List;

/**
 * Created by Radhika on 5/12/2017.
 */

public class SubCategoryPowerSupplyAdapter extends RecyclerView.Adapter<SubCategoryPowerSupplyAdapter.ViewHolder>{
    private Context context;
    private String category,inspectionType;
    private List<ITLab> labInfoList;


    public SubCategoryPowerSupplyAdapter(List getInfoList, String category, Context context) {

            this.labInfoList = getInfoList;

        this.context = context;
        this.category = category;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView name;
        public TextView category;
        public EditText quantity;
        public EditText editTradeShift3;
        public EditText editTradeTotal;

        public ViewHolder(View v) {
            super(v);
            name = (TextView) v.findViewById(R.id.name);
            category = (TextView) v.findViewById(R.id.category);
            quantity = (EditText) v.findViewById(R.id.quantity);


        }
    }

    @Override
    public SubCategoryPowerSupplyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        // create a new view
        View v = LayoutInflater.from(context).inflate(
                R.layout.lab_list, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final  ViewHolder holder, final int position) {
            //Toast.makeText(context,"name"+technicalInfoList.get(position).getName(),Toast.LENGTH_LONG).show();
            holder.name.setText(labInfoList.get(position).getNameofequipment());
            holder.category.setText(labInfoList.get(position).getCategory());
            holder.quantity.setText(labInfoList.get(position).getQuantity());


    }

    @Override
    public int getItemCount() {
            return labInfoList.size();
    }
}
