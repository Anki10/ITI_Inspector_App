package com.ss.nsdc.main;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.ss.nsdc.R;
import com.ss.nsdc.utility.DimensionUtils;

/**
 * Created by Arjit on 03-09-2016.
 */
public class BaseActivity extends AppCompatActivity {

    ProgressDialog pdialog;
    AlertDialog dialog;
    Dialog customDialog;

    protected void toHideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void progressDialog(Context context, String title, String message, boolean cancelable, boolean isTitle) {
        pdialog = new ProgressDialog(context);

        if (isTitle) {
            pdialog.setTitle(title);
        }

        pdialog.setMessage(message);
        if (!cancelable) {
            pdialog.setCancelable(false);
        }

        pdialog.show();
    }

    public void cancelProgressDialog() {
        pdialog.cancel();
    }

    public void alert(Context context, String title, String message, String positivebutton, String negativeButton, boolean isNegativeButton, boolean isCustomView, View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        dialog = null;
        //builder.setTitle(title);

        if (isCustomView) {
            builder.setView(view);
        } else {
            builder.setMessage(message);
            builder.setPositiveButton(positivebutton, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            if (isNegativeButton) {
                builder.setNegativeButton(negativeButton, null);
            }
        }
        dialog = builder.show();
    }

    public void alert(Context context, String title, String message, String positiveButton, String negativeButton, boolean isNegativeButton, boolean isTitle) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if (isTitle) {
            builder.setTitle(title);
        }

        builder.setMessage(message);
        builder.setPositiveButton(positiveButton, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        if (isNegativeButton) {
            builder.setNegativeButton(negativeButton, null);
        }

        builder.show();
    }

    public Dialog creatingDialog(Activity context, boolean isCancelableBack, boolean isCancellableOutside, View view, int height, int width, boolean heightMatchParent) {

        height = 260;
        customDialog = new Dialog(context, R.style.dialogTheme);

        if (view.getParent() != null) {
            ViewGroup viewGroup = (ViewGroup) view.getParent();
            viewGroup.removeView(view);
        }
        customDialog.setCanceledOnTouchOutside(isCancellableOutside);
        customDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        Window window = customDialog.getWindow();
        window.setGravity(Gravity.CENTER);

        customDialog.getWindow().getAttributes().windowAnimations = R.style.PauseDialogAnimation;
        customDialog.setContentView(view);
        customDialog.show();

        if (heightMatchParent) {
            customDialog.getWindow().setLayout(DimensionUtils.toPixels(context, width), WindowManager.LayoutParams.MATCH_PARENT);
        } else {
            customDialog.getWindow().setLayout(DimensionUtils.toPixels(context, width), DimensionUtils.toPixels(context, height));
        }

        return customDialog;
    }

    public void cancelCustomDialog() {
        if (customDialog != null) {
            customDialog.cancel();
        }
        //toHideKeyboard();
    }

}
