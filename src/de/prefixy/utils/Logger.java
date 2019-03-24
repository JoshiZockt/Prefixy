package de.prefixy.utils;

public class Logger {
	
	public Logger() {}
	
	public void sendError(String message) {
		System.err.println("[ERROR] " + message);
	}
	
	public void sendInfo(String message) {
		System.out.println("[INFO] " + message);
	}
	
	public void sendWarn(String message) {
		System.out.println("[WARNING] " + message);
	}

}
