/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package penny.master.blockbase;

/**
 *
 * @author jens
 */
public class BaseSensorBlock extends BaseBlock implements ISensorBlock {

    public BaseSensorBlock(String name, Object status){
        setName(name);
        setStatus(status);
        setType(TYPE.INPUT);
    }

    public String getNaturalStatus() {
        return "De status van basisblokken is abstract en kan niet gebruikt worden";
    }
    
}
