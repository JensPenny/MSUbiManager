package penny.master.proto1.demonstrate;

import penny.master.blockbase.BaseBlock;
import penny.master.proto1.R;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class BlockDetailsDialog extends AlertDialog{
	private Context context;
	private AlertDialog dialog= null;
	private BaseBlock block;
	private int location;
	
	private AlertDialog.Builder builder;
	private Message returnmsg = null;

	protected BlockDetailsDialog(Context context, BaseBlock block, int location) {
		super(context);
		this.block = block;
		this.context = context;
		this.location = location;
		constructDialog();
	}

	public BlockDetailsDialog(Context context, boolean cancelable,
			OnCancelListener cancelListener, BaseBlock block, int location) {
		super(context, cancelable, cancelListener);
		this.block = block;
		this.context = context;
		this.location = location;
		constructDialog();
	}

	public BlockDetailsDialog(Context context, int theme, BaseBlock block, int location) {
		super(context, theme);
		this.block = block;
		this.context = context;
		this.location = location;
		constructDialog();
	}

	private void constructDialog(){
		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View dialogView = inflater.inflate(R.layout.blockinfo_dialog, (ViewGroup)findViewById(R.id.dialog_block_root));
		Button btnOk = (Button)dialogView.findViewById(R.id.dialog_btnOk);
		Button btnDelete = (Button)dialogView.findViewById(R.id.dialog_btnDelete);
		Button btnMove = (Button)dialogView.findViewById(R.id.dialog_btnMove);
		returnmsg = Message.obtain();
		
		ImageView icon = (ImageView)dialogView.findViewById(R.id.dialog_detail_icon);
		icon.setImageResource(block.getImageresID());
		TextView name = (TextView)dialogView.findViewById(R.id.dialog_detail_name);
		name.setText(block.getName());
		TextView desc = (TextView)dialogView.findViewById(R.id.dial_txtDescription);
		desc.setText(block.getDescription());
		//TODO: dial_spstatus -> vullen met enum | dial_txtklasse | 
		// dial_txtlocatie | 
		builder = new AlertDialog.Builder(context);
		builder.setView(dialogView);
		dialog = builder.create();
		
		btnOk.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
		btnDelete.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				returnmsg.what = 1;
				if (returnmsg.getTarget() != null)
					returnmsg.sendToTarget();
				dialog.dismiss();
			}
		});
		btnMove.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				returnmsg.what = 2;
				if (returnmsg.getTarget() != null)
					returnmsg.sendToTarget();
				dialog.dismiss();
			}
		});
	}
	public AlertDialog getDialog(){
		return dialog;
	}
	public void setReturnHandler(Handler handle){
		returnmsg = Message.obtain(handle);
		returnmsg.setTarget(handle);
		returnmsg.arg1 = location;
	}

}
