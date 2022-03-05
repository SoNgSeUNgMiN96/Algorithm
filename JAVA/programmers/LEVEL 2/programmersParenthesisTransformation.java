public class programmersParenthesisTransformation {

    String change(String p){
        if (p==""){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(p);
        int lcount=0,rcount=0;
        int length =p.length();
        String u="",v="";
        String[] uv;
        for (int i=0;i<length;i++){
            if (Character.compare(p.charAt(i),'(')==0){
                lcount++;
            }
            else {
                rcount++;
            }
            if (lcount==rcount){

                sb.insert(i+1,"$");
                p = sb.toString();
                uv =p.split("[$]");
                if(i==length-1){
                    u = uv[0];
                    v = "";
                }
                else {

                    u = uv[0];
                    v = uv[1];
                }
                break;
            }
        }

        lcount =0;rcount =0;
        boolean d=false;
        for(int i=0;i<u.length();i++){
            if(Character.compare(u.charAt(i),'(')==0){
                lcount++;
            }
            else{
                rcount++;
            }
            if (rcount>lcount){
                d =true;
                break;
            }
        }
        if(d==false){
            return u +change(v);
        }
        String temp = "(";
        temp +=change(v)+")";
        StringBuilder sbb = new StringBuilder();
        sbb.append(u);
        sbb = sbb.deleteCharAt(u.length()-1);
        sbb = sbb.deleteCharAt(0);
        for (int i=0;i<sbb.length();i++){
            if(Character.compare(sbb.charAt(i),'(')==0){
                sbb.setCharAt(i, ')');
            }
            else {
                sbb.setCharAt(i, '(');
            }
        }
        u = sbb.toString();
        return temp+u;
    }

    public String solution(String p) {
        return change(p);
    }
}
