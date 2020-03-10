import java.net.SocketOption;
import java.util.*;

public class part1_RuleList {
    private static List<String> rules;
    private static HashMap<String,TreeSet> permitted;

public part1_RuleList(List<String> rules){
    this.rules=rules;
}

public static Set<String> noFollow(String elem){
TreeSet<String> result=new TreeSet<String>();
    for (int i = 0; i <rules.size()-1 ; i++) {
        if(rules.get(i).equals(elem))
            result.add(rules.get(i+1));

    }
    return result;
}
private static boolean isLegal(List<String> list){
    for (int i = 0; i <list.size()-1 ; i++) {
        if(noFollow(list.get(i)).contains(list.get(i+1)))
            return false;
    }
    return true;
}
public static void reduce(List<String> list) {

    while (isLegal(list) == false) {
        for (int i = list.size() - 1; i > 0; i--) {
            if (noFollow(list.get(i - 1)).contains(list.get(i))) {
                list.remove(i);
                break;
            }
        }
    }
}

    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        List<String> rule=new ArrayList<>(),lst=new ArrayList<>() ;
        int n=input.nextInt();
        for (int i = 0; i <n ; i++) {
            rule.add(input.next());
        }
        new part1_RuleList(rule);
        int m=input.nextInt();
        for (int i = 0; i <m ; i++) {
            lst.add(input.next());
        }
        reduce(lst);
        System.out.println(lst.toString());
    }
}
