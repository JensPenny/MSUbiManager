/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package penny.master.networking;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.List;

import android.os.AsyncTask;
import android.util.Log;
import penny.master.blockbase.BaseBlock;

/**
 *
 * @author Jens
 */
public class NetSender {
    private InetAddress target;
    private int port;
    String protocol;

    public NetSender(String protocol, String targetIP, int port) throws UnknownHostException
    {
        this.target = InetAddress.getByName("localhost");
        this.port = 2500;
        this.protocol = "json";

        if (protocol != null)
            this.protocol = protocol;
        if (targetIP != null)
            this.target =  InetAddress.getByName(targetIP);
        if (port != 0)
            this.port = port;
    }
    
    public void sendBlock(BaseBlock block)
    {
    	new BuildAndSendPacket().execute(block);
    }

    private class BuildAndSendPacket extends AsyncTask<BaseBlock, Integer, Boolean>
    {
        public boolean sendToTarget(BaseBlock tosend)
        {
            boolean success = true;
            try {
                DatagramSocket s = new DatagramSocket(port);
                s.send(buildPacket(tosend));
            } catch (SocketException ex) {
                Log.e(this.getClass().getCanonicalName(), ex.toString());
                success = false;
            } catch (IOException ex){
            	Log.e(this.getClass().getCanonicalName(),ex.toString());
                success = false;
            }
            return success;
        }
        
        private DatagramPacket buildPacket(BaseBlock tosend)
        {
            String json = JSONObjectManager.getJsonObject(tosend);
            return new DatagramPacket(json.getBytes(), json.getBytes().length, target, port);
        }

		@Override
		protected Boolean doInBackground(BaseBlock... blocks) {
	         int count = blocks.length;
	         boolean success = false;
	         for (int i = 0; i < count; i++) {
	        	 success = sendToTarget(blocks[i]);
	             publishProgress((int) ((i / (float) count) * 100));
	         }
	         return success;
		}
    }
}
