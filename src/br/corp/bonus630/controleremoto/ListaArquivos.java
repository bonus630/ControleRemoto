package br.corp.bonus630.controleremoto;


import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class ListaArquivos extends Activity {

	HttpRequestAsyncTask httpReq;
	ListAdapter listAdapter;
	ListView listaArquivos;
	
	ArrayList<DetalhesArquivo> arrayArquivos = new ArrayList<DetalhesArquivo>();
	AdapterDetalhesArquivo adapterDetalhesArquivo;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista_arquivos);
		listaArquivos = (ListView) findViewById(R.id.lista);
		 
		
		populaLista("browser.html");
		adapterDetalhesArquivo = new AdapterDetalhesArquivo(this);
		
		listaArquivos.setAdapter(adapterDetalhesArquivo);
		
		listaArquivos.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
				//Log.e("type",arg2+" - "+arg3);
				
				adapterDetalhesArquivo.notifyDataSetChanged();
				
				
				
				//Log.e("type","Entrada: "+arrayArquivos.get((int) arg3).getType()+" - comparação: "+arrayArquivos.get((int) arg3).getType().equals("Directory"));
			
				if(!arrayArquivos.get(arg2).getType().equals("Directory")){
				finish();	
					//Intent i = new Intent(getApplicationContext(), MainActivity.class);
					// startActivity(i);
					 
				}
				populaLista(arrayArquivos.get(arg2).getCmd());
				
				
			}
		});
		
	}
	
	private void populaLista(String cmd)
	{
		arrayArquivos.clear();
		httpReq = new HttpRequestAsyncTask();
		httpReq.execute(cmd);
		String strFiles = "";
		try {
			strFiles = httpReq.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Pattern cmdDir = Pattern.compile(
				"<td( class=\"dirname\")?><a href=\"/(.+)\">(.+)</a></td>\n"+
						 "<td( class=\"dirtype\">|><span class=\"nobr\">)(.+)<(</span><)?/td>\n"
						+ "<td( class=\"dirsize\">|><span class=\"nobr\">)(.+)<(</span><)?/td>\n"
				,Pattern.MULTILINE);
		
		Matcher m = cmdDir.matcher(strFiles);
	
		while(m.find())
		{
			String type = m.group(5);
			if(type.contains("</span>")){
				type = type.substring(0, type.indexOf("</span>"));
			}
			String size = m.group(8);
			if(size.contains("</span>")){
				size = size.substring(0, size.indexOf("</span>"));
			}
			size = convertBytes(size);
			arrayArquivos.add(new DetalhesArquivo(m.group(3),m.group(2),type,size));
			//Log.e("Reg",(m.group(3)+" - "+m.group(2)+" - "+type+" - "+size));
		}
		
	}
	private String convertBytes(String tamanho) {
		Pattern p = Pattern.compile("[0-9]{1,}");
		Matcher m = p.matcher(tamanho);
		
		long bytes = 0;
		
		while(m.find())
		{
			bytes = Long.decode(m.group());
		}
		
		int unit =1000 ;
	    if (bytes < unit) return bytes + " B";
	    int exp = (int) (Math.log(bytes) / Math.log(unit));
	    String pre = ( "kMGTPE").charAt(exp-1)+"";
	    return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
	}

	class AdapterDetalhesArquivo extends ArrayAdapter<DetalhesArquivo>
	{
		Context context;
		
		public AdapterDetalhesArquivo(Context context) {
			super(context, R.layout.item_lista,arrayArquivos);
			this.context = context;
		}
		
		public View getView(int position,View view,ViewGroup parent)
		{
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			View item = inflater.inflate(R.layout.item_lista, null);
			
			TextView titulo = (TextView) item.findViewById(R.id.item_titulo);
			titulo.setText(arrayArquivos.get(position).getName());
			
			TextView type = (TextView) item.findViewById(R.id.item_type);
			String tipo = arrayArquivos.get(position).getType();
			type.setText(tipo);
			
			TextView size = (TextView) item.findViewById(R.id.item_size);
			size.setText(arrayArquivos.get(position).getSize());
			
			ImageView imagem = (ImageView) item.findViewById(R.id.img_icone);
			if(tipo.equals("Directory"))
				imagem.setBackgroundResource(R.drawable.folder_icon);
			if(tipo.equals("MP4")||tipo.equals("Matroska")||tipo.equals("AVI"))
				imagem.setBackgroundResource(R.drawable.photo_video_film_icon);
			if(tipo.equals("MP3"))
				imagem.setBackgroundResource(R.drawable.music_note_icon);
			if(tipo.equals("Outros")||tipo.equals("&nbsp;"))
				imagem.setBackgroundResource(R.drawable.archive2_icon);
			return item;
		}
}

}

