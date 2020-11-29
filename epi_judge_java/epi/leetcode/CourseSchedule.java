package epi.leetcode;

import java.util.*;

public class CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        //prepare graph, preReq->Course, [1] -> [0]
        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int[] pair : prerequisites){
            if(map.containsKey(pair[1])){
                map.get(pair[1]).add(pair[0]);
            }else{
                List<Integer> list = new LinkedList<>();
                list.add(pair[0]);
                map.put(pair[1], list);
            }
        }

        boolean[] visited = new boolean[numCourses], vCycle = new boolean[numCourses];
        Arrays.fill(visited, false);
        boolean isCycle = false;
        for(int index: map.keySet()){
            Arrays.fill(vCycle, false);
            isCycle = hasCycle(index, map, visited, vCycle);
            if(isCycle)
                break;
        }
        return !isCycle;
    }

    public boolean hasCycle(int ind, Map<Integer, List<Integer>> map, boolean[] visited, boolean[] vCycle){
        if(vCycle[ind])
            return true;
        if(visited[ind])
            return false;

        visited[ind] = true;
        vCycle[ind] = true;
        boolean isCycle = false;
        if(map.containsKey(ind)){
            for(int index: map.get(ind)){
                isCycle = hasCycle(index, map, visited, vCycle);
                if(isCycle)
                    break;
            }
        }
        vCycle[ind] = false;
        return isCycle;
    }


}
