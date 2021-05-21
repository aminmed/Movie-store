package pipeAndFilter;

public abstract class Filter implements Runnable {
	protected Pipe _dataINPipe;
    protected Pipe _dataOUTPipe;

    public String getData(){
        return _dataINPipe.dataOUT();
    }
     
    public void sendData( String tempData){
        _dataOUTPipe.dataIN(tempData);
    }
    public abstract void execute();
}
