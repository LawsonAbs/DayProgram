package multi.thread.result;

import java.util.ArrayList;
import java.util.List;

/**
 * 01.the result is represented by List<String>
 */
public class ResultList {
    private List<String> lists = new ArrayList<String>();

    public List<String> getLists() {
        return lists;
    }

    public void setLists(List<String> lists) {
        this.lists = lists;
    }
}
