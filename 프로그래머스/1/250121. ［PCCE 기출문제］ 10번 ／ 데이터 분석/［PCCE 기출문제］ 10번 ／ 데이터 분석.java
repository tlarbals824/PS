import java.util.*;

class Solution {
    
    
    public int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {

        List<Data> selectDataList = new ArrayList<Data>();
        for(int i=0;i<data.length;i++){
            if(data[i][convertIdx(ext)] < val_ext){
                selectDataList.add(new Data(data[i][0],data[i][1],data[i][2],data[i][3], sort_by));
            }
        }
        
        Collections.sort(selectDataList);
        int[][] answer = new int[selectDataList.size()][4];
        
        for(int i=0;i<selectDataList.size();i++){
            answer[i]=selectDataList.get(i).convertToArray();
        }
        return answer;
    }
    
    private int convertIdx(String ext){
        switch(ext){
            case "code" : return 0;
            case "date" : return 1;
            case "maximum" : return 2;
            default : return 3;
        }
    }
    
    
    private class Data implements Comparable<Data>{
        int code;
        int date;
        int maximum;
        int remain;
        int compareCode;
        
        public Data(int code, int date, int maximum, int remain, String sort_by){
            this.code = code;
            this.date = date;
            this.maximum = maximum;
            this.remain = remain;
            this.compareCode = convertIdx(sort_by);
        }
        
        public int[] convertToArray(){
            return new int[]{this.code, this.date, this.maximum, this.remain};
        }
        
        @Override
        public int compareTo(Data o){
            switch(compareCode){
                case 0 : return this.code - o.code;
                case 1 : return this.date - o.date;
                case 2 : return this.maximum - o.maximum;
                default : return this.remain - o.remain;
            }
        }
    }
}