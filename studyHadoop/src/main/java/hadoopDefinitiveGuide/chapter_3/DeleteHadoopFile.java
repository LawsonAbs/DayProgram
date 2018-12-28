package hadoopDefinitiveGuide.chapter_3;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.net.URI;

public class DeleteHadoopFile {
    public static void main(String[] args) throws IOException {
        Configuration conf = new Configuration();
        if(args.length<2){
            System.out.println("args not enough");
            System.exit(1);
        }
        String uri = args[0];
        FileSystem fs = FileSystem.get(URI.create(uri), conf);
        boolean recursive = new Boolean(args[1]);
        Path deltePath = new Path(args[0]);
        System.out.println("The file will be deleted is: "+deltePath.getName());
        fs.delete(deltePath, recursive);
        System.out.println("delete successfully!");
    }
}
