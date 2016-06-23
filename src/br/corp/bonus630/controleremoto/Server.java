package br.corp.bonus630.controleremoto;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

public class Server {

	int ip;
	int port = 13579;
	HttpRequestAsyncTask teste;
	public static String url;
	private ArrayList<String> listaIPs;
	
	
	public Server(Context contexto)
	{
		WifiManager wifiManager = (WifiManager) contexto.getSystemService(Context.WIFI_SERVICE);
		WifiInfo wifiInfo = wifiManager.getConnectionInfo();
		int ip = wifiInfo.getIpAddress();
		this.ip = ip;
	  
	}
	
	
	
	public ArrayList<String> getIPList()
	{
		ArrayList<String> urlList= new ArrayList<String>();
		for(int i = 0;i<256;i++)
		{
			String strIP = String.format("http://%d.%d.%d.%d:%d/",  
		        (ip & 0xff),  
		        (ip >> 8 & 0xff),  
		        (ip >> 16 & 0xff),  
		        (i),port);
			//Log.e("ip",String.valueOf(ip & 0xff));
			
			urlList.add(strIP);
		
		}
		return urlList;
	}
	//public class ServerDetail()
	//{
		
		
	//}
}
