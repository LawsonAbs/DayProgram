package multi.thread.threadpool;

import multi.thread.produce.Producer;
import multi.thread.result.ResultList;

public class ProduceThread implements Runnable {
    private Producer producer;
    private ResultList resultList;

    public ProduceThread(Producer producer, ResultList resultList) {
        this.producer = producer;
        this.resultList = resultList;
    }
    public void run() {
        producer.produce();
    }
}
