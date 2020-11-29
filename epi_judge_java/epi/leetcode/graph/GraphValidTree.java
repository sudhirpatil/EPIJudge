package epi.leetcode.graph;

import java.util.*;

public class GraphValidTree {
    public boolean validTree(int n, int[][] edges) {
        if(edges.length != n-1)
            return false;

        //Create Adjecency List, both ways
        List<List<Integer>> list = new ArrayList<>(n);
        for(int i =0; i<n;i++)
            list.add(i, new ArrayList<Integer>());
        for(int[] edge: edges){
            list.get(edge[0]).add(edge[1]);
            list.get(edge[1]).add(edge[0]);
        }

        // Check cycle using DFS, remove reverse edge using map, duplicate/cycle check using seen
        // Stack for DFS, instead of recursion
        Deque<Integer> stack = new ArrayDeque<>();
        // Set to check cycle & quickly exit if graph has cycle
        Set<Integer> set = new HashSet<>();
        // map to skip traversing reverse edge, since it's bidirectional graph
        // parents map, Map<child/neighbour, parent>
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0,-1);
        stack.push(0);
        while(!stack.isEmpty()){
            int node = stack.pop();
            for(int edge: list.get(node)){
                // skip reverse edges, if previous parent is edge
                if(map.get(node) == edge)
                    continue;
                if(set.contains(edge))
                    return false;

                map.put(edge, node);
                stack.push(edge);
            }
            // mark node as visited
            set.add(node);
        }

        // cases where node with 0 is not given edges, need to check size
        return set.size() == n;

    }
}