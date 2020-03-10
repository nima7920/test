import java.util.*;
public class RuleList {
    //IN THIS CLASS, YOU NEED TO DEFINE THE APPROPRIATE FIELD(S)
    private static List<String> rules;

    /**
     * Constructs a new Taboo using the given rules
     *
     * @param rules rules for new Taboo
     */
    public RuleList(List<String> rules) {
        this.rules = rules;
    }

    /**
     * Returns the set of elements which should not follow
     * the given element.
     *
     * @param elem
     * @return elements which should not follow the given element
     */
    public static Set<String> noFollow(String elem) {
        TreeSet<String> result = new TreeSet<String>();
        for (int i = 0; i < rules.size() - 1; i++) {
            if (rules.get(i).equals(elem))
                result.add(rules.get(i + 1));

        }
        return result;
        // YOUR CODE HERE
    }

    /**
     * Removes elements from the given list that
     * violate the rules
     * @param list collection to reduce
     */
    private static boolean isLegal(List<String> list){
        for (int i = 0; i <list.size()-1 ; i++) {
            if(noFollow(list.get(i)).contains(list.get(i+1)))
                return false;
        }
        return true;
    }
    public static void reduce(List<String> list) {
        while(isLegal(list)==false){
            for (int i = list.size()-1; i >0 ; i--) {
                if(noFollow(list.get(i-1)).contains(list.get(i)))
                    list.remove(i);
            }
        }
    }

    public static void main(String[] args) {
        reduce(new List<String>() {
        })
    }
}
