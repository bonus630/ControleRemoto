package br.corp.bonus630.controleremoto;


import java.net.SocketTimeoutException;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;

import android.os.Handler;
import android.util.Log;


public class StatusUpdateTask extends TimerTask{

	Handler handler;
	MainActivity ref;
	HttpRequestAsyncTask teste;
	public  String resultTest = "";
	Variables variables;
	int IndexIPRange = 0;
	Server server;
	public static boolean serverFound = false;
	
	
	
	public StatusUpdateTask(Handler handler,MainActivity ref,Boolean findServer){
		super();
		this.handler = handler;
		this.ref = ref;
		variables = new Variables();
		server = new Server(ref);
	}
	
	@Override
	public void run() {
		handler.post(new Runnable() {
			
			@Override
			public void run() {
				
				
				teste = new HttpRequestAsyncTask();
				//teste.execute("variables.html");
				teste.execute("status.html");
				try {
					String resp = teste.get();
				  if(resp !=null)
				  {
					  IndexIPRange =0;
					  //Log.e("erro","null");
					  variables.updateVariables(resp);
					  ref.update(variables);
					  serverFound = true;
					  //this.wait(600);
					  //encontra novo server
				  }
				  else
				  {
					/*  HttpRequestAsyncTask[] a = new HttpRequestAsyncTask[255];
					while(!serverFound)
					{
						a[IndexIPRange] = new HttpRequestAsyncTask();
						a[IndexIPRange].setFindServer(true);
						a[IndexIPRange].execute("status.html");
						 // 
					//		teste = new HttpRequestAsyncTask();
							//teste.execute("variables.html");
						//	teste.execute("status.html");
						  //ref.update(Server.url);
						String resps = teste.get();
						  if(resp !=null)
						  {
							  serverFound = true;
							  Server.url = server.getIPList().get(IndexIPRange);
						  }
						  else{
							  serverFound = false;
						  }
						  IndexIPRange++;
							if(IndexIPRange==255)
								IndexIPRange =0;
					}
*/					  Server.url = server.getIPList().get(IndexIPRange);
					  ref.update(Server.url);
					  serverFound = false;
					  IndexIPRange++;
						if(IndexIPRange==255)
							IndexIPRange =0;
				  }
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
			}
		});
		
	}

}
