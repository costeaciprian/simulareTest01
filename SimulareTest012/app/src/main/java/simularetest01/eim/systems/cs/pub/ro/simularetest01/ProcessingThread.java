package simularetest01.eim.systems.cs.pub.ro.simularetest01;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import java.sql.Date;
import java.util.Random;

/**
 * Created by ciprian on 3/28/2018.
 */

public class ProcessingThread extends Thread {

    private int left_num = 0, right_num = 0;
    private boolean isRunning = true;
    private int arith_mean = 0;
    private double geom_mean = 0;
    private Random rand = new Random();
    private Context context;

    ProcessingThread(Context context, int left_num, int right_num) {
        this.left_num = left_num;
        this.right_num = right_num;
        this.context = context;
    }

    @Override
    public void run() {
        super.run();

        arith_mean = (left_num + right_num) / 2;
        geom_mean = (Math.sqrt(left_num * right_num));

        while(isRunning) {
            sleep();
            sendMessage();
        }
    }

    private void sleep() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException interr) {
            interr.printStackTrace();
        }
    }

    public void stopThread() {
        isRunning = false;
    }

    public void sendMessage() {
        Intent intent = new Intent();
        intent.setAction(Constants.actions[rand.nextInt(Constants.actions.length)]);
        intent.putExtra("msg", new Date(System.currentTimeMillis()) +
                " " + arith_mean + " " + geom_mean);
        context.sendBroadcast(intent);
    }
}
