package epi.HashCh12;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/*
Over thinking, not implementing quickly, incorrect understanding of the question
Understanding of LinkedHashMap, ordering based on access, making it LRU cache using removeEldest

Avoid if else spl while returning boolean e.g : lMap.remove(key) != null

 */
public class LruCache12_03 {
  LinkedHashMap<Integer, Integer> lMap = null;
  LruCache12_03(final int capacity) {
    lMap = new LinkedHashMap<Integer, Integer>(capacity, 0.8f, true){
      @Override
      protected boolean removeEldestEntry(Map.Entry<Integer, Integer> e){
        return this.size() > capacity;
      }
    };
  }

  public Integer lookup(Integer key) {
    return lMap.getOrDefault(key, -1);
  }

  public void insert(Integer key, Integer value) {
    lMap.putIfAbsent(key, value);
  }

  public Boolean erase(Object key) {
    return lMap.remove(key) != null;
  }

  @EpiUserType(ctorParams = {String.class, int.class, int.class})
  public static class Op {
    String code;
    int arg1;
    int arg2;

    public Op(String code, int arg1, int arg2) {
      this.code = code;
      this.arg1 = arg1;
      this.arg2 = arg2;
    }
  }

  @EpiTest(testDataFile = "lru_cache.tsv")
  public static void runTest(List<Op> commands) throws TestFailure {
    if (commands.isEmpty() || !commands.get(0).code.equals("LruCache")) {
      throw new RuntimeException("Expected LruCache as first command");
    }
    LruCache12_03 cache = new LruCache12_03(commands.get(0).arg1);
    for (Op op : commands.subList(1, commands.size())) {
      int result;
      switch (op.code) {
      case "lookup":
        result = cache.lookup(op.arg1);
        if (result != op.arg2) {
          throw new TestFailure("Lookup: expected " + String.valueOf(op.arg2) +
                                ", got " + String.valueOf(result));
        }
        break;
      case "insert":
        cache.insert(op.arg1, op.arg2);
        break;
      case "erase":
        result = cache.erase(op.arg1) ? 1 : 0;
        if (result != op.arg2) {
          throw new TestFailure("Erase: expected " + String.valueOf(op.arg2) +
                                ", got " + String.valueOf(result));
        }
        break;
      default:
        throw new RuntimeException("Unexpected command " + op.code);
      }
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LruCache.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
