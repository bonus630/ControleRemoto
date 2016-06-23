package br.corp.bonus630.controleremoto;



import java.util.Timer;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.widget.SeekBar;
import android.widget.TextView;



public class MainActivity extends Activity{
	
	//public final String server = "http://192.168.25.14:13579/";
	
	Button btn_play,btn_stop,btn_legenda,btn_fullscreen,btn_mute,btn_voltar,btn_avancar,btn_audio,btn_config; 
	TextView tv_position_string,tv_duration_string,tv_file,tv_state;
	
	HttpRequestAsyncTask teste;
	ProgressDialog pd =null;
	SeekBar progresso,volume;       
	Timer statusTimer;
	int timerInterval = 500;
	StatusUpdateTask updateTask;
	Handler handler;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		/*Configuration configuration = getResources().getConfiguration();

		if(configuration.orientation == Configuration.ORIENTATION_LANDSCAPE)
		{
			Log.e("status", "land");
			setContentView(R.layout.activity_main2);
		}
		if(configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
		{
			Log.e("status", "port");
			setContentView(R.layout.activity_main);
		}*/
		Log.e("status","onCreate");
		 pd = new ProgressDialog(this, ProgressDialog.BUTTON_NEGATIVE);
		 
		Server.url = "http://192.168.25.14:13579/";
		
		btn_play = (Button) findViewById(R.id.play);
		btn_stop = (Button) findViewById(R.id.stop);
		btn_mute = (Button) findViewById(R.id.mute);
		btn_fullscreen = (Button) findViewById(R.id.fullscreen);
		btn_legenda = (Button) findViewById(R.id.legenda);
		btn_voltar = (Button) findViewById(R.id.voltar);
		btn_avancar = (Button) findViewById(R.id.avancar);
		btn_audio = (Button) findViewById(R.id.audio);
		btn_config = (Button) findViewById(R.id.config);
		tv_position_string = (TextView) findViewById(R.id.position_string);
		tv_duration_string = (TextView) findViewById(R.id.duration_string);
		tv_state = (TextView) findViewById(R.id.state_string);
		//tv_status = (TextView) findViewById(R.id.textView2);
		progresso = (SeekBar) findViewById(R.id.progresso);
		volume = (SeekBar) findViewById(R.id.volume);
		
		tv_file = (TextView) findViewById(R.id.file);
		handler = new Handler();
		//updateTask = new StatusUpdateTask(handler, this);
		progresso.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				if(fromUser){
					int porcent = (100 * progress)/seekBar.getMax();
					//Log.e("user",String.valueOf(porcent));
					teste = new HttpRequestAsyncTask();
					teste.execute("command.html?wm_command=-1&percent="+String.valueOf(porcent));
				}
				
				
			}
		});
		
		volume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				if(fromUser){
					//int porcent = (100 * progress)/seekBar.getMax();
					//Log.e("user",String.valueOf(porcent));
					teste = new HttpRequestAsyncTask();
					teste.execute("command.html?wm_command=-2&volume="+String.valueOf(progress));
				}
				
				
			}
		});
		tv_file.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(), ListaArquivos.class);
				startActivity(i);
			}
		});
		
	
		btn_stop.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				teste = new HttpRequestAsyncTask();
				teste.execute("command.html?wm_command=890");
				
			}
		});
		btn_mute.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				teste = new HttpRequestAsyncTask();
				teste.execute("command.html?wm_command=909");
				
			}
		});
	btn_avancar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				teste = new HttpRequestAsyncTask();
				teste.execute("command.html?wm_command=920");
				
			}
		});
		btn_voltar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				teste = new HttpRequestAsyncTask();
				teste.execute("command.html?wm_command=921");
				
			}
		});
		btn_fullscreen.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				teste = new HttpRequestAsyncTask();
				teste.execute("command.html?wm_command=830");
				
			}
		});
		
		btn_legenda.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				teste = new HttpRequestAsyncTask();
				teste.execute("command.html?wm_command=954");
				
			}
		});
		
		btn_audio.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				teste = new HttpRequestAsyncTask();
				teste.execute("command.html?wm_command=952");
				
			}
		});
		
		btn_play.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				runClickButton();
				
			}
		});
		
		btn_config.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(),Config.class);
				
				startActivity(i);
			}
		});
	}
	
	protected void runClickButton()
	{
		if(btn_play.getText()==""){
			teste = new HttpRequestAsyncTask();
			teste.execute("command.html?wm_command=887");
		}else
		{
			teste = new HttpRequestAsyncTask();
			teste.execute("command.html?wm_command=888");
		}

	}
	
	

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
		Log.e("status","onConfigurationChange");
		
		
	}

	@Override
	protected void onStart() {
		super.onStart();
		
		Log.e("status","onStart");
		createNewTimer();
		//statusTimer = new Timer();
		//statusTimer.schedule(updateTask, 0, timerInterval);
	}
	//@Override
	//protected void onResume(){
	///	super.onResume();
	//	Log.e("status","onResume");
		//if(statusTimer.)
		//statusTimer.schedule(updateTask, 0, timerInterval);
