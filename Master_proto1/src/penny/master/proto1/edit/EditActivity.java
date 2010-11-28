package penny.master.proto1.edit;

import penny.master.blockbase.BaseBlock;
import penny.master.blockbase.BaseSensorBlock;
import penny.master.blockbase.PhoneIOBlock;
import penny.master.blockbase.TYPE;
import penny.master.blockbase.dataenums.GSMSTATUS;
import penny.master.proto1.R;
import penny.master.proto1.UbiProtoMain;
import penny.master.proto1.demonstrate.DemonstrateActivity;
import penny.master.repositories.BlockRepository;
import penny.master.repositories.EditChangeListener;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
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
	
	private BlockRepository ifblocklist = new BlockRepository(); //List with if - blocks
	private BlockRepository thenblocklist = new BlockRepository(); //List with then - blocks
	private BlockRepository unknownblocklist = new BlockRepository(); //List with not - placed blocks
	private BlockRepository conflictedblocklist = new BlockRepository(); //List with conflicting blocks
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.editview);
		
		UbiProtoMain app = (UbiProtoMain) this.getApplication();
		processDemonstrateList(app.getRepoManager().getEventRepository());
		
		setUpGallery();
		setUpAllLists();
		setUpRest();
	}
	
	//TODO:Experimental - voeg hier clicklisteners toe ipv in create
	@Override
	protected void onResume() {
		super.onResume();
	}
	
	private void processDemonstrateList(BlockRepository eventrepo)
	{
		BlockRepository repoclone = (BlockRepository) eventrepo.clone(); //Clone to be able to do operations while maintaining order for the demonstrate - view
		int itemcount = repoclone.size();
		//Step 1: find collisions (same block 2 times in list with different states)
		//TODO: zie stap 1
		//Step 2: all events of the type INPUT -> for- list | Output -> then | TODO: inout
		if (itemcount > 0){
			for (BaseBlock b : repoclone) {
				if (b.getType().equals(TYPE.INPUT))
					ifblocklist.add(b);
				else if (b.getType().equals(TYPE.OUTPUT))
					thenblocklist.add(b);
				else if (b.getType().equals(TYPE.INOUT)) //TODO: eerste X % -> if, laatste out, alles ertussen -> unknown
					unknownblocklist.add(b);
				else
					Log.w(this.getClass().getName(), "Could not order block " + b.getName() + " with type " + b.getType().toString());
			}
		}
	}
	private void setUpGallery(){
		blockgallery = (Gallery)findViewById(R.id.glBlockGallery);
		blockgallery.setAdapter(new BlockAdapter(this));
		blockgallery.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView parent, View v, int pos,
					long id) {
				if (pos == 0) //1e item: aanpassen met id
				{
					showGalleryDialog(unknownblocklist);
				}else{
					Toast.makeText(EditActivity.this, " " + pos, Toast.LENGTH_SHORT).show();	
				}
			}
		});
	}
	private void setUpAllLists(){
		//TODO: blockrow aanpassen + Toegekregen lijst inputten (voor nu hardcoden?
		//ifblocklist.add(new PhoneIOBlock("TestIOblok", GSMSTATUS.Normal));
		//thenblocklist.add(new PhoneIOBlock("TestIOblok", GSMSTATUS.Normal));
		unknownblocklist.add(new PhoneIOBlock("TestIOblok", GSMSTATUS.Normal));
		
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
		
		volgendestap.setOnClickListener(new OnClickListener() {	
			@Override
			public void onClick(View v) {
				//Registreer regel + voeg toe aan repo 
				//TODO: Verzend regel naar framework
			}
		});
		
		//Zet listeners op lijsten
		ifblocklist.addChangeListener(new EditChangeListener(this));
		thenblocklist.addChangeListener(new EditChangeListener(this));
		
		//Initiele text in textview
		updateTextView();
	}
	
	//TODO: refactor in aparte klasse
	/**
	 * Show the in - app dialog to select a block from a list
	 */
	private void showGalleryDialog(BlockRepository toshow)
	{
		final AlertDialog.Builder builder;
		final AlertDialog dialog;
		final ListView lv;
		final Message returnmsg = Message.obtain(dialoghandler);
		
		if (toshow != null || !toshow.isEmpty()){
			//Context context = getApplicationContext();//crash
			Context context = this; //Attach to activity, not to application
			LayoutInflater inflater = (LayoutInflater)context.getSystemService(LAYOUT_INFLATER_SERVICE);
			View dialogview = inflater.inflate(R.layout.edit_chooseblocks_dialog, (ViewGroup)findViewById(R.id.dialog_root));
			
			lv = (ListView)dialogview.findViewById(R.id.diagblocklist);
			EditListAdapter diagadp = new EditListAdapter(this, R.layout.blockrow, toshow); //TODO: eigen row nodig
			lv.setAdapter(diagadp);
			builder = new AlertDialog.Builder(context);			
			builder.setView(dialogview);
			dialog = builder.create();
			
			lv.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position,
						long id) {
					BaseBlock currobj = (BaseBlock)lv.getItemAtPosition(position);
					//Toast.makeText(getApplicationContext(), "Geklikt op item met naam " + currobj.getName(), Toast.LENGTH_SHORT).show();
					returnmsg.obj = currobj;
					returnmsg.sendToTarget();
					dialog.dismiss();
				}
			});	
			
			dialog.show();
		}
		else{
			Toast.makeText(getApplicationContext(), "Geen onbekende blokken gevonden", Toast.LENGTH_SHORT);
		}
	}
	/**
	 * Handler for the callbacks received from the 
	 */
	private Handler dialoghandler = new Handler(){
		public void handleMessage(Message msg){
			//TODO: hier moet de blok getekend worden -> dragdrop - gedoe
			if (msg.obj != null){
				BaseBlock b = (BaseBlock) msg.obj;
				Toast.makeText(getApplicationContext(), "Geklikt op item met naam " + b.getName(), Toast.LENGTH_SHORT).show();
			}
		}
	};

	public void updateTextView() {
		if(txtruleexplained != null){
			String text = "";
			text += "ALS ";
			boolean firstelem = true;
			for (BaseBlock b : ifblocklist) {
				try{
					if (!firstelem){
						text += " EN ";
						firstelem = false;
					}
					BaseSensorBlock s = (BaseSensorBlock)b;
					text += s.getNaturalStatus();
				}
				catch (ClassCastException ex){
					Log.e(this.getClass().getName(), "Could not cast block to sensorblock");
				}
			}
			
			text += " DAN ";
			firstelem = true;
			
			for (BaseBlock b : thenblocklist) {
				try{
					if (!firstelem){
						text += " EN ";
						firstelem = false;
					}
					BaseSensorBlock s = (BaseSensorBlock)b;
					text += s.getNaturalStatus();
				}
				catch (ClassCastException ex){
					Log.e(this.getClass().getName(), "Could not cast block to sensorblock");
				}
			}			
			
			txtruleexplained.setText(text);
		}
	}
}
