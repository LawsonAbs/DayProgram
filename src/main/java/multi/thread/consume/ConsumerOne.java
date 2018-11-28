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
        if (this.getResultList().getLists().size() > 0) {
            synchronized (this.getResultList()){//lock the list
                int index = this.getResultList().getLists().size();
                System.out.println("Consume："+this.getResultList().getLists().get(index));
                this.getResultList().getLists().remove(index);// remove one element
                notify();//notify the producer
            }
        }
        else{// if the list don't have any element,so wait
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
