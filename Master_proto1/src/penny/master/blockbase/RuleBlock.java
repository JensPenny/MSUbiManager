/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package penny.master.blockbase;

import java.util.Iterator;
import penny.master.blockbase.dataenums.RULESTATUS;
import penny.master.repositories.BlockRepository;

/**
 *
 * @author jens
 * The baseblock for the management of rules
 * untriggeredconditions: contains all untriggered conditions
 * triggeredconditions: contains all triggered actions
 * actions: contains the actions that are associated with the rule
 * ruleEnabled: true if the rule is active
 */
public class RuleBlock extends BaseBlock {

	private BlockRepository untriggeredconditions = new BlockRepository(null);
    private BlockRepository triggeredconditions = new BlockRepository(null);
	private BlockRepository actions = new BlockRepository(null);
	private boolean ruleEnabled = true;

	public RuleBlock(String name, RULESTATUS status){
		setName(name);
		setStatus(status);
		setType(TYPE.INOUT);
		setLocatie("EDM");
		setStatusKlasseNaam(this.getClass().getName());
	}

	public BlockRepository getConditions() {
		return untriggeredconditions;
	}
	public BlockRepository getActions() {
		return actions;
	}
	public boolean getIsRuleEnabled(){
		return ruleEnabled;
	}
	public void setIsRuleEnabled(boolean ruleEnabled){
		this.ruleEnabled = ruleEnabled;
	}
    public void addNewCondition(BaseBlock block, Object status){
            BaseBlock toAdd = (BaseBlock)block.clone();
            toAdd.setStatus(status);
            untriggeredconditions.add(toAdd);
        }
    public void addNewAction(BaseBlock block, Object status){
            BaseBlock toAdd = (BaseBlock)block.clone();
            toAdd.setStatus(status);
            actions.add(toAdd);
        }

    public boolean triggerCondition(BaseBlock triggered){
            boolean foundCondition = false;
            //Guid checken of volledige condition checken, who cares
            //!check voor exacte conditie, maar maakt normaal niet. 
            for (Iterator<BaseBlock> it = untriggeredconditions.iterator(); it.hasNext();) {
                BaseBlock baseBlock = it.next();
                if(baseBlock.getId().equals(triggered.getId()))
                {
                    untriggeredconditions.remove(baseBlock);
                    triggeredconditions.add(baseBlock);
                    checkRuleTriggered();
                    foundCondition = true;
                }
            }
            return foundCondition;
        } //Returns true if it could find the condition
    public void checkRuleTriggered(){
            if(untriggeredconditions.isEmpty())
            {
                for (Iterator<BaseBlock> it = actions.iterator(); it.hasNext();) { //Elke actie: uitvoeren (andere thread: service)
                    BaseBlock action = it.next();
                   //TODO: voer regel uit in regelmanager -> ServiceRuleManager->executeEvent(block) --> Moet callback zijn
                }
            }
        }
    public void resetConditions(){
            untriggeredconditions.addAll(triggeredconditions);
            triggeredconditions.clear();
        }

	@Override
	public String getDescription() {
		if (super.getDescription().equals("Dit is een standaard " + this.getKlasse()))
		{
			return "Een regel met naam " + getName();
		}else
		{
			return super.getDescription();
		}
	}

}

