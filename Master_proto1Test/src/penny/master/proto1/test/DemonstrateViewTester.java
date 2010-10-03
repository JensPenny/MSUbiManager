package penny.master.proto1.test;

import penny.master.blockbase.DrukSensorBlock;
import penny.master.blockbase.IMOutputBlock;
import penny.master.blockbase.PersoonSensorBlock;
import penny.master.blockbase.PhoneDisplayOrientationSensor;
import penny.master.blockbase.PhoneIOBlock;
import penny.master.blockbase.dataenums.BASESENSORSTATUS;
import penny.master.blockbase.dataenums.GSMDISPLAYORIENTATION;
import penny.master.blockbase.dataenums.GSMSTATUS;
import penny.master.blockbase.dataenums.IMSTATUS;
import penny.master.proto1.UbiProtoMain;
import penny.master.proto1.demonstrate.DemonstrateActivity;
import penny.master.repositories.RepositoryManager;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.ListView;

public class DemonstrateViewTester extends ActivityInstrumentationTestCase2<penny.master.proto1.demonstrate.DemonstrateActivity>{
	//build the blocks here
	PersoonSensorBlock persblok = new PersoonSensorBlock("TestPersBlock", BASESENSORSTATUS.OFF);
	DrukSensorBlock drukblokc = new DrukSensorBlock("testdruk", BASESENSORSTATUS.OFF);
	IMOutputBlock imblok = new IMOutputBlock("TestIMBlock", IMSTATUS.Offline);
	PhoneDisplayOrientationSensor disporblok = new PhoneDisplayOrientationSensor("TestDispBlok", GSMDISPLAYORIENTATION.Unknown);
	PhoneIOBlock ioblok = new PhoneIOBlock("TestIOblok", GSMSTATUS.Normal);
	
	RepositoryManager repomanager = null;
	
	DemonstrateActivity thisact;
	Button moreblocks;
	Button startrec;
	Button settings;
	ListView mainview;
	Button toeditscreen;

	public DemonstrateViewTester() {
		//super(pkg, activityClass);
		super("penny.master.proto1", DemonstrateActivity.class);
	}


	@Override
	protected void setUp() throws Exception {
		
		thisact = (DemonstrateActivity) this.getActivity();

		UbiProtoMain app = (UbiProtoMain)thisact.getApplication();
		repomanager = app.getRepoManager();
		//repomanager = new RepositoryManager();
		repomanager.getBlockRepository().add(persblok);
		repomanager.getBlockRepository().add(drukblokc);
		repomanager.getBlockRepository().add(imblok);
		repomanager.getBlockRepository().add(disporblok);
		repomanager.getBlockRepository().add(ioblok);
		//thisact.setRepomanager(repomanager);
		
		moreblocks = (Button)thisact.findViewById(penny.master.proto1.R.id.btnDemMoreBlocks);
		startrec = (Button)thisact.findViewById(penny.master.proto1.R.id.btnDemRecordStop);
		settings = (Button)thisact.findViewById(penny.master.proto1.R.id.btnDemSetting);
		mainview = thisact.getListView();
		toeditscreen = (Button)thisact.findViewById(penny.master.proto1.R.id.btnNaarBewerkStap);
	}
	
	public void testThisActivityPreCondition(){
		assertNotNull(thisact);
	}
	public void testapplicationstarted(){
		assertNotNull(thisact.getApplication());
	}
	public void testrepoNotNull(){
		assertNotNull(repomanager);
	}
	public void testrepoAreEqual(){
		assertEquals(repomanager, thisact.getRepomananger());
	}
	public void testblockrepoNotNull(){
		assertNotNull(repomanager.getBlockRepository());
	}	
	public void testimBlock(){
		assertEquals(thisact.getRepomananger().getBlockRepository().contains(imblok), true);
	}
	public void testAllBlocks(){
		assertEquals(thisact.getRepomananger().getBlockRepository().contains(imblok), true);
		assertEquals(thisact.getRepomananger().getBlockRepository().contains(disporblok), true);
		assertEquals(thisact.getRepomananger().getBlockRepository().contains(ioblok), true);
		assertEquals(thisact.getRepomananger().getBlockRepository().contains(persblok), true);
		assertEquals(thisact.getRepomananger().getBlockRepository().contains(drukblokc), true);
	}
	public void testAllInterfaceComponents(){
		assertEquals(moreblocks.getClass().getName(), Button.class.getName());
		assertEquals(toeditscreen.getClass().getName(), Button.class.getName());
		assertEquals(settings.getClass().getName(), Button.class.getName());
		assertEquals(startrec.getClass().getName(), Button.class.getName());
		assertEquals(mainview.getClass().getName(), ListView.class.getName());
	}
}
