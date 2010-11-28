package penny.master.repositories;

import penny.master.proto1.edit.EditActivity;
import android.util.Log;

public class EditChangeListener implements ListChangeListener{
	private EditActivity toupdate;
	
	public EditChangeListener(EditActivity toupdate){
		this.toupdate = toupdate;
	}
	
	@Override
	public void fireChangedEvent(){
		Log.i(this.getClass().getName(), "Caught event for change in editscreen");
		toupdate.updateTextView();
	}

}
