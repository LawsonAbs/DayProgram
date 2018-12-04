package chapter18;

import java.io.File;
import java.io.FilenameFilter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

public class Main {
    private static String fileName = "D:\\Java_Project\\DayProgram\\studyJava";
    private static List<File> fileList = new  ArrayList<File>();
    public static void main(String[] args) {
        local(fileName);
        walk(fileName);

        System.out.println(fileName+" 下的所有文件名为：");
        for (File file : fileList) {
            System.out.println(file.getName());
        }
    }

    public static void listFile(){
        System.out.println(fileName+" 下的文件（夹）有：");
        File file = new File(fileName);
        String fileList [] = file.list();
        for (String str : fileList) {
            System.out.println("file name is: "+str );
            //System.out.println("file is a directory:" + );
        }
    }

    public static void listMatchFile(String regex,String dir){
        System.out.println("\n\n匹配的文件有：");
        File file = new File(dir);
        String res []  = file.list(new Filter(regex));
        for(String str : res)
        System.out.println(str);
    }

    public static class Filter implements FilenameFilter {
        private Pattern pattern;

        public Filter(String regex){
            pattern = Pattern.compile(regex);
        }

        public boolean accept(File dir, String name) {
            return pattern.matcher(name).matches();
        }
    }


    public static File[] local(String fileName){
        File file = new File(fileName);
        File [] files = file.listFiles();
        for(File fe: files)
            System.out.println(fe.getName());
        return files;
    }

    //以下方法失败的原因是：
    //java 不支持多层遍历。即当前层取到的File 对象，只在当前层中有效。如果再往下一层，则不可以了。
    public static void walk(String filePath){
        //the file name in the specific path
        System.out.println(filePath);
        File file = new File(filePath);

        //if file is a directory
        for(String fileName:file.list()){
            File tempFile = new File(fileName);
            // 如果这个fileName对应的文件是一个文件夹，那么再次遍历
            fileList.add(tempFile);//先添加进 fileList
            System.out.println(tempFile.getName());
            while (tempFile.isDirectory()) {
                //System.out.println(tempFile.getPath());
                //System.out.println(tempFile.getAbsolutePath());
                walk(filePath+"\\"+tempFile.getName());
                  //walk(tempFile.getName());
                //break;
            }
        }
    }

    public static class TreeInfo implements Iterable<File> {

        public Iterator<File> iterator() {
            return null;
        }
    }
}
