package multi.thread.produce;


import multi.thread.result.ResultList;

/**
 * 01.抽象类，代表生产者
 */
public abstract class Producer {
    private String name;//the name of producer
    private ResultList resultList;// the share resultList to write

    public Producer(String name, ResultList resultList) {
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

    //生产方法
    public void produce(){
    }
}
