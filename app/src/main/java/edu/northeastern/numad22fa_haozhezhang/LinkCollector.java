package edu.northeastern.numad22fa_haozhezhang;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class LinkCollector extends AppCompatActivity implements GenerateDialog.UrlDialogListener{
    private FloatingActionButton FAB_add_url;
    private TextView textUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link_collector);

//        textUrl = (TextView) findViewById(R.id.text)
        FAB_add_url = findViewById(R.id.add_website_link_btn);
        FAB_add_url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });
    }

    private void openDialog() {
        GenerateDialog generateDialog = new GenerateDialog();
        generateDialog.show(getSupportFragmentManager(), "example dialog");
    }

    @Override
    public void applyTexts(String urlAddress) {

    }
}

//        FAB_add_url.setOnClickListener(new View.OnClickListener(){
//        @Override
//        public void onClick(View view) {
//                Snackbar snackbar = Snackbar.make(view,"add a link here", Snackbar.LENGTH_LONG)
//                        .setAction("Yes", new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                Snackbar mSnackbar = Snackbar.make(view, "Message successfully deleted.", Snackbar.LENGTH_SHORT);
//                                mSnackbar.show();
//                            }
//                        });
//                snackbar.show();
//        }
//    });
//}