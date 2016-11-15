package com.ll.horsebean;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Liujc on 2016/8/17.
 * Email: liujiangchuan@hotmail.com
 */
public final class C
{
    public static final class sharedPreference
    {
    }

    public static final class statistic
    {
        /**
         * --------------------------------  demo  --------------------------------
         */
        //database
        public static final String DEMO_DATABASE_ADD = "demo_database_add";
        public static final String DEMO_DATABASE_DELETE = "demo_database_delete";
        //http
        public static final String DEMO_HTTP_REFRESH = "demo_http_refresh";
        //ui dialog
        public static final String DEMO_DIALOG_MESSAGE = "demo_dialog_message";
        public static final String DEMO_DIALOG_CONFIRM = "demo_dialog_confirm";
        public static final String DEMO_DIALOG_SINGLE_CHOICE = "demo_dialog_single_choice";
        public static final String DEMO_DIALOG_MULTI_CHOICE = "demo_dialog_multi_choice";
        public static final String DEMO_DIALOG_LIST = "demo_dialog_list";
    }

    public static final class test
    {
        public static final int RED = 0;
        public static final int GREEN = 1;
        public static final int YELLOW = 2;
    }

    @IntDef({test.RED, test.GREEN, test.YELLOW}) @Retention(RetentionPolicy.SOURCE)
    public @interface TestColors
    {
    }
}
