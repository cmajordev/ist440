/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ist.pkg440;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author andre
 */
public class LoginController implements ActionListener{
    private LoginView view;
    private JFrame frame;
    OCRController ocr;
    
    public LoginController()
    {
        view = new LoginView();
        // Display
        frame = new JFrame();
        frame.add(view);
        frame.setVisible(true);
        frame.setSize(740, 520);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Action Listeners
        this.view.getLoginButton().addActionListener((ActionEvent event) ->
        {
            long t0 = System.currentTimeMillis();
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                public void run() {
                    if (System.currentTimeMillis() - t0 > 2 * 1000) {
                        cancel();
                        view.removeAll();
                        view.revalidate();
                        view.repaint();
                        try {
                            frame.dispose();
                            ocr = new OCRController();
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            };
            timer.scheduleAtFixedRate(task, 1000, 1000);
            if (authenticate()) {
                view.removeAll();
                view.revalidate();
                view.repaint();
                view.confirm();
            }
        });
    }
    public boolean authenticate() {
        // Code for authentication API goes here. I have external code for JSON authentication if needed.
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
