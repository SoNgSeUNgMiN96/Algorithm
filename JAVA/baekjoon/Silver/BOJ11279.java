import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ11279 {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int m;

        int arr[] = new int[100001];
        int idx =1, tempIdx, temp;


        for (int i = 0; i < N; i++) {
            int x = Integer.parseInt(br.readLine());
            if(x == 0){
                if(idx==1){
                    bw.write("0\n");
                }else{
                    bw.write(arr[1]+"\n");
                    tempIdx =  1;
                    arr[1] = arr[--idx];
                    while (tempIdx*2<idx){
                        if(tempIdx*2+1<idx){   //둘 다 자식이 있는경우.
                            if(arr[tempIdx*2]<arr[tempIdx*2+1]){        //오른쪽 자식이 더 큰 경우,
                                if(arr[tempIdx]<arr[tempIdx*2+1]){      //나보다 오른쪽 자식이 더 큰 경우.
                                    temp = arr[tempIdx*2+1];
                                    arr[tempIdx*2+1] = arr[tempIdx];
                                    arr[tempIdx] = temp;
                                    tempIdx = tempIdx * 2+1;
                                }else break;
                            }else{      //왼쪽 자식이 더 큰 경우
                                if(arr[tempIdx]<arr[tempIdx*2]){      //나보다 왼쪽 자식이 더 큰 경우
                                    temp = arr[tempIdx*2];
                                    arr[tempIdx*2] = arr[tempIdx];
                                    arr[tempIdx] = temp;
                                    tempIdx = tempIdx * 2;
                                }else break;
                            }
                        }else{      //오른쪽 자식이 없이 왼쪽 자식만 있는경우.
                            if(arr[tempIdx]<arr[tempIdx*2]){      //나보다 왼쪽 자식이 더 큰 경우
                                temp = arr[tempIdx*2];
                                arr[tempIdx*2] = arr[tempIdx];
                                arr[tempIdx] = temp;
                                tempIdx = tempIdx * 2;
                            }else break;
                        }
                    }
                }
            }else{
                tempIdx = idx;
                arr[idx++] = x;
                while (tempIdx>1){
                    if(arr[tempIdx/2]<arr[tempIdx]){        //부모보다 내가 크면?
                        temp = arr[tempIdx/2];
                        arr[tempIdx/2] = arr[tempIdx];
                        arr[tempIdx] = temp;
                        tempIdx /=2;
                    }else break;
                }
            }
        }
        bw.flush();
        bw.close();
    }
}

