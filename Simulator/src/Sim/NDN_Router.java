package Sim;

import java.util.HashMap;
import java.util.Vector;

public class NDN_Router extends SimEnt{
	HashMap<String,NDN_Data> CS = new HashMap<String,NDN_Data>();
	Vector<PendingInterest> PIT = new Vector<PendingInterest>();
	Vector<SimEnt> FIB = new Vector<SimEnt>();
	private int id;
	
	
	public NDN_Router(int id) {
		this.id = id;
	}
	
	public void recv(SimEnt src, Event ev){
		if(ev instanceof InterestMessage){
			System.out.println("Router: " +id + " received interest msg at time: " + SimEngine.getTime());
			InterestMessage msg = (InterestMessage)ev;
			//If data is in content store, send the data immediately
			if(CS.containsKey(msg.getName())) {
				System.out.println("Data is cached in content store");
				send(src,CS.get(msg.getName()),0);
			}else {
				System.out.println("Adding to PIT");
				PIT.add(new PendingInterest(msg,src));
				forward(msg,src);
			
			}	
		}
		
		if (ev instanceof NDN_Data) {
			NDN_Data data = (NDN_Data)ev;
			for(int i = 0; i< PIT.size();i++) {
				if(PIT.elementAt(i).getInterestName() == data.getName()) {
					//Cache data in content store
					CS.put(data.getName(), data);
					send(PIT.elementAt(i).getSource(),data,0);
					PIT.removeElementAt(i);
				}
			}
		}
		
	}
	
	//Broadcasts the interest msg...
	public void forward(InterestMessage msg, SimEnt src) {
		for(int i = 0; i < FIB.size();i++) {
			SimEnt dest = FIB.elementAt(i);
			if(dest != src) {
				send(dest,msg,0);
			}
			
		}
	}
	
	public void connectInterface(SimEnt ent) {
		FIB.addElement(ent);
		if(ent instanceof Link) {
			Link link = (Link) ent;
			link.setConnector(this);
		}
	}
}
