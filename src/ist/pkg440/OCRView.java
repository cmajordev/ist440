/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ist.pkg440;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author andre
 */
public class OCRView extends JPanel{
    private GridBagLayout gbl;
    private GridBagConstraints gbc;
    private JTextField userNameField;
    private JPasswordField passwordField;
    private JLabel instructionsLabel;
    private JLabel startOCR;
    private JLabel errorLabel;
    private JLabel logo;
    private JLabel checkmark;
    private JButton explorerButton;
    private ImageIcon logoPic;
    private ImageIcon checkmarkPic;
    private BufferedImage bg;
    private String fileName;
    
    public OCRView() {
        // Set Layout
        gbl = new GridBagLayout();
        this.setLayout(gbl);
        gbc = new GridBagConstraints();
        
        // Displays the logo
        logoPic = new ImageIcon("440 logo.png");
        logo = new JLabel();
        logo.setIcon(logoPic);
        gbc.gridx = 0; gbc.gridy = 0;
        this.add(logo, gbc);
        System.out.println("test");
        
        // Instructions
        instructionsLabel = new JLabel("Choose a picture file to submit to the OCR decryption client.");
        instructionsLabel.setForeground(Color.white);
        gbc.gridy = 1;
        this.add(instructionsLabel, gbc);
        
        // Filler Space
        JLabel empty = new JLabel(" ");
        gbc.gridy = 2;
        this.add(empty, gbc);
        
        // OCR Button
        explorerButton = new JButton("File Explorer");
        gbc.gridy = 3;
        this.add(explorerButton, gbc);
        
        // Filler Space
        JLabel empty2 = new JLabel(" ");
        gbc.gridy = 4;
        this.add(empty2, gbc);
        
        // File Name
        fileName = "N/A";
        instructionsLabel = new JLabel("File: " + fileName);
        instructionsLabel.setForeground(Color.white);
        gbc.gridy = 5;
        this.add(instructionsLabel, gbc);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        try {                
          bg = ImageIO.read(new File("bg.png"));
       } catch (IOException ex) {
            System.out.println("Error");
       }
        super.paintComponent(g);
            g.drawImage(bg, 0, 0, null);
    }
    
    /**
     * @return the explorerButton
     */
    public JButton getExplorerButton()
    {
        return explorerButton;
    }
    
    public void setFileName(String name) 
    {
        System.out.println(name);
        fileName = name;
    }
}
