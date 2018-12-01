package multi.thread.consume;

import multi.thread.result.ResultList;

/**
 * 01.代表抽象消费者
 */
public abstract class Consumer {
    private String name ;//the name of Consumer
    private ResultList resultList;// the shared resultList to read/write

    public Consumer(String name, ResultList resultList) {
        this.name = name;
        this.resultList = resultList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ResultList getResultList() {
        return resultList;
    }

    public void setResultList(ResultList resultList) {
        this.resultList = resultList;
    }

    public void comsum(){}

}
