package edu.northeastern.numad22fa_haozhezhang;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class LinkCollector extends AppCompatActivity {
    private ArrayList<URL> urlList = new ArrayList<>();
    private AlertDialog inputDialog;
    private FloatingActionButton FAB_add_url;
    private EditText textUrlName;
    private EditText textUrlAddress;
    private RecyclerView recyclerView;
    private URLAdaptor urlAdaptor;
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link_collector);

        // Adding the floating action button
        FAB_add_url = findViewById(R.id.add_website_link_btn);
        // Init FAB
        FAB_add_url.setOnClickListener(view -> addNewUrl());


        createInputAlertDialog();
        createRecyclerView();
        urlAdaptor.setLinkClickListener(position -> urlList.get(position).onClickLink(this));


    }

    private void addNewUrl() {
        textUrlName.getText().clear();
        textUrlAddress.getText().clear();
        textUrlAddress.setText(R.string.HTTP);
        textUrlName.requestFocus();
        inputDialog.show();
    }

    public void createRecyclerView() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView = findViewById(R.id.recyclerView);
//        recyclerView.setHasFixedSize(true);
        urlAdaptor = new URLAdaptor(urlList);

        recyclerView.setAdapter(urlAdaptor);
        recyclerView.setLayoutManager(layoutManager);
    }

    public void createInputAlertDialog() {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View view = layoutInflater.inflate(R.layout.activity_dialog_link_collector, null);

        textUrlName = view.findViewById(R.id.url_name_input);
        textUrlAddress = view.findViewById(R.id.url_address_input);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setView(view);

        alertDialogBuilder.setCancelable(false)
                .setPositiveButton(getString(R.string.Add),
                        (dialog, id) -> {
                            URL urlItem = new URL(textUrlName.getText().toString(), textUrlAddress.getText().toString());
                            if (urlItem.validUrl()) {
                                urlList.add(0, urlItem);
                                urlAdaptor.notifyDataSetChanged();
                                Snackbar.make(recyclerView, getString(R.string.SuccessAddUrl), Snackbar.LENGTH_LONG).show();
                            } else {
                                Snackbar.make(recyclerView, getString(R.string.FailAddUrl), Snackbar.LENGTH_LONG).show();
                            }
                        })
                .setNegativeButton(getString(R.string.Cancel),
                        (dialog, id) -> dialog.cancel());
        inputDialog = alertDialogBuilder.create();
    }


}

