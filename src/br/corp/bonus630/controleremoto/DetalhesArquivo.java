package br.corp.bonus630.controleremoto;

public class DetalhesArquivo {
	private String name;
	private String cmd;
	private String type;
	private String size;
	public void setName(String name) {
		this.name = name;
	}
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getName() {
		return name;
	}
	public String getCmd() {
		return cmd;
	}
	public String getType() {
		return type;
	}
	public String getSize() {
		return size;
	}
	public DetalhesArquivo(String name, String cmd, String type, String size) {
		
		this.name = name;
		this.cmd = cmd;
		this.type = type;
		this.size = size;
	}
	public DetalhesArquivo(String name, String cmd) {
		
		this.name = name;
		this.cmd = cmd;
		
	}
}
