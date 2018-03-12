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
			System.out.println(data.getData());
		}
		
		if(event instanceof InterestMessage) {
			System.out.println("Node: "+id + " Received Interest Msg!!");
			InterestMessage msg = (InterestMessage)event;
			for (int i = 0; i < dataStore.size(); i++) {
				System.out.println(dataStore.elementAt(i).getName());
				System.out.println(msg.getName());
				if(dataStore.elementAt(i).getName() == msg.getName()) {
					System.out.println("Found data in data store");
					send(source,dataStore.elementAt(i),0);
				}
			}
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
	
	//Schedules an interestmsg to be sent after a given time
	public void sendInterestMessageAfterTime(String name, int time) {
		send(_peer,new InterestMessage(name),time);
	}
	
	public void setData(String name, String data) {
		this.dataStore.add(new NDN_Data(name,data));
	}

}
