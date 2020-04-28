package it.unina.p2.proxyskeleton.counter;


//CASO 2: implementazione per delega
//public class CounterImpl implements ICounter {

//CASO 1: implementazione per ereditarieta'
public class CounterImpl extends CounterSkel {

	private int count;
	
	public CounterImpl() {
		count = 0;
	}
	
	public int get() {
		return count;
	}

	
	public synchronized void inc() {
		count++;
	}

	
	public synchronized void sum (int value)  {
		count += value;
	}
	
	public  synchronized void square(){
		count=count*count;
	}
	
}
