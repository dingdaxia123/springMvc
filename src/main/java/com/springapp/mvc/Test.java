package com.springapp.mvc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by dinghy on 2017/7/11.
 */
public class Test {
    public static void main(String[] args) throws ExecutionException,
            InterruptedException {
        System.out.println("----Start----");
        Date date1 = new Date();

        int taskSize = 5;
        // ����һ���̳߳�
        ExecutorService pool = Executors.newFixedThreadPool(taskSize);
        // ��������з���ֵ������
        List<Future> list = new ArrayList<Future>();
        for (int i = 0; i < taskSize; i++) {
            Callable c = new MyCallable(i + " ");
            // ִ�����񲢻�ȡFuture����
            Future f = pool.submit(c);
            // System.out.println(">>>" + f.get().toString());
            list.add(f);
        }
        // �ر��̳߳�
        pool.shutdown();

        // ��ȡ���в�����������н��
        for (Future f : list) {
            // ��Future�����ϻ�ȡ����ķ���ֵ�������������̨
            System.out.println(">>>" + f.get().toString());
        }

        Date date2 = new Date();
        System.out.println("----End----,Program time["
                + (date2.getTime() - date1.getTime()) + "ms]");
    }
}

class MyCallable implements Callable<Object> {
    private String taskNum;

    MyCallable(String taskNum) {
        this.taskNum = taskNum;
    }

    public Object call() throws Exception {
        System.out.println(">>>" + taskNum + "Task starts");
        Date dateTmp1 = new Date();
        Thread.sleep(1000);
        Date dateTmp2 = new Date();
        long time = dateTmp2.getTime() - dateTmp1.getTime();
        System.out.println(">>>" + taskNum + "Task end");
        return taskNum + "Task return run result,current task time[" + time + "ms]";
    }
}

