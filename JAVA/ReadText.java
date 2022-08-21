package algorithms;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class ReadText {
    ReadText() {}
    static int[] readInts(File f) {
        byte[] bytes = readBytes(f);
        return bytesToInts(bytes);
    }

    static long[] readLongs(File f) {
        byte[] bytes = readBytes(f);
        return bytesToLongs(bytes);
    }

    static int[][] readIntArrays(File f) {
        byte[] bytes = readBytes(f);
        LinkedList<int[]> list = new LinkedList<>();
        int previous = 0;
        for(int i = 0; i < bytes.length; i++) {
            if(bytes[i] == '\r' || bytes[i] == '\n') {//Generally, each line end with "\r\n"
                list.add(bytesToInts(bytes, previous, i));
                if(bytes[i + 1] == '\n' || bytes[i + 1] == '\r') i++;//go over '\n'
                previous = i + 1;
            }
        }
        return list.toArray(new int[0][]);
    }

    static byte[] readBytes(File f) {
        try {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            FileInputStream input = new FileInputStream(f);
            byte[] buffer = new byte[1024];
            int length = 0;
            while((length = input.read(buffer)) != -1) {
                stream.write(buffer, 0 , length);
            }
            return stream.toByteArray();
        }
        catch(IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    static int[] bytesToInts(byte[] bytes) {
        return bytesToInts(bytes, 0, bytes.length);
    }

    static long[] bytesToLongs(byte[] bytes) {
        return bytesToLongs(bytes, 0, bytes.length);
    }

    static int[] bytesToInts(byte[] bytes, int start, int end) {
        ArrayList<Integer> list = new ArrayList<>();
        Integer x = 0;
        int i = start;
        while(i < end && (bytes[i] < '0' || bytes[i] > '9') && bytes[i] != '-') i++;
        boolean minus;
        while(i < end) {
            minus = false;
            if(bytes[i] == '-') {
                minus = true;
                i++;
            }
            while(i < end && bytes[i] >= '0' && bytes[i] <= '9') { x = x * 10 + (bytes[i++] - '0'); }
            if(minus) x = -x;
            list.add(x);
            x = 0;
            while(i < end && (bytes[i] < '0' || bytes[i] > '9') && bytes[i] != '-') i++;
        }
        Integer[] temp = list.toArray(new Integer[0]);//new Integer[0] to specify the Array's type
        int[] r = new int[temp.length];

        int j = 0;
        for(int t : temp) { r[j++] = t; }
        return r;
    }

    static long[] bytesToLongs(byte[] bytes, int start, int end) {
        ArrayList<Long> list = new ArrayList<>();
        Long x = 0l;
        int i = start;
        while(i < end && (bytes[i] < '0' || bytes[i] > '9') && bytes[i] != '-') i++;
        boolean minus;
        while(i < end) {
            minus = false;
            if(bytes[i] == '-') {
                minus = true;
                i++;
            }
            while(i < end && bytes[i] >= '0' && bytes[i] <= '9') { x = x * 10 + (bytes[i++] - '0'); }
            if(minus) x = -x;
            list.add(x);
            x = 0l;
            while(i < end && (bytes[i] < '0' || bytes[i] > '9') && bytes[i] != '-') i++;
        }
        Long[] temp = list.toArray(new Long[0]);//new Integer[0] to specify the Array's type
        long[] r = new long[temp.length];

        int j = 0;
        for(long t : temp) { r[j++] = t; }
        return r;
    }
}
