package com.brillion.libs.martyweb.web;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.brillion.libs.martyweb.MWActivity;
import com.brillion.libs.martyweb.Manager;
import com.brillion.libs.martyweb.interfaces.Bridge;
import com.brillion.libs.martyweb.interfaces.OnAlertListener;

import static com.brillion.libs.martyweb.Manager.isbuiltInZoomControls;
import static com.brillion.libs.martyweb.Manager.isdisplayZoomControls;
import static com.brillion.libs.martyweb.Manager.isdomStorage;
import static com.brillion.libs.martyweb.Manager.isjavaScriptEnabled;
import static com.brillion.libs.martyweb.Manager.issupportZoom;
import static com.brillion.libs.martyweb.Manager.iswideViewPort;

public class MWebView extends WebView {
    public MWebView(Context context) {
        super(context);
    }

    public void init(MWActivity activity , OnAlertListener alertListener){
        WebSettings settings = getSettings();
        settings.setUseWideViewPort(iswideViewPort);
        settings.setDomStorageEnabled(isdomStorage);
        settings.setJavaScriptEnabled(isjavaScriptEnabled);
        settings.setBuiltInZoomControls(isbuiltInZoomControls);
        settings.setDisplayZoomControls(isdisplayZoomControls);
        settings.setSupportZoom(issupportZoom);

        setWebChromeClient(new MChromClient(activity,alertListener));
        setWebViewClient(new MWebClient());
        addJavascriptInterface(new Bridge(activity),Manager.bridge_name);


    }


}
