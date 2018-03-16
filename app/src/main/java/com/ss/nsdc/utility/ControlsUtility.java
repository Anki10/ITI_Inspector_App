package com.ss.nsdc.utility;

import com.ss.nsdc.R;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class ControlsUtility {

    /**
     * This method sets spinner data
     *
     * @param spin
     * @param stringArray
     */
    public static void setSpinnerData(Spinner spin, String[] stringArray,
                                      View view) {
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(
                view.getContext(), android.R.layout.simple_spinner_item,
                stringArray);

        dataAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spin.setAdapter(dataAdapter);
    }

    public static void setDefaultEditText(EditText editText, String data) {
        if (data != null && !data.equalsIgnoreCase("null")) {
            editText.setText(data);
        }
        editText.setEnabled(false);
    }

    public static void setDefaultRadioText(RadioGroup group,
                                           RadioButton radioButton1, RadioButton radioButton2, String data,
                                           int btn1, int btn2) {

        data = (data.equalsIgnoreCase("") || data.equalsIgnoreCase("null")) ? null : data;
        if (data != null) {
            if (radioButton1.getText().equals(data.trim())) {
                group.check(btn1);
            } else if (radioButton2.getText().equals(data)) {
                group.check(btn2);
            }
        }
        radioButton1.setEnabled(false);
        radioButton2.setEnabled(false);

    }


    public static void setDefaultRadioText(RadioGroup group,
                                           RadioButton radioButton1, RadioButton radioButton2, RadioButton radioButton3, String data,
                                           int btn1, int btn2, int btn3) {

        data = (data.equalsIgnoreCase("") || data.equalsIgnoreCase("null")) ? null : data;
        if (data != null) {
            if (radioButton1.getText().equals(data.trim())) {
                group.check(btn1);
            } else if (radioButton2.getText().equals(data)) {
                group.check(btn2);
            } else if (radioButton3.getText().equals(data)) {
                group.check(btn3);
            }
        }
        radioButton1.setEnabled(false);
        radioButton2.setEnabled(false);
        radioButton3.setEnabled(false);
    }


    public static void setDefaultCheck(CheckBox[] group, String data) {

        if (data != null && !data.equalsIgnoreCase("null")) {

            for (int i = 0; i < group.length; i++) {
                group[i].setEnabled(false);
                if ((data.contains((group[i].getText()).toString()))) {
                    group[i].setChecked(true);
                }
            }
        }

    }

    public static void setDefaultSpinnerText(Spinner spinner, String data,
                                             String[] dataArr) {

        if (data != null && !data.equalsIgnoreCase("null")) {
            data = data.replace("  ", " ");

            int position = 0;

            for (int i = 0; i < dataArr.length; i++) {
                if (dataArr[i].equalsIgnoreCase((data.trim()))) {
                    position = i;
                }
            }
            spinner.setSelection(position);
        }
        spinner.setEnabled(false);
    }

    public static void okImageViewAction(ImageView imageView, final EditText editText, View view, int quesStage) {

        editText.setEnabled(false);
        if (quesStage == 0)
            imageView.setImageDrawable(view.getResources().getDrawable(R.drawable.ok_green));
        else if (quesStage == 1)
            imageView.setImageDrawable(view.getResources().getDrawable(R.drawable.flag));
    }

    public static void editImageViewAction(ImageView imageView,
                                           final EditText editText, View view) {

        editText.setEnabled(true);
        imageView.setImageDrawable(view.getResources().getDrawable(
                R.drawable.ok_grey));
    }

    public static void okImageViewAction(ImageView imageView,
                                         RadioButton radioButton1, RadioButton radioButton2, int btn1,
                                         int btn2, View view, int quesStage) {

        radioButton1.setEnabled(false);
        radioButton2.setEnabled(false);
        if (quesStage == 0)
            imageView.setImageDrawable(view.getResources().getDrawable(R.drawable.ok_green));
        else if (quesStage == 1)
            imageView.setImageDrawable(view.getResources().getDrawable(R.drawable.flag));
    }

    public static void editImageViewAction(ImageView imageView,
                                           RadioButton radioButton1, RadioButton radioButton2, int btn1,
                                           int btn2, View view) {

        radioButton1.setEnabled(true);
        radioButton2.setEnabled(true);
        imageView.setImageDrawable(view.getResources().getDrawable(
                R.drawable.ok_grey));
    }


    public static void okImageViewAction(ImageView imageView,
                                         RadioButton radioButton1, RadioButton radioButton2, RadioButton radioButton3, int btn1,
                                         int btn2, int btn3, View view, int quesStage) {

        radioButton1.setEnabled(false);
        radioButton2.setEnabled(false);
        radioButton3.setEnabled(false);
        if (quesStage == 0)
            imageView.setImageDrawable(view.getResources().getDrawable(R.drawable.ok_green));
        else if (quesStage == 1)
            imageView.setImageDrawable(view.getResources().getDrawable(R.drawable.flag));
    }

    public static void editImageViewAction(ImageView imageView,
                                           RadioButton radioButton1, RadioButton radioButton2, RadioButton radioButton3, int btn1,
                                           int btn2, int btn3, View view) {

        radioButton1.setEnabled(true);
        radioButton2.setEnabled(true);
        radioButton3.setEnabled(true);
        imageView.setImageDrawable(view.getResources().getDrawable(
                R.drawable.ok_grey));
    }


    public static void okImageViewAction(ImageView imageView,
                                         final Spinner spinner, View view, int quesStage) {

        spinner.setEnabled(false);
        if (quesStage == 0)
            imageView.setImageDrawable(view.getResources().getDrawable(R.drawable.ok_green));
        else if (quesStage == 1)
            imageView.setImageDrawable(view.getResources().getDrawable(R.drawable.flag));
    }

    public static void editImageViewAction(ImageView imageView,
                                           final Spinner spinner, View view) {
        spinner.setEnabled(true);
        imageView.setImageDrawable(view.getResources().getDrawable(R.drawable.ok_grey));
    }

    public static String getSelectedRadioText(RadioGroup radioGroup) {
        if (radioGroup.getCheckedRadioButtonId() != -1) {
            int id = radioGroup.getCheckedRadioButtonId();
            View radioButton = radioGroup.findViewById(id);
            int radioId = radioGroup.indexOfChild(radioButton);
            RadioButton btn = (RadioButton) radioGroup.getChildAt(radioId);
            return (String) btn.getText();
        }
        return null;
    }

    public static void editImageViewAction(ImageView imageView,
                                           CheckBox[] boxs, View view) {
        for (int i = 0; i < boxs.length; i++) {
            boxs[i].setEnabled(true);
        }
        imageView.setImageDrawable(view.getResources().getDrawable(
                R.drawable.ok_grey));
    }

    public static void okImageViewAction(ImageView imageView, CheckBox[] boxs,
                                         View view, int quesStage) {

        for (int i = 0; i < boxs.length; i++) {
            boxs[i].setEnabled(false);
        }
        if (quesStage == 0)
            imageView.setImageDrawable(view.getResources().getDrawable(R.drawable.ok_green));
        else if (quesStage == 1)
            imageView.setImageDrawable(view.getResources().getDrawable(R.drawable.flag));
    }


    public static void setDefaultLikeSpinnerText(Spinner spinner, String data,
                                                 String[] dataArr) {
        if (data != null) {
            int position = 0;

            for (int i = 0; i < dataArr.length; i++) {
                if (dataArr[i].contains(data.trim())) {
                    position = i;
                    break;

                }
            }
            spinner.setSelection(position);
            spinner.setEnabled(false);
        }
    }
}
