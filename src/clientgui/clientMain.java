/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientgui;

/**
 *
 * @author Chairman
 */
public class clientMain {
    public static void main(String [] args) throws Exception{
        boolean GUI = testGUI();
        //GUI = false;
        if(GUI){
            ClientGui cg = new ClientGui();
            cg.main();
        }
        
        else{
            clientText ct = new clientText();
            ct.main();
        }
        
    }
    
    public static boolean testGUI(){
        try{
            ClientGui cg = new ClientGui();
            return true;
        }
        catch(Exception e){
            System.out.println("will not work");
            return false;
        }
        
    }
}
