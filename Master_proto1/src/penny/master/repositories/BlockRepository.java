package penny.master.repositories;

import java.util.ArrayList;

import penny.master.blockbase.BaseBlock;

/**
 * A collection of all found sensors. Excludes the rules that are found in the RuleRepository
 */
public class BlockRepository extends ArrayList<BaseBlock>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6271322042799569163L;

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
}
