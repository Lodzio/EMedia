package DataOrganizer.SOS;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;
import java.util.Map;

import com.sun.org.apache.xml.internal.serializer.ElemDesc;

import PositionInputStream.PositionInputStream;
import UserInterface.Menu;
import UserInterface.Menu.Option;
import coding.XOR;

public class SOS {
    private PositionInputStream inputStream;
    private int sizeOfBlock = 0;
    private int[] buffor = {-1, -1, -1};
    private XOR xor = new XOR();
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
        if(Menu.option == Option.DECODE){
            while(!checkIfFoundEndOfSegment()){
            int data = inputStream.read();
            decodeData(data);
        }
    }
        else
            while(!checkIfFoundEndOfSegment()){
                int data = inputStream.read();
                decodeData(data);
            }
            Menu.settingMinPositionToCheck = inputStream.getPos()-2;
    }

    private boolean checkIfFoundEndOfSegment() throws IOException{
        inputStream.mark(2);
        int segmentStartMarker = inputStream.read();
        int marker = inputStream.read();
        inputStream.changePos(-2);
        boolean result = (segmentStartMarker == 0xFF && markerBytes.containsValue(marker) && Menu.settingMinPositionToCheck < inputStream.getPos());
        inputStream.reset();
        return result;
    }

    private void decodeData(int data){
        changeFile(inputStream.getPos() - 1,xor.xor((byte)data));
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
            RandomAccessFile out = new RandomAccessFile("Metro.jpg", "rw");
            out.seek(posStart);
            out.writeByte(data);
            out.close();
        } catch (Exception e) {
            System.out.println("error while writing file: " + e.getMessage());
        }
    }
};

