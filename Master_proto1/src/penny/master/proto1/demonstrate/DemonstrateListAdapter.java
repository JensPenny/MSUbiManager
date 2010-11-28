package penny.master.proto1.demonstrate;

import java.util.List;

import penny.master.blockbase.BaseBlock;
import penny.master.proto1.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 
 * @author jens
 * Adapter voor de listview in het Demonstreer - scherm
 */
public class DemonstrateListAdapter extends ArrayAdapter<BaseBlock>{
    private List<BaseBlock> items;
    private Context mContext;

    public DemonstrateListAdapter(Context context, int textViewResourceId, List<BaseBlock> items) {
            super(context, textViewResourceId, items);
            this.items = items;
            this.mContext = context;
    }
    
    /**
     * @param position: gekozen positie
     * @param convertView: View in het holder pattern: alleen nieuwe view bouwen bij instantiatie
     * @param parent: De std. ouder
     * @return View: De view van de rij in de lijst
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
    	View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(penny.master.proto1.R.layout.blockrow, null);
        }
        
        BaseBlock currObject = items.get(position);
        if (currObject != null){
        	TextView name = (TextView)v.findViewById(R.id.blockname);
        	TextView desc = (TextView)v.findViewById(R.id.blockdescription);
        	TextView loc = (TextView)v.findViewById(R.id.blockLocation);
        	TextView status = (TextView)v.findViewById(R.id.blockStatus);
        	ImageView img = (ImageView)v.findViewById(R.id.blockimgIcon);
        	
        	loc.setText("EDM");
        	name.setText(currObject.getName());
        	//desc.setText("Geen beschrijving gegeven");
        	desc.setText(currObject.getDescription());
        	status.setText(currObject.getStatus().toString());
        	//img.setImageResource(R.drawable.icon);
        	try{
        	img.setImageResource(currObject.getImageresID());
        	}catch (Exception ex){
        		//Kon de resource niet zetten -> standaard icon tonen
        	}
        }
    	return v;
    }
}
