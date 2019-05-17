package DataOrganizer;
import PositionInputStream.PositionInputStream;
import DataOrganizer.DHT.*;
import DataOrganizer.SOF.*;
import DataOrganizer.SOS.*;
import java.io.*;
import java.util.Map;
import java.util.TreeMap;

public class DataOrganizer {

    private PositionInputStream inputStream;
    private boolean firstByteMarkerSet = false;
    private Map<String,Integer> markerBytes = new TreeMap<>();
    private Map<String,Runnable> markerHandler = new TreeMap<>();
    private final int markerSizeFields =2;
    private SOS sos;

    @FunctionalInterface
    public interface Runnable {
        public void run();
    }

    private void initMarkers(){
        markerBytes.put("startMarker", 0xFF);
        markerBytes.put("SOI", 0xD8);
        markerBytes.put("SOF0", 0xC0);
        markerBytes.put("SOF2", 0xC2);
        markerBytes.put("DHT", 0xC4);
        markerBytes.put("DQT", 0xDB);
        markerBytes.put("DRI", 0xDD);
        markerBytes.put("SOS", 0xDA);
        markerBytes.put("COM", 0xFE);
        markerBytes.put("EOI", 0xD9 );
        markerBytes.put("APP1", 0xE1);
        markerBytes.put("APP2", 0xE2);
    }

    private void initMarkerHandlers(){
        markerHandler.put("DRI",() -> skipMarkerSegments());
        markerHandler.put("DQT",() -> skipMarkerSegments());
        markerHandler.put("SOS",() -> sos.run(true));
        markerHandler.put("DHT",() -> (new DHT(inputStream)).run());
        markerHandler.put("SOF0",() -> (new SOF(inputStream)).run(true));
    }

    public DataOrganizer(PositionInputStream _inputStream){
        inputStream = _inputStream;
        sos = new SOS(inputStream);
        initMarkers();
        initMarkerHandlers();
    }

    public void run(){
        int bufforByte;
        try{
            while((bufforByte = inputStream.read()) != -1){
                if (this.firstByteMarkerSet && markerBytes.containsValue(bufforByte)){
                    for ( String key : markerBytes.keySet() ) {
                        if (key == "startMarker"){
                            continue;
                        }
                        if (markerBytes.get(key) == bufforByte){
                            System.out.println("\ndetected:" + key+" position "+ inputStream.getPos());
                            if (markerHandler.containsKey(key)){
                                markerHandler.get(key).run();
                            }
                        }
                    }
                }
                this.firstByteMarkerSet = (bufforByte == markerBytes.get("startMarker"));
            } 
        } catch(IOException e){
            System.out.println("error while reading file." + e.getMessage());
        }
    }

    private void skipMarkerSegments(){
        int[] sizeMakerSegments = new int[2];
        try {
            for(int i=0;i<markerSizeFields;i++)
            {
                sizeMakerSegments[i] = inputStream.read();
            }
            int sizeMakerSegment=Integer.parseInt(
                String.format("%02X%02X", sizeMakerSegments[0], sizeMakerSegments[1]),
                16);
            inputStream.skip(sizeMakerSegment-markerSizeFields);
        } catch (IOException e) {
            System.out.println("error while skiping file." + e.getMessage());
        }
    }
}