package de.kevrecraft.api;

public class MySQLConfigFile {
	private static FileWriter fw = new FileWriter("MySQL//", "config");
	
	private String path;
	
	public MySQLConfigFile(String path) {
		this.path = path;
	}
	
	// Private -----------------------------------------------------
	private String getHostPath() {
		return this.path + ".host";
	}
	
	private String getPortPath() {
		return this.path + ".port";
	}
	
	private String getDatabasePath() {
		return this.path + ".database";
	}
	
	private String getUsernamePath() {
		return this.path + ".username";
	}
	
	private String getPasswordPath() {
		return this.path + ".password";
	}
	
	// Public ------------------------------------------------------
	public String getHost() {
		return fw.getString(getHostPath());
	}
	
	public String getPort( ) {
		return fw.getString(getPortPath());
	}
	
	public String getDatabase() {
		return fw.getString(getDatabasePath());
	}
	
	public String getUsername() {
		return fw.getString(getUsernamePath());
	}
	
	public String getPassword() {
		return fw.getString(getPasswordPath());
	}
	
	public void createValuesIfNotExist() {
		if(fw.getString(getHostPath()) == null || fw.getString(getHostPath()) == "") {
			fw.setValue(getHostPath(), "localhost");
			fw.setValue(getPortPath(), "3306");
			fw.setValue(getDatabasePath(), "database");
			fw.setValue(getUsernamePath(), "username");
			fw.setValue(getPortPath(), "password");
			
			fw.save();
		}
	}
}
