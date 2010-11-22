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
		//netreceiver = new NetReceiver(this);		// Geef de applicatie mee, zodat de thread terug kan koppelen
		// Netreceiver ook maar meteen starten e. Vroeg begonnen = half gewonnen
	}

	public RepositoryManager getRepoManager() {
		return repomanager;
	}

	public NetReceiver getNetReceiver() {
		return netreceiver;
	}
	public void buildAndStartNetReceiver(int port){ //Geinitialiseert in demonstrateactivity, wegens issues in starten in app
		if (netreceiver != null)
			netreceiver.stop();
		netreceiver = new NetReceiver(this, port);
		startNetReceiver();
	}

	@Override
	public void onTerminate() {
		super.onTerminate();
		stopNetReceiver();
	}

	public void stopNetReceiver() {
		if (netreceiver != null)
			netreceiver.stop();
	}

	public void startNetReceiver() {
		if (netreceiver != null)
			netreceiver.start();
	}
}