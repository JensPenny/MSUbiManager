package penny.master.proto1;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.PreferenceActivity;

public class EditPreferences extends PreferenceActivity implements OnSharedPreferenceChangeListener{

	private EditTextPreference txtIncomingPort;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.mainpreferences);
		txtIncomingPort = (EditTextPreference)getPreferenceScreen().findPreference("inet_inc_port");
	}
	
	@Override
	protected void onResume() {
		super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
	}

	@Override
	public void onSharedPreferenceChanged(SharedPreferences prefs, String key) {
		UbiProtoMain main = (UbiProtoMain)this.getApplication();
		main.stopNetReceiver();
		main.buildAndStartNetReceiver(new Integer(prefs.getString(key, "2700")));
	}
	
	
}
