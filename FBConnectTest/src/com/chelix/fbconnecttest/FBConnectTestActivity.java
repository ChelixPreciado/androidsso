package com.chelix.fbconnecttest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.Facebook.DialogListener;
import com.facebook.android.FacebookError;
import com.ironbit.fbconnecttest.R;

public class FBConnectTestActivity extends Activity {
	
	Facebook facebook = new Facebook("140118786125876");
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        facebook.authorize(this, new DialogListener() {
            
        	@Override
            public void onComplete(Bundle values) {
            	Log.i("SSO Test", "JSON de Respuesta: " + values);
            }

            @Override
            public void onFacebookError(FacebookError error) {
            	Log.i("SSO Test", "A facebook error have been ocurred...");
            	Log.i("SSO Test", error.toString());
            }

            @Override
            public void onError(DialogError e) {
            	Log.i("SSO Test", "A dialog error have been ocurred...");
            	Log.i("SSO Test", e.toString());
            }

            @Override
            public void onCancel() {
            	Log.i("SSO Test", "Action Canceled...");
            }
        });
    }
    
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        facebook.authorizeCallback(requestCode, resultCode, data);
    }
}