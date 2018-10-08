import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class MergeSort {
    private FileWriter fileWriter;
    private List<BufferedReader> fileReaders;
    private PriorityQueue<Element> heap;

    public MergeSort(List<BufferedReader> fileReaders , FileWriter fileWriter) throws IOException {
        this.fileReaders = fileReaders;
        this.fileWriter = fileWriter;
        heap = new PriorityQueue<Element>();
        init();
    }

    public void init() throws IOException {
        int i = 0;
        for(BufferedReader bufferedReader : fileReaders){
            String tmp = null;
            if((tmp=bufferedReader.readLine())!=null){
                heap.add(new Element(tmp,i));
            }
            else {
                bufferedReader.close();
            }
            i++;
        }
    }

    public boolean hasNext(){
        return heap.size()>0;
    }

    public String next() throws IOException {
        Element element = heap.poll();
        update(element);
        return element.str;
    }

    public void update(Element elem) throws IOException {
        int i = elem.i;
        String tmp = null;
        if((tmp=fileReaders.get(i).readLine())!=null){
            heap.add(new Element(tmp,i));
        }
        else {
            fileReaders.get(i).close();
        }
    }


    public void write() throws IOException {
        String lineSeparator = System.getProperty("line.separator");
        while(hasNext()){
            fileWriter.write(next());
            fileWriter.write(lineSeparator);
            fileWriter.flush();
        }
        fileWriter.close();
    }

    class Element implements Comparable<Element>{
        public int i;
        public String str;

        public Element(String str, int i){
            this.str = str;
            this.i = i;
        }

        public int compareTo(Element o) {
            return str.compareTo(o.str);
        }
    }
}
