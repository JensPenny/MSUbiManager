package penny.master.proto1.demonstrate;

import penny.master.blockbase.BaseBlock;
import penny.master.networking.NetSender;
import penny.master.proto1.EditPreferences;
import penny.master.proto1.R;
import penny.master.proto1.UbiProtoMain;
import penny.master.proto1.edit.EditActivity;
import penny.master.repositories.ListChangeListener;
import penny.master.repositories.RepositoryManager;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class DemonstrateActivity extends ListActivity {
	
	SharedPreferences prefs;
	private Activity thisact = this;
	private RepositoryManager repomananger;
	private ListView lv;
	DemonstrateListAdapter adp;
    // Need handler for callbacks to the UI thread
    final Handler mHandler = new Handler();
    // Create runnable for posting
    final Runnable mUpdateResults = new Runnable() {
        public void run() {
        	adp.notifyDataSetChanged();
        }
    };
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.demonstrateview);
		setUp();
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		UbiProtoMain app = (UbiProtoMain) this.getApplication();
		app.stopNetReceiver();
	}
	
	private void setUp(){
		repomananger = ((UbiProtoMain)getApplication()).getRepoManager();
		
		prefs = PreferenceManager.getDefaultSharedPreferences(this);
		//Opzetten netreceiver
		UbiProtoMain app = (UbiProtoMain) this.getApplication();
		app.buildAndStartNetReceiver(new Integer(prefs.getString("inet_inc_port", "2700")));
		//repomananger = new RepositoryManager();
		Button volgstap = (Button)findViewById(R.id.btnNaarBewerkStap);

		volgstap.setOnClickListener(new OnClickListener() {		
			@Override
			public void onClick(View v) {
				//TODO: checken van inputlijst
		    	Intent edit = new Intent(thisact, EditActivity.class);
		    	startActivity(edit);				
			}
		});
		
		adp = new DemonstrateListAdapter(this, R.layout.blockrow, repomananger.getEventRepository());
		setListAdapter(adp);
		repomananger.getEventRepository().addChangeListener(new ListChangeListener(this));		//Add the listener to keep this thing updated from thread
	    lv = getListView();
		lv.setTextFilterEnabled(true);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				BaseBlock currobj = (BaseBlock)lv.getItemAtPosition(position);
				Toast.makeText(getApplicationContext(), "Geklikt op item met naam " + currobj.getName(), Toast.LENGTH_SHORT).show();
			}
		});
	}
	
	//The following classes are required for testing purposes
	public RepositoryManager getRepomananger() {
		return repomananger;
	}
	public void setRepomanager(RepositoryManager repomananger) {
		this.repomananger = repomananger;
	}
	public void updateListView(){
		mHandler.post(mUpdateResults);
	}

	//Menu functions: bring up a level
	private static final int EDIT_ID = 1;
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(Menu.NONE, EDIT_ID, Menu.NONE, "Edit prefs");
		return super.onCreateOptionsMenu(menu);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()){
		case EDIT_ID:
			startActivity(new Intent(this, EditPreferences.class));
			return(true);
		}
		return super.onOptionsItemSelected(item);
	}
}
