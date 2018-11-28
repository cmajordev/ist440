/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ist.pkg440;

import java.awt.Color;
import java.awt.Dimension;
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
public class LoginView extends JPanel {
    private GridBagLayout gbl;
    private GridBagConstraints gbc;
    private JTextField userNameField;
    private JPasswordField passwordField;
    private JLabel userNameLabel;
    private JLabel passwordLabel;
    private JLabel errorLabel;
    private JLabel logo;
    private JLabel checkmark;
    private JButton loginButton;
    private ImageIcon logoPic;
    private ImageIcon checkmarkPic;
    private BufferedImage bg;
    
    public LoginView()
    {
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
        
        // Generate Text Fields
        userNameField = new JTextField("");
        userNameField.setPreferredSize(new Dimension(150, 25));
        userNameLabel = new JLabel("UserName:");
        userNameLabel.setForeground(Color.white);
        passwordField = new JPasswordField("");
        passwordField.setPreferredSize(new Dimension(150, 25));
        passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.white);
        
        //Display Text Fields
        gbc.gridy = 1;
        this.add(userNameLabel, gbc);
        gbc.gridy = 2;
        this.add(userNameField, gbc);
        gbc.gridy = 3;
        this.add(passwordLabel, gbc);
        gbc.gridy = 4;
        this.add(passwordField, gbc);
        gbc.gridy = 5;
        JLabel empty = new JLabel(" ");
        this.add(empty, gbc);
        
        // Displays Login Button
        loginButton = new JButton("Login");
        gbc.gridy = 6;
        add(loginButton, gbc);
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
    
    public void confirm() {
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
        
        // Displays the logo
        checkmarkPic = new ImageIcon("success.png");
        checkmark = new JLabel();
        checkmark.setIcon(checkmarkPic);
        gbc.gridy = 1;
        this.add(checkmark, gbc);
        
        // Generate Text Fields
        userNameLabel = new JLabel("Login Successful");
        userNameLabel.setForeground(Color.white);
        gbc.gridy = 2;
        this.add(userNameLabel, gbc);
    }
    
    /**
     * @return the loginButton
     */
    public JButton getLoginButton()
    {
        return loginButton;
    }
}
