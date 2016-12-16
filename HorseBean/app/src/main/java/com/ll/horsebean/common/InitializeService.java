package com.ll.horsebean.common;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Liujc on 2016/12/16.
 * Email: liujiangchuan@hotmail.com
 */
public class InitializeService extends IntentService
{
    private static final String ACTION_INIT_WHEN_APP_CREATE = "service.action.INIT";

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     */
    public InitializeService()
    {
        //Used to name the worker thread, important only for debugging.
        super("InitializeService");
    }

    public static void start(Context context)
    {
        Intent intent = new Intent(context, InitializeService.class);
        intent.setAction(ACTION_INIT_WHEN_APP_CREATE);
        context.startService(intent);
    }


    @Override
    protected void onHandleIntent(Intent intent)
    {
        if (intent != null)
        {
            final String action = intent.getAction();
            if (ACTION_INIT_WHEN_APP_CREATE.equals(action))
            {
                performInit();
            }
        }
    }

    private void performInit()
    {

    }
}
