package DataOrganizer.SOS;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import PositionInputStream.PositionInputStream;
import coding.XOR;

public class SOS {
    private PositionInputStream inputStream;
    private int sizeOfBlock = 0;
    private int[] buffor = {-1, -1, -1};
    // private XOR xor = new XOR();
    Map<String,Integer> markerBytes;

    public SOS(PositionInputStream _inputStream, Map<String,Integer> _markerBytes) {
        inputStream = _inputStream;
        markerBytes = _markerBytes;
    }

    public void run(boolean printData)
    {
        try {
            readHeader();
            readImageData();
        } catch (IOException e) {
            System.out.println("error while reading SOS. " + e.getMessage());
        }
    }

    private void readHeader() throws IOException{
        setSizeOfSegment();
        int numOfComponent = readNumberOfComponent();
        for(int i = 0; i < numOfComponent; i++){
            int componentId = readComponentIdentifier();
            int huffmanSelector = inputStream.read();
            int DCHuffmanSelector = transformByteIntoDCHuffmanSelector(huffmanSelector);
            int ACHuffmanSelector = transformByteIntoACHuffmanSelector(huffmanSelector);
            System.out.println("componentId: "+ componentId);
            System.out.println("DCHuffmanSelector: "+ DCHuffmanSelector);
            System.out.println("ACHuffmanSelector: "+ ACHuffmanSelector);
        }
        pushByte(inputStream.read());
        pushByte(inputStream.read());
        pushByte(inputStream.read());
        if(!isEndOfBlock()){
            System.out.println("error while reading SOS.");
            return;
        }
    }

    private void readImageData() throws IOException{
        while(!checkIfFoundEndOfSegment()){
            int data = inputStream.read();
            decodeData(data);
        }
    }

    private boolean checkIfFoundEndOfSegment() throws IOException{
        inputStream.mark(2);
        int segmentStartMarker = inputStream.read();
        int marker = inputStream.read();
        boolean result = (segmentStartMarker == 0xFF && markerBytes.containsValue(marker));
        inputStream.reset();
        return result;
    }

    private void decodeData(int data){
        // changeFile(inputStream.getPos() - 1,xor.encode(data));
        // int decodedData = (int)RSA_alg.RSA_alg_decode(encodedData);
        // if (data != decodedData){
        //     System.out.println("no, RSA nie dziala chyba jeszcze");
        //}
    }

    private int readComponentIdentifier() throws IOException{
        int componentId = inputStream.read();
        return componentId;
    }

    private int readNumberOfComponent() throws IOException{
        int componentNum = inputStream.read();
        return componentNum;
    }

    private int transformByteIntoDCHuffmanSelector(int data){return data >> 4;}

    private int transformByteIntoACHuffmanSelector(int data){return data & 0x0F;}

    private void setSizeOfSegment() throws IOException{
        int[] sizeMakerSegments = new int[2];
        sizeMakerSegments[0] = inputStream.read();
        sizeMakerSegments[1] = inputStream.read();
        int sizeMakerSegment=Integer.parseInt(
            String.format("%02X%02X", sizeMakerSegments[0], sizeMakerSegments[1]),
            16);
        this.sizeOfBlock = sizeMakerSegment;
    }

    private void pushByte(int data){
        buffor[0] = buffor[1];
        buffor[1] = buffor[2];
        buffor[2] = data;
    }

    private boolean isEndOfBlock(){
        return (buffor[0] == 0 && buffor[1] == 63 && buffor[2] == 0);
    }

    void changeFile(long posStart, byte data) {
        try {
            System.out.println("saving file ");
            PositionInputStream is = new PositionInputStream("Metro.jpg");
            FileWriter fstream = new FileWriter("Metro_kopia.jpg");
            BufferedWriter out = new BufferedWriter(fstream);
            is.skip(posStart);
            out.write(data);
            out.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
};

