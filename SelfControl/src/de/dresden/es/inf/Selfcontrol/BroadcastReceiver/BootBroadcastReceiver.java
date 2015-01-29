package de.dresden.es.inf.Selfcontrol.BroadcastReceiver;

import de.dresden.es.inf.Selfcontrol.SuperService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootBroadcastReceiver extends BroadcastReceiver{
	@Override
    public void onReceive(Context context, Intent intent) {
        Intent startServiceIntent = new Intent(context, SuperService.class);
        context.startService(startServiceIntent);
    }
}
