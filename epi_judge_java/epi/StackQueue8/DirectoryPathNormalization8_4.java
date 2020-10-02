package epi.StackQueue8;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.stream.Collectors;

public class DirectoryPathNormalization8_4 {
  @EpiTest(testDataFile = "directory_path_normalization.tsv")

  /**
   * In Stack consider descending iterator when out put needed in FIFO order
   * Missed checking stack empty & case of double ../..
   * Think critically about edge cases and what can go wrong, / when empty & extra / in the end
   * return handling in case of starting with /
   */
  public static String shortestEquivalentPath(String path) {
    /*
    String split produces empty string when first char is split char, e.g /str1 .split("/") produces []["str1]
    Deque use removeLast (remove first element in case of stack) when you want to use queue like feature

    edge cases . .. , only .. & no path strings
     */
    ArrayDeque<String> stack = new ArrayDeque<>();
    int pCount = 0;
    for(String s: path.split("/")) {
      if (s.equals(".") || s.equals("")) {
        continue;
      } else if (s.equals("..") && pCount > 0) {
        stack.pop();
        pCount--;
      } else {
        stack.push(s);
        if (!s.equals("..")) pCount++;
      }
    }

    StringBuilder sb = new StringBuilder();
    if(path.startsWith("/"))
      sb.append("/");
    while(!stack.isEmpty()){
      sb.append(stack.removeLast());
      if(!stack.isEmpty())
        sb.append("/");
    }
    return sb.toString();
  }








  public static String shortestEquivalentPath3(String path) {
    Deque<String> st = new ArrayDeque<>();
    int fCount = 0;
    for(String p: path.split("/")){
      if(p.equals(".") || p.equals(""))
        continue;
      else if(p.equals("..") && fCount >0){
        st.pop();
        fCount--;
      }else if(p.equals("..")){
        st.push(p);
      }else{
        st.push(p);
        fCount++;
      }
    }
    StringBuilder sb = new StringBuilder();
    while(!st.isEmpty()){
      sb.append(st.removeLast());
      if(!st.isEmpty())
        sb.append("/");
    }
//    sb = sb.reverse();
    return ((path.startsWith("/")) ? "/" : "") + sb.toString();
  }










    public static String shortestEquivalentPath2(String path) {
    // split by /
    // stack to store dir
    // iterate split ignore "" "." , for .. pop from stack & rest push in stack
    // join stack with /

    Deque<String> stack = new ArrayDeque<>();
    for(String subPath : path.split("/")){
      if(subPath.equals("") || subPath.equals(".")) continue;
      else if(subPath.equals("..")) {
        if(!stack.isEmpty() && !stack.peek().equals("..")) {
//          if(path.startsWith("/")) throw new IllegalArgumentException("/../ is not valid path");
//          else
            stack.pop();
        }
        else stack.push(subPath);
      }
      else stack.push(subPath);
    }

    StringBuilder sb = new StringBuilder();
    Iterator<String> iterator = stack.descendingIterator();
    if(!iterator.hasNext() && path.startsWith("/")) sb.append("/");
    while (iterator.hasNext()){
      if(sb.length() ==0 && !path.startsWith("/")) sb.append(iterator.next());
      else sb.append("/").append(iterator.next());

    }
    return sb.toString();
//    String newPath = "";
//    while (!stack.isEmpty()) {
//      if(newPath.equals("")) newPath = stack.pop();
//      else newPath = stack.pop() +"/"+ newPath;
//    }
//
//    if(path.startsWith("/")) return "/"+newPath;
//    else return newPath;
  }

  public static void main(String[] args) {
//    Deque<String> de_que = new ArrayDeque<String>();
//
//    // Use add() method to add elements into the Queue
//    de_que.offer("Welcome");
//    de_que.offer("To");
//    de_que.offer("Geeks");
//    de_que.offer("4");
//    de_que.offer("Geeks");
//
//    // Displaying the ArrayDeque
//    System.out.println("ArrayDeque: " + de_que);

    System.exit(
        GenericTest
            .runFromAnnotations(args, "DirectoryPathNormalization.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }

}
