package pipeAndFilter;

public abstract class Pipe {
	
	abstract public  void dataIN (String in);
    
	abstract public String dataOUT ();
	abstract public boolean isEmpty ();
}
