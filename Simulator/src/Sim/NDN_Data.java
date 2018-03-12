package Sim;

public class NDN_Data implements Event{

	private String name;
	private String data;
	
	
	public NDN_Data(String name,String data) {
		this.name = name;
		this.data = data;
	}
	
	public void entering(SimEnt locale) {
		// TODO Auto-generated method stub
		
	}

	public String getName() {
		return name;
	}
	
	public String getData() {
		return data;
	}
}
