package penny.master.networking;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import penny.master.blockbase.BaseBlock;
import penny.master.proto1.UbiProtoMain;
import android.util.Log;


/**
 *
 * @author Jens
 */
public class NetReceiver extends Thread{

	//SharedPreferences prefs;
	//Todo: uit preferences halen = ez modo
    private boolean running = true;
    private int recPort;
    DatagramSocket recsock = null;
    UbiProtoMain app = null;
    //WARNING: Be mindful of context leaks. Not stopping this thread WILL cause a leak
    /*public NetReceiver(UbiProtoMain app)
    {
    	this.app = app;
    	if (app!= null)
    	{
    		prefs = PreferenceManager.getDefaultSharedPreferences(app.getBaseContext());
    		recPort = prefs.getInt("inet_inc_port", 2700);
    	}else
    	{
    		recPort = 2700;
    	}
        try {
            recsock = new DatagramSocket(recPort);
        } catch (SocketException ex) {
            Log.e(this.getName(), ex.toString());
        }
    }*/
    
    public NetReceiver(UbiProtoMain app, int ontvpoort)
    {
    	recPort = ontvpoort;
    	this.app = app;
        try {
            recsock = new DatagramSocket(recPort);
        } catch (SocketException ex) {
            Log.e(this.getName(), ex.toString());
        }
        this.setName("Netreceiver_thread");
    }
    /*
     * Will gracefully end the socket -> IF this doesn't work, just let it throw an exception in the run
     */
    public void stopSocketThread()
    {
        running = false;
    }

    @Override
    public void run() {
        Log.i(this.getClass().getName(),"Starting the receiving socket");
        while(running)
        {
            byte[] buff = new byte[10000];
            DatagramPacket dp = new DatagramPacket(buff, buff.length);
            try{
                recsock.receive(dp);
                String receiveddata = new String(dp.getData());
                Object rec = JSONObjectManager.decodeJSONObject(receiveddata);
                BaseBlock b = (BaseBlock)rec;
                Log.i(this.getName(), "Received object " + b.getName() + " van klasse " + b.getKlasse());
                if (app.getRepoManager().getRecording()) //Als recording -> opnemen
                {
                	app.getRepoManager().getEventRepository().add(b);
                	Log.i(this.getName(), "Adding latest object to eventrepository");
                }
            }catch (IOException ex){
                Log.e(this.getName(), "IO Error " + ex.toString());
            }catch (ClassCastException ex){
                Log.e(this.getName(), "Could not cast to baseblock");
            }catch (NullPointerException ex){
            	Log.e(this.getName(), "Could not receive on socket");
            	ex.printStackTrace();
            	stopSocketThread(); //TODO: MOET GRACEFUL -> Interrupten dus: http://stackoverflow.com/questions/680180/where-to-stop-destroy-threads-in-android-service-class
            }
        }
    }
}
