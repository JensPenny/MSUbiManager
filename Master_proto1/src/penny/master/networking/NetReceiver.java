package penny.master.networking;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.logging.Level;

import android.util.Log;

import penny.master.blockbase.BaseBlock;


/**
 *
 * @author Jens
 */
public class NetReceiver extends Thread{

	//Todo: uit preferences halen = ez modo
    private boolean running = true;
    private int recPort;
    DatagramSocket recsock = null;
    public NetReceiver()
    {
        recPort = 2500;
        try {
            recsock = new DatagramSocket(recPort);
        } catch (SocketException ex) {
            Log.e(this.getName(), ex.toString());
        }
    }
    
    @Deprecated // Alleen voor testing -> inetaddr en poort uit preferences halen
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
            }catch (IOException ex){
                Log.e(this.getName(), "IO Error " + ex.toString());
            }catch (ClassCastException ex){
                Log.e(this.getName(), "Could not cast to baseblock");
            }
        }
    }
}
