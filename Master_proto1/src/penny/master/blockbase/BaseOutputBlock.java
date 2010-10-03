/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package penny.master.blockbase;
/**
 *
 * @author jens
 */
public class BaseOutputBlock extends BaseBlock {

    public BaseOutputBlock(String name, Object status){
        setName(name);
        setStatus(status);
        setType(TYPE.OUTPUT);
    }
}
