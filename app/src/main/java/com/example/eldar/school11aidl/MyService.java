package com.example.eldar.school11aidl;


import android.app.IntentService;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

public class MyService extends Service {
    private final static int MODE = Service.START_NOT_STICKY;
    Messenger messenger = new Messenger(new IncomingHandler());
    List<Messenger> clients = new ArrayList<>();
    List<String> list = new ArrayList<String>(Arrays.asList("John","Albert","Vladimir","Eldar"));
    DataStorage dataStorage = new DataStorage(this);
    private IBinder iBinder = new LocalBinder();




    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }



    public class LocalBinder extends Binder {
        MyService getService() {
            return MyService.this;
        }
    }



    public static final Intent newIntent(Context context) {
        Intent intent = new Intent(context, MyService.class);
        return intent;
    }


    private class IncomingHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    clients.add(msg.replyTo);
                    dataStorage.setUserName("Eldar");
                    msg = Message.obtain();
                    msg.obj = dataStorage.getUserName();
                    msg.replyTo = messenger;
                    try {
                        clients.get(0).send(msg);
                    } catch (RemoteException e) {

                    }
            }

        }
    }



}
