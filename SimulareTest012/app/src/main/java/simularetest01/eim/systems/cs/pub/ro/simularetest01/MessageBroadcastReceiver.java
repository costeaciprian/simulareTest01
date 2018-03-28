package simularetest01.eim.systems.cs.pub.ro.simularetest01;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by ciprian on 3/28/2018.
 */

public class MessageBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String mesg = intent.getStringExtra("msg");
        Log.d("[BroadcastReceiver]", mesg);
    }
}
