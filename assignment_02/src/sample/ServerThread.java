package sample;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.Vector;

public class ServerThread extends Thread {
    protected Socket socket       = null;
    protected PrintWriter out     = null;
    protected BufferedReader in   = null;


    public ServerThread(Socket socket){
        super();
        this.socket = socket;

        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            System.err.println("IOEXception while opening a read/write connection");
        }
    }

}