package data_algorithm.chapter_4;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class SecondarySortGroupComparator extends WritableComparator {

    //这个构造参数一定要实现，否则会报错
    public SecondarySortGroupComparator() {
        super(User.class,true);
    }

    /*
    如果这个Group 类中的compare 方法是针对每个mapper 进行一次使用的话，那么就无效；
    如果是针对所有的mapper进行一次使用，那么则是有效的。
    现在遇到的问题就是：在使用两个Mapper之后，无论如何都无法合并两个输出，得到的结果集总是如下的样子：
    u1	UT,
    u2	GA,
    u3	CA,
    u4	CA,
    u5	GA,
    u2	p2,p1,
    u4	p4,
    u1	p4,p1,p1,p3,
     */
    @Override
    public int compare(WritableComparable wc1, WritableComparable wc2) {
        //System.out.println("start group comparator....");
        User user1 = (User)wc1;
        User user2 = (User)wc2;

        boolean res = user1.getUser_id().equals(user2.getUser_id());
        System.out.println("user1.getUser_id"+user1.getUser_id()+",res: "+res);
        int status = res ? 0: 1;
        System.out.println(status);
        return  status;
    }

}
