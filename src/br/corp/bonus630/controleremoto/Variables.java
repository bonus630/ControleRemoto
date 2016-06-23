package br.corp.bonus630.controleremoto;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Variables {

	private String variables = "";
	
	//Pattern[] exp;
	Pattern exp;
	public Variables()
	{
		exp = Pattern.compile("OnStatus\\(\"(.+)\", \"([a-zA-Z/]{2,14})\", ([0-9]{0,}), \"([0-9]{2}:[0-9]{2}:[0-9]{2})\", ([0-9]{0,}), \"([0-9]{2}:[0-9]{2}:[0-9]{2})\", (0|1), ([0-9]{0,3}), \"(.+)\"\\)");
	
	}
	
	public void updateVariables(String variables)
	{
		Matcher m = exp.matcher(variables);
		while(m.find())
		{
			this.fileName = m.group(1);
			this.state = m.group(2);
			this.position = m.group(3);
			this.positionstring = m.group(4);
			this.duration = m.group(5);
			this.durationstring =m.group(6);
			this.muted = m.group(7);
			this.volumelevel = m.group(8);
			this.filepath = m.group(9);
			this.statestring = this.state;
		}
	}
	private String fileName;	
     private String filepatharg;
     private String filepath;
     private String filedirarg;
     private String filedir;
     private String state;
     private String statestring;
     private String position;
     private String positionstring;
     private String duration;
     private String durationstring;
     private String volumelevel;
     private String muted;
     private String playbackrate;
     private String reloadtime;
	
	public String getFilepath()
	{
		return this.filepath;
	}
	
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilepatharg() {
		return filepatharg;
	}

	public String getFiledirarg() {
		return filedirarg;
	}

	public String getFiledir() {
		return filedir;
	}

	public int getState() {
		if(this.state.equals("Pausado"))
			return Variables.State.PAUSED;
		if(this.state.equals("Reproduzindo"))
			return Variables.State.PLAYING;
		if(this.state.equals("Parado"))
			return Variables.State.STOPED;
		return Variables.State.NAN;
	}

	public String getStatestring() {
		return statestring;
	}

	public String getPosition() {
		return position;
	}

	public String getPositionstring() {
		return positionstring;
	}

	public String getDuration() {
		return duration;
	}

	public String getDurationstring() {
		return durationstring;
	}

	public String getVolumelevel() {
		return volumelevel;
	}

	public String getMuted() {
		return muted;
	}

	public String getPlaybackrate() {
		return playbackrate;
	}

	public String getReloadtime() {
		return reloadtime;
	}
	public class State
	{
		
		public final static int STOPED = 0;
		public final static int PAUSED = 1;
		public final static int PLAYING = 2;
		public final static int NAN = 3;
		
	}
}


