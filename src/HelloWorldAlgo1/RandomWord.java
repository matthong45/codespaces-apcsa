package HelloWorldAlgo1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {

    public static void main(String args[]) {
        String champion = "";
        double count = 1;
        while (!StdIn.isEmpty()) {
            String value = StdIn.readString();
            double probability = 1.0 / count;
            if (StdRandom.bernoulli(probability)) {
                champion = value;
            }
            count = count + 1;
        }

        StdOut.print(champion);
    }

}
