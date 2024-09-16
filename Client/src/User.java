import java.util.*;
import java.io.*;

public class User implements IUser, Serializable{
	private String username;
	private String password;
	

	public User(String user, String pass){
		username	= user;
		password = pass;
	}

	public String getUser(){
		return username;
	}

	public String getPassword(){
		return password;
	}

	public boolean checkPassword(String pass){
		if(password.equals(pass)){
			return true;
		}
		return false;
	}


}
