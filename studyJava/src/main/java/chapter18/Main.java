package chapter18;

import java.io.File;
import java.io.FilenameFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Main {
    private static String fileName = "src";
    private static List<File> fileList = new  ArrayList<File>();
    public static void main(String[] args) {
       // local(fileName);
        walk(new File(fileName));

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
    //01. 尝试直接使用一个文件名(String filePath)去构建一个File对象，但是因为某种特殊关系，导致无法得到连续的File对象
    //02. 正是由于上面的原因，才有了下面的重载方法walk(File file)。将File对象作为参数传递，而不是构造出来的 filePath
    public static void walk(String filePath){
        //the file name in the specific path
        System.out.println(filePath);
        File file = new File(filePath);

        //if file is a directory
        for(String fileName : file.list()){
            File tempFile = new File(fileName);
            // 如果这个fileName对应的文件是一个文件夹，那么再次遍历
            fileList.add(tempFile);//先添加进 fileList
            System.out.println(tempFile.getName());
            while (tempFile.isDirectory()) {
                //System.out.println(tempFile.getPath());
                //System.out.println(tempFile.getAbsolutePath());
                walk(filePath+"\\"+tempFile.getName());
            }
        }
    }

    public static void walk(File file){
        //if file is a directory
        for(File sunFiles:file.listFiles()){
            // 如果这个fileName对应的文件是一个文件夹，那么再次遍历
            fileList.add(sunFiles);//先添加进 fileList
            System.out.println(sunFiles.getName());
            while (sunFiles.isDirectory()) {
                walk(sunFiles);
                //you must break, or you get a die loop
                break;
            }
        }
    }
}
