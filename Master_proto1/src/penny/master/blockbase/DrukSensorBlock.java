/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package penny.master.blockbase;

import penny.master.blockbase.dataenums.BASESENSORSTATUS;

/**
 *
 * @author jens
 */
public class DrukSensorBlock extends BaseSensorBlock{

    public DrukSensorBlock(String name, BASESENSORSTATUS status){
        super(name, status);
    }

    @Override
    public BASESENSORSTATUS getStatus() {
        return (BASESENSORSTATUS)super.getStatus();
    }

    @Override
    public String getNaturalStatus() {
        if (BASESENSORSTATUS.ON.equals(this.getStatus()))
            return "De druksensor " + getName() + " is ingedrukt";
        else
            return "De druksensor " + getName() + " is niet ingedrukt";
    }

}
