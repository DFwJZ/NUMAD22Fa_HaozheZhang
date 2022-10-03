package edu.northeastern.numad22fa_haozhezhang;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class GenerateDialog extends AppCompatDialogFragment {
    private EditText editTextUrl;
    private UrlDialogListener urlDialogListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.custom_dialog, null);

        builder.setView(view)
                .setTitle("Adding URL")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String urlAddress = editTextUrl.getText().toString();
                        urlDialogListener.applyTexts(urlAddress);
                    }
                });

        editTextUrl = view.findViewById(R.id.edit_url);
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // An UrlDialogListener must be implemented in order to open the dialog box,
        // otherwise throw an exception
        try {
            urlDialogListener = (UrlDialogListener) context;
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString() + "must implement UrlDialogListener");
        }
    }

    public interface UrlDialogListener {
        void applyTexts(String urlAddress);
    }
}
