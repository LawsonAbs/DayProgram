package multi.thread.threadpool;

import crawler.utils.ip.consume.Consumer;

public class ConsumeThread implements Runnable{
    private Consumer consumer ;

    public ConsumeThread(Consumer consumer){
        this.consumer = consumer;
    }

    public void run() {
        consumer.comsum();
    }
}
