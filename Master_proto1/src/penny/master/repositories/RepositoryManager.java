package penny.master.repositories;

public class RepositoryManager {
	
	private BlockRepository blockrepo = null; //Verzameling van blokken: nodig om nieuwe events manueel in te voeren
	private RuleRepository rulerepo = null; //Verzameling van regels: nodig om events aan te toetsen
	private BlockRepository eventrepo = null; //Verzameling van alle events die gebeurt zijn
	private boolean recording = false;
	
	/**
	 * 
	 * @return A list of all different blocks over all connected firmware (middleware needs to have sent all blocks: not implemented)
	 */
	public BlockRepository getBlockRepository(){
		if (blockrepo == null)
			blockrepo = new BlockRepository(null);
		return blockrepo;
	}
	/**
	 * 
	 * @return A map of all the rules
	 */
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
	/**
	 * Sets the application state to 'recording' for the listening socket
	 */
	public void startRecording()
	{
		recording = true;
	}
	/**
	 * Gracefully ends the recording for the listening socket
	 */
	public void stopRecording(){
		recording = false;
	}
	/**
	 * 
	 * @return The current state of the recording system
	 */
	public synchronized boolean getRecording(){
		return recording;
	}
	
	/**
	 * @return get the list of all current recorded events or build a new one
	 * TODO: this repo needs a max size
	 */
	public BlockRepository getEventRepository(){
		if (eventrepo == null)
			eventrepo = new BlockRepository(null);
		return eventrepo;
	}
	/**
	 * Reset the event repository (call when restart recording)
	 * This method is thread-safe
	 */
	public synchronized void resetEventRepository(){
		eventrepo.clear();
	}
}
