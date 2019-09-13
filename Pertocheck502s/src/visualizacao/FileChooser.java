/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visualizacao;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

/**
 *
 * @author rhuan
 */
public class FileChooser {

    
    public String chooser(String path) {
        JFileChooser jfc = new JFileChooser(path);

        int returnValue = jfc.showOpenDialog(null);
        // int returnValue = jfc.showSaveDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jfc.getSelectedFile();
            return selectedFile.getAbsolutePath();
        }
        return null;
    }
    
    
    public String chooser() {
        return chooser(FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath());
    }
}
