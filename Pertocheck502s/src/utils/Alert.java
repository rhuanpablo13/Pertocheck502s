/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.awt.Component;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;

/**
 *
 * @author rhuan
 */
public class Alert {
    
    public void show(String msg) {
        showMessage(msg);
    }

    public void show(String msg, boolean log) {
        showMessage(msg);
        if (log) {
            Log.getInstance().appendLog(msg);
        }
    }

    
    
    private void showMessage(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Atenção", JRootPane.PLAIN_DIALOG);
    }

    
    public static boolean confirm(Component component, String msg) {
        int reply = JOptionPane.showConfirmDialog(null, msg, "Atenção", JOptionPane.YES_NO_OPTION);
        return (reply == JOptionPane.YES_OPTION);
    }

}
