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
		netreceiver = new NetReceiver(this);		// Geef de applicatie mee, zodat de thread terug kan koppelen
		// Netreceiver ook maar meteen starten e. Vroeg begonnen = half gewonnen
		netreceiver.start();
	}

	public RepositoryManager getRepoManager() {
		return repomanager;
	}

	public NetReceiver getNetReceiver() {
		return netreceiver;
	}

	@Override
	public void onTerminate() {
		super.onTerminate();
		stopNetReceiver();
	}

	public void stopNetReceiver() {
		netreceiver.stop();
	}

	public void startNetReceiver() {
		netreceiver.start();
	}

}