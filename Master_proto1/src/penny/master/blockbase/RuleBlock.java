package penny.master.blockbase;

import penny.master.blockbase.dataenums.RULESTATUS;
import penny.master.repositories.BlockRepository;

public class RuleBlock extends BaseBlock {
	
	private BlockRepository conditions = new BlockRepository(null);
	private BlockRepository actions = new BlockRepository(null);
	
	public RuleBlock(String name, RULESTATUS status){
		setName(name);
		setStatus(status);
		setType(TYPE.INOUT);
		setLocatie("EDM");
		setStatusKlasseNaam(this.getClass().getName());
	}
	
	public BlockRepository getConditions() {
		return conditions;
	}
	public BlockRepository getActions() {
		return actions;
	}

}
