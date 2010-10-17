package penny.master.proto1;

import penny.master.networking.NetReceiver;
import penny.master.repositories.RepositoryManager;
import android.app.Application;

public class UbiProtoMain extends Application {
    /** Called when the activity is first created. */
	private RepositoryManager repomanager;
	private NetReceiver netreceiver;
	public UbiProtoMain() {
		super();
        repomanager = new RepositoryManager();
        netreceiver = new NetReceiver();
        //Netreceiver ook maar meteen starten e. Vroeg begonnen = half gewonnen :D
        netreceiver.start();
	}
	
	public RepositoryManager getRepoManager()
	{
		return repomanager;
	}
	public NetReceiver getNetReceiver()
	{
		return netreceiver;
	}
	public void stopNetReceiver()
	{
		netreceiver.stop();
	}
	public void startNetReceiver()
	{
		netreceiver.start();
	}
}