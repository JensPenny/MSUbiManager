package penny.master.proto1;

import java.util.logging.Logger;

import penny.master.proto1.demonstrate.DemonstrateActivity;
import penny.master.repositories.BlockRepository;
import penny.master.repositories.RepositoryManager;
import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.os.Bundle;
import android.view.OrientationListener;

public class UbiProtoMain extends Application {
    /** Called when the activity is first created. */
	private RepositoryManager repomanager;
	public UbiProtoMain() {
		super();
        repomanager = new RepositoryManager();
//    	Intent demo = new Intent(this.getApplicationContext(), DemonstrateActivity.class);
//    	startActivity(demo);	
	}
	
	public RepositoryManager getRepoManager()
	{
		return repomanager;
	}
}