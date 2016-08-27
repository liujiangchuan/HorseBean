package com.ll.horsebean.demo.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import com.ll.horsebean.R;
import com.ll.horsebean.common.DemoBaseActivity;
import com.ll.horsebean.common.view.dialog.DialogCreator;
import com.ll.services.helper.FLog;
import com.ll.services.view.titlebar.IFTitlebar;

import butterknife.OnClick;

/**
 * Created by Liujc on 2016/8/22.
 * Email: liujiangchuan@hotmail.com
 */
public class DialogActivity extends DemoBaseActivity
{
    @Override protected int getLayoutResource()
    {
        return R.layout.activity_dialog;
    }

    @Override protected void initTitlebar(IFTitlebar titlebar)
    {
        titlebar.setTitleText(getClass().getName());
    }

    @Override protected void onInit(Bundle savedInstanceState)
    {

    }

    @Override protected void loadData()
    {

    }

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5}) public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.btn1:
                DialogCreator.createMessageDialog(this, "title", "文字", "button",
                        new DialogInterface.OnClickListener()
                        {
                            @Override public void onClick(DialogInterface dialog, int which)
                            {
                                FLog.i("click");
                                dialog.dismiss();
                            }
                        }).show();
                break;
            case R.id.btn2:
                DialogCreator.createConfirmDialog(this, "title", "message", "pos", "neg",
                        new DialogInterface.OnClickListener()
                        {
                            @Override public void onClick(DialogInterface dialog, int which)
                            {
                                FLog.i("pos click");
                                dialog.dismiss();
                            }
                        }, new DialogInterface.OnClickListener()
                        {
                            @Override public void onClick(DialogInterface dialog, int which)
                            {
                                FLog.i("neg click");
                                dialog.dismiss();
                            }
                        }).show();
                break;
            case R.id.btn3:
                DialogCreator.createSingleChoiceDialog(this, "title",
                        new String[]{"item0", "item1", "item2"}, "pos", "neg",
                        new DialogInterface.OnClickListener()
                        {
                            @Override public void onClick(DialogInterface dialog, int which)
                            {
                                FLog.i("item which: " + which);
                                dialog.dismiss();
                            }
                        }, new DialogInterface.OnClickListener()
                        {
                            @Override public void onClick(DialogInterface dialog, int which)
                            {
                                FLog.i("pos click");
                                dialog.dismiss();
                            }
                        }, new DialogInterface.OnClickListener()
                        {
                            @Override public void onClick(DialogInterface dialog, int which)
                            {
                                FLog.i("neg click");
                                dialog.dismiss();
                            }
                        }).show();
                break;
            case R.id.btn4:
                DialogCreator.createMultiChoiceDialog(this, "title",
                        new String[]{"item0", "item1", "item2", "item3", "item4"}, "btn",
                        new DialogInterface.OnMultiChoiceClickListener()
                        {
                            @Override public void onClick(DialogInterface dialog, int which,
                                                          boolean isChecked)
                            {
                                FLog.i("item which: " + which + ", isChecked: " + isChecked);
                                dialog.dismiss();
                            }
                        }, new DialogInterface.OnClickListener()
                        {
                            @Override public void onClick(DialogInterface dialog, int which)
                            {
                                FLog.i("click");
                                dialog.dismiss();
                            }
                        }).show();

                break;
            case R.id.btn5:
                DialogCreator.createListDialog(this, new String[]{"item0,item1"}, "btn",
                        new DialogInterface.OnClickListener()
                        {
                            @Override public void onClick(DialogInterface dialog, int which)
                            {
                                FLog.i("item which: " + which);
                                dialog.dismiss();
                            }
                        }, new DialogInterface.OnClickListener()
                        {
                            @Override public void onClick(DialogInterface dialog, int which)
                            {
                                FLog.i("click");
                                dialog.dismiss();
                            }
                        }).show();
                break;
        }
    }
}
