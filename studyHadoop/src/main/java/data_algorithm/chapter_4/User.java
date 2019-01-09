package data_algorithm.chapter_4;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class User implements Writable, WritableComparable<User>{
    private String user_id;
    private int level;//the level of 1 or 2
    private String location;//user location
    private String product_name;//the product name of user buy

    //为什么需要添加无参的构造函数
    public User() {
    }

    public User(String user_id, int level, String location, String product_name) {
        this.user_id = user_id;
        this.level = level;
        this.location = location;
        this.product_name = product_name;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int compareTo(User user) {
        int compareValue = this.getUser_id().compareTo(user.getUser_id());
        if (compareValue == 0) {
            //return this.getLevel() - user.getLevel();  //先出现 location, 再出现 product
            return user.getLevel() - this.getLevel(); //先出现product , 再出现 location
        }
        return compareValue;
    }

    public void write(DataOutput out) throws IOException {
        out.writeUTF(this.user_id);
        out.writeInt(this.level);
        out.writeUTF(this.location);
        out.writeUTF(this.product_name);
    }

    public void readFields(DataInput in) throws IOException {
        this.user_id = in.readUTF();
        this.level = in.readInt();
        this.location = in.readUTF();
        this.product_name = in.readUTF();
    }

    @Override
    public int hashCode() {
        int result = this.getUser_id() != null ? this.getUser_id().hashCode() : 0;
        //result = 31 * result + level ;
        //不能使用这个+ level 值的，否则会将相同user_id 的分到不同的区？
        if (this.getUser_id().equals("u1")) {
            System.out.println("u1");
            return 1;
        }
        if (this.getUser_id().equals("u2")) {
            System.out.println("u2");
            return 2;
        }
//        if (this.getUser_id().equals("u3")) return 3;
//        if (this.getUser_id().equals("u4")) return 4;
//        if (this.getUser_id().equals("u5")) return 5;

        return result;
    }

    @Override
    public String toString() {
        return this.getUser_id();
    }
}
