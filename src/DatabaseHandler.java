import java.util.*;
import java.io.*;

public class DatabaseHandler{
	//private static final String DATABASE_FILE = System.getenv("DATABASE");
	private static final String DATABASE_FILE = "database.dat";
	private static ArrayList<User> users;

	public DatabaseHandler(){
		users = new ArrayList<User>();
		loadDatabase();
	}

	public void addUser(User user){
		users.add(user);
		saveDatabase();
	}

	public boolean validateUser(User user){
		for(User u : users){
			if(u.getUser().equals(user.getUser())){
				if(u.checkPassword(user.getPassword())){
					return true;
				}
			}
		}
		return false;
	}

	private void loadDatabase(){
		try{
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(DATABASE_FILE));
			users = (ArrayList<User>) in.readObject();
			in.close();
		}catch(Exception e){System.out.println("Load Database | ERR: " + e.getStackTrace()[1].getLineNumber());}
	}

	private void saveDatabase(){
		try{
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(DATABASE_FILE));
			out.writeObject(users);
			out.close();
		}catch(Exception e){System.out.println("Save Database | ERR: " + e.getStackTrace()[1].getLineNumber());}
	}

}
