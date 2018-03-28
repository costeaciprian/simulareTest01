package simularetest01.eim.systems.cs.pub.ro.simularetest01;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by ciprian on 3/28/2018.
 */

public class MyService extends Service {

    private ProcessingThread prTh = null;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        int left_num = Integer.parseInt(intent.getStringExtra("left_clicks"));
        int right_num = Integer.parseInt(intent.getStringExtra("right_clicks"));

        prTh = new ProcessingThread(getApplicationContext(), left_num, right_num);

        prTh.start();

        return Service.START_REDELIVER_INTENT;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        prTh.stopThread();
    }
}
