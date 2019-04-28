package DataOrganizer.SOF;

import java.io.IOException;

import PositionInputStream.PositionInputStream;

public class SOF {
    private PositionInputStream inputStream;

    public SOF(PositionInputStream _inputStream) {
        inputStream = _inputStream;
    }

    public void run()
    {
        int[] segments = new int[17];
        for(int i=0;i<17;i++)
        {
            try {
                segments[i] = inputStream.read();
            } catch (IOException e) {
                System.out.println("error while reading SOF." + e.getMessage());
            }
        }

        int sizeMakerSegment=Integer.parseInt(
            String.format("%02X%02X", segments[0], segments[1]),
            16);
        int verticalLines = Integer.parseInt(
            String.format("%02X%02X", segments[3], segments[4]),
            16);
        int horizontalLines = Integer.parseInt(
            String.format("%02X%02X", segments[5], segments[6]),
            16);
            
        System.out.println("Size: "+sizeMakerSegment);
        System.out.println("data precision: "+segments[2]);
        System.out.println("Vertical lines: "+verticalLines);
        System.out.println("Horizontal lines: "+horizontalLines);
        System.out.println("Components: "+segments[7]);
        System.out.println("\nComponent number: "+segments[8]);
        System.out.println("H0 i V0: "+segments[9]);
        System.out.println("Quanization esignation: "+segments[10]);
        System.out.println("\nComponent number: "+segments[11]);
        System.out.println("H1 i V1: "+segments[12]);
        System.out.println("Quanization esignation: "+segments[13]);
        System.out.println("\nComponent number: "+segments[14]);
        System.out.println("H2 i V2: "+segments[15]);
        System.out.println("Quanization esignation: "+segments[16]);
    }
};

