package penny.master.proto1;

import penny.master.repositories.RepositoryManager;
import android.app.Application;

public class UbiProtoMain extends Application {
    /** Called when the activity is first created. */
	private RepositoryManager repomanager;
	public UbiProtoMain() {
		super();
        repomanager = new RepositoryManager();
	}
	
	public RepositoryManager getRepoManager()
	{
		return repomanager;
	}
}