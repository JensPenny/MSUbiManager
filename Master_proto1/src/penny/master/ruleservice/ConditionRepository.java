/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package penny.master.ruleservice;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import penny.master.blockbase.BaseBlock;
import penny.master.blockbase.RuleBlock;
import penny.master.repositories.BlockRepository;
import penny.master.repositories.RuleRepository;

/**
 *
 * @author Jens
 */
public class ConditionRepository {
    //TODO: Does map allow doubles? These are needed
    private Map<BaseBlock, List<RuleBlock>> allConditions = new HashMap<BaseBlock, List<RuleBlock>>();

    //TODO: TEST DEES
    /**
     * The base constructor needs a main rule repo to extract the first conditions
     * @param repo the original repository
     */
    public ConditionRepository(RuleRepository repo){
        for (Iterator<RuleBlock> it = repo.iterator(); it.hasNext();) {
            RuleBlock ruleBlock = it.next();
            BlockRepository conditions = ruleBlock.getConditions();
            for (Iterator<BaseBlock> it1 = conditions.iterator(); it1.hasNext();) {
                BaseBlock baseBlock = it1.next();
                List<RuleBlock> l = allConditions.get(baseBlock);
                if(l == null) //This is silly, but needed. Trust me...
                    l = new RuleRepository();
                l.add(ruleBlock);
                allConditions.put(baseBlock, l); //All conditions for a rule added
            }
        }//All rules processed
    }

    /**
     * Add a sinlge condition to the condition repo
     * @param base block that represents a condition
     * @param connectedRule rule that is associated with the condition
     */
    public void addCondition(BaseBlock base, RuleBlock connectedRule){
        List<RuleBlock> l = allConditions.get(base);
        if ( l == null)
            l = new RuleRepository();
        l.add(connectedRule);
        allConditions.put(base, l);
    }

}
