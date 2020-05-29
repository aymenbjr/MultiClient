package de.fhac.ami;

//Java implementation of Server side 
//It contains two classes : Server and ClientHandler 
//Save file as Server.java 

import java.io.*; 
import java.text.*; 
import java.util.*; 
import java.net.*; 

//Server class 
public class Server 
{ 
	public static void main(String[] args) throws IOException 
	{ 

		ServerSocket ss = new ServerSocket(5052); 
		
		// running infinite loop for getting client request 
		while (true) 
		{ 
			Socket s = null; 
			
			try
			{ 
				// socket object to receive incoming client requests 
				System.out.println("Warten auf eine Client-Anfrage ....."); 
				s = ss.accept(); 
				
				System.out.println("Ein neuer Client ist verbunden: " + s); 
				
				// obtaining input and out streams 
				DataInputStream dis = new DataInputStream(s.getInputStream()); 
				DataOutputStream dos = new DataOutputStream(s.getOutputStream()); 
				
				System.out.println("Zuweisen eines neuen Threads für diesen Client"); 

				// create a new thread object 
				Thread t = new ClientHandler(s, dis, dos); 
				t.start(); 
				
			} 
			catch (Exception e){ 
				s.close(); 
				e.printStackTrace(); 
			} 
		} 
	} 
} 


class ClientHandler extends Thread {
/* 
 * Server: chofo bla matfr3o lya rassi layr7mbabakom ha wa7d lhandler likom smito clientHandler 3ndo ga3 les infos dyali 
 *		    tfahmo m3ah okhliwni 3likom fti9ar. ana 9wet chghel ma3ziza 3lya ghi wa7d baraka 3lya alklab
 */

 
	final DataInputStream dis; 
	final DataOutputStream dos; 
	final Socket s; 
	

	// Constructor 
	public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos) //for handling any client using multithreading
	{ 
		this.s = s; 
		this.dis = dis; 
		this.dos = dos; 
	} 

	@Override
	public void run() 
	{ 
		String received = null; 
		String toreturn; 
		while (true) 
		{ 
			try { 

				String ask = "Geben Sie einen Test ein: ";
				dos.writeUTF(ask);
				received = dis.readUTF(); 
				
				if(received.equals("exit")) 
				{ 
					dos.writeUTF(received);
					System.out.println("Client " + this.s + " sendet exit...");   
					this.s.close(); 
					System.out.println("Verbindung geschlossen"); 
					break; 
				} 
				
				dos.writeUTF(received);
				
			} catch (IOException e) { 
				e.printStackTrace(); 
			} 
				 
		}  try {
				this.dis.close(); 
				this.dos.close(); 
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	} 
} 
