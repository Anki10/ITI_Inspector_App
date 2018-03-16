package com.ss.nsdc.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
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

public class SubCategoryITLabAdapter extends RecyclerView.Adapter<SubCategoryITLabAdapter.ViewHolder>{
    private Context context;
    private String category,inspectionType;
    private List<ITLab> labInfoList;

    public SubCategoryITLabAdapter(List<ITLab> getLabInfoList, String category, Context context) {

        this.labInfoList = getLabInfoList;
        this.context = context;
        this.category = category;
    }

    public SubCategoryITLabAdapter(ITLab getLabInfo, String category, Context context) {
    }


//    public SubCategoryITLabAdapter(List getInfoList, String category, Context context) {
//
//            this.labInfoList = getInfoList;
//
//        this.context = context;
//        this.category = category;
//    }


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

            quantity.addTextChangedListener(new TextWatcher() {
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
                public void afterTextChanged(Editable editable) {}
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if(quantity.getTag()!=null){
                        labInfoList.get((int)quantity.getTag()).setQuantity(charSequence.toString());
                    }
                }
            });
        }
    }

    @Override
    public SubCategoryITLabAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
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
            holder.quantity.setTag(position);
            holder.quantity.setText(labInfoList.get(position).getQuantity());

       /* holder.quantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence,
                                      int start, int before, int count) {
                boolean userChange = Math.abs(count - before) == 1;
                if (userChange) {
                    labInfoList.get(position).setQuantity(charSequence.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });*/

    }

    @Override
    public int getItemCount() {
            return labInfoList.size();
    }
}
