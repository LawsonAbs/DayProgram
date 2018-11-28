package multi.thread.produce;

import multi.thread.produce.Producer;
import multi.thread.result.ResultList;

public class ProducerOne extends Producer {

    public ProducerOne(String name, ResultList resultList) {
        super(name, resultList);
    }

    @Override
    public String produce() {
        String temp = "work 1 produce";
        String res = null;
        for(int i = 0;i < 10 ;i++) {
            res = temp + i;//追加
            synchronized (this.getResultList()) {//the lock of result
                this.getResultList().getLists().add(res);// add the string to resultList
                if (this.getResultList().getLists().size() > 10) {
                    try {
                        wait();// because the resultList's capacity limit ,so wait
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return res;
    }
}
