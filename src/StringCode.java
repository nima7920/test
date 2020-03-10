import java.util.HashSet;
import java.util.Set;

public class StringCode {
    public static int maxCons(String str){
        int max=0,count=0,temp=0;
        temp=str.charAt(0);
        for (int i = 0; i <str.length() ; i++) {
            if(temp==str.charAt(i)){
                count++;
            }else{
                if(count>max)
                    max=count;
                count=1;
                temp=str.charAt(i);

            }
        }
        if(count>max)
            max=count;
        return max;
    }
    private static String copyChar(int n,char c){
        String s="";
        for(int i=0;i<n;i++){
            s+=c;
        }
        return s;
    }
    public static String blowup(String str){
        String result ="";
        for (int i = 0; i <str.length() ; i++) {
            if(str.charAt(i)<='9' && str.charAt(i)>'0') {
                String t = copyChar(str.charAt(i) - 49, str.charAt(i + 1));
                result = result + t;
            } else if(str.charAt(i)=='0'){
                i++;
            }else {
                result = result + str.charAt(i);
            }
        }
        return result;
    }
    public static boolean isIntersected(String a,String b,int len){
        for (int i = 0; i <a.length() ; i++) {
            int k=b.indexOf(a.charAt(i));
            if(k>-1){
                if(len==1)
                    return true;
                if(isIntersected(a.substring(i+1),b.substring(k+1),len-1))
                    return true;
            }
        }
        return false;
    }

}
