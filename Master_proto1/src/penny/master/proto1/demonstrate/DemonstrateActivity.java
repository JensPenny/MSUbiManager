package penny.master.proto1.demonstrate;

import penny.master.blockbase.BaseBlock;
import penny.master.networking.NetSender;
import penny.master.proto1.R;
import penny.master.proto1.UbiProtoMain;
import penny.master.repositories.RepositoryManager;
import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class DemonstrateActivity extends ListActivity {
	
	private Activity thisact = this;
	private RepositoryManager repomananger;
	private ListView lv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.demonstrateview);
		setUp();
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	
	private void setUp(){
		repomananger = ((UbiProtoMain)getApplication()).getRepoManager();
		
		//repomananger = new RepositoryManager();
		Button volgstap = (Button)findViewById(R.id.btnNaarBewerkStap);

		volgstap.setOnClickListener(new OnClickListener() {		
			@Override
			public void onClick(View v) {
//		    	Intent edit = new Intent(thisact, EditScherm.class);
//		    	startActivity(edit);				
			}
		});
		
		DemonstrateListAdapter adp = new DemonstrateListAdapter(this, R.layout.blockrow, repomananger.getBlockRepository());
		setListAdapter(adp);
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
}
