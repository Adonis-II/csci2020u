package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.stage.DirectoryChooser;

import java.awt.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller extends Frame {
    @FXML private TreeView<String> projectTreeView;
    @FXML private TextField DIR;
    private static String fileName;
    private Socket socket = null;
    private BufferedReader in = null;
    private PrintWriter networkOut = null; // will print to console
    private BufferedReader networkIn = null; //

    public static String SERVER_ADDRESS = "localhost";
    public static int SERVER_PORT = 6969;






    public void initialize() throws IOException {

        try {
            socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
        } catch (UnknownHostException e ) {
            System.err.println("Unknown host: " + SERVER_ADDRESS);
        } catch (IOException e) {
            System.err.println("IOException while connecting to server: " + SERVER_ADDRESS);
        }

        try {
            networkOut = new PrintWriter(socket.getOutputStream(),true);
            networkIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }
        catch (IOException e){
            System.err.println("IOEXception while opening a read/write connection");
        }

        in = new BufferedReader(new InputStreamReader(System.in));

    }


    public TreeItem<String> getNodesForDirectory(File directory) {
        TreeItem<String> root = new TreeItem<String>(directory.getName());

        for(File f : directory.listFiles()) {

            System.out.println("Loading " + f.getName());
            if(f.isDirectory()) {
                root.getChildren().add(getNodesForDirectory(f));

            } else {
                root.getChildren().add(new TreeItem<String>(f.getName()));


            }
        }
        return root;
    }

    public void DIR(ActionEvent event) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select Directory");
        // directoryChooser.setInitialDirectory(new File("/home/hunchojack/Downloads"));
        File mainDirectory = directoryChooser.showDialog(null);

        projectTreeView.setRoot(getNodesForDirectory(mainDirectory));
        DIR.setText(mainDirectory.getAbsolutePath());
        DIR.getText();

    }

    public void upload(ActionEvent event) {
        try {
            System.err.print("Enter file name: ");
            fileName = in.readLine();

            File myFile = new File(fileName);
            byte[] mybytearray = new byte[(int) myFile.length()];

            FileInputStream fis = new FileInputStream(myFile);
            BufferedInputStream bis = new BufferedInputStream(fis);


            DataInputStream dis = new DataInputStream(bis);
            dis.readFully(mybytearray, 0, mybytearray.length);

            OutputStream os = socket.getOutputStream();

            //Sending file name and file size to the server
            DataOutputStream dos = new DataOutputStream(os);
            dos.writeUTF(myFile.getName());
            dos.writeLong(mybytearray.length);
            dos.write(mybytearray, 0, mybytearray.length);
            dos.flush();
            System.out.println("File "+fileName+" sent to Server.");
        } catch (Exception e) {
            System.err.println("File does not exist!");
        }
    }





    public void download(ActionEvent event){
        String fileName;
        try{
            int bytesRead;
            InputStream in = socket.getInputStream();

            DataInputStream clientData = new DataInputStream(in);

            fileName = clientData.readUTF();
            OutputStream output = new FileOutputStream(("received_from_server_" + fileName));
            long size = clientData.readLong();
            byte[] buffer = new byte[1024];
            while (size > 0 && (bytesRead = clientData.read(buffer, 0, (int) Math.min(buffer.length, size))) != -1) {
                output.write(buffer, 0, bytesRead);
                size -= bytesRead;
            }

            output.close();
            in.close();

            System.out.println("File "+fileName+" received from Server.");
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}

