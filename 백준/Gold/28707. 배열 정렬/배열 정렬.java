import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args)throws Exception{
        var br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] aArray = Arrays.stream(br.readLine().split(" "))
            .mapToInt(str -> {
                return Integer.parseInt(str);
            })
            .toArray();
            
            
        int m = Integer.parseInt(br.readLine());

        int[][] commandArr = new int[m][];
        for(int i=0;i<m;i++){
            int[] command = Arrays.stream(br.readLine().split(" "))
            .mapToInt(str -> {
                return Integer.parseInt(str);
            })
            .toArray();
            commandArr[i]=command;
        }
        
        PriorityQueue<Ascending> pq = new PriorityQueue<>();
        var first = new Ascending(aArray, -1, 0);
        pq.add(first);

        Map<Integer, Integer> duplicateSet = new HashMap<>();
        duplicateSet.put(first.hashCode(), 0);


        while(!pq.isEmpty()){
            var top = pq.poll();

            if(top.isAscending()){
                System.out.println(top.count);
                return;
            }

            for(int i=0;i<m;i++){
                if(top.prevControl==i) continue;
                int[] arr = top.arr.clone();
                swap(arr, commandArr[i][0]-1,commandArr[i][1]-1);
                var newObject = new Ascending(arr, i, top.count+commandArr[i][2]);
                if(duplicateSet.containsKey(newObject.hashCode())){
                    int count = duplicateSet.get(newObject.hashCode());
                    if(count > newObject.count){
                        duplicateSet.put(newObject.hashCode(), newObject.count);
                    }else{
                        continue;
                    }
                }
                pq.add(newObject);
                duplicateSet.put(newObject.hashCode(), newObject.count);
            }
        }

        System.out.println(-1);
    }  

    

    static void swap(int[] arr, int a,int b){
        int tmp = arr[a];
        arr[a]=arr[b];
        arr[b]=tmp;
    }
    
    static class Ascending implements Comparable<Ascending>{
        int[] arr;
        int prevControl;
        int count;

        public Ascending(int[] arr, int prevControl, int count){
            this.arr = arr;
            this.prevControl=prevControl;
            this.count=count;
        }

        public int compareTo(Ascending other){
            return this.count - other.count; 
        }

        boolean isAscending(){
            for(int i=1;i<arr.length;i++){
                if(arr[i]<arr[i-1]){
                    return false;
                }
            }
            return true;
        }
    
        @Override
        public boolean equals(Object other){
            if(other instanceof Ascending){
                var otherAscending = (Ascending)other;
                return Arrays.equals(otherAscending.arr, this.arr);
            }
            return false;
        }

        @Override
        public int hashCode(){
            int result=0;
            for(int i=0;i<arr.length;i++){
                result = result*11 + (arr[i]-1);
            }
            return result;
        }
        
    }

    
}
