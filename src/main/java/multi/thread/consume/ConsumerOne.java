package multi.thread.consume;

import multi.thread.result.ResultList;

/**
 * 01.factual consumer
 */
public class ConsumerOne extends Consumer {
    public ConsumerOne(String name, ResultList resultList) {
        super(name, resultList);
    }

    @Override
    public void comsum() {
        while(true) {
            synchronized (this.getResultList()) {//lock the list
                if (this.getResultList().getLists().size() == 0) {
                    try {
                        System.out.println("consumer wait...");
                        this.getResultList().wait();
                        System.out.println("consumer is waking...");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //if have else,consume
                // the size is more than index [size = peek + 1]
                int index = this.getResultList().getLists().size() - 1;
                System.out.println("start consume：" + this.getResultList().getLists().get(index));
                System.out.println("   before consume: " + this.getResultList().getLists().size());
                this.getResultList().getLists().remove(index);// remove one element
                System.out.println("   after consume: " + this.getResultList().getLists().size());
                this.getResultList().notify();//notify the producer
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

/**
 01. wait 和 notify的顺序写反了，导致出现错误。
 **/