package metier;
import screens.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import pipeAndFilter.*; 
public class mainApp {
	private static Data data = new Data(); 
	public static boolean debug = false; 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Deserialization 
		 ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(new FileInputStream("data"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  
		try {
			data = (Data)in.readObject();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//shutdown hook for Serialization of data object after application closed
		Runtime.getRuntime().addShutdownHook(new Thread(){  
			public void run(){  
	 			try {
					FileOutputStream file=new FileOutputStream("data");
					ObjectOutputStream out=new ObjectOutputStream(file);  
					out.writeObject(data);
					out.close();
					file.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			    System.out.println("shut down hook task completed..");  
			    }  
			}
		);
		BlockingQueue fromGuiToQuery = new BlockingQueue(); 
		BlockingQueue fromQueryToGui = new BlockingQueue();
		BlockingQueue fromTransactionToQuery = new BlockingQueue(); 
		BlockingQueue fromQueryToTransaction = new BlockingQueue();
        GUI  gui = new GUI(fromQueryToGui, fromGuiToQuery ); 
        QueryProcessor query = new QueryProcessor(fromGuiToQuery,fromQueryToGui,fromQueryToTransaction, fromTransactionToQuery); 
        TransactionProcessor transaction = new TransactionProcessor(data,fromQueryToTransaction,fromTransactionToQuery); 
		RunningInterface runningInterface = new RunningInterface(); 
		Thread guiThread = new Thread(gui);
		Thread queryThread = new Thread(query);
		Thread transactionThread = new Thread(transaction); 
		Thread interfaceThread = new Thread(runningInterface);
		guiThread.start();  
		queryThread.start();
		transactionThread.start(); 
		interfaceThread.start(); 

	}

}
