package DataOrganizer.SOF;

import java.io.IOException;

import PositionInputStream.PositionInputStream;

public class SOF {
    private PositionInputStream inputStream;

    public SOF(PositionInputStream _inputStream) {
        inputStream = _inputStream;
    }

    private int getSizeOfSegment(){
        int sizeMakerSegment;
        int[] segments = new int[2];
        try {
            segments[0] = inputStream.read();
            segments[1] = inputStream.read();
        } catch (IOException e) {
            System.out.println("error while reading SOF." + e.getMessage());
        }
        sizeMakerSegment=Integer.parseInt(
            String.format("%02X%02X", segments[0], segments[1]),
            16);
        return sizeMakerSegment;
    }

    public void run(boolean printData)
    {
        int sizeMakerSegment=getSizeOfSegment();
        int[] segments = new int[sizeMakerSegment];

        for(int i=0;i<sizeMakerSegment -2;i++)
        {
            try {
                segments[i] = inputStream.read();
            } catch (IOException e) {
                System.out.println("error while reading SOF." + e.getMessage());
            }
        }
        int verticalLines = Integer.parseInt(
            String.format("%02X%02X", segments[1], segments[2]),
            16);
        int horizontalLines = Integer.parseInt(
            String.format("%02X%02X", segments[3], segments[4]),
            16);
        if (printData){
            System.out.println("Size: "+sizeMakerSegment);
            System.out.println("data precision: "+segments[0]);
            System.out.println("Vertical lines: "+verticalLines);
            System.out.println("Horizontal lines: "+horizontalLines);
            System.out.println("Components: "+segments[5]);
            for (int i = 0; i < segments[5]; i++){
                System.out.println("\nComponent number: "+segments[6 + (i*3)]);
                System.out.format("H%d i V%d: (%d, %d)", i, i, (segments[7 + (i*3)] >> 4), (segments[7 + (i*3)] & 0x0F));
                System.out.println("\nQuanization esignation: "+segments[8 + (i*3)]);
            }
        }
    }
};

