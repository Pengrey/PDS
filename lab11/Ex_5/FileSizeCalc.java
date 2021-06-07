public class FileSizeCalc {
    private final static String usage_msg = "Usage:\njava FileSizeCalc [-Option] <PathName>\nOptions:\n   -h: Displays usage message\n   -r: Gets size of directories inside given directory aswell\n";
    
    public static void main(String[] args) {
        // There were given arguments
        if(1 <= args.length && args.length <= 3){
            for(String s : args) System.out.println(s);
            boolean isRecursive = false;
            int cursor = 0;
            for(String s : args){
                if(s.startsWith("-") && s.length() == 2){
                    cursor++;
                    switch(s){
                        case "-h":
                                System.out.println(usage_msg);
                                break;
                        case "-r":
                                isRecursive = true;
                                System.out.println("YES");
                                break;
                        default:
                            System.out.println(usage_msg);
                            System.exit(1);
                            break;
                    }
                }else{
                    break;
                }
            }

            //for(String s: args)System.out.println(s);
            FileIterator calc = new FileIterator();
            System.out.println(args[cursor]);
            System.out.println("Total: "+ calc.getSize(args[cursor],isRecursive) + "kB");
        }else{
            // No arguments or too many were given
            System.out.println(usage_msg);
            System.exit(1);
        }
    }
}