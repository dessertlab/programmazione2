package it.unina.p2.proxyskeleton.counter;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class CounterSkeletonDelega implements ICounter {
	
	private ICounter counter;
	
	public CounterSkeletonDelega ( ICounter c ){
		counter=c;
	}
	
	public void runSkeleton() {
		ServerSocket serverSocket = null;
		Socket socket = null;
		
		try {
			serverSocket = new ServerSocket(2500); //socket server
			System.out.println("Server in ascolto sulla porta 2500");
			while (true){ 
				socket = serverSocket.accept();
				SkeletonThread st = new SkeletonThread(socket,this);
				st.start();
			}
		} catch (IOException e) {
			// Eccezione dovuta alle socket
			e.printStackTrace();
		}
	}
	
	

	public void inc(){
		counter.inc();
	}
	public void sum(int value){
		counter.sum(value);
	}
	
	public void square (){
		counter.square();
	}
	
	public int get(){
		return counter.get();
	}

}
