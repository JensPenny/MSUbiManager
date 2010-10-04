package penny.master.blockbase;

import penny.master.blockbase.dataenums.RULESTATUS;
import penny.master.repositories.BlockRepository;

public class RuleBlock extends BaseBlock {
	
	private BlockRepository conditions = new BlockRepository(null);
	private BlockRepository actions = new BlockRepository(null);
	private boolean ruleEnabled = true;
	
	public RuleBlock(String name, RULESTATUS status){
		setName(name);
		setStatus(status);
		setType(TYPE.INOUT);
		setLocatie("EDM");
		setStatusKlasseNaam(this.getClass().getName());
	}
	
	/**
	 * @return a repo of all the conditions of this rule
	 */
	public BlockRepository getConditions() {
		return conditions;
	}
	public BlockRepository getActions() {
		return actions;
	}
	public void addCondition(BaseBlock toadd){
		conditions.add(toadd);
	}
	public void addAction(BaseBlock toadd){
		actions.add(toadd);
	}
	public boolean getIsRuleEnabled(){
		return ruleEnabled;
	}
	public void setIsRuleEnabled(boolean ruleEnabled){
		this.ruleEnabled = ruleEnabled;
	}
	public void executeRule(){
		//TODO: Execute the rule. ONLY implement this AFTER the ruleservice on android has been built
		//REASON: Possibility for grave errors / this functionality could be moved to the ruleService
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
