/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package penny.master.blockbase;
import penny.master.blockbase.dataenums.GSMDISPLAYORIENTATION;

/**
 *
 * @author jens
 */
public class PhoneDisplayOrientationSensor extends BaseSensorBlock {

    public PhoneDisplayOrientationSensor(String name, GSMDISPLAYORIENTATION status){
        super(name, status);
    }

    @Override
    public GSMDISPLAYORIENTATION getStatus() {
        return (GSMDISPLAYORIENTATION)super.getStatus();
    }

}
