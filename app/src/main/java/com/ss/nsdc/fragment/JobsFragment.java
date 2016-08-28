package com.ss.nsdc.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.ss.nsdc.R;
import com.ss.nsdc.dao.JobRolesModel;
import com.ss.nsdc.dao.SubCategoryLab;

/**
 * Created by Mayank on 29/08/2016.
 */
public class JobsFragment extends Fragment implements AdapterView.OnItemSelectedListener, View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    View view;

    Spinner spinnerSkillSector;
    ImageView imageSkillSectorEdit;
    ImageView imageSkillSectorSave;

    Spinner spinnerType;
    ImageView imageTypeEdit;
    ImageView imageTypeSave;

    Spinner spinnerJobRole;
    ImageView imageJobRoleEdit;
    ImageView imageJobRoleSave;

    Spinner spinnerProposedTrainer;
    ImageView imageProposedTrainerEdit;
    ImageView imageProposedTrainerSave;

    EditText editParallelBatches;
    ImageView imageParallelBatchesEdit;
    ImageView imageParallelBatchesSave;

    RadioButton radioModelContentYes;
    RadioButton radioModelContentNo;
    ImageView imageModelContentEdit;
    ImageView imageModelContentSave;

    EditText editRemarkIfAny;
    ImageView imageRemarkEdit;
    ImageView imageRemarkSave;

    Button btnDraft;
    Button btnSubmit;

    JobRolesModel jobRolesModel;


    public JobsFragment(JobRolesModel jobRolesModel) {
        super();
        this.jobRolesModel = jobRolesModel;
    }

    public JobsFragment() {
        super();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.classroom_form, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViews();
        initListners();
    }

    private void initViews() {
        spinnerSkillSector = (Spinner) view.findViewById(R.id.spinnerSkillSector);
        imageSkillSectorEdit = (ImageView) view.findViewById(R.id.imageSkillSectorEdit);
        imageSkillSectorSave = (ImageView) view.findViewById(R.id.imageSkillSectorSave);

        spinnerType = (Spinner) view.findViewById(R.id.spinnerType);
        imageTypeEdit = (ImageView) view.findViewById(R.id.imageTypeEdit);
        imageTypeSave = (ImageView) view.findViewById(R.id.imageTypeSave);

        spinnerJobRole = (Spinner) view.findViewById(R.id.spinnerJobRole);
        imageJobRoleEdit = (ImageView) view.findViewById(R.id.imageJobRoleEdit);
        imageJobRoleSave = (ImageView) view.findViewById(R.id.imageJobRoleSave);

        spinnerProposedTrainer = (Spinner) view.findViewById(R.id.spinnerProposedTrainer);
        imageProposedTrainerEdit = (ImageView) view.findViewById(R.id.imageProposedTrainerEdit);
        imageProposedTrainerSave = (ImageView) view.findViewById(R.id.imageProposedTrainerSave);

        editParallelBatches = (EditText) view.findViewById(R.id.editParallelBatches);
        imageParallelBatchesEdit = (ImageView) view.findViewById(R.id.imageParallelBatchesEdit);
        imageParallelBatchesSave = (ImageView) view.findViewById(R.id.imageParallelBatchesSave);

        radioModelContentYes = (RadioButton) view.findViewById(R.id.radioModelContentYes);
        radioModelContentNo = (RadioButton) view.findViewById(R.id.radioModelContentNo);
        imageModelContentEdit = (ImageView) view.findViewById(R.id.imageModelContentEdit);
        imageModelContentSave = (ImageView) view.findViewById(R.id.imageModelContentSave);

        editRemarkIfAny = (EditText) view.findViewById(R.id.editRemarkIfAny);
        imageRemarkEdit = (ImageView) view.findViewById(R.id.imageRemarkEdit);
        imageRemarkSave = (ImageView) view.findViewById(R.id.imageRemarkSave);

        btnDraft = (Button) view.findViewById(R.id.btnDraft);
        btnSubmit = (Button) view.findViewById(R.id.btnSubmit);
    }


    private void initListners() {
        spinnerSkillSector.setOnItemSelectedListener(this);
        imageSkillSectorEdit.setOnClickListener(this);
        imageSkillSectorSave.setOnClickListener(this);

        spinnerType.setOnItemSelectedListener(this);
        imageTypeEdit.setOnClickListener(this);
        imageTypeSave.setOnClickListener(this);

        spinnerJobRole.setOnItemSelectedListener(this);
        imageJobRoleEdit.setOnClickListener(this);
        imageJobRoleSave.setOnClickListener(this);

        spinnerProposedTrainer.setOnItemSelectedListener(this);
        imageProposedTrainerEdit.setOnClickListener(this);
        imageProposedTrainerSave.setOnClickListener(this);

        editParallelBatches.setOnClickListener(this);
        imageParallelBatchesEdit.setOnClickListener(this);
        imageParallelBatchesSave.setOnClickListener(this);

        radioModelContentYes.setOnCheckedChangeListener(this);
        radioModelContentNo.setOnCheckedChangeListener(this);

        imageModelContentEdit.setOnClickListener(this);
        imageModelContentSave.setOnClickListener(this);


        imageRemarkEdit.setOnClickListener(this);
        imageRemarkSave.setOnClickListener(this);

        btnDraft.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        switch (adapterView.getId()) {
            case R.id.spinnerSkillSector: {
                break;
            }
            case R.id.spinnerType: {
                break;
            }
            case R.id.spinnerJobRole: {
                break;
            }
            case R.id.spinnerProposedTrainer: {
                break;
            }

        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnDraft: {
                break;
            }
            case R.id.btnSubmit: {
                break;
            }

            case R.id.imageSkillSectorEdit: {
                break;
            }
            case R.id.imageSkillSectorSave: {
                break;
            }
            case R.id.imageTypeEdit: {
                break;
            }
            case R.id.imageTypeSave: {
                break;
            }
            case R.id.imageJobRoleEdit: {
                break;
            }
            case R.id.imageJobRoleSave: {
                break;
            }
            case R.id.imageProposedTrainerEdit: {
                break;
            }
            case R.id.imageProposedTrainerSave: {
                break;
            }
            case R.id.imageParallelBatchesEdit: {
                break;
            }
            case R.id.imageParallelBatchesSave: {
                break;
            }
            case R.id.imageModelContentEdit: {
                break;
            }
            case R.id.imageModelContentSave: {
                break;
            }
            case R.id.imageRemarkEdit: {
                break;
            }
            case R.id.imageRemarkSave: {
                break;
            }
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

        switch (compoundButton.getId()) {
            case R.id.radioModelContentYes: {
                break;
            }
            case R.id.radioModelContentNo: {
                break;
            }
        }

    }
}
