package Models;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

//1571020257 - Nguyễn Ngọc Đan Trường CNTT15-04
public class Session{
    public static String host = "127.0.0.1";
    //1571020257 - Nguyễn Ngọc Đan Trường CNTT15-04
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;
    public boolean isConnected = false;
    public final Object monitor = new Object();
    //1571020257 - Nguyễn Ngọc Đan Trường CNTT15-04
    public String personName;
    public String personID;
    //1571020257 - Nguyễn Ngọc Đan Trường CNTT15-04
    private final static Session session = new Session();
    //1571020257 - Nguyễn Ngọc Đan Trường CNTT15-04
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
    //1571020257 - Nguyễn Ngọc Đan Trường CNTT15-04
    public void set(Socket socket) {
        try {
            this.socket = socket;
            this.reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream(),"UTF-8"));
            this.writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF8"), true);
        } catch (IOException e) {
            
        }
    }
    //1571020257 - Nguyễn Ngọc Đan Trường CNTT15-04
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
    //1571020257 - Nguyễn Ngọc Đan Trường CNTT15-04
    public BufferedReader getReader() {
        return reader;
    }
    //1571020257 - Nguyễn Ngọc Đan Trường CNTT15-04
    public void sendFile(File file){
        new Thread(new RunnableServerFile(file)).start();
        this.sendMessage("upload_file-" + file.getName().replaceAll("\\-", "_"));
    }
    
}
