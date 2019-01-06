package hadoopDefinitiveGuide.chapter_5;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Writable;

import java.io.*;

public class WritableDemo {
    public static byte[] serialize(Writable writable) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        DataOutputStream dataOut = new DataOutputStream(out);
        writable.write(dataOut);
        dataOut.close();
        return out.toByteArray();
    }

    public static byte[] deserialize(Writable writable,byte[] bytes) throws IOException {
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        DataInputStream dataIn = new DataInputStream(in);
        writable.readFields(dataIn);
        dataIn.close();
        return bytes;
    }

    public static void main(String[] args) throws IOException {

        /*step 1: 将 IntWritable 对象作为输入流，放到bytes中
         */
        IntWritable inW = new IntWritable(163);
        byte[] bytes = serialize(inW);
        System.out.println("bytes.length: "+bytes.length);

        /*step 2: 将上述的 bytes 作为输入流，放到outW中
         */
        IntWritable outW = new IntWritable();
        deserialize(outW, bytes);
        System.out.println(outW.get());
    }
}
