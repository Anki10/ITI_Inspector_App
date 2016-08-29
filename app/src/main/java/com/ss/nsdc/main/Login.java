package com.ss.nsdc.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ss.nsdc.R;
import com.ss.nsdc.dao.Institute;
import com.ss.nsdc.dao.NSDCDBController;
import com.ss.nsdc.dao.User;
import com.ss.nsdc.utility.UtilityService;

public class Login extends Activity {

	Button loginButton;
	EditText username;
	EditText password;
	final Context context = this;
	ProgressDialog ringProgressDialog;
	public static final String access_token="bnNkYzd0ZWNoaWVzYXBp";
	UtilityService utilityService=UtilityService.getInstance();
	User user;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		username=(EditText)findViewById(R.id.input_name);
		password=(EditText)findViewById(R.id.input_password);

		loginButton = (Button) findViewById(R.id.btn_signup);
		getLoggedUserFromDB();
		loginButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if(user==null)
				{
					user=new User();
					user.setUsername(username.getText().toString());
					user.setPassword(password.getText().toString());
					validateUserOnline(user);
				}
				else
				{
					login_successfull();
				}

			}
		});
	}
	public void login_successfull()
	{
		NSDCDBController sqlite=new NSDCDBController(context);
		List<Institute> instituteList=sqlite.getInstituteList();
		if(instituteList!=null && instituteList.size()>0){
			Intent intent = new Intent(context, InstituteActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
			startActivity(intent);
		}
		else
		{
			new UpdateInstituteData().execute(user);
		}

	}
	private void getLoggedUserFromDB()
	{
		NSDCDBController sqllite = new NSDCDBController(context);
		user=sqllite.getLoggedUser();
		sqllite.close();

		//########## Setting logged USER
		if(user!=null)
		{
			password.setText(user.getPassword());
			username.setText(user.getUsername());

			password.setEnabled(false);
			username.setEnabled(false);
		}


	}
	private void validateUserOnline(User newUser)
	{
		if(utilityService.getConnectivityStatus(context))
		{

			new ExecuteLoginOperation().execute(newUser);
		}
		else
		{
			utilityService.showIntenetSettingsAlert(context);
			user = null;
		}

	}
	class ExecuteLoginOperation extends AsyncTask<User, Integer, JSONObject>
	{
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			ringProgressDialog = ProgressDialog.show(new ContextThemeWrapper(context, android.R.style.Theme_Holo), null,"Logging from Server..", true);
			ringProgressDialog.setCancelable(false);
			ringProgressDialog.setCanceledOnTouchOutside(false);
		}

		@Override
		protected JSONObject doInBackground(User... user) {
			// TODO Auto-generated method stub
			JSONObject response=validate_login(user);
			return response;
		}


		@Override
		protected void onPostExecute(JSONObject result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);

			if(result!=null && result.has("responsecode"))
			{
				try {
					if(Integer.valueOf(result.get("responsecode").toString())==2)
					{
						int _userId = Integer.parseInt(result.getString("userid"));

						NSDCDBController sqllite = new NSDCDBController(context);
						sqllite.addLoggedUser(user.getUsername(), user.getPassword(),_userId);
						user=sqllite.getLoggedUser();
						sqllite.close();
						ringProgressDialog.cancel();
						login_successfull();
					}
					else
					{
						user = null;
						ringProgressDialog.cancel();
						Toast.makeText(context,"Invalid Username/Password", Toast.LENGTH_LONG).show();
					}
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}else{
				user = null;
				ringProgressDialog.cancel();
			}
		}
	}

	public JSONObject validate_login(User...user)
	{
		String response="";
		JSONObject response_json=null;
		try{
			URL url = new URL("http://nsdc.qci.org.in/api/caaf/inspectorlogin.php");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setReadTimeout(10000);
			conn.setConnectTimeout(15000);
			conn.setRequestMethod("POST");
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			String str="Username="+user[0].getUsername()+"&Password="+user[0].getPassword()+"&Token=bnNkYzd0ZWNoaWVzYXBp";
			byte[] outputInBytes = str.getBytes("UTF-8");
			conn.getOutputStream().write(outputInBytes);
			conn.connect();
			int responsecode=conn.getResponseCode();
			if (responsecode == HttpURLConnection.HTTP_OK) {
				String line;
				BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
				while ((line=br.readLine()) != null) {
					response+=line;
				}
			}else if(responsecode == HttpURLConnection.HTTP_CLIENT_TIMEOUT)
			{
				response_json=new JSONObject();
				response_json.put("error", "Connection to server lost..");
			}

			response_json=new JSONObject(response);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return response_json;
	}
	public JSONObject updateInstituteList(User...user)
	{
		String response="";
		JSONObject response_json=null;
		try{
			URL url = new URL("http://nsdc.qci.org.in/api/caaf/AllotedApplication.php");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setReadTimeout(10000);
			conn.setConnectTimeout(15000);
			conn.setRequestMethod("POST");
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			String str="Userid="+user[0].getUserid()+"&Token=bnNkYzd0ZWNoaWVzYXBp";
			byte[] outputInBytes = str.getBytes("UTF-8");
			conn.getOutputStream().write(outputInBytes);
			conn.connect();
			int responsecode=conn.getResponseCode();
			if (responsecode == HttpURLConnection.HTTP_OK) {
				String line;
				BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
				while ((line=br.readLine()) != null) {
					response+=line;
				}
			}
			response_json=new JSONObject(response);
		}catch(Exception e)
		{
			e.printStackTrace();
			return response_json;
		}
		return response_json;
	}

	class UpdateInstituteData extends AsyncTask<User, Integer, JSONObject>{

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			ringProgressDialog = ProgressDialog.show(new ContextThemeWrapper(context, android.R.style.Theme_Holo), null,"Logging from Server..", true);
			ringProgressDialog.setCancelable(false);
			ringProgressDialog.setCanceledOnTouchOutside(false);
		}
		@Override
		protected JSONObject doInBackground(User... user) {
			JSONObject response=updateInstituteList(user);
			return response;

		}
		@Override
		protected void onPostExecute(JSONObject result) {

			if(result!=null && result.has("responsecode"))
			{
				try {
					if(Integer.valueOf(result.get("responsecode").toString())==2)
					{
						NSDCDBController sqllite = new NSDCDBController(context);

						boolean add_status=sqllite.addInstituteData(result);
						sqllite.close();	
						ringProgressDialog.cancel();
						if(add_status){

							Toast.makeText(context,"Allocation Updated Successfully", Toast.LENGTH_LONG).show();
							login_successfull();
						}
					}else{
						user = null;
						Toast.makeText(context,"No Data Found..", Toast.LENGTH_LONG).show();
					}
				}catch(Exception e)
				{
					Log.e("Login","Allocation Data Failed..");
				}
			}else{
				user = null;
				ringProgressDialog.cancel();				
			}
		}
	}
}