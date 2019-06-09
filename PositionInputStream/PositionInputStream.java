package PositionInputStream;
import java.io.*;

public class PositionInputStream extends BufferedInputStream {
    private long pos = 0;

    public PositionInputStream(String file) throws FileNotFoundException{
        super(new FileInputStream(file));
    }

    @Override
    public int read() throws IOException {
        pos += 1;
        return super.read();
    }
    
    @Override
    public long skip(long n) throws IOException {
        pos += n;
        return super.skip(n);
    }

  /*  @Override 
    public void mark(int n) {
        pos -= (long)n;
    }*/

    public void changePos(long n) {
        pos += n;
    }

    public long getPos(){
        return pos;
    }
};