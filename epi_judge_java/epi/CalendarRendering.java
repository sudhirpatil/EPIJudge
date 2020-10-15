package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class CalendarRendering {
  @EpiUserType(ctorParams = {int.class, int.class})

  public static class Event {
    public int start, finish;

    public Event(int start, int finish) {
      this.start = start;
      this.finish = finish;
    }
  }

  private static class Endpoint {
    public int time;
    public boolean isStart;

    Endpoint(int time, boolean isStart) {
      this.time = time;
      this.isStart = isStart;
    }

  }

  @EpiTest(testDataFile = "calendar_rendering.tsv")

  public static int findMaxSimultaneousEvents(List<Event> A) {
    /*
    Flatten the event list to Array with with 2 points start & end with indicator of start or end
    Sort Array with precedence to start time
    Iterate through sorted arr increase count if start point, decrease if end point
      flattening & increase/decrease logic in single iteration makes it order of nlogn
     */
    Endpoint[] arr = new Endpoint[A.size() * 2];
    List<Endpoint> list = new ArrayList<>();
    int i =0;
    for(Event event : A){
      arr[i++] = new Endpoint(event.start, true);
      arr[i++] = new Endpoint(event.finish, false);
    }
    Arrays.sort(arr, (a,b) -> {
      if(a.time != b.time)
        return Integer.compare(a.time, b.time);

      // result have to be transitive e.g if both a.start & b.start are both true it  might return -1 or 1, ideally return should be 0
      return a.isStart && !b.isStart ? -1 : !a.isStart && b.isStart ? 1 : 0;
    });

    int count=0, maxCount=0;
    for(Endpoint p : arr){
      if(p.isStart){
        count++;
        maxCount = Math.max(maxCount, count);
      }else {
        count--;
      }
    }
    return maxCount;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "CalendarRendering.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
