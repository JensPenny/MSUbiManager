package penny.master.proto1.test;

import junit.framework.Test;
import junit.framework.TestSuite;
import android.test.InstrumentationTestCase;
import android.test.suitebuilder.TestSuiteBuilder;

public class MainTestSuite extends TestSuite{	
	//TODO: naloeren en testen opbouwen, 1st design opzetten en klassen maken
	//NetActivityTester nettest = new NetActivityTester("penny.master.proto1.net", JsonNetHandler.class);
	//RuleTester ruletest = new RuleTester("penny.master.proto1.rulerepo", RuleManagerActivity.class);
	//BlockTester blocktest = new BlockTester("penny.master.proto1.blockrepo", BlockManagerActivity.class);
	//Alle klassen in een instrumentationlijst steken --> 1 voor 1 setup en runnen

	public static Test MainTestSuite() {
		return(new TestSuiteBuilder(MainTestSuite.class)
						.includeAllPackagesUnderHere()
						.build());
	}

}
