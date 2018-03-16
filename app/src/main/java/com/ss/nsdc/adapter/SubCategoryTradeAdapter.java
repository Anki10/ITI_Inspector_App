package com.ss.nsdc.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ss.nsdc.R;
import com.ss.nsdc.entity.Accommodation;

import java.util.List;

/**
 * Created by Radhika on 5/12/2017.
 */

public class SubCategoryTradeAdapter extends RecyclerView.Adapter<SubCategoryTradeAdapter.ViewHolder>{
    private Context context;
    private String category,inspectionType;
    private List<Accommodation> accommodationInfoList;


    public SubCategoryTradeAdapter(List getInfoList, String category, Context context) {

            this.accommodationInfoList = getInfoList;

        this.context = context;
        this.category = category;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView editTradeName;
        public TextView editTradeShift1;
        public TextView editTradeShift2;
        public TextView editTradeShift3;
        public ViewHolder(View v) {
            super(v);
            editTradeName = (TextView) v.findViewById(R.id.editTradeName);
            editTradeShift1 = (TextView) v.findViewById(R.id.editTradeShift1);
            editTradeShift2 = (TextView) v.findViewById(R.id.editTradeShift2);
            editTradeShift3 = (TextView) v.findViewById(R.id.editTradeShift3);

        }
    }

    @Override
    public SubCategoryTradeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        // create a new view
        View v = LayoutInflater.from(context).inflate(
                R.layout.accommodation_list, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final  ViewHolder holder, final int position) {
            //Toast.makeText(context,"name"+technicalInfoList.get(position).getName(),Toast.LENGTH_LONG).show();
            holder.editTradeName.setText(accommodationInfoList.get(position).getNameofTrade());
            holder.editTradeShift1.setText(accommodationInfoList.get(position).getShift1());
            holder.editTradeShift2.setText(accommodationInfoList.get(position).getShift2());
            holder.editTradeShift3.setText(accommodationInfoList.get(position).getShift3());


    }

    @Override
    public int getItemCount() {
            return accommodationInfoList.size();
    }
}
