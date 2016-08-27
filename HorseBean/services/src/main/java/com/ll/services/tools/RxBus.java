package com.ll.services.tools;

import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by Liujc on 2016/8/21.
 * Email: liujiangchuan@hotmail.com
 */
public class RxBus
{
    private static RxBus ourInstance = new RxBus();
    private final Subject<Object, Object>
            rxBus = new SerializedSubject<>(PublishSubject.create());

    public static RxBus getInstance()
    {
        return ourInstance;
    }

    private RxBus()
    {
    }
}
