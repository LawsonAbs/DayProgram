package multi.thread.entrance;

import crawler.utils.ip.consume.Consumer;
import crawler.utils.ip.consume.ConsumerOne;
import multi.thread.produce.Producer;
import multi.thread.produce.ProducerOne;
import multi.thread.result.ResultList;

public class Main {
    public static void main(String[] args) {
        ResultList resultList = new ResultList();
        Consumer consumer = new ConsumerOne("con1",resultList);
        Producer producer = new ProducerOne("pro1",resultList);

        //new Thread(Runnable)
        Thread threadConsumer = new Thread(consumer);
        Thread threadProducer = new Thread(producer);

        threadProducer.setName("producer");
        threadConsumer.setName("consumer");

        threadConsumer.start();
        threadProducer.start();
    }
}
