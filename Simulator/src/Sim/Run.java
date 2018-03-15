package Sim;

// An example of how to build a topology and starting the simulation engine

public class Run {
	public static void main (String [] args)
	{
 		//Creates two links
 		Link link1 = new Link();
		Link link2 = new Link();
		Link link3 = new Link();
		Link link4 = new Link();
		
		NDN_Node host1 = new NDN_Node(1);
		NDN_Node host2 = new NDN_Node(2);
		NDN_Node host3 = new NDN_Node(3);
		
		host1.setPeer(link1);
		host2.setPeer(link2);
		host3.setPeer(link4);
		
		NDN_Router r1 = new NDN_Router(1);
		NDN_Router r2 = new NDN_Router(2);
		
		r1.connectInterface(link1);
		r1.connectInterface(link3);
		r1.connectInterface(link4);
		
		r2.connectInterface(link3);
		r2.connectInterface(link2);
		
		//host 2 will hold the data Video with contents Data123
		host2.setData("Video","Data123");
		//host1 sends an interest message for Video after 5 seconds
		host1.sendInterestMessageAfterTime("Video", 5);
		//host3 sends an interest message for Video after 20 seconds
		host3.sendInterestMessageAfterTime("Video", 20);
		
		// Start the simulation engine and of we go!
		Thread t=new Thread(SimEngine.instance());
	
		t.start();
		try
		{
			t.join();
		}
		catch (Exception e)
		{
			System.out.println("The motor seems to have a problem, time for service?");
		}		



	}
}
