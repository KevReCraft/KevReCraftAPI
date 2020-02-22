package de.kevrecraft.api.type;

public enum MySQLType {
	PLAYERDATA("PlayerData"),
	PERMISSIONS("Permission");
	
	private String name;
	
	MySQLType(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	// STATIC -------------------------------------------------
	public static MySQLType[] getAll() {
		MySQLType[] list = { 
				MySQLType.PLAYERDATA,
				MySQLType.PERMISSIONS
		};
		return list;
	}
}
