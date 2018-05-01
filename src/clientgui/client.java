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

class client {
    static String bid;
    static int port;
    
    public client() throws SocketException, IOException{
        findGoodPort();
        contactServer();
        ClientGui.currentBid.setText("good luck");
        
    }
    
    public void findGoodPort() throws SocketException{
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
    }
    
    public void contactServer() throws IOException{
        sendBid("-1");
    }
    
    public void sendBid(String bid) throws SocketException, IOException{
        DatagramSocket clientSocket = new DatagramSocket(port);
        InetAddress IPAddress = InetAddress.getByName("localhost");

        int buf = 2000;
        String sentence ="";
        int number = 0;
        
        Thread t = new Thread(new MyTask());
	t.start();
	sentence = bid;
        double sendData = Double.parseDouble(sentence);
	//long startTime = System.nanoTime();
	System.out.println("client sent " + sentence + " as bid");
	DatagramPacket sendPacket = new DatagramPacket(ByteBuffer.allocate(8).putDouble(sendData).array(), 8, IPAddress, 2694);
	clientSocket.send(sendPacket);
	clientSocket.close();
    }
    
    public String getBid(){
        return bid;
    }
        
    public static void main(String args[]) throws Exception {
        
    }
}



class MyTask implements Runnable{

	public void run(){
		while(true){
		byte[] receiveData = new byte[2000];
		try{

			DatagramSocket clientSocket = new DatagramSocket(client.port+1);
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			clientSocket.receive(receivePacket);
			client.bid = new String(receivePacket.getData());
                        client.bid = client.bid.trim();
			System.out.println("bid: " + client.bid);
                     
                        ClientGui.currentBid.setText("Current max Bid: " + client.bid);
			clientSocket.close();
		}
		catch(Exception e){
		}

			
		
		}
	}
}
