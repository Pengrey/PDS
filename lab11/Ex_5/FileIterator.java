import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class FileIterator{
    
    public Long getSize(String path, boolean isRecursive){
        Path dir;
        
        try{
            dir = Paths.get(path);
            TotalSize size = new TotalSize(); 

            //Walk tree
            Files.walkFileTree(dir, new SimpleFileVisitor<Path>() {
                @Override
                //Visit a file and check if we should continue or not
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) 
                  throws IOException {
                    String[] path = (file.toString()).split("/");
                    String[] rootDir = (dir.toString()).split("/");
                    String dirName = path[path.length-2];
                    String fileName = path[path.length-1];

                    if(!dirName.equals(rootDir[rootDir.length-1]))
                        if(isRecursive)
                            fileName = dirName + "/" + fileName;
                        else
                            return FileVisitResult.CONTINUE;

                    size.addVal(attrs.size());
                    System.out.println("\t"+fileName + ": " + attrs.size()/1000 + " kB");
                    return FileVisitResult.CONTINUE;
                }
            });

            return size.getVal();

        }catch(Exception e){
            System.out.println("ERROR");
            System.exit(1);
        }
        return null;
    }
}