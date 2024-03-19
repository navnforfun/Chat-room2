package Models;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

// Ngoc Anh 1504
public class Session{
    public static String host = "127.0.0.1";
    // Ngoc Anh 1504
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;
    public boolean isConnected = false;
    public final Object monitor = new Object();
    // Ngoc Anh 1504
    public String personName;
    public String personID;
    // Ngoc Anh 1504
    private final static Session session = new Session();
    // Ngoc Anh 1504
    public AuthenciationService authenciationService;
    public String hashKey;
    
    public void resetSession(){
        this.personID = "";
        this.personName = "";
        this.isConnected = false;
        try {
            this.reader.close();
            this.writer.close();
            this.socket.close();
        } catch (Exception e) {
        }
    }
    
    public static Session gI(){
        return Session.session;
    }
    // Ngoc Anh 1504
    public void set(Socket socket) {
        try {
            this.socket = socket;
            this.reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream(),"UTF-8"));
            this.writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF8"), true);
        } catch (IOException e) {
            
        }
    }
    // Ngoc Anh 1504
    public void sendMessage(String message){
        if(Session.gI().authenciationService != null){
            try {
                Session.gI().authenciationService.active(this.hashKey);
            } catch (Exception e) {
            }
        }
        try {
            System.out.println("[Command] " + message);
            this.writer.println(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // Ngoc Anh 1504
    public BufferedReader getReader() {
        return reader;
    }
    // Ngoc Anh 1504
    public void sendFile(File file){
        new Thread(new RunnableServerFile(file)).start();
        this.sendMessage("upload_file-" + file.getName().replaceAll("\\-", "_"));
    }
    
}
