import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        String dir = "/Users/suyue/ty_test/ty_test_consistency_1h";
        String[] argsu={"/41/demo_test_output/","/42/demo_test_output/","/43/demo_test_output/",
        "/45/demo_test_output/","/46/demo_test_output/","/out/t_"};
        for(int i = 0;i<6;i++){
            argsu[i] = dir+argsu[i]+args[0];
        }

        argsu[5]+=".txt";

        int l = argsu.length;
        if(l<2){
            System.out.println("参数不合法，参数应为输入文件列表，输出文件目录。");
            return;
        }
        int inNum = l - 1;
        int outIndex = l - 1;
        List<BufferedReader> fileReaders = new ArrayList<BufferedReader>();
        for(int i = 0;i < inNum; i++){
            try {
                FileInputStream fileInputStream = new FileInputStream(argsu[i]);
                BufferedReader fr = new BufferedReader(new InputStreamReader(fileInputStream));
                fileReaders.add(fr);
            } catch (FileNotFoundException e) {
                System.out.println("FILE NOT FOUND :"+argsu[i]);
                e.printStackTrace();
            }
        }

        FileWriter fw = new FileWriter(argsu[outIndex]);
        MergeSort mergeSort = new MergeSort(fileReaders,fw);
        mergeSort.write();
    }
}
