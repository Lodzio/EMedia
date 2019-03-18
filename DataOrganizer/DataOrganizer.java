package DataOrganizer;
import java.io.*;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class DataOrganizer {

    private InputStream inputStream;
    private boolean firstByteMarkerSet = false;
    private Map<String,Integer> markerBytes = new TreeMap<>();

    public DataOrganizer(InputStream _inputStream){
        inputStream = _inputStream;
        markerBytes.put("startMarker", 0xFF);
        markerBytes.put("SOI", 0xD8);
        markerBytes.put("SOF0", 0xC0);
        markerBytes.put("SOF2", 0xC2);
        markerBytes.put("DHT ", 0xC4);
        markerBytes.put("DQT", 0xDB);
        markerBytes.put("DRI", 0xDD);
        markerBytes.put("SOS", 0xDA);
        markerBytes.put("COM", 0xFE);
        markerBytes.put("EOI", 0xD9 );
    }

    public void run(){
        int bufforByte;

        try{
            checkStartOfImage();

            while((bufforByte = inputStream.read()) != -1) {
                processByte(bufforByte);
            } 

        } catch(IOException e){
            System.out.println("error while reading file." + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("error while reading file." + e.getMessage());
        }
    }

    private void checkStartOfImage() throws IOException{
        if (markerBytes.get("startMarker") == inputStream.read() && markerBytes.get("SOI") == inputStream.read()){
            return;
        } else {
            throw new RuntimeException("start byte is not detected");
        }
    }

    private void checkMarker(int Byte){
        if (this.firstByteMarkerSet && markerBytes.containsValue(Byte)){
            for ( String key : markerBytes.keySet() ) {
                if (key == "startMarker"){
                    continue;
                }
                if (markerBytes.get(key) == Byte){
                    System.out.println("detected:" + key);
                }
            }
        }
        this.firstByteMarkerSet = (Byte == markerBytes.get("startMarker"));
    }

    private void processByte(int Byte){
        checkMarker(Byte);
    }
}