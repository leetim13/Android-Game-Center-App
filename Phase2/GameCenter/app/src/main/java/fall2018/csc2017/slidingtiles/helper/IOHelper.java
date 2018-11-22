package fall2018.csc2017.slidingtiles.helper;
import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
* functional tool for IO operations, for reading and writing process
*The static methods have the same use like System.Math, (utility) with only small scope of use
* it's not a code smell. We're not creating object from any of these static method, or extends these
* classes with static methods.
* */
public class IOHelper {
    /*
    * @param Object obj: the object to write into file
    * @param String path: the path to store the object file
    * */
    private final static void writeMap(Object obj, String path) throws IOException {
        File userFile = new File(path);
        if (!userFile.exists()){
            System.out.println("using new file method...");
            userFile.createNewFile();
            System.out.println("the path is ..." + path);
            System.out.println("trying to create one....");
        }

        FileOutputStream outFile = new FileOutputStream(userFile, false);
        ObjectOutputStream objStream = new ObjectOutputStream(outFile);
        objStream.writeObject(obj);
        objStream.flush();
        objStream.close();
    }
    /*
    * write object file within android context
    * */
    public final static void writeAndroidMap(Object obj, String path, Context ctx) throws IOException{
        writeMap(obj, ctx.getFilesDir() + path);
    }
    /*
    *
    * */
    private final static HashMap readMap (String path) throws IOException{
        File userFile = new File(path);
        if (userFile.exists()) {
            System.out.println("the path is: ..." + path);
            FileInputStream inFile = new FileInputStream(userFile);
            ObjectInputStream mapStream = new ObjectInputStream(inFile);

            try {
                HashMap map = (HashMap) mapStream.readObject();
                mapStream.close();
                return map;

            } catch (ClassNotFoundException e) {
                System.out.println("this file is not a hashMap!");
                mapStream.close();
            }
        } else {
            System.out.println("no such a file for user identity!");
        }

        return null;
    }

    public final static HashMap readAndroidMap (String path, Context ctx) throws IOException{
        return readMap(ctx.getFilesDir() + path);
    }

    // sort any kinds of map through values
    public final static List<SequenceBundlers> convertMap(Map<String, int[]> map) {
        List<SequenceBundlers> returned_list = new ArrayList <SequenceBundlers>();
        for (Object key: map.keySet()) {
            int[] values = map.get(key);
            for (int item: values) {
                int val = item;
                returned_list.add(new SequenceBundlers((String) key, val));
            }
        }

        return returned_list;
    }
}