package penny.master.repositories;

public class RepositoryManager {
	
	private BlockRepository blockrepo = null;
	private RuleRepository rulerepo = null;
	
	public BlockRepository getBlockRepository(){
		if (blockrepo == null)
			blockrepo = new BlockRepository(null);
		return blockrepo;
	}
	public RuleRepository getRuleRepository(){
		if (rulerepo == null)
			rulerepo = new RuleRepository();
		return rulerepo;
	}
	
	public void setBlockRepository(BlockRepository repo){
		blockrepo = repo;
	}
	
	public void setRuleRepository(RuleRepository repo){
		rulerepo = repo;
	}
}
