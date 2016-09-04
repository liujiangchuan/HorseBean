package com.ll.horsebean.demo.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;

import com.ll.horsebean.C;
import com.ll.horsebean.R;
import com.ll.horsebean.common.DemoBaseActivity;
import com.ll.horsebean.common.view.dialog.DialogCreator;
import com.ll.services.helper.FStatisticAgent;
import com.ll.services.tools.FToast;
import com.ll.services.view.titlebar.IFTitlebar;

import butterknife.BindString;
import butterknife.OnClick;

/**
 * Created by Liujc on 2016/8/22.
 * Email: liujiangchuan@hotmail.com
 */
public class DialogActivity extends DemoBaseActivity
{
    @BindString(R.string.ok) String mStrOk;
    @BindString(R.string.cancel) String mStrCancel;

    private String mTitle;
    private String mMessage;
    private String[] mItems;
    private int mSingleChoiceIndex;
    private SparseArray<String> mMultiChoiceItem;

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
        mTitle = "- Title -";
        mMessage = "Message...";
        mItems = new String[]{"Item0", "Item1", "Item2"};
        mSingleChoiceIndex = 0;
        mMultiChoiceItem = new SparseArray<>();
    }

    @Override protected void loadData()
    {

    }

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5}) public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.btn1:
                DialogCreator.createMessageDialog(this, mTitle, mMessage, mStrOk,
                        new DialogInterface.OnClickListener()
                        {
                            @Override public void onClick(DialogInterface dialog, int which)
                            {
                                FToast.showShort(mStrOk);
                                dialog.dismiss();
                            }
                        }).show();
                FStatisticAgent.onEvent(C.statistic.DEMO_DIALOG_MESSAGE);
                break;
            case R.id.btn2:
                DialogCreator.createConfirmDialog(this, mTitle, mMessage, mStrOk, mStrCancel,
                        new DialogInterface.OnClickListener()
                        {
                            @Override public void onClick(DialogInterface dialog, int which)
                            {
                                FToast.showShort(mStrOk);
                                dialog.dismiss();
                            }
                        }, new DialogInterface.OnClickListener()
                        {
                            @Override public void onClick(DialogInterface dialog, int which)
                            {
                                FToast.showShort(mStrCancel);
                                dialog.dismiss();
                            }
                        }).show();
                FStatisticAgent.onEvent(C.statistic.DEMO_DIALOG_CONFIRM);
                break;
            case R.id.btn3:
                mSingleChoiceIndex = 0;
                DialogCreator.createSingleChoiceDialog(this, mTitle, mItems, mStrOk, mStrCancel,
                        new DialogInterface.OnClickListener()
                        {
                            @Override public void onClick(DialogInterface dialog, int which)
                            {
                                mSingleChoiceIndex = which;
                            }
                        }, new DialogInterface.OnClickListener()
                        {
                            @Override public void onClick(DialogInterface dialog, int which)
                            {
                                FToast.showShort("Single choice: " + mItems[mSingleChoiceIndex]);
                                dialog.dismiss();
                            }
                        }, new DialogInterface.OnClickListener()
                        {
                            @Override public void onClick(DialogInterface dialog, int which)
                            {
                                FToast.showShort(R.string.cancel);
                                dialog.dismiss();
                            }
                        }).show();
                FStatisticAgent.onEvent(C.statistic.DEMO_DIALOG_SINGLE_CHOICE);
                break;
            case R.id.btn4:
                mMultiChoiceItem.clear();
                DialogCreator.createMultiChoiceDialog(this, mTitle, mItems, mStrOk, mStrCancel,
                        new DialogInterface.OnMultiChoiceClickListener()
                        {
                            @Override public void onClick(DialogInterface dialog, int which,
                                                          boolean isChecked)
                            {
                                if (isChecked)
                                {
                                    mMultiChoiceItem.append(which, mItems[which]);
                                }
                                else
                                {
                                    mMultiChoiceItem.delete(which);
                                }
                            }
                        }, new DialogInterface.OnClickListener()
                        {
                            @Override public void onClick(DialogInterface dialog, int which)
                            {
                                StringBuilder stringBuilder = new StringBuilder();
                                int length = mItems.length;
                                for (int i = 0; i < length; i++)
                                {
                                    String item = mMultiChoiceItem.get(i);
                                    if (!TextUtils.isEmpty(item))
                                    {
                                        stringBuilder.append(item);
                                        stringBuilder.append(",");
                                    }
                                }
                                if (stringBuilder.length() > 0)
                                {
                                    FToast.showShort(
                                            stringBuilder.deleteCharAt(stringBuilder.length() - 1)
                                                    .toString());
                                }
                                else
                                {
                                    FToast.showShort("selected nothing!");
                                }
                                dialog.dismiss();
                            }
                        }, new DialogInterface.OnClickListener()
                        {
                            @Override public void onClick(DialogInterface dialog, int which)
                            {
                                FToast.showShort(R.string.cancel);
                                dialog.dismiss();
                            }
                        }).show();
                FStatisticAgent.onEvent(C.statistic.DEMO_DIALOG_MULTI_CHOICE);
                break;
            case R.id.btn5:
                DialogCreator.createListDialog(this, mItems, mStrCancel,
                        new DialogInterface.OnClickListener()
                        {
                            @Override public void onClick(DialogInterface dialog, int which)
                            {
                                FToast.showShort("List select: " + mItems[which]);
                                dialog.dismiss();
                            }
                        }, new DialogInterface.OnClickListener()
                        {
                            @Override public void onClick(DialogInterface dialog, int which)
                            {
                                FToast.showShort(R.string.cancel);
                                dialog.dismiss();
                            }
                        }).show();
                FStatisticAgent.onEvent(C.statistic.DEMO_DIALOG_LIST);
                break;
        }
    }
}
