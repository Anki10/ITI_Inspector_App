package com.ss.nsdc.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.ss.nsdc.R;
import com.ss.nsdc.entity.ITLab;
import com.ss.nsdc.entity.TradeWiseTool;

import java.util.List;

/**
 * Created by Radhika on 5/12/2017.
 */

public class SubCategoryTradeWiseAdapter extends RecyclerView.Adapter<SubCategoryTradeWiseAdapter.ViewHolder>{
    private Context context;
    private String category,inspectionType;
    public static List<TradeWiseTool> tradewiseList;


    public SubCategoryTradeWiseAdapter(List<TradeWiseTool> getInfoList, String category, Context context) {

            this.tradewiseList = getInfoList;

        this.context = context;
        this.category = category;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView name;
        public EditText quantity;
        public CheckBox check;

        public ViewHolder(View v) {
            super(v);
            name = (TextView) v.findViewById(R.id.equipmentName);
            quantity = (EditText) v.findViewById(R.id.equipmentQuantity);
            check = (CheckBox) v.findViewById(R.id.check);

            quantity.addTextChangedListener(new TextWatcher() {
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
                public void afterTextChanged(Editable editable) {}
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if(quantity.getTag()!=null){
                        tradewiseList.get((int)quantity.getTag()).setAvailableUnit(charSequence.toString());
                    }
                }
            });

        }
    }

    @Override
    public SubCategoryTradeWiseAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        // create a new view
        View v = LayoutInflater.from(context).inflate(
                R.layout.trade_wise_tool_list, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final  ViewHolder holder, final int position) {
            //Toast.makeText(context,"name"+technicalInfoList.get(position).getName(),Toast.LENGTH_LONG).show();
            holder.name.setText(tradewiseList.get(position).getEquipmentName());
            holder.quantity.setTag(position);
            holder.quantity.setText(tradewiseList.get(position).getReqUnit());

        Log.e("quantity",tradewiseList.get(position).getReqUnit());

         if(tradewiseList.get(position).getMarkTools().equalsIgnoreCase("N")){
             holder.check.setChecked(false);
         }else {
             holder.check.setChecked(true);
         }

        holder.check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.check.isChecked()) {
                    tradewiseList.get(position).setMarkTools("Y");
                }else
                if(!holder.check.isChecked()) {
                    tradewiseList.get(position).setMarkTools("N");
                }

            }
        });

    }

    @Override
    public int getItemCount() {
            return tradewiseList.size();
    }
}
