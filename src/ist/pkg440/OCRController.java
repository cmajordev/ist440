/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ist.pkg440;

import ist.pkg440.requests.DecipherImageProccess;
import ist.pkg440.requests.DecipherImageProccessDelegate;
import ist.pkg440.requests.DecipherImageProccessStep;
import ist.pkg440.requests.TranslationLanguage;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Action;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
    TranslationLanguage language;
    OCRView view;
    
    public OCRController() throws FileNotFoundException {
        frame = new JFrame();
        view = new OCRView();
        frame.add(view);
        frame.setVisible(true);
        frame.setSize(740, 520);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Action Listeners
        this.view.getExplorerButton().addActionListener((ActionEvent event) ->
        {
            searchFile();
        });
        
        this.view.getLanguageList().addActionListener((ActionEvent event) ->
        {
            JComboBox comboBox = (JComboBox)event.getSource();
            int selectedIndex = comboBox.getSelectedIndex();
            if (selectedIndex > 0) {
                this.language = TranslationLanguage.all()[selectedIndex - 1];
            } else {
                this.language = null;
            }
            
        });
        
        this.view.getProcessButton().addActionListener((ActionEvent event) ->
        {
            if (file == null) {
                JOptionPane.showMessageDialog(
                        null, 
                        "Please select a file before proceeding!", 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (language == null) {
                JOptionPane.showMessageDialog(
                        null, 
                        "Please select a language before proceeding!", 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            byte[] data = image(file.getAbsolutePath());
            decipher(language, data);
        });
    }
        
    public void searchFile() {
        fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            //get the file
            file = fileChooser.getSelectedFile();

            //create scanner for the file
            view.setFileName(file.getName());
        }
    }
    
    private byte[] image(String fileName) {
        try {
            BufferedImage img = ImageIO.read(new File(fileName));
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            ImageIO.write(img, "png", stream);
            return stream.toByteArray();
        } catch (IOException ex) {
            return null;
        }
    }
    
    private void decipher(TranslationLanguage language, byte[] image) {
        DecipherImageProccess process = new DecipherImageProccess(language, new DecipherImageProccessDelegate() {
            @Override
            public void finishedStep(DecipherImageProccessStep step) {
                switch (step) {
                    case OCR:
                        // Update Progress 1/3
                        System.out.println("Finished OCR");
                        break;
                    case DECIPHER:
                        // Update Progress 2/3
                        System.out.println("Finished DECIPHER");
                        break;
                    case TRANSLATE:
                        // Update Progress 3/3
                        System.out.println("Finished TRANSLATION");
                        break;
                }
            }

            @Override
            public void success(String decipheredMessage) {
                JOptionPane.showMessageDialog(null, decipheredMessage, "Success!", JOptionPane.PLAIN_MESSAGE);
            }

            @Override
            public void error(String errorMessage) {
                System.out.println("There was an error: " + errorMessage);
            }
        });
        process.decipher(image);
    }
}