//	}
	@Override
	protected void onStop() {
		super.onStop();
		Log.e("status","onStop");
		statusTimer.cancel();
		statusTimer.purge();
	}
//	protected void onPause() {
//		super.onPause();
//		Log.e("status","onPause");
		//statusTimer.cancel();
		//statusTimer.purge();
//	}
	public void update(Variables result){
		
		//updateTimer();timerInterval = 1000;
		//Log.e("time",timerInterval+"");
		pd.dismiss();
		if(result.getState()!=Variables.State.NAN){
			
			if(result.getState()==Variables.State.PAUSED||result.getState()==Variables.State.STOPED)
			{
				btn_play.setText("");
				btn_play.setBackgroundResource(R.drawable.media_controls_play_icon);//btn_play.setText("Play");
			}
			else
			{
				btn_play.setText(" ");
				btn_play.setBackgroundResource(R.drawable.media_controls_pause_icon);
			}
			tv_position_string.setText(result.getPositionstring());
			tv_duration_string.setText(result.getDurationstring());
			tv_state.setText(result.getStatestring());
			//tv_testar.setText(result.getState());
			progresso.setMax(Integer.parseInt(result.getDuration()));
			progresso.setProgress(Integer.parseInt(result.getPosition()));
			
			
		}
		int volumeLevel = Integer.parseInt(result.getVolumelevel());
			volume.setProgress(volumeLevel);
			if(volumeLevel >50)
			{
				btn_mute.setBackgroundResource(R.drawable.volume_controls_volume_up_icon);
			}
			if(volumeLevel <= 50 && volumeLevel >0)
			{
				btn_mute.setBackgroundResource(R.drawable.volume_controls_volume_down_icon);
			}
			if(volumeLevel == 0 || result.getMuted().equals("1"))
			{
				btn_mute.setBackgroundResource(R.drawable.volume_controls_mute_icon);
			}
		tv_file.setText(result.getFilepath());
	}
	public void update(String msg)
	{
		//Se não existe nenhum server e o usuario não quer digitar um, mostre o pd
		//updateTimer();timerInterval = 200;
		//  Log.e("time",timerInterval+"");
		if(!pd.isShowing())
			pd = ProgressDialog.show(this, "Procurando MPC aberto!", msg);
		else
			pd.setMessage(msg);
		
	}
	public void createNewTimer()
	{
		statusTimer = new Timer();
		updateTask= new StatusUpdateTask(handler, this,false);
		statusTimer.schedule(updateTask, 0, timerInterval);
	}
	public void updateTimer()
	{
		if(StatusUpdateTask.serverFound)
		{
			if(timerInterval ==200)
			{
				statusTimer.cancel();
				statusTimer.purge();
				
				createNewTimer();
			}
		}
		if(!StatusUpdateTask.serverFound)
		{
			if(timerInterval ==1000)
			{
				statusTimer.cancel();
				statusTimer.purge();
				
				createNewTimer();
			}
		}
	
	}
}
