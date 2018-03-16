package com.ss.nsdc.utility;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;
import android.util.Base64OutputStream;
import android.util.Log;

import com.google.gson.Gson;
import com.ss.nsdc.entity.VideoObjectModel;
import com.ss.nsdc.entity.VideoSectionModel;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * This class is intended to have methods, which uses java IO APIs.
 * If any method uses other APIs, that should be added to any other more suitable Utility class.
 * Even android.util.Log is not used in this class.
 */
public class DataUtils {

    /**
     * Read string data from a file located in "assets" folder
     * @param context
     * @param fileAssetPath -  path of asset file to read
     * @return
     */
    public static String readFromAssets(Context context, String fileAssetPath) {

        StringBuilder buf = new StringBuilder("");
        try {
            String str;
            InputStream json = context.getAssets().open(fileAssetPath);//("book/contents.json");
            BufferedReader in =
                    new BufferedReader(new InputStreamReader(json, "UTF-8"));
            while ((str = in.readLine()) != null) {
                buf.append(str);
            }


            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buf.toString();
    }

    /**
     * @param pInputStream
     * @return
     */
    public static byte[] convertStreamToBytes(InputStream pInputStream) {
        int read = 0;
        byte[] data = new byte[1024];    /** data will be read in chunks */
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            while ((read = pInputStream.read(data)) != -1) {
                baos.write(data, 0, read);
            }
            return baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param pFilePath
     * @return
     */
    public static FileInputStream getFileInputStream(String pFilePath) {
        try {
            return new FileInputStream(new File(pFilePath));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param pFilePath
     * @return
     */
    public static byte[] getFileData(String pFilePath) {
        try {
            return convertStreamToBytes(getFileInputStream(pFilePath));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    public static void saveHashMapInShared(Context conetxt, HashMap<String, VideoObjectModel> inputMap)
    {

        SharedPreferences pSharedPref = conetxt.getSharedPreferences("MyVariables", Context.MODE_PRIVATE);
        if (pSharedPref != null){
            Gson gson = new Gson();
            String hashMapString = gson.toJson(inputMap);

         //   JSONObject jsonObject = new JSONObject(inputMap);
        ///    String jsonString = jsonObject.toString();
            SharedPreferences.Editor editor = pSharedPref.edit();
            editor.remove("My_map").commit();
            editor.putString("My_map", hashMapString);
            editor.commit();
        }

    }

    public static HashMap<String, VideoObjectModel> loadMap(Context context){
        HashMap<String, VideoObjectModel> outputMap = new HashMap<>();
        SharedPreferences pSharedPref = context.getSharedPreferences("MyVariables", Context.MODE_PRIVATE);
        try{
            if (pSharedPref != null){
                String jsonString = pSharedPref.getString("My_map","");
                JSONObject jsonObject = new JSONObject(jsonString);
                Iterator<String> keysItr = jsonObject.keys();
                while(keysItr.hasNext()) {
                   String key = keysItr.next();
                   String hashMapString= jsonObject.optString(key);

                    Gson gson = new Gson();
                    VideoObjectModel videoObjectModel= gson.fromJson(hashMapString,VideoObjectModel.class);

                    //   VideoSectionModel value = (VideoSectionModel) jsonObject.get(key);
                  //  VideoObjectModel value1 = (VideoObjectModel) jsonObject.get(key);
                    outputMap.put(key, videoObjectModel);
                }

                //loadMap = HashMap(loadMap);
                Log.d("","");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return outputMap;
    }


    public static void deleteSharedPref(Context context)
    {
        SharedPreferences pSharedPref = context.getSharedPreferences("MyVariables", Context.MODE_PRIVATE);
        pSharedPref.edit().remove("My_map").commit();


    }


    // Converting File to Base64.encode String type using Method
    public static String getStringFile(File f) {
        InputStream inputStream = null;
        String encodedFile= "", lastVal;
        try {
            inputStream = new FileInputStream(f.getAbsolutePath());

            byte[] buffer = new byte[10240];//specify the size to allow
            int bytesRead;
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            Base64OutputStream output64 = new Base64OutputStream(output, Base64.DEFAULT);

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                output64.write(buffer, 0, bytesRead);
            }
            output64.close();
            encodedFile =  output.toString();
        }
        catch (Exception e1 ) {
            e1.printStackTrace();
        }
        lastVal = encodedFile;
        return lastVal;
    }


}
