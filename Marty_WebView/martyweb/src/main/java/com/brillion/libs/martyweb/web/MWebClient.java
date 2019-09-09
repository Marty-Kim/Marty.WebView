package com.brillion.libs.martyweb.web;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.brillion.libs.martyweb.Manager;

import java.net.URI;
import java.net.URL;

import static com.brillion.libs.martyweb.Manager.blank_scheme;
import static com.brillion.libs.martyweb.Manager.download_scheme;
import static com.brillion.libs.martyweb.Manager.tel_scheme;

public class MWebClient extends WebViewClient {


    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        if (Manager.isshouldOverrideUrl) {
            String current_url = view.getUrl();
            if (!tel_scheme.isEmpty() && current_url.startsWith(Manager.tel_scheme)){
                Intent inte = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+current_url.split("://")[1]));
                view.getContext().startActivity(inte);
            }else if (!blank_scheme.isEmpty() && current_url.startsWith(blank_scheme)){
                Intent inte = new Intent(Intent.ACTION_VIEW, Uri.parse(current_url.split(blank_scheme)[1]));
                view.getContext().startActivity(inte);
            }else if (!download_scheme.isEmpty() && current_url.startsWith(Manager.download_scheme)){
                Intent inte = new Intent(Intent.ACTION_VIEW, Uri.parse(current_url.split(download_scheme)[1]));
                view.getContext().startActivity(inte);
            }else
                return super.shouldOverrideUrlLoading(view, request);
            return true;
        }else{
            return super.shouldOverrideUrlLoading(view, request);
        }

    }
}
