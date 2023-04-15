import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SingleWordFrequencyCounter {

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


    public static void main(String[] args) {
        long startTime = System.nanoTime();

        BufferedReader buffer;
        String filename = "enwik9";

        try {
            buffer = new BufferedReader(new FileReader(filename));
            //String line = buffer.readLine();

            for (int i = 0; i < 1000000000; i++) {

                String line = buffer.readLine();
                line = line.replaceAll("[^a-zA-Z0-9\\-\\_]", " ");
                List<String> wordList = new ArrayList<String>(Arrays.asList(line.split((" "))));
                for (String string : wordList) {
                    string.trim();
                    if (string.isEmpty()){
                        continue;
                    }else{
                        lengthCounter(string);
                    }
                }
            }

            buffer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(frequencyPrinter());
        double elapsedTime = ((System.nanoTime() - startTime)/1000000000.00);
        System.out.println(df.format(elapsedTime) + " Seconds");

    }

    public static void lengthCounter(String string){
        int wordLength = string.length();

        switch (wordLength) {
            case 1: 
                singleLengthCounter++;
                totalWordCounter++;
                break; 
            case 2: 
                twoLengthCounter++;
                totalWordCounter++;
                break; 
            case 3: 
                threeLengthCounter++;
                totalWordCounter++;
                break; 
            case 4: 
                fourLengthCounter++;
                totalWordCounter++;
                break; 
            case 5: 
                fiveLengthCounter++;
                totalWordCounter++;
                break; 
            case 6: 
                sixLengthCounter++;
                totalWordCounter++;
                break; 
            case 7: 
                sevenLengthCounter++;
                totalWordCounter++;
                break; 
            default:
                eightPlusLengthCounter++;
                totalWordCounter++;
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