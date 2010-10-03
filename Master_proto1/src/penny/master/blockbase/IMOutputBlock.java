/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package penny.master.blockbase;

import penny.master.blockbase.dataenums.IMSTATUS;

/**
 *
 * @author jens
 */
public class IMOutputBlock extends BaseOutputBlock{

    public IMOutputBlock(String name, IMSTATUS status){
        super(name, status);
    }

    @Override
    public IMSTATUS getStatus() {
        return (IMSTATUS)super.getStatus();
    }

    @Override
    public void setStatus(Object status) {
        super.setStatus(status);
    }


}
