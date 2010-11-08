package penny.master.repositories;

import android.util.Log;
import penny.master.proto1.demonstrate.DemonstrateActivity;
import penny.master.proto1.demonstrate.DemonstrateListAdapter;

public class ListChangeListener {
	private DemonstrateActivity toupdate;
	
	public ListChangeListener(DemonstrateActivity toupdate){
		this.toupdate = toupdate;
	}
	
	public void fireChangedEvent(){
		Log.i(this.getClass().getName(), "Caught event for change of list");
		toupdate.updateListView();
	}
}
