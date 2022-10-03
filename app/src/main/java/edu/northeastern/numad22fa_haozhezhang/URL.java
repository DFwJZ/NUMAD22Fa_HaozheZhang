package edu.northeastern.numad22fa_haozhezhang;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Patterns;

import java.net.MalformedURLException;
import java.net.URISyntaxException;


public class URL {
    private final String urlName;
    private final String urlAddress;

    public URL (String urlName, String urlAddress) {
        this.urlName = urlName;
        this.urlAddress = urlAddress;
    }

    public String getUrlAddress() {
        return this.urlAddress;
    }

    public String getUrlName() {
        return this.urlName;
    }

    public void onClickLink(Context context) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(urlAddress));
        context.startActivity(intent);
    }

    public boolean validUrl() {
        try {
            new java.net.URL(urlAddress).toURI();

        } catch (MalformedURLException | URISyntaxException e) {
            return false;
        }
        return Patterns.WEB_URL.matcher(urlAddress).matches();
    }
}
