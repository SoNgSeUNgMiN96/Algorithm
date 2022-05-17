import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class programmersBannedUser {
    public static Set<Set<Integer>> combi ;
    public static int solution(String[] user_id, String[] banned_id) {
        ArrayList<Integer>[] bannedList = new ArrayList[banned_id.length];
        boolean[] visited = new boolean[user_id.length];
        combi = new HashSet<>();
        StringBuffer sb;

        for(int i=0;i<banned_id.length;i++){
            sb = new StringBuffer();
            bannedList[i] = new ArrayList<>();

            for(int j=0; j<user_id.length;j++){
                if(user_id[j].length()!=banned_id[i].length()) continue;
                sb = new StringBuffer();
                sb.append(user_id[j]);
                for (int k = 0; k < banned_id[i].length(); k++) {
                    if(banned_id[i].charAt(k)=='*') sb.replace(k,k+1,"*");
                }

                if(banned_id[i].equals(sb.toString())) bannedList[i].add(j);
            }
        }

        Set<Integer> pick = new HashSet<>();
        dfs(0, bannedList, visited, pick);

        return combi.size();
    }

    public static void dfs(int depth, ArrayList<Integer>[] bannedList,  boolean[] visited, Set<Integer> pick){
        if(depth == bannedList.length){
            combi.add(new HashSet<>(pick));
            return;
        }
        for (int i = 0; i < bannedList[depth].size(); i++) {
            if(!visited[bannedList[depth].get(i)]){
                visited[bannedList[depth].get(i)] = true;
                pick.add(bannedList[depth].get(i));
                dfs(depth+1,bannedList,visited,pick);
                pick.remove(bannedList[depth].get(i));
                visited[bannedList[depth].get(i)] = false;
            }
        }
    }

    public static void main(String[] args) {
        String[] user_id = {"frodo", "fradi", "crodo", "abc123", "frodoc"}, banned_id = {"fr*d*", "*rodo", "******", "******"};
        //System.out.println(solution(user_id,banned_id));

        String temp ="BAAAAAA";
        temp = temp.replace("AA","");
        System.out.println(temp);
    }

}
