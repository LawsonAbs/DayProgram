package hadoopDefinitiveGuide.chapter_3;

import org.apache.hadoop.conf.Configuration;

import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.PathFilter;

import java.io.IOException;
import java.net.URI;

public class RegexExcludePathFilter implements PathFilter {
    private final String regex;

    public RegexExcludePathFilter(String regex) {
        this.regex = regex;
    }

    public boolean accept(Path path) {
        return !path.toString().matches(regex);
    }

    public static void main(String[] args) throws IOException {
        Configuration conf = new Configuration();
        String uri = args[0];
        //FileSystem can't get instance through new FileSystem(),so you must call get() method
        FileSystem fs = FileSystem.get(URI.create(uri), conf);
        FileStatus [] fileStatuses  = fs.globStatus(new Path("/*"),
                        new RegexExcludePathFilter("/input"));

        for(int i = 0;i< fileStatuses.length;i++) {
            System.out.println("accessTime: "+fileStatuses[i].getAccessTime());
            System.out.println("owner: "+fileStatuses[i].getOwner());
        }
    }
}
