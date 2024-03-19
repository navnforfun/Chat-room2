package Client;

import Models.Session;
import java.util.Properties;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

// Ngoc Anh 1504
public class RunClient {

    // Ngoc Anh 1504
    public static void main(String[] args) {
        // Ngoc Anh 1504
        // Ngoc Anh 1504
        UIManager.LookAndFeelInfo[] lafInfo = UIManager.getInstalledLookAndFeels();
        try {
            UIManager.setLookAndFeel(lafInfo[3].getClassName());
        } catch (Exception e) {
        }
        
//        new FrmDashboard(name).setVisible(true);
        new FrmLogin().setVisible(true);
    }
    
}
