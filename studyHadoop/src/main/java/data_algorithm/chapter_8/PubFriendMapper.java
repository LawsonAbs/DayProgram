package data_algorithm.chapter_8;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class PubFriendMapper extends Mapper<LongWritable,Text,Text,Text> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String[] line = value.toString().split("[,]");
        //System.out.println("line = "+ Arrays.toString(line));
        String curUser = line[0];
        String combineKey ;
        for(int i = 1;i < line.length;i++) {
            String friendList = "";
            if (sortUser(curUser, line[i]) < 0) {
                combineKey = "<" + curUser + "," + line[i] + ">";
            } else {
                combineKey = "<" + line[i] + "," + curUser + ">";
            }

            for(int j = 1;j< line.length ;j++) {
                if (j != i) {
                    friendList += line[j] ;
                    friendList += " ";
                }
            }

            System.out.println("combineKey = "+combineKey);
            System.out.println("friendList = "+friendList);
            context.write(new Text(combineKey),new Text(friendList));
        }
    }

    public int sortUser(String user_id,String friend) {
        return user_id.compareTo(friend);
    }

    public static void main(String[] args) {
        String str = "100,200,300,400,500,600";
        String[] line = str.split("[,]");
        String curUser = line[0];
        String combineKey ;
        for(int i = 1;i < line.length;i++) {
            String friendList = "";
            if (curUser.compareTo(line[i]) < 0) {
                combineKey = "<" + curUser + "," + line[i] + ">";
            } else {
                combineKey = "<" + line[i] + "," + curUser + ">";
            }

            for(int j = 1;j< line.length ;j++) {
                if (j != i) {
                    friendList += line[j] ;
                    friendList += " ";
                }
            }
            System.out.println("combineKey = "+combineKey);
            System.out.println("friendList = "+friendList);
        }
    }
}
