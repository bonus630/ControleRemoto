package br.corp.bonus630.controleremoto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;

import android.os.AsyncTask;
import android.util.Log;



public class HttpRequestAsyncTask extends AsyncTask<String, Void, String>
{
	private String resultado = null;
	//private String server = "http://192.168.25.14:13579/";
	private Boolean findServer = false;
	
	
	protected String onPostExecute()
	{
		super.onPostExecute(resultado);
			return resultado;
		
	}
	
	@Override
	protected String doInBackground(String... params) {
		
		try {
			getPage(Server.url+params[0]);
		} catch (MalformedURLException e) {
			Log.e("Erro",e.getMessage()+"mal");
		} catch (IOException e) {
			Log.e("Erro",e.getMessage()+"IO");
		}
		
		return resultado;
	}

	public void getPage(String url) throws MalformedURLException, IOException {
	    //HttpURLConnection con = (HttpURLConnection) new URL("http://192.168.25.14:13579").openConnection();
	    HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
	   	
	    if(findServer)
	    	con.setConnectTimeout(40);
	    else
	    	con.setConnectTimeout(400);
	   	con.connect();
	   	
		if (con.getResponseCode() == HttpURLConnection.HTTP_OK) 
			    resultado =  inputStreamToString(con.getInputStream());
		
	}

	public Boolean getFindServer() {
		return findServer;
	}

	public void setFindServer(Boolean findServer) {
		this.findServer = findServer;
	}

	private String inputStreamToString(InputStream in) throws IOException {
	    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
	    StringBuilder stringBuilder = new StringBuilder();
	    String line = null;

	    while ((line = bufferedReader.readLine()) != null) {
	        stringBuilder.append(line + "\n");
	    }

	    bufferedReader.close();
	    return stringBuilder.toString();
	}

	
	
}
