/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientgui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Chairman
 */

public class ClientGui extends JFrame {
static JLabel currentBid = new JLabel();
    public ClientGui(){
        initStartComp();
    }
    
    public void initStartComp(){
        
        setTitle("Bidding");
        setSize(300, 100);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        
        panel.setSize(200, 200);
        // Initialize the components
        JLabel label = new JLabel("Would you like to start the bidding app");
        JButton startButton = new JButton("yes");
        
        
        //format the components
       

        
        // Setup the action listeners 
        startButton.addActionListener((ActionEvent event) -> {
           panel.setVisible(false); 
           getContentPane().remove(panel);
            try {
                initBidComp();
            } catch (IOException ex) {
                
            }
            
       });
       
       // Add all of the labels to the JFrame
       panel.add(label);
       panel.add(startButton);
       getContentPane().add(panel);
       setDefaultCloseOperation(EXIT_ON_CLOSE);
       
    }
    
    public void initBidComp() throws IOException{
        client client = new client();
        setTitle("Bidding");
        setSize(300, 100);
        setLocationRelativeTo(null);
        
        // Initialize components
        JPanel panel = new JPanel();
        panel.setSize(300,300);
        JButton submitButton = new JButton("Submit");
        JTextField enterBid = new JTextField("Enter Bid          ");
        
        
        //Add actionListeners
        
        submitButton.addActionListener((ActionEvent event) -> {
            System.out.println("hello");
            
            try {
                client.sendBid(enterBid.getText());
            } catch (IOException ex) {
                Logger.getLogger(ClientGui.class.getName()).log(Level.SEVERE, null, ex);
            }
            
       });
        
       // Add components
       panel.add(submitButton);
       panel.add(enterBid);
       panel.add(currentBid);
       getContentPane().add(panel);
       setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        
    }
    
    
    public void main() {
       EventQueue.invokeLater(() -> {
            ClientGui gui = new ClientGui();
            gui.setVisible(true);
        });
        
    }
    
}
