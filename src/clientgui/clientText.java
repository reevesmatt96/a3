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


import java.util.*;
import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;

public class clientText {
    
	public static String bid;
	public static int port;
    public void main() throws Exception {
    	DatagramSocket testSocket = new DatagramSocket();
    	boolean goodport = false;
    	Random rand = new Random();
    	port = rand.nextInt(65535 + 1);
		while(!goodport){
			try{
			 	testSocket = new DatagramSocket(port);
				
				testSocket.close();

				try{
				    
				    testSocket = new DatagramSocket(port+1);
				    goodport = true;
				    testSocket.close();
				}
				catch(BindException e){
				    System.out.println("badPort");
				    port = rand.nextInt(65535 + 1);
				}
				
			}
			catch(BindException e){
				System.out.println("badPort");
				port = rand.nextInt(65535 + 1);

			}
		}
		
        int buf = 2000;
        String sentence ="";
        int number = 0;
	int count = 0;

        StringBuilder sb = new StringBuilder();

        Scanner sc = new Scanner(System.in);
        
	while(true){
	
		DatagramSocket clientSocket = new DatagramSocket(port);
		InetAddress IPAddress = InetAddress.getByName("localhost");
	    if(count == 0){
		System.out.println("Would You like to bid?(y/n)");
		sentence = sc.nextLine();
		while(!sentence.equals("y")){
		    System.out.println("Let me ask again.... Would you like to bid? (y/n)");
		    sentence = sc.nextLine();
		}
		count++;
		sentence = "-1";

		
		
	    }
	    
	    else{
			System.out.println("Enter a bid you would like to send");
			Thread t = new Thread(new MyTask2());
			t.start();
			sentence = sc.nextLine();
			
			//sentence = Integer.toString(bid);
	    }

            

		//System.out.println(sentence.getBytes().length);
	    double sendData = Double.parseDouble(sentence);
		
		
		
		//long startTime = System.nanoTime();
		System.out.println("client sent " + sentence + " as bid");
		DatagramPacket sendPacket = new DatagramPacket(ByteBuffer.allocate(8).putDouble(sendData).array(), 8, IPAddress, 2694);
		clientSocket.send(sendPacket);
		clientSocket.close();
		
	}

    }
    
    
    


    

}


class MyTask2 implements Runnable{

	public void run(){
            
		while(true){
                
		byte[] receiveData = new byte[2000];
		try{

			DatagramSocket clientSocket = new DatagramSocket(clientText.port+1);
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			clientSocket.receive(receivePacket);
			clientText.bid = new String(receivePacket.getData());
                        clientText.bid = clientText.bid.trim();
			System.out.println("current max bid: " + clientText.bid);
                        
			clientSocket.close();
		}
		catch(Exception e){
                    
		}

			
		
		}
	}
}
