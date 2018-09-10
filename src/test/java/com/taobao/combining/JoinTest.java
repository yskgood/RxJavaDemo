package com.taobao.combining;

import io.reactivex.Observable;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @author huichi  shaokai.ysk@alibaba-inc.com
 * @Description:
 * @date 2018/9/5 上午6:51
 */
public class JoinTest {

    @Test
    public void test() throws InterruptedException {

        Observable<Long> ob1 = Observable.interval(0, 3, TimeUnit.SECONDS);

        Observable<Long> ob2 = Observable.interval(0, 1, TimeUnit.SECONDS);

        ob1.join(ob2,
                aLong -> {
                    //System.out.println("left接收了：" + aLong);
                    return Observable.empty();
                },
                aLong -> {
                    //System.out.println("right接收了：" + aLong);
                    return Observable.never();
                },
                (left, right) -> "left = " + left + ",right = " + right).
                blockingSubscribe(System.out::println);


    }

    @Test
    public void testJoinGroup() {
        Observable<Long> ob1 = Observable.interval(0, 1, TimeUnit.SECONDS);

        Observable<Long> ob2 = Observable.interval(2, 2, TimeUnit.SECONDS);
        ob1.groupJoin(ob2, aLong -> {
            System.out.println("left接收了：" + aLong);
            return Observable.never();
        }, aLong -> {
            System.out.println("right接收了：" + aLong);
            return Observable.never();
        }, (left, right) -> "left = " + left + ",right = " + right).blockingSubscribe(System.out::println);
    }
}

