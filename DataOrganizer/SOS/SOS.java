package DataOrganizer.SOS;
import java.io.IOException;
import PositionInputStream.PositionInputStream;

public class SOS {
    private PositionInputStream inputStream;
    private int sizeOfBlock = 0;
    private int[] buffor = {-1, -1, -1};

    public SOS(PositionInputStream _inputStream) {
        inputStream = _inputStream;
    }

    public void run(boolean printData)
    {
        try {
            readHeader();

        } catch (IOException e) {
            System.out.println("error while reading SOS." + e.getMessage());
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

    void changeFile(long posStart, List<String> str) {
        try {
            System.out.println("saving file ");
            PositionInputStream is = new PositionInputStream("Metro.jpg");
            FileWriter fstream = new FileWriter("Metro — kopia.jpg");
            BufferedWriter out = new BufferedWriter(fstream);
            int inputLine;
            while ((inputLine = is.read()) != -1) {
                if (inputLine == 0)
                    is.skip(posStart);
                    for(String s : str)
                        out.write(s);
                }
            out.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
};

