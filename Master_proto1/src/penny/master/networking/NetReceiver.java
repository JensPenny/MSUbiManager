package penny.master.networking;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.logging.Level;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import penny.master.blockbase.BaseBlock;
import penny.master.proto1.UbiProtoMain;


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
    UbiProtoMain app;
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
    
    public NetReceiver(int ontvpoort)
    {
    	recPort = ontvpoort;
        try {
            recsock = new DatagramSocket(recPort);
        } catch (SocketException ex) {
            Log.e(this.getName(), ex.toString());
        }
    }
    /*
     * Will gracefully end the socket after the reception of one last message.
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
                app.getRepoManager().getEventRepository().add(b);
            }catch (IOException ex){
                Log.e(this.getName(), "IO Error " + ex.toString());
            }catch (ClassCastException ex){
                Log.e(this.getName(), "Could not cast to baseblock");
            }
        }
    }
}
