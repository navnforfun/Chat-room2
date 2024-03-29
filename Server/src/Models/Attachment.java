package Models;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
// Ngoc Anh 1504
public class Attachment {
    protected final File file;
    private String fileSize;
    protected String clientOwned;
    private final String type;
    protected final int idOwner;
    // Ngoc Anh 1504
    public Attachment(Client clientOwned, File file) throws IOException {
        this.idOwner = clientOwned.idClient;
        this.clientOwned = clientOwned.nameClient;
        this.file = file;
        double size = (double)Files.size(file.toPath())/1024/1024;
        
        this.fileSize = String.format("%.3f MB", size);
        this.type = Files.probeContentType(file.toPath());
    }
    // Ngoc Anh 1504
    @Override
    public String toString(){
        //fileName1#size1#type1#owner1
        return String.format("%s#%s#%s#%s",file.getName().split("\\__")[1],fileSize,type,clientOwned);
    }
    // Ngoc Anh 1504
    public void destruct(){
        try {
            Files.delete(file.toPath());
        } catch (Exception e) {
        }
    }
}
