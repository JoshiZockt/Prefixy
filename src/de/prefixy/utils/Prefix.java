package de.prefixy.utils;


public class Prefix {
	
	private int id;
	private String name;
	private String colorCode;
	private boolean bold;
	
	public Prefix(String name, int id, String colorCode, boolean bold) {
		this.id = id;
		this.name = name;
		this.colorCode = colorCode;
		this.bold = bold;
		
		new Database().putPrefix(name, colorCode, bold, id);
		
	}
	
	public String getName() {
		return name;
	}
	
	public int getID() {
		return id;
	}
	
	public String getColorCode() {
		return "§" + colorCode;
	}
	
	public boolean isBold() {
		return bold;
	}

}
