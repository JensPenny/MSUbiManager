package penny.master.repositories;

import android.util.Log;
import penny.master.proto1.demonstrate.DemonstrateActivity;
import penny.master.proto1.demonstrate.DemonstrateListAdapter;

public class DemoChangeListener implements ListChangeListener{
	private DemonstrateActivity toupdate;
	
	public DemoChangeListener(DemonstrateActivity toupdate){
		this.toupdate = toupdate;
	}

	@Override
	public void fireChangedEvent(){
		Log.i(this.getClass().getName(), "Caught event for change of list");
		toupdate.updateListView();
	}

}
