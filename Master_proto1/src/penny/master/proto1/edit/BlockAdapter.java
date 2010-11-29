package penny.master.proto1.edit;

import penny.master.proto1.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

public class BlockAdapter extends BaseAdapter{

	private Context context;
	int gallerybackground;
	
	//TODO: Aanpassen -> Dit is een test
    private Integer[] imageids = {
            /*R.drawable.sample_1,
            R.drawable.sample_2,
            R.drawable.sample_3,
            R.drawable.sample_4,
            R.drawable.sample_5,
            R.drawable.sample_6,
            R.drawable.sample_7*/
    		R.drawable.blockbase_onbekende,
    		R.drawable.blockbase_conflicterende,
    		R.drawable.blockbase_alle
    };
	
	public BlockAdapter(Context c) {
		context = c;
        TypedArray a = c.obtainStyledAttributes(R.styleable.Gallery1);
        gallerybackground = a.getResourceId(R.styleable.Gallery1_android_galleryItemBackground, 0);
        a.recycle();
	}

	@Override
	public int getCount() {
		return imageids.length;
	}

	@Override
	public Object getItem(int position) {
		return imageids[position];
	}

	//ERRRRR???
	@Override
	public long getItemId(int position) {
		return position;
	}

	//TODO: O ho ho, dus dit MOET geen imageview zijn, maar zou ook een knop kunnen zijn? Testen!
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView i = new ImageView(context);
		i.setImageResource(imageids[position]);
		i.setLayoutParams(new Gallery.LayoutParams(110,60));
		i.setScaleType(ImageView.ScaleType.CENTER);
		i.setBackgroundResource(gallerybackground);
		return i;
	}

}
