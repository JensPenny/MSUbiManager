/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package penny.master.blockbase;

import penny.master.blockbase.dataenums.BASESENSORSTATUS;
import penny.master.proto1.R;

/**
 *
 * @author jens
 */

//TODO: Deze klasse zit krom ineen, persoon sensor + persoon status moeten GESCHEIDEN zijn --> is een regel in het scenario
public class PersoonSensorBlock extends BaseSensorBlock{

    public PersoonSensorBlock(String name, BASESENSORSTATUS status){
            super(name, status);
            setImageresID(R.drawable.test_icon_sensor);
    }

    @Override
    public BASESENSORSTATUS getStatus() {
        return (BASESENSORSTATUS)super.getStatus();
    }

    @Override
    public String getNaturalStatus() {
        if (this.getStatus().equals(BASESENSORSTATUS.ON))
            return "De persoon is in het bereik van de sensor";
        else
            return "De persoon is buiten het bereik van de sensor";
    }

}
