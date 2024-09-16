import java.net.*;
import java.util.*;
import java.io.*;

/**
 * Client for SendIt
 * @author Patrick Parker
 */

public class Main {

    public static void main(String[] args) throws Exception{
        //starts client file
        /**String address = "";
        Scanner in = new Scanner(System.in);
        System.out.println("enter in your IP address. (Ex. 127.0.0.0");
        address = in.nextLine();*/
        InetAddress address = InetAddress.getLocalHost();
        Client c = new Client("localhost", 12345);
        Thread client = new Thread(c);
        client.start();
        LoginWindow g = new LoginWindow(c);
        //GUI menu = new GUI(address.getHostAddress());
        //menu.DisplayMenu();
    }
}
