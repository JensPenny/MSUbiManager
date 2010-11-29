package penny.master.proto1.demonstrate;

import penny.master.blockbase.BaseBlock;
import penny.master.proto1.EditPreferences;
import penny.master.proto1.R;
import penny.master.proto1.UbiProtoMain;
import penny.master.proto1.edit.EditActivity;
import penny.master.repositories.DemoChangeListener;
import penny.master.repositories.RepositoryManager;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class DemonstrateActivity extends ListActivity {
	
	SharedPreferences prefs;
	private Activity thisact = this;
	private RepositoryManager repomananger;
	private ListView lv;
	DemonstrateListAdapter adp;
    // Need handler for callbacks to the UI thread
    private final Handler mHandler = new Handler();
    private final Handler dialogHandler = new Handler(){
    	public void handleMessage(Message msg){
    		if (msg.what == 1){ //Delete - knop
    			repomananger.getEventRepository().remove(msg.arg1);
    		}
    		else if (msg.what == 2){ //Verplaats - knop
    			//TODO: verplaats - ding
    		}
    		
    	}
    };
    // Create runnable for actually doing the work that is called in the handler 
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
		Button volgstap = (Button)findViewById(R.id.btnNaarBewerkStap);
		volgstap.setOnClickListener(new OnClickListener() {		
			@Override
			public void onClick(View v) {
				//TODO: checken van inputlijst
		    	Intent edit = new Intent(thisact, EditActivity.class);
		    	startActivity(edit);				
			}
		});
		Button record = (Button)findViewById(R.id.btnDemRecordStop);
		record.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				boolean recording = repomananger.getRecording();
				Button b = (Button)v;
				if(recording){
					//Van opnemen -> niet opnemen
					repomananger.stopRecording();
					b.setText("Start opname");
				}else{
					//Van niet opnemen -> opnemen
					repomananger.resetEventRepository();
					repomananger.startRecording();
					b.setText("Stop opname");
					mHandler.post(mUpdateResults);
				}
			}
		});
		Button instellingen = (Button)findViewById(R.id.btnDemSetting);
		instellingen.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent((DemonstrateActivity)thisact, EditPreferences.class));				
			}
		});
		
		adp = new DemonstrateListAdapter(this, R.layout.blockrow, repomananger.getEventRepository());
		setListAdapter(adp);
		repomananger.getEventRepository().addChangeListener(new DemoChangeListener(this));//Add the listener to keep this thing updated from thread
	    lv = getListView();
		lv.setTextFilterEnabled(true);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				BaseBlock currobj = (BaseBlock)lv.getItemAtPosition(position);
				BlockDetailsDialog diag = new BlockDetailsDialog(thisact, currobj, position);
				diag.setReturnHandler(dialogHandler);
				diag.getDialog().show();
				//Toast.makeText(getApplicationContext(), "Geklikt op item met naam " + currobj.getName(), Toast.LENGTH_SHORT).show();
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

	//TODO: Menu functions: bring up a level -> To all activities
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
