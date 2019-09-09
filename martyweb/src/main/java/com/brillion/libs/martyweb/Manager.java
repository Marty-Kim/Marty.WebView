package com.brillion.libs.martyweb;

import android.content.res.AssetManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Manager {
    static JSONObject data = null;
    private static final String BASE_URL = "BASE_URL";
    private static final String BRIDGE_NAME = "BRIDGE_NAME";
    private static final String Tel_Scheme = "Tel_Scheme";
    private static final String Blank_Scheme = "Blank_Scheme";
    private static final String Download_Scheme = "Download_Scheme";
    private static final String Location_Bridge_Method_Name = "Location_Bridge_Method_Name";
    private static final String Tel_Bridge_Method_Name  = "Tel_Bridge_Method_Name";
    private static final String Blank_Bridge_Method_Name  = "Blank_Bridge_Method_Name";
    private static final String Download_Bridge_Method_Name  = "Download_Bridge_Method_Name";
    private static final String LocationSuccess_CallbackMethod_Name = "LocationSuccess_CallbackMethod_Name";
    private static final String LocationFailed_CallbackMethod_Name = "LocationFailed_CallbackMethod_Name";
    private static final String LocationDisable_CallbackMethod_Name = "LocationDisable_CallbackMethod_Name";
    private static final String DoubleClick_AppExit_String = "DoubleClick_AppExit_String";
    private static final String DoubleClick_Execute_Url = "DoubleClick_Execute_Url";
    private static final String isBridge = "isBridge";
    private static final String isShouldOverrideUrl = "isShouldOverrideUrl";
    private static final String isAlert = "isAlert";
    private static final String isCustomAlert = "isCustomAlert";
    private static final String isLocationService = "isLocationService";
    private static final String isDoubleClickAppExit = "isDoubleClickAppExit";

    //WebView Default Settings List
    private static final String isWideViewPort = "isWideViewPort";
    private static final String isDomStorage = "isDomStorage";
    private static final String isJavaScriptEnabled = "isJavaScriptEnabled";
    private static final String isBuiltInZoomControls = "isBuildInZoomControls";
    private static final String isDisplayZoomControls = "isDisplayZoomControls";
    private static final String isSupportZoom = "isSupportZoom";


    public static  String base_url = "BASE_URL";
    public static String bridge_name= "BRIDGE_NAME";
    public static  String tel_scheme = "Tel_Scheme";
    public static  String blank_scheme = "Blank_Scheme";
    public static  String download_scheme = "Download_Scheme";
    public static  String location_bridge_method_name = "Location_Bridge_Method_Name";
    public static  String tel_bridge_method_name  = "Tel_Bridge_Method_Name";
    public static  String blank_bridge_method_name  = "Blank_Bridge_Method_Name";
    public static  String locationSuccess_callbackMethod_name = "LocationSuccess_CallbackMethod_Name";
    public static  String locationFailed_callbackMethod_name = "LocationSuccess_CallbackMethod_Name";
    public static  String locationDisable_callbackMethod_name = "locationDisable_callbackMethod_name";
    public static  String doubleClick_appExit_string = "DoubleClick_AppExit_String";
    public static  String doubleClick_execute_url = "DoubleClick_Execute_Url";
    public static  boolean isbridge = false;
    public static  boolean isshouldOverrideUrl = false;
    public static  boolean isalert = false;
    public static  boolean iscustomAlert = false;
    public static  boolean islocationService = false;
    public static  boolean isdoubleClickAppExit = false;

    public static  boolean iswideViewPort = false;
    public static  boolean isdomStorage = false;
    public static  boolean isjavaScriptEnabled = false;
    public static  boolean isbuiltInZoomControls = false;
    public static  boolean isdisplayZoomControls = false;
    public static  boolean issupportZoom = false;





    private Manager (AssetManager manager,String file_path) throws IOException, JSONException {
        InputStream stream = manager.open(file_path);
        BufferedReader streamReader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
        StringBuilder responseStrBuilder = new StringBuilder();

        String inputStr;
        while ((inputStr = streamReader.readLine()) != null)
            responseStrBuilder.append(inputStr);

        try {
            JSONObject jsonObject = new JSONObject(responseStrBuilder.toString());

            base_url = jsonObject.getString(BASE_URL);
            bridge_name = jsonObject.getString(BRIDGE_NAME);
            tel_scheme = jsonObject.getString(Tel_Scheme);
            blank_scheme = jsonObject.getString(Blank_Scheme);
            download_scheme = jsonObject.getString(Download_Scheme);
            location_bridge_method_name = jsonObject.getString(Location_Bridge_Method_Name);
            tel_bridge_method_name = jsonObject.getString(Tel_Bridge_Method_Name);
            blank_bridge_method_name = jsonObject.getString(Blank_Bridge_Method_Name);
            locationSuccess_callbackMethod_name = jsonObject.getString(LocationSuccess_CallbackMethod_Name);
            locationFailed_callbackMethod_name = jsonObject.getString(LocationFailed_CallbackMethod_Name);
            locationDisable_callbackMethod_name = jsonObject.getString(LocationDisable_CallbackMethod_Name);
            doubleClick_appExit_string = jsonObject.getString(DoubleClick_AppExit_String);
            doubleClick_execute_url = jsonObject.getString(DoubleClick_Execute_Url);

            isbridge = jsonObject.getBoolean(isBridge);
            isalert = jsonObject.getBoolean(isAlert);
            iscustomAlert = jsonObject.getBoolean(isCustomAlert);
            islocationService = jsonObject.getBoolean(isLocationService);
            isdoubleClickAppExit = jsonObject.getBoolean(isDoubleClickAppExit);
            isshouldOverrideUrl = jsonObject.getBoolean(isShouldOverrideUrl);

            iswideViewPort = jsonObject.getBoolean(isWideViewPort);
            isdomStorage = jsonObject.getBoolean(isDomStorage);
            isjavaScriptEnabled = jsonObject.getBoolean(isJavaScriptEnabled);
            isbuiltInZoomControls = jsonObject.getBoolean(isBuiltInZoomControls);
            isdisplayZoomControls = jsonObject.getBoolean(isDisplayZoomControls);
            issupportZoom = jsonObject.getBoolean(isSupportZoom);

        }catch (JSONException ex){
            throw new JSONException("Json Parsing Error by Marty :::"  +ex.getMessage());
        }


    }
     public static Manager getInstance(AssetManager manager,String asset_file) throws IOException,JSONException{
         Manager man = new Manager(manager,asset_file);
         return man;
    }

}
