package cascalog_play;
import java.io.*;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.io.IOException;

public class Importer {

    public static void main(String... aArgs) throws IOException{

        String ROOT = "/Users/johnervine/govtrack.us-data/data/bills";
        FileVisitor<Path> fileProcessor = new ProcessFile();
        Files.walkFileTree(Paths.get(ROOT), fileProcessor);
    }

    private static final class ProcessFile extends SimpleFileVisitor<Path> {
        @Override public FileVisitResult visitFile(
                Path aFile, BasicFileAttributes aAttrs
        ) throws IOException {
            FileWriter fileWriter = null;
            String absolutePath = "";
            String filePath="";
            String outFilePath = "";
            File f = new File(aFile.toString());
            System.out.println(f.getName());
            absolutePath = f.getAbsolutePath();
            filePath = absolutePath.substring(0,absolutePath.lastIndexOf(File.separator));

            outFilePath = "/Users/johnervine/govtrack.us-data/data/bills-out" + filePath.substring(18).replace("/", "_");;
            System.out.println("new file is " + outFilePath);
            if (f.getName().toString().equals("data.json")){
                System.out.println("Processing file:" + aFile);

                BufferedReader br = new BufferedReader(new FileReader(aFile.toString()));
                try {
                    StringBuilder sb = new StringBuilder();
                    String line = br.readLine();

                    while (line != null) {
                        sb.append(line);
                        sb.append(" ");
                        line = br.readLine();
                    }
                    File newTextFile = new File(outFilePath+"_data1.json");
                    fileWriter = new FileWriter(newTextFile);
                    fileWriter.write(sb.toString());
                    fileWriter.close();
                } finally {
                    br.close();
                }
            }
            return FileVisitResult.CONTINUE;
        }

        @Override  public FileVisitResult preVisitDirectory(
                Path aDir, BasicFileAttributes aAttrs
        ) throws IOException {
            return FileVisitResult.CONTINUE;
        }
    }

}
