package com.ll.horsebean.common.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;

/**
 * Created by Liujc on 2016/7/21.
 * Email: liujiangchuan@hotmail.com
 */
public class DialogCreator
{
    /**
     * Create a dialog with message.
     *
     * @param context
     * @param title
     * @param message
     * @param btnName
     * @param listener
     * @return
     */
    public static Dialog createMessageDialog(Context context, String title, String message,
                                             String btnName,
                                             DialogInterface.OnClickListener listener)
    {
        Dialog dialog;
        CommonAlertDialog.Builder builder = new CommonAlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(btnName, listener);
        dialog = builder.create();
        return dialog;
    }

    public static Dialog createMessageDialog(Context context, String title, String message)
    {
        return createMessageDialog(context, title, message, null, null);
    }

    public static Dialog createMessageDialog(Context context, String message)
    {
        return createMessageDialog(context, null, message);
    }

    public static Dialog createMessageDialog(Context context, String message, String btnName,
                                             DialogInterface.OnClickListener listener)
    {
        return createMessageDialog(context, null, message, btnName, listener);
    }

    /**
     * Create a dialog about confirm.
     *
     * @param context
     * @param title
     * @param message
     * @param posBtnName
     * @param negBtnName
     * @param posBtnListener
     * @param negBtnListener
     * @return
     */
    public static Dialog createConfirmDialog(Context context, String title, String message,
                                             String posBtnName, String negBtnName,
                                             DialogInterface.OnClickListener posBtnListener,
                                             DialogInterface.OnClickListener negBtnListener)
    {
        Dialog dialog;
        CommonAlertDialog.Builder builder = new CommonAlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(posBtnName, posBtnListener);
        builder.setNegativeButton(negBtnName, negBtnListener);
        dialog = builder.create();
        return dialog;
    }

    public static Dialog createConfirmDialog(Context context, String message, String posBtnName,
                                             String negBtnName,
                                             DialogInterface.OnClickListener posBtnListener,
                                             DialogInterface.OnClickListener negBtnListener)
    {
        return createConfirmDialog(context, null, message, posBtnName, negBtnName, posBtnListener,
                negBtnListener);
    }

    /**
     * Create a dialog with edit.
     *
     * @param context
     * @param title
     * @param posBtnName
     * @param negBtnName
     * @param posBtnListener
     * @param negBtnListener
     * @return
     */
    public static Dialog createEditDialog(Context context, String title, String editHint,
                                          String posBtnName, String negBtnName,
                                          CommonAlertDialog.OnEditClickListener posBtnListener,
                                          DialogInterface.OnClickListener negBtnListener)
    {
        Dialog dialog;
        CommonAlertDialog.Builder builder = new CommonAlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setEditHint(editHint);
        builder.setPositiveButton(posBtnName, posBtnListener);
        builder.setNegativeButton(negBtnName, negBtnListener);
        dialog = builder.create();
        return dialog;
    }

    public static Dialog createEditDialog(Context context, String editHint, String posBtnName,
                                          String negBtnName,
                                          CommonAlertDialog.OnEditClickListener posBtnListener,
                                          DialogInterface.OnClickListener negBtnListener)
    {
        return createEditDialog(context, null, editHint, posBtnName, negBtnName, posBtnListener,
                negBtnListener);
    }

    /**
     * Create a dialog with single choice.
     * Using system alert dialog.
     *
     * @param context
     * @param title
     * @param items
     * @param posBtnName
     * @param negBtnName
     * @param itemListener
     * @param posBtnListener
     * @param negBtnListener
     * @return
     */
    public static Dialog createSingleChoiceDialog(Context context, String title, String[] items,
                                                  String posBtnName, String negBtnName,
                                                  DialogInterface.OnClickListener itemListener,
                                                  DialogInterface.OnClickListener posBtnListener,
                                                  DialogInterface.OnClickListener negBtnListener)
    {
        Dialog dialog;
        CommonAlertDialog.Builder builder = new CommonAlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setSingleChoiceItems(items, 0, itemListener);
        builder.setPositiveButton(posBtnName, posBtnListener);
        builder.setNegativeButton(negBtnName, negBtnListener);
        dialog = builder.create();
        return dialog;
    }

    public static Dialog createSingleChoiceDialog(Context context, String[] items,
                                                  String posBtnName, String negBtnName,
                                                  DialogInterface.OnClickListener itemListener,
                                                  DialogInterface.OnClickListener posBtnListener,
                                                  DialogInterface.OnClickListener negBtnListener)
    {
        return createSingleChoiceDialog(context, null, items, posBtnName, negBtnName, itemListener,
                posBtnListener, negBtnListener);
    }

    public static Dialog createSingleChoiceDialog(Context context, String title, String[] items,
                                                  String btnName,
                                                  DialogInterface.OnClickListener itemListener,
                                                  DialogInterface.OnClickListener btnListener)
    {
        return createSingleChoiceDialog(context, title, items, btnName, null, itemListener,
                btnListener, null);
    }

    public static Dialog createSingleChoiceDialog(Context context, String[] items, String btnName,
                                                  DialogInterface.OnClickListener itemListener,
                                                  DialogInterface.OnClickListener btnListener)
    {
        return createSingleChoiceDialog(context, null, items, btnName, itemListener, btnListener);
    }

    public static Dialog createSingleChoiceDialog(Context context, String title, String[] items,
                                                  DialogInterface.OnClickListener itemListener)
    {
        return createSingleChoiceDialog(context, title, items, null, itemListener, null);
    }

