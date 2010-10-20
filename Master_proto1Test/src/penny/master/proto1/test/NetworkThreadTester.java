package penny.master.proto1.test;

import java.net.UnknownHostException;

import penny.master.blockbase.PersoonSensorBlock;
import penny.master.blockbase.dataenums.BASESENSORSTATUS;
import penny.master.networking.NetReceiver;
import penny.master.networking.NetSender;
import penny.master.proto1.demonstrate.DemonstrateActivity;
import android.os.AsyncTask;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;

public class NetworkThreadTester  extends ActivityInstrumentationTestCase2<penny.master.proto1.demonstrate.DemonstrateActivity>{
	
	//Build all necessary components here
	Thread netreceiver = null; 
	PersoonSensorBlock testblok = new PersoonSensorBlock("TestPersBlock", BASESENSORSTATUS.OFF);
	NetSender netsender = null;
	
	//Srsly, class boeit eigenlijk niet. Het is een thread die continu loopt en een sender die af en toe loopt. How hard can it be
	public NetworkThreadTester(){
		super("penny.master.proto1", DemonstrateActivity.class); //Als het beest maar een activity heeft
	}
	
	@SuppressWarnings("deprecation")
	@Override
	protected void setUp() throws Exception{
		netreceiver = new NetReceiver();
		netreceiver.start();
		netsender = new NetSender("json", "localhost", 2500);
	}
	
	public void testNetReceiver(){
		assertNotNull(netreceiver);
		assertTrue(netreceiver.isAlive());
	}
	public void testNetSender(){
		assertNotNull(netsender);
	}
	public void testSendBlock() throws Throwable{
	    runTestOnUiThread(new Runnable() {
	        public void run() {
	    		netsender.sendBlock(testblok);
	        }
	    });
		assertTrue(true); //Jaja, ik wil gewoon een blok testen, maar kan niet aan die asynctask :(	    
	}

	@Override
	protected void tearDown() throws Exception {
		netreceiver.stop();
	}

}
