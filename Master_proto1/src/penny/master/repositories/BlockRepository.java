package penny.master.repositories;

import java.util.ArrayList;
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
			if (b.getLocatie().equals(location))
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
}
