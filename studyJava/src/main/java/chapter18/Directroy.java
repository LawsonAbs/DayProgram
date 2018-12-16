package chapter18;


import sun.reflect.generics.tree.Tree;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

public final class Directroy {
    public static File[] local(File dir, final String regex) {
        return dir.listFiles(new FilenameFilter() {
            private Pattern pattern = Pattern.compile(regex);
            public boolean accept(File dir, String name) {
                return pattern.matcher(new File(name).getName()).matches();
            }
        });
    }

    public static File[] local(String path,final String regex){
        return local(new File(path), regex);
    }

    public static class TreeInfo implements Iterable<File> {
        public List<File> files = new ArrayList<File>();//file List
        public List<File> dirs = new ArrayList<File>();// directory list

        public Iterator<File> iterator(){
            return files.iterator();
        }

        void addAll(TreeInfo other) {
            files.addAll(other.files);
            dirs.addAll(other.dirs);
        }

        public String toString() {
            return "dirs: "+ dirs + files;
        }
    }

    static TreeInfo recurseDirs(File startDir, String regex) {
        TreeInfo result = new TreeInfo();
        for (File item : startDir.listFiles()) {//list the file in current filepath
            if (item.isDirectory()) {//if it is a directory
                System.out.println(item.toString());
                result.dirs.add(item);//first add dir
                result.addAll(recurseDirs(item, regex)); // then addAll
            }else
            {
                if (item.getName().matches(regex)) {
                    result.files.add(item);
                }
            }
        }
        return result;
    }

    // three overload function
    public static TreeInfo walk(String start) {
        return recurseDirs(new File(start), ".*");
    }

    public static TreeInfo walk(File start, String regex) {
        return recurseDirs(start, ".");
    }

    public static TreeInfo walk(String start, String regex) {
        return recurseDirs(new File(start), regex);
    }

    public static void main(String [] args){
        if (args.length == 0) {
            System.out.println(walk("."));
        }
        else{
            for (String arg : args) {
                System.out.println(walk(arg));
            }
        }
    }
}
