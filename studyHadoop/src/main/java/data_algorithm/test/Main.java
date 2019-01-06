package data_algorithm.test;

import org.apache.hadoop.io.Text;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;



public class Main {
    public static void main(String[] args) {
        String s = "Hadoop";
        System.out.println("hadoop: "+s.hashCode());
        s = "spark";
        System.out.println("spark: "+s.hashCode());

        Text txt = new Text("hado op \n");
        System.out.println("hadoop.getLength(): "+txt.getLength());
        System.out.println("hadoop.getBytes(): "+txt.getBytes().length);
    }
    public static void test() {
        Model model = ModelFactory.createDefaultModel();
    }
}
