package com.ss.nsdc.fragment;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.ss.nsdc.R;
import com.ss.nsdc.entity.SubCategorySupportStaff;

/**
 * Created by Arjit on 29-08-2016.
 */
public class SupportStaffFragment extends Fragment implements AdapterView.OnItemSelectedListener, View.OnClickListener {


    Spinner spinnerStaffType;
    ImageView imageStaffTypeEdit;
    ImageView imageStaffTypeSave;

    EditText editStaffName;
    ImageView imageStaffNameEdit;
    ImageView imageStaffNameSave;

    EditText editRemarks;
    ImageView imageRemarksEdit;
    ImageView imageRemarksSave;

    EditText editWork;
    ImageView imageWorkEdit;
    ImageView imageWorkSave;

    Button btnDraft;
    Button btnSave;

    View view;
    Context context;
    SubCategorySupportStaff staffData;

    public SupportStaffFragment() {
        super();
    }

    public SupportStaffFragment(SubCategorySupportStaff subCategorySupportStaff) {
        super();
        this.staffData = subCategorySupportStaff;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_support_staff, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViews();
        initListeners();
    }

    private void initViews() {
        spinnerStaffType = (Spinner) view.findViewById(R.id.spinner_ss_type);
        imageStaffTypeEdit = (ImageView) view.findViewById(R.id.image_ss_type_edit);
        imageStaffTypeSave = (ImageView) view.findViewById(R.id.image_ss_type_save);

        editStaffName = (EditText) view.findViewById(R.id.edit_ss_name);
        imageStaffNameEdit = (ImageView) view.findViewById(R.id.image_ss_name_edit);
        imageStaffNameSave = (ImageView) view.findViewById(R.id.image_ss_name_save);

        editRemarks = (EditText) view.findViewById(R.id.edit_ss_remarks);
        imageRemarksEdit = (ImageView) view.findViewById(R.id.image_ss_remarks_edit);
        imageRemarksSave = (ImageView) view.findViewById(R.id.image_ss_remarks_save);

        btnDraft = (Button) view.findViewById(R.id.btn_ss_draft);
        btnSave = (Button) view.findViewById(R.id.btn_ss_submit);
    }


    private void initListeners() {
        spinnerStaffType.setOnItemSelectedListener(this);
        imageStaffTypeEdit.setOnClickListener(this);
        imageStaffTypeSave.setOnClickListener(this);

        btnDraft.setOnClickListener(this);
        btnSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (view.getId()) {
            case R.id.btn_ss_draft: {
                break;
            }

            case R.id.btn_ss_submit: {
                break;
            }

            case R.id.image_ss_type_edit: {
                break;
            }

            case R.id.image_ss_type_save: {
                break;
            }

            case R.id.image_ss_name_edit: {
                break;
            }

            case R.id.image_ss_name_save: {
                break;
            }

            case R.id.image_ss_remarks_edit: {
                break;
            }

            case R.id.image_ss_remarks_save: {
                break;
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        switch (adapterView.getId()) {
            case R.id.spinner_ss_type: {
                break;
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
