package Sim;

import java.util.Vector;

public class NDN_Node extends SimEnt{

	private SimEnt _peer;
	Vector<NDN_Data> dataStore = new Vector<NDN_Data>();
	int id;
	public NDN_Node(int id) {
		this.id = id;
	}
	
	@Override
	public void recv(SimEnt source, Event event) {
		if(event instanceof NDN_Data) {
			NDN_Data data = (NDN_Data)event;
			System.out.println("Node: " + id + " received data with name: " + data.getName() +  " containing data: " +  data.getData());
		}
		
		if(event instanceof InterestMessage) {
			System.out.println("Node: "+id + " Received Interest Msg");
			InterestMessage msg = (InterestMessage)event;
			for (int i = 0; i < dataStore.size(); i++) {
				
				if(dataStore.elementAt(i).getName() == msg.getName()) {
					System.out.println("Node: " + id + " found data with name " + dataStore.elementAt(i).getName());
					System.out.println("Node: " + id + " Sends data packet back");
					send(source,dataStore.elementAt(i),0);
				}
			}
		}
		if(event instanceof NDN_TimerEvent) {
			NDN_TimerEvent msg = ((NDN_TimerEvent) event);
			System.out.println("Node: " + id + " Sends interest message for: " + msg.getName());
			send(_peer,new InterestMessage(msg.getName()),0);
		}
	}
	
	// Sets the peer to communicate with. This node is single homed
	
	public void setPeer (SimEnt peer)
	{
		_peer = peer;
		
		if(_peer instanceof Link )
		{
			 ((Link) _peer).setConnector(this);
		}
	}
	
	//Schedules an interest message to be sent after a given time
	public void sendInterestMessageAfterTime(String name, int time) {
//		send(_peer,new InterestMessage(name),time);
		send(this,new NDN_TimerEvent(name),time);
	}
	
	public void setData(String name, String data) {
		this.dataStore.add(new NDN_Data(name,data));
	}

}
