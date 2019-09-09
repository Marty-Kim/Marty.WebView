package com.brillion.libs.martyweb.interfaces;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Looper;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.webkit.JavascriptInterface;

import com.brillion.libs.martyweb.MWActivity;

public class Bridge {
    MWActivity Activity;

    public Bridge(MWActivity activity) {
        this.Activity = Activity;
    }

    @JavascriptInterface
    public void Call(String number){
        Activity.Call(number);

    }
    @JavascriptInterface
    public void Blank(String link){
        Activity.Blank(link);

    }

    @JavascriptInterface
    public void download(String link){
        Activity.download(link);

    }
    @JavascriptInterface
    public void getLocationInfo(){
        Activity.getLocationInfo();

    }




}
