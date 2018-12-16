package multi.thread.produce;

import multi.thread.result.ResultList;

public class ProducerOne extends Producer{

    public ProducerOne(String name, ResultList resultList) {
        super(name, resultList);
    }

    @Override
    public void produce() {
        while(true) {
            synchronized (this.getResultList()) {//the lock of result
                //System.out.println("produce's size: " + this.getResultList().getLists().size());
                if (this.getResultList().getLists().size() > 0) {
                    try {
                        System.out.println("producer wait...");
                        this.getResultList().wait();//wait the consumer to consume
                        System.out.println("producer is waking...");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                if (this.getResultList().getLists().size() == 0) {
                    System.out.println("start produce...");
                    System.out.println("   before produce: "+this.getResultList().getLists().size());
                    this.getResultList().getLists().add("1");// add the string to resultList
                    System.out.println("   after produce: "+this.getResultList().getLists().size());
                    this.getResultList().notify();//notify the cosumer
                }
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
