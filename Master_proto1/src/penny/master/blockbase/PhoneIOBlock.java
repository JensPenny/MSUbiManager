/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package penny.master.blockbase;
import penny.master.blockbase.dataenums.GSMSTATUS;

/**
 *
 * @author jens
 */
public class PhoneIOBlock extends BaseIOBlock{

    public PhoneIOBlock(String name, GSMSTATUS status) {
        super(name, status);
        
    }

    @Override
    public GSMSTATUS getStatus() {
        //return GSMSTATUS.valueOf(super.getStatus().toString());
        return (GSMSTATUS)super.getStatus();
    }



}