    public static Dialog createSingleChoiceDialog(Context context, String[] items,
                                                  DialogInterface.OnClickListener itemListener)
    {
        return createSingleChoiceDialog(context, null, items, itemListener);
    }

    /**
     * Create a dialog with multi choice.
     * Using system alert dialog.
     *
     * @param context
     * @param title
     * @param items
     * @param posBtnName
     * @param negBtnName
     * @param itemListener
     * @param posBtnListener
     * @param negBtnListener
     * @return
     */
    public static Dialog createMultiChoiceDialog(Context context, String title, String[] items,
                                                 String posBtnName, String negBtnName,
                                                 DialogInterface.OnMultiChoiceClickListener itemListener,
                                                 DialogInterface.OnClickListener posBtnListener,
                                                 DialogInterface.OnClickListener negBtnListener)
    {
        Dialog dialog;
        CommonAlertDialog.Builder builder = new CommonAlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMultiChoiceItems(items, itemListener);
        builder.setPositiveButton(posBtnName, posBtnListener);
        builder.setNegativeButton(negBtnName, negBtnListener);
        dialog = builder.create();
        return dialog;
    }

    public static Dialog createMultiChoiceDialog(Context context, String[] items, String posBtnName,
                                                 String negBtnName,
                                                 DialogInterface.OnMultiChoiceClickListener itemListener,
                                                 DialogInterface.OnClickListener posBtnListener,
                                                 DialogInterface.OnClickListener negBtnListener)
    {
        return createMultiChoiceDialog(context, null, items, posBtnName, negBtnName, itemListener,
                posBtnListener, negBtnListener);
    }

    public static Dialog createMultiChoiceDialog(Context context, String title, String[] items,
                                                 String btnName,
                                                 DialogInterface.OnMultiChoiceClickListener itemListener,
                                                 DialogInterface.OnClickListener btnListener)
    {
        return createMultiChoiceDialog(context, title, items, btnName, null, itemListener,
                btnListener, null);
    }

    public static Dialog createMultiChoiceDialog(Context context, String[] items, String btnName,
                                                 DialogInterface.OnMultiChoiceClickListener itemListener,
                                                 DialogInterface.OnClickListener btnListener)
    {
        return createMultiChoiceDialog(context, null, items, btnName, itemListener, btnListener);
    }

    /**
     * Create a dialog with a list to choose.
     * Using system alert dialog.
     *
     * @param context
     * @param title
     * @param items
     * @param btnName
     * @param itemListener
     * @param btnListener
     * @return
     */
    public static Dialog createListDialog(Context context, String title, String[] items,
                                          String btnName,
                                          DialogInterface.OnClickListener itemListener,
                                          DialogInterface.OnClickListener btnListener)
    {
        Dialog dialog;
        CommonAlertDialog.Builder builder = new CommonAlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setItems(items, itemListener);
        builder.setNegativeButton(btnName, btnListener);
        dialog = builder.create();
        return dialog;
    }

    public static Dialog createListDialog(Context context, String[] items, String btnName,
                                          DialogInterface.OnClickListener itemListener,
                                          DialogInterface.OnClickListener btnListener)
    {
        return createListDialog(context, null, items, btnName, itemListener, btnListener);
    }

    public static Dialog createListDialog(Context context, String title, String[] items,
                                          DialogInterface.OnClickListener itemListener)
    {
        return createListDialog(context, title, items, null, itemListener, null);
    }

    public static Dialog createListDialog(Context context, String[] items,
                                          DialogInterface.OnClickListener itemListener)
    {
        return createListDialog(context, null, items, itemListener);
    }

    /**
     * Create a dialog with a custom view.
     *
     * @param context
     * @param title
     * @param posBtnName
     * @param negBtnName
     * @param view
     * @param posBtnListener
     * @param negBtnListener
     * @return
     */
    public static Dialog createCustomDialog(Context context, String title, String posBtnName,
                                            String negBtnName, View view,
                                            DialogInterface.OnClickListener posBtnListener,
                                            DialogInterface.OnClickListener negBtnListener)
    {
        Dialog dialog;
        CommonAlertDialog.Builder builder = new CommonAlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setView(view);
        builder.setPositiveButton(posBtnName, posBtnListener);
        builder.setNegativeButton(negBtnName, negBtnListener);
        dialog = builder.create();
        return dialog;
    }

    public static Dialog createCustomDialog(Context context, String posBtnName, String negBtnName,
                                            View view,
                                            DialogInterface.OnClickListener posBtnListener,
                                            DialogInterface.OnClickListener negBtnListener)
    {
        return createCustomDialog(context, null, posBtnName, negBtnName, view, posBtnListener,
                negBtnListener);
    }

    public static Dialog createCustomDialog(Context context, String title, String btnName,
                                            View view, DialogInterface.OnClickListener listener)
    {
        return createCustomDialog(context, title, btnName, null, view, listener, null);
    }

    public static Dialog createCustomDialog(Context context, String btnName, View view,
                                            DialogInterface.OnClickListener listener)
    {
        return createCustomDialog(context, btnName, null, view, listener, null);
    }

    public static Dialog createCustomDialog(Context context, String title, View view)
    {
        return createCustomDialog(context, title, null, view, null);
    }

    public static Dialog createCustomDialog(Context context, View view)
    {
        return createCustomDialog(context, null, view);
    }
}
