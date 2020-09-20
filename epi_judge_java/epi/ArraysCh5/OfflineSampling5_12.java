package epi.ArraysCh5;
import epi.test_framework.EpiTest;
import epi.test_framework.RandomSequenceChecker;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class OfflineSampling5_12 {
  // API for Random Number. Random().nextInt(maxBound)
  // missed case for i + random
  // for use case validation, write down numbers on paper and validate cas
  public static void randomSampling(int k, List<Integer> A) {
    Random rand = new Random();
    for(int i=0; i<k ; i++){
      Collections.swap(A, i , i+ rand.nextInt(A.size()-i));
    }
  }




















  public static void randomSampling1(int k, List<Integer> A) {
    Random rand = new Random();
    k--;
    // get random nos from count to n, in loop till count =k
    // replace rand with count element
    for(int count=0; count < k; count++){
      Collections.swap(A, count, rand.nextInt(k-count) +count);
    }
  }
  private static boolean randomSamplingRunner(TimedExecutor executor, int k,
                                              List<Integer> A)
      throws Exception {
    List<List<Integer>> results = new ArrayList<>();

    executor.run(() -> {
      for (int i = 0; i < 1000000; ++i) {
        randomSampling(k, A);
        results.add(new ArrayList<>(A.subList(0, k)));
      }
    });

    int totalPossibleOutcomes =
        RandomSequenceChecker.binomialCoefficient(A.size(), k);
    Collections.sort(A);
    List<List<Integer>> combinations = new ArrayList<>();
    for (int i = 0; i < RandomSequenceChecker.binomialCoefficient(A.size(), k);
         ++i) {
      combinations.add(
          RandomSequenceChecker.computeCombinationIdx(A, A.size(), k, i));
    }
    List<Integer> sequence = new ArrayList<>();
    for (List<Integer> result : results) {
      Collections.sort(result);
      sequence.add(combinations.indexOf(result));
    }
    return RandomSequenceChecker.checkSequenceIsUniformlyRandom(
        sequence, totalPossibleOutcomes, 0.01);
  }

  @EpiTest(testDataFile = "offline_sampling.tsv")
  public static void randomSamplingWrapper(TimedExecutor executor, int k,
                                           List<Integer> A) throws Exception {
    RandomSequenceChecker.runFuncWithRetries(
        () -> randomSamplingRunner(executor, k, A));
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "OfflineSampling.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
