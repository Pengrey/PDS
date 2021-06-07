public class TotalSize{
    Long current_size;

    public TotalSize(){
        this.current_size = 0L;
    }

    public void addVal(Long new_val){
        this.current_size += new_val/1024L;
    }

    public Long getVal(){
        return this.current_size;
    }
} 