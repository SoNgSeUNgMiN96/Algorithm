public class programmersGroupPhoto {
    static int answer = 0;
    public int solution(int n, String[] data) {
        answer = 0;
        char[] friends = {'A','C','F','J','M','N','R','T'};
        boolean[] visited = new boolean[8];
        char[] pick = new char[8];

        permutation(friends,data,pick,visited,0);
        return answer;
    }
    public static void permutation(char[] friends, String[] data, char[] pick, boolean[] visited, int depth){
        if (depth==8){
            checkAnswer(pick,data);
            return;
        }

        for(int i=0;i<8;i++){
            if(!visited[i]){
                pick[depth] = friends[i];
                visited[i] = true;
                permutation(friends,data,pick,visited,depth+1);
                visited[i] = false;
            }
        }
    }
    public static void checkAnswer(char[] pick, String[] data){
        String split1[], split2[];

        for(String dataParse: data){
            int start=0, to=0;
            if(dataParse.contains("=")){    //split1[0] 에 출발지 split2[0]에 목적지   split2[1]에 간격

                split1 = dataParse.split("~");
                split2 = split1[1].split("=");
                for(int i=0;i<8;i++){
                    if(pick[i]==split1[0].charAt(0)){
                        start=i;
                    }
                    if(pick[i]==split2[0].charAt(0)){
                        to=i;
                    }
                }
                if(Math.abs(to-start)-1!=Integer.parseInt(split2[1])){
                    return;
                }
            }
            if(dataParse.contains("<")){    //split1[0] 에 출발지 split2[0]에 목적지   split2[1]에 간격
                split1 = dataParse.split("~");
                split2 = split1[1].split("<");
                for(int i=0;i<8;i++){
                    if(pick[i]==split1[0].charAt(0)){
                        start=i;
                    }
                    if(pick[i]==split2[0].charAt(0)){
                        to=i;
                    }
                }
                if(Math.abs(to-start)-1>=Integer.parseInt(split2[1])){
                    return;
                }
            }
            if(dataParse.contains(">")){    //split1[0] 에 출발지 split2[0]에 목적지   split2[1]에 간격
                split1 = dataParse.split("~");
                split2 = split1[1].split(">");
                for(int i=0;i<8;i++){
                    if(pick[i]==split1[0].charAt(0)){
                        start=i;
                    }
                    if(pick[i]==split2[0].charAt(0)){
                        to=i;
                    }
                }
                if(Math.abs(to-start)-1<=Integer.parseInt(split2[1])){
                    return;
                }
            }
        }
        answer++;
    }
}
