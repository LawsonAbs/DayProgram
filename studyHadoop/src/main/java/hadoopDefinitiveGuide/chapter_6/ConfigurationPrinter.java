package hadoopDefinitiveGuide.chapter_6;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.util.Map;

public class ConfigurationPrinter extends Configured implements Tool {

    static
    {
        Configuration.addDefaultResource("hdfs-default.xml");
    }
    @Override
    public int run(String[] args) throws Exception {
        //这里可以直接调用getConf() 方法是因为：getConf()是从Configured 中继承来的
        //而 Configured 的方法是从 Configurable 中实现而来的。
        Configuration conf = getConf();
        for (Map.Entry<String, String> entry : conf) {
            System.out.printf("%s=%s\n", entry.getKey(), entry.getValue());
        }
        return 0;
    }

    public static void main(String[] args) throws Exception {
        int exitCode = ToolRunner.run(new ConfigurationPrinter(), args);
        System.exit(exitCode);
    }
}
