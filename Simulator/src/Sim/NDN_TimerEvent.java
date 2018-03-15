package Sim;

public class NDN_TimerEvent implements Event{

	private String name;
	public void entering(SimEnt locale) {
		// TODO Auto-generated method stub
		
	}
	
	public NDN_TimerEvent(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

}
