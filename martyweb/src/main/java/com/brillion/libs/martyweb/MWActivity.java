package com.brillion.libs.martyweb;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.widget.LinearLayout;

import com.brillion.libs.martyweb.web.MWebView;

public class MWActivity extends AppCompatActivity {
    protected int disable_flag = 0;
    protected MWebView webView;
    protected LocationManager lm;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        webView = new MWebView(this);
//        webView.set


//        ViewGroup layout =  findViewById(android.R.id.content);
//
//
//
//// layout param 생성
//
//        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT /* layout_width */, ViewGroup.LayoutParams.MATCH_PARENT /* layout_height */);
//        webView = new MWebView(this);  // 새로 추가할 textView 생성
//        webView.setLayoutParams(layoutParams);  // textView layout 설정
//        layout.addView(webView); // 기존 linearLayout에 textView 추가




    }

    void initWebView(MWebView webView){
        this.webView = webView;
    }

    public void Call(String number){
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+number));
        startActivity(intent);

    }
    public void Blank(String link){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
        intent.setPackage("com.android.chrome");   // 브라우저가 여러개 인 경우 콕 찍어서 크롬을 지정할 경우
        startActivity(intent);
    }

    public void download(String link){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
        intent.setPackage("com.android.chrome");   // 브라우저가 여러개 인 경우 콕 찍어서 크롬을 지정할 경우
        startActivity(intent);
    }
    public void getLocationInfo(){
        initLocationSetting();
    }

    final LocationListener gpsLocationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            final double longitude = location.getLongitude();
            final double latitude = location.getLatitude();
            Log.d("<TAG>>>" , "Location onLocationChanged("+ latitude +  ", " +longitude+ ")");
            webView.post(new Runnable() {
                @Override
                public void run() {
                    webView.loadUrl("javascript:"+Manager.locationSuccess_callbackMethod_name+"("+ latitude + "," +longitude+ ")");
                }
            });
        }
        public void onStatusChanged(String provider, int status, Bundle extras) {
            Log.d("<TAG>>>" , "Location onStatusChanged");

        }

        public void onProviderEnabled(String provider) {
            Log.d("<TAG>>>" , "Location onProviderEnabled");

        }

        public synchronized void onProviderDisabled(String provider) {
            if (++disable_flag >= 2){
                disable_flag = 0;
                webView.loadUrl("javascript:"+Manager.locationDisable_callbackMethod_name+"()");
            }



        }
    };
    void initLocationSetting(){
        if ( Build.VERSION.SDK_INT >= 23 && ContextCompat.checkSelfPermission( getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions( MWActivity.this, new String[] {  android.Manifest.permission.ACCESS_FINE_LOCATION  }, 0 );
        }else {
            Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            Log.d("<TAG>>>" , "Location permission granted");
            lm.requestSingleUpdate(LocationManager.GPS_PROVIDER,
                    gpsLocationListener, Looper.getMainLooper());
            lm.requestSingleUpdate(LocationManager.NETWORK_PROVIDER,
                    gpsLocationListener,Looper.getMainLooper());
        }

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.d("<TAG>>>" , "Location permission 111");
        if (requestCode == 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d("<TAG>>>", "Location permission 222");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    Log.d("<TAG>>>", "Location permission granted");

                    lm.requestSingleUpdate(LocationManager.GPS_PROVIDER,
                            gpsLocationListener, Looper.getMainLooper());
                    lm.requestSingleUpdate(LocationManager.NETWORK_PROVIDER,
                            gpsLocationListener, Looper.getMainLooper());
                }
            } else {
                webView.loadUrl("javascript:" + Manager.locationFailed_callbackMethod_name + "()");
                Log.d("<TAG>>>", "Location Denied");
            }
        }else if (requestCode == 1){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d("<TAG>>>", "Location permission 222");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {

                }
            } else {
                webView.loadUrl("javascript:" + Manager.locationFailed_callbackMethod_name + "()");
                Log.d("<TAG>>>", "Location Denied");
            }
        }
    }

}