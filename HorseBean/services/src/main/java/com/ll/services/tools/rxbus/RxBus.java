package com.ll.services.tools.rxbus;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by Liujc on 2016/8/21.
 * Email: liujiangchuan@hotmail.com
 */
public class RxBus
{
    private static volatile RxBus ourInstance = new RxBus();
    private final Subject<Object, Object> rxBus;

    public static RxBus getInstance()
    {
        return ourInstance;
    }

    private RxBus()
    {
        rxBus = new SerializedSubject<>(PublishSubject.create());
    }

    public void post(Object o)
    {
        rxBus.onNext(o);
    }

    public <T> Observable<T> toObservable(Class<T> eventType)
    {
        return rxBus.ofType(eventType);
    }
}
