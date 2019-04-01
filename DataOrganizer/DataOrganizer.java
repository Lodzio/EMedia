package DataOrganizer;
import java.io.*;
import java.util.Map;
import java.util.TreeMap;

public class DataOrganizer {

    private InputStream inputStream;
    private boolean firstByteMarkerSet = false;
    private Map<String,Integer> markerBytes = new TreeMap<>();
    private int markerSizeFields =1;
    private long pos = 0;
    
    public DataOrganizer(InputStream _inputStream){
        inputStream = _inputStream;
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
    public void run(){
        int bufforByte;

        try{
            int k =0;
            while((bufforByte = inputStream.read()) != -1){
               pos++;
                if (this.firstByteMarkerSet && markerBytes.containsValue(bufforByte)){
                    for ( String key : markerBytes.keySet() ) {
                        if (key == "startMarker"){
                            continue;
                        }
                        if (markerBytes.get(key) == bufforByte){
                            System.out.println("detected:" + key+" position "+ pos);
                            if(key == "DQT")
                            {
                                skipMarkerSegments();
                            }
                            if(key == "DHT")
                            { 
                          DHT dht = new DHT(inputStream);
                            dht.run();
                            inputStream = dht.inputStream;

                            }
                            if(key == "DRI")
                            {
                                skipMarkerSegments();
                            }
                            if(key == "SOF0")
                            {
                              SOF();
                              pos+=17;
                            }
                            if(key == "SOS")
                            {
                                skipMarkerSegments(); 
                            }
                        }
                    }
 
                }
                this.firstByteMarkerSet = (bufforByte == markerBytes.get("startMarker"));
                if(k>0)
                {
                    System.out.println(bufforByte);
                    k--;
                }
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

    private void skipMarkerSegments() throws IOException
    {
        int[] sizeMakerSegments = new int[2];
        try {
        for(int i=0;i<2;i++)
        {
            
                sizeMakerSegments[i] = inputStream.read();
                //System.out.println(sizeMakerSegments[i]);
         }
        int sizeMakerSegment=Integer.parseInt(Integer.toHexString(sizeMakerSegments[0])
        +Integer.toHexString(sizeMakerSegments[1]),16);
        System.out.println(sizeMakerSegment);
            inputStream.skip(sizeMakerSegment-markerSizeFields);
        } catch (Exception e) {
            System.out.println("error while reading file." + e.getMessage());
        }
    }
    private void SOF() throws IOException
    {
        int[] Segments = new int[17];
        for(int i=0;i<17;i++)
        {
            try {
                Segments[i] = inputStream.read();
               // System.out.println(Segments[i]);
            } catch (Exception e) {
                System.out.println("error while reading file." + e.getMessage());
            }
        }
        int sizeMakerSegment=Integer.parseInt(Integer.toHexString(Segments[0])
        +Integer.toHexString(Segments[1]),16);
        System.out.println("Size: "+sizeMakerSegment);
        System.out.println("data precision: "+Segments[2]);
        int verticalLines = Integer.parseInt(Integer.toHexString(Segments[3])
        +Integer.toHexString(Segments[4]),16);
        System.out.println("Vertical lines: "+verticalLines);
        int horizontalLines = Integer.parseInt(Integer.toHexString(Segments[5])
        +Integer.toHexString(Segments[6])+0,16);
        System.out.println("Horizontal lines: "+horizontalLines);
        System.out.println("Components: "+Segments[7]);
        System.out.println("Component number: "+Segments[8]);
        System.out.println("H0 i V0: "+Segments[9]);
        System.out.println("Quanization esignation: "+Segments[10]);
        System.out.println("Component number: "+Segments[11]);
        System.out.println("H1 i V1: "+Segments[12]);
        System.out.println("Quanization esignation: "+Segments[13]);
        System.out.println("Component number: "+Segments[14]);
        System.out.println("H2 i V2: "+Segments[15]);
        System.out.println("Quanization esignation: "+Segments[16]);
    }
}