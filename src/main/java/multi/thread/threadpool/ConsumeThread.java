package multi.thread.threadpool;

import multi.thread.consume.Consumer;
import multi.thread.result.ResultList;

public class ConsumeThread implements Runnable{
    private Consumer consumer ;
    private ResultList resultList;

    public ConsumeThread(Consumer consumer, ResultList resultList){
        this.consumer = consumer;
        this.resultList = resultList;
    }

    public void run() {
        consumer.comsum();
    }
}
