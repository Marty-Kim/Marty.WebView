package com.brillion.libs.martyweb.web;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.brillion.libs.martyweb.interfaces.OnAlertListener;
import static com.brillion.libs.martyweb.Manager.isalert;
import static com.brillion.libs.martyweb.Manager.iscustomAlert;

public class MChromClient extends WebChromeClient {

    OnAlertListener onCustomAlert;
    Activity activity;

    public MChromClient( Activity activity,OnAlertListener onCustomAlert) {
        if (onCustomAlert == null){
            onCustomAlert = new OnAlertListener() {
                @Override
                public void onCustomAlertCalled() { }
                @Override
                public void onDefaultAlertConfirmCallback() { }
            };
        }
        this.onCustomAlert = onCustomAlert;
        this.activity = activity;
    }

    @Override
    public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
        if (isalert){
            result.cancel();
            if (iscustomAlert){
                onCustomAlert.onCustomAlertCalled();
            }else{
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setTitle("")
                        .setMessage(message)
                        .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                onCustomAlert.onDefaultAlertConfirmCallback();
                                dialog.dismiss();
                            }
                        })
                        .setCancelable(true)
                        .create().show();
                return true;

            }
        }
        return super.onJsAlert(view, url, message, result);
    }


}
