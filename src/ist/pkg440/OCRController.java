/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ist.pkg440;

import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Action;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author andre
 */
public class OCRController {
    JFrame frame;
    JFileChooser fileChooser;
    StringBuilder sb;
    java.io.File file;
    OCRView view;
    
    public OCRController() throws FileNotFoundException {
        frame = new JFrame();
        view = new OCRView();
        frame.add(view);
        frame.setVisible(true);
        frame.setSize(740, 520);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        searchFile();
        
        //Action Listeners
        this.view.getExplorerButton().addActionListener((ActionEvent event) ->
        {
            searchFile();
        });
    }
        
    public void searchFile() {
        fileChooser = new JFileChooser();
        sb = new StringBuilder();
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            //get the file
            file = fileChooser.getSelectedFile();
            
            //create scanner for the file
            view.setFileName(file.getName());
        }
    }
}
