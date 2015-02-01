package com.fid.facerec;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;



public class Settings extends Activity {

	EditText ipaddressText;
	EditText portText;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     
      
        setContentView(R.layout.settings);
        
        
       
        
    }
	public void getHome(View view){
		
		
		Intent intent = new Intent(this, MainActivity.class);
		ipaddressText = (EditText)findViewById(R.id.ipAddressValue);
		portText = (EditText)findViewById(R.id.portNumberValue);
		String ipaddress = ipaddressText.getText().toString();
		String port = portText.getText().toString();
		Log.e("IP", ipaddress);
		Log.e("PORT", port);
		setResult(RESULT_OK, intent);
		
		Bundle b = new Bundle();
		b.putString("ip", ipaddressText.getText().toString());
		b.putString("port", portText.getText().toString());
		
		intent.putExtras(b);
		
	//	intent.putExtra(MainActivity.IP, ipaddress);
	//	intent.putExtra(MainActivity.PORT, port);	
		
		//finish();
		
		startActivity(intent);
	} 
}
