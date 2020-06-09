package file;

import java.io.*;

public class FileTest {
    public static void main(String[] args) throws IOException {
//        File file = new File("d:\\file\\f.txt");
//        if (!file.exists()) {
//            file.createNewFile();
//        }


    }

    public static void readFile(String path){
        int position = 0;
        String[] buffer = new String[1024];
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line = null;
            while ((line=br.readLine())!=null){
                buffer[position] = line;
                position++;
            }
            br.close();
            for (int i=0;i<position;i++){
                System.out.println(buffer[i]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void writeFile (String path){
        int n = 1;

        try {
            OutputStream os = new FileOutputStream("d:\\file\\f.txt");
            PrintWriter pw = new PrintWriter(os);
            for (int i = 0; i < 10; i++
                    ) {
                String s = ""+n;
                pw.write(s);
                n++;
            }
            pw.close();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
