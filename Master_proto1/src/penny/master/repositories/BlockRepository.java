package penny.master.repositories;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import penny.master.blockbase.BaseBlock;

/**
 * A collection of all found sensors. Excludes the rules that are found in the RuleRepository
 */
public class BlockRepository extends ArrayList<BaseBlock>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6271322042799569163L;
	private ArrayList<ListChangeListener> listeners = new ArrayList<ListChangeListener>();
	
	public BlockRepository()
	{
		//Thinking...still thinking
	}
	/**
	 *
	 * @param base: A possible baselist. Can be null
	 */
	public BlockRepository(ArrayList<BaseBlock> base)
	{
		if (base != null){
			this.addAll(base);
		}
	}
	
	//TODO: Abstraheren zodat deze iterator/functies ook bruikbaar zijn voor regels
	
	/**
	 * @param location The location where the sensor is located. Should match the location perfectly
	 * @return a repository of all blocks in the given location
	 */
	public BlockRepository getBlocksByLocation(String location){
		BlockRepository toreturn = new BlockRepository();
		Iterator<BaseBlock> iter = this.iterator();
		while (iter.hasNext()){
			BaseBlock b = iter.next();
			if (b.getLocation().equals(location))
				toreturn.add(b);
		}
		return toreturn;
	}
	
	/**
	 * 
	 * @param classToFind the class the blocks that are wanted should be
	 * @param getSubClassesToo true if you need subclasses of the given class, false if you just need the class
	 * @return a repository of all blocks of a given (sub?)class
	 */
	public BlockRepository getBlocksByClass(Class<?> classToFind, boolean getSubClassesToo)
	{
		BlockRepository toreturn = new BlockRepository();
		Iterator<BaseBlock> iter = this.iterator();
		while (iter.hasNext()){
			BaseBlock b = iter.next();
			if(getSubClassesToo)
			{
				if(b.getClass().isInstance(classToFind)){
					toreturn.add(b);
				}
			}else
			{
				if(b.getKlasse().equals(classToFind.getCanonicalName()));
			}
		}
		return toreturn;
	}
	
	/* Overridden to add the notify - call */
	@Override
	public boolean add(BaseBlock object) {
		boolean toreturn = super.add(object);
		notifyAllChangeListeners();
		return toreturn;
	}
	@Override
	public void add(int index, BaseBlock object) {
		super.add(index, object);
		notifyAllChangeListeners();
	}
	@Override
	public boolean addAll(Collection<? extends BaseBlock> collection) {
		boolean toreturn =  super.addAll(collection);
		notifyAllChangeListeners();
		return toreturn;
	}
	@Override
	public boolean addAll(int location, Collection<? extends BaseBlock> collection) {
		boolean toreturn =  super.addAll(location, collection);
		notifyAllChangeListeners();
		return toreturn;
	}
	@Override
	public void clear() {
		super.clear();
		notifyAllChangeListeners();
	}
	@Override
	public BaseBlock remove(int index) {
		BaseBlock toreturn =  super.remove(index);
		notifyAllChangeListeners();
		return toreturn;
	}
	@Override
	public boolean remove(Object object) {
		boolean toreturn =  super.remove(object);
		notifyAllChangeListeners();
		return toreturn;
	}
	//Listener methods
	public void addChangeListener(ListChangeListener l){
		listeners.add(l);
	}
	public void removeChangeListener(ListChangeListener l){
		if (listeners.contains(l))
			listeners.remove(listeners.indexOf(l));
	}
	public void notifyAllChangeListeners(){
		for(ListChangeListener l : listeners){
			l.fireChangedEvent();
		}
	}
}
