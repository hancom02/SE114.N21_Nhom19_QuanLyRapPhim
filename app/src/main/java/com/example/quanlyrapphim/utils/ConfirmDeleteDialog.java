package com.example.quanlyrapphim.utils;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import androidx.appcompat.app.AlertDialog;

public class ConfirmDeleteDialog extends DialogFragment {

    private OnDeleteListener deleteListener = null;
    private OnCancelListener cancelListener = null;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Bạn có chắc chăn muốn xoá?")
                .setPositiveButton("Xoá", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (deleteListener != null)
                            deleteListener.onDeleteClick();
                    }
                })
                .setNegativeButton("Quay lại", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (cancelListener != null)
                            cancelListener.onCancelClick();
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }

    public void setDeleteListener(OnDeleteListener listener) {
        deleteListener = listener;
    }

    public void setCancelListener(OnCancelListener listener) {
        cancelListener = listener;
    }

    public interface OnDeleteListener {
        void onDeleteClick();
    }

    public interface OnCancelListener {
        void onCancelClick();
    }
}
