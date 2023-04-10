import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MultiWordFrequencyCounter {

    static int singleLengthCounter = 0;
    static int twoLengthCounter = 0;
    static int threeLengthCounter = 0;
    static int fourLengthCounter = 0;
    static int fiveLengthCounter = 0;
    static int sixLengthCounter = 0;
    static int sevenLengthCounter = 0;
    static int eightPlusLengthCounter = 0;
    static int totalWordCounter = 0;

    private static DecimalFormat df = new DecimalFormat("0.00");

    private static BlockingQueue<String> sharedQueue = new LinkedBlockingQueue<>(1000000000); 
    
    private static boolean inputFinished = false;

    public static void main(String[] args) {
        long startTime = System.nanoTime();

        try {
            Thread ioThread = new Thread(new IOThread());
            Thread countThread = new Thread(new CountingThread());
            ioThread.start();
            countThread.start();
            ioThread.join();
            countThread.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println((frequencyPrinter()));
        double runTime = ((System.nanoTime() - startTime)/1000000000.00);
        System.out.println(df.format(runTime) + " Seconds");

    }

    public static class IOThread implements Runnable{


        public IOThread(){
        }

        @Override
        public void run(){
            try{
                BufferedReader buffer = new BufferedReader(new FileReader("enwik9"));
                String line = buffer.readLine();
                for (int i =0; i < 10000000; i++){
                    line = buffer.readLine();
                    if (line != null) {
                        sharedQueue.put(line);
                    }
                }
                buffer.close();
            } catch (Exception e){
                e.printStackTrace();
            }
            inputFinished = true;
        }
    }

    public static class CountingThread implements Runnable{        
    
    @Override
    public void run(){
        
        while (!inputFinished || !sharedQueue.isEmpty()){
            
            String line = sharedQueue.poll();
            if (line == null) continue;
            line = line.replaceAll("[^a-zA-Z0-9\\-\\_]", " ");
            List<String> wordList = new ArrayList<String>(Arrays.asList(line.split((" "))));
            for (String s : wordList) {
                if (s.isEmpty()){
                    continue;
                }else{
                    totalWordCounter++;
                    lengthCounter(s.trim());
                }
            }
        }
    }
}

    


    public static void lengthCounter(String string){
        int wordLength = string.length();

        switch (wordLength) {
            case 1: 
                singleLengthCounter++;
                break; 
            case 2: 
                twoLengthCounter++;
                break; 
            case 3: 
                threeLengthCounter++;
                break; 
            case 4: 
                fourLengthCounter++;
                break; 
            case 5: 
                fiveLengthCounter++;
                break; 
            case 6: 
                sixLengthCounter++;
                break; 
            case 7: 
                sevenLengthCounter++;
                break; 
            default:
                eightPlusLengthCounter++;
                break; 
        }
    }

    public static String frequencyPrinter(){
        final StringBuffer sb = new StringBuffer("Frequency of differnt sized words in text dump:\n");
        sb.append("One letter words: ").append(singleLengthCounter).append(" of: ").append(totalWordCounter).append(". For a frequency of: ").append((df.format(frequencyCalculator(singleLengthCounter, totalWordCounter)))).append("%").append(System.getProperty("line.separator"));
        sb.append("Two letter words: ").append(twoLengthCounter).append(" of: ").append(totalWordCounter).append(". For a frequency of: ").append((df.format(frequencyCalculator(twoLengthCounter, totalWordCounter)))).append("%").append(System.getProperty("line.separator"));
        sb.append("Three letter words: ").append(threeLengthCounter).append(" of: ").append(totalWordCounter).append(". For a frequency of: ").append((df.format(frequencyCalculator(threeLengthCounter, totalWordCounter)))).append("%").append(System.getProperty("line.separator"));
        sb.append("Four letter words: ").append(fourLengthCounter).append(" of: ").append(totalWordCounter).append(". For a frequency of: ").append((df.format(frequencyCalculator(fourLengthCounter, totalWordCounter)))).append("%").append(System.getProperty("line.separator"));
        sb.append("Five letter words: ").append(fiveLengthCounter).append(" of: ").append(totalWordCounter).append(". For a frequency of: ").append((df.format(frequencyCalculator(fiveLengthCounter, totalWordCounter)))).append("%").append(System.getProperty("line.separator"));
        sb.append("Six letter words: ").append(sixLengthCounter).append(" of: ").append(totalWordCounter).append(". For a frequency of: ").append((df.format(frequencyCalculator(sixLengthCounter, totalWordCounter)))).append("%").append(System.getProperty("line.separator"));
        sb.append("Seven letter words: ").append(sevenLengthCounter).append(" of: ").append(totalWordCounter).append(". For a frequency of: ").append((df.format(frequencyCalculator(sevenLengthCounter, totalWordCounter)))).append("%").append(System.getProperty("line.separator"));
        sb.append("Eight or more letter words: ").append(eightPlusLengthCounter).append(" of: ").append(totalWordCounter).append(". For a frequency of: ").append((df.format(frequencyCalculator(eightPlusLengthCounter, totalWordCounter)))).append("%").append(System.getProperty("line.separator"));

        return sb.toString();
    }

    public static float frequencyCalculator(int count, int total){
        float quotient = (count / (float) total)* 100;
        return quotient;
    }

}