package penny.master.proto1.edit;

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

public class EditListAdapter extends ArrayAdapter<BaseBlock>{
	private List<BaseBlock> items;
	private Context mContext;
	
	public EditListAdapter(Context context, int textViewResId, List<BaseBlock> items){
		super(context, textViewResId, items);
		this.items = items;
		this.mContext = context;
	}
	
	//TODO: Herschrijven voor grootte van rij
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
        	desc.setText("test");
        	status.setText("test");
        	img.setImageResource(R.drawable.icon);
        }
    	return v;
    }

}
