package br.corp.bonus630.controleremoto;


import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;



public class Config extends Activity {

	EditText et_server;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_config);
		
		et_server = (EditText) findViewById(R.id.et_server);
		
		et_server.setText(Server.url.toString());
		
		
	}



	

}
