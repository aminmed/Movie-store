package metier;
import screens.*;
import pipeAndFilter.*; 
public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Data data = new Data(); 
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
