package multi.thread.threadpool;

import multi.thread.produce.Producer;

public class ProduceThread implements Runnable {
    private Producer producer;

    public ProduceThread(Producer producer) {
        this.producer = producer;
    }
    public void run() {
        producer.produce();
    }
}
