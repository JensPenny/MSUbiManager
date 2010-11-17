package penny.master.proto1.edit;

import penny.master.blockbase.BaseBlock;
import penny.master.blockbase.PhoneIOBlock;
import penny.master.blockbase.dataenums.GSMSTATUS;
import penny.master.proto1.R;
import penny.master.proto1.demonstrate.DemonstrateActivity;
import penny.master.repositories.BlockRepository;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class EditActivity extends ListActivity{

	private Gallery blockgallery;
	private ListView iflistview;
	private ListView thenlistview;
	private EditListAdapter iflistadapter;
	private EditListAdapter thenlistadapter;
	private TextView txtruleexplained;
	
	private BlockRepository ifblocklist = new BlockRepository();
	private BlockRepository thenblocklist = new BlockRepository();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.editview);
		
		setUpGallery();
		setUpAllLists();
		setUpRest();
	}
	
	//TODO:Experimental - voeg hier clicklisteners toe ipv in create
	@Override
	protected void onResume() {
		super.onResume();
	}
	
	private void setUpGallery(){
		blockgallery = (Gallery)findViewById(R.id.glBlockGallery);
		blockgallery.setAdapter(new BlockAdapter(this));
		blockgallery.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView parent, View v, int pos,
					long id) {
				Toast.makeText(EditActivity.this, " " + pos, Toast.LENGTH_SHORT).show();				
			}
		});
	}
	private void setUpAllLists(){
		//TODO: blockrow aanpassen + Toegekregen lijst inputten (voor nu hardcoden?
		ifblocklist.add(new PhoneIOBlock("TestIOblok", GSMSTATUS.Normal));
		thenblocklist.add(new PhoneIOBlock("TestIOblok", GSMSTATUS.Normal));
		
		//iflistview = (ListView)findViewById(R.id.iflistview);
		iflistview = getListView();
		iflistadapter = new EditListAdapter(this, R.layout.blockrow, ifblocklist);
		//iflistview.setAdapter(iflistadapter);
		setListAdapter(iflistadapter);
		//TODO: refactoren
		iflistview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				BaseBlock currobj = (BaseBlock)iflistview.getItemAtPosition(position);
				Toast.makeText(getApplicationContext(), "Geklikt op item met naam " + currobj.getName(), Toast.LENGTH_SHORT).show();
			}
		});
		
		thenlistview = (ListView)findViewById(R.id.thenlistview);
		thenlistadapter = new EditListAdapter(this, R.layout.blockrow, thenblocklist);
		thenlistview.setAdapter(thenlistadapter);
		thenlistview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				BaseBlock currobj = (BaseBlock)thenlistview.getItemAtPosition(position);
				Toast.makeText(getApplicationContext(), "Geklikt op item met naam " + currobj.getName(), Toast.LENGTH_SHORT).show();
			}
		});
	}
	private void setUpRest(){
		txtruleexplained = (TextView)findViewById(R.id.txtRuleExplained);
		Button vorigestap = (Button)findViewById(R.id.btnEditVorige);
		Button volgendestap = (Button)findViewById(R.id.btnEditVolgende);
		
		vorigestap.setOnClickListener(new OnClickListener() {		
			@Override
			public void onClick(View v) {
				Intent i = new Intent(EditActivity.this, DemonstrateActivity.class);
				startActivityIfNeeded(i, RESULT_OK);
				//finish en setresult zou info terug kunnen sturen, maar is niet nodig denk ik
			}
		});
	}
}
