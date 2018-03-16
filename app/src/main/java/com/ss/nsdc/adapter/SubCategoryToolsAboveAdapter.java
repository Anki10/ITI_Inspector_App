package com.ss.nsdc.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ss.nsdc.R;
import com.ss.nsdc.entity.ToolsAbove;
import com.ss.nsdc.entity.TradeWiseTool;

import java.util.List;

import static com.ss.nsdc.adapter.SubCategoryTradeWiseAdapter.tradewiseList;

/**
 * Created by Administrator on 6/4/2017.
 */

public class SubCategoryToolsAboveAdapter extends RecyclerView.Adapter<SubCategoryToolsAboveAdapter.ViewHolder> {

    private Context context;
    private String category,inspectionType;
    public static List<ToolsAbove> toolsAboveList;


    public SubCategoryToolsAboveAdapter(List<ToolsAbove> getToolsAbove, String category, Context context) {
        this.toolsAboveList = getToolsAbove;
        this.context = context;
        this.category = category;
    }


    @Override
    public SubCategoryToolsAboveAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        // create a new view
        View v = LayoutInflater.from(context).inflate(
                R.layout.tools_above_list, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public CheckBox groutingRequired;
        public CheckBox groutingAvailable;
        public CheckBox engravingRequired;
        public CheckBox engravingAvailable;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.equipmentName);
            groutingRequired = (CheckBox) itemView.findViewById(R.id.check_groutingRequired);
            groutingAvailable = (CheckBox) itemView.findViewById(R.id.check_groutingAvailable);
            engravingAvailable = (CheckBox) itemView.findViewById(R.id.check_engravingAvailable);
            engravingRequired = (CheckBox) itemView.findViewById(R.id.check_engravingRequired);
        }
    }

    @Override
    public void onBindViewHolder(final SubCategoryToolsAboveAdapter.ViewHolder holder, final int position) {
        Log.e("List",toolsAboveList.get(position).getEquipmentName());
        holder.name.setText(toolsAboveList.get(position).getEquipmentName());


        if(toolsAboveList.get(position).getGroutingRequired().equalsIgnoreCase("yes")){
            holder.groutingRequired.setChecked(true);
        }else {
            holder.groutingRequired.setChecked(false);
        }

        if(toolsAboveList.get(position).getGroutingAvailable().equalsIgnoreCase("yes")){
            holder.groutingAvailable.setChecked(true);
        }else {
            holder.groutingAvailable.setChecked(false);
        }


        if(toolsAboveList.get(position).getEngravingRequired().equalsIgnoreCase("yes")){
            holder.engravingRequired.setChecked(true);
        }else {
            holder.engravingRequired.setChecked(false);
        }

        if(toolsAboveList.get(position).getEngravingAvailable().equalsIgnoreCase("yes")){
            holder.engravingAvailable.setChecked(true);
        }else {
            holder.engravingAvailable.setChecked(false);
        }





        holder.engravingAvailable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.engravingAvailable.isChecked()) {
                    toolsAboveList.get(position).setEngravingAvailable("yes");
                    //Log.e("yes",toolsAboveList.get(position).getRequired());
                }else
                if(!holder.engravingAvailable.isChecked()) {
                    toolsAboveList.get(position).setEngravingAvailable("no");
                    //Log.e("yes",toolsAboveList.get(position).getRequired());
                }

            }
        });
        holder.engravingRequired.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.engravingRequired.isChecked()) {
                    toolsAboveList.get(position).setEngravingRequired("yes");
                    //Log.e("yes",toolsAboveList.get(position).getAvailable());
                }else
                if(!holder.engravingRequired.isChecked()) {
                    toolsAboveList.get(position).setEngravingRequired("no");
                    //Log.e("no",toolsAboveList.get(position).getAvailable());
                }

            }
        });

        holder.groutingRequired.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.groutingRequired.isChecked()) {
                    toolsAboveList.get(position).setGroutingRequired("yes");
                    //Log.e("yes",toolsAboveList.get(position).getAvailable());
                }else
                if(!holder.groutingRequired.isChecked()) {
                    toolsAboveList.get(position).setGroutingRequired("no");
                    //Log.e("no",toolsAboveList.get(position).getAvailable());
                }

            }
        });

        holder.groutingAvailable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.groutingAvailable.isChecked()) {
                    toolsAboveList.get(position).setGroutingAvailable("yes");
                    //Log.e("yes",toolsAboveList.get(position).getAvailable());
                }else
                if(!holder.groutingAvailable.isChecked()) {
                    toolsAboveList.get(position).setGroutingAvailable("no");
                    //Log.e("no",toolsAboveList.get(position).getAvailable());
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return toolsAboveList.size();
    }


}
