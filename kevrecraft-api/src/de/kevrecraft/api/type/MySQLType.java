package de.kevrecraft.api.type;

import java.util.ArrayList;

public enum MySQLType {
	
	PlayerData;
	
	public static ArrayList<MySQLType> getTypeList() {
		ArrayList<MySQLType> list = new ArrayList<MySQLType>();
		
		list.add(MySQLType.PlayerData);
		
		return list;
	}
}
