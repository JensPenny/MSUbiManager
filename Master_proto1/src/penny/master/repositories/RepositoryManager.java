package penny.master.repositories;

public class RepositoryManager {
	
	private BlockRepository blockrepo = null; //Verzameling van blokken: nodig om nieuwe events manueel in te voeren
	private RuleRepository rulerepo = null; //Verzameling van regels: nodig om events aan te toetsen
	private BlockRepository eventrepo = null; //Verzameling van alle events die gebeurt zijn
	private boolean recording = false;
	
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
	//TODO: refactor
	public void startRecording()
	{
		recording = true;
	}
	public void stopRecording(){
		recording = false;
	}
	
	/*
	 * Initiate the repo for all the events
	 * TODO: This repo needs a max size
	 */
	public BlockRepository getEventRepository(){
		if (eventrepo == null)
			eventrepo = new BlockRepository(null);
		return eventrepo;
	}
	/*
	 * Reset the event repository (called when restart recording)
	 */
	public void resetEventRepository(){
		eventrepo = new BlockRepository(null);
	}
}
