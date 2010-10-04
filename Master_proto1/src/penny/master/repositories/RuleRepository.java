package penny.master.repositories;

import java.util.ArrayList;
import java.util.Iterator;

import penny.master.blockbase.BaseBlock;
import penny.master.blockbase.RuleBlock;
import penny.master.blockbase.dataenums.RULESTATUS;

/**
 * 
 * @author jens
 * A collection of all found rules
 */
//TODO: change this architecture from arraylist to a more complicated system
//Think it over, seriously
public class RuleRepository extends ArrayList<RuleBlock>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public RuleRepository(){
	}
	
	/**
	 * 
	 * @return A repository that contains all active rules
	 */
	public RuleRepository getAllEnabledRules(){
		RuleRepository toreturn = new RuleRepository();
		Iterator<RuleBlock> iter = this.iterator();
		while (iter.hasNext()){
			RuleBlock b = iter.next();
			if(b.getIsRuleEnabled())
				toreturn.add(b);
		}
		return toreturn;
	}
}
