import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;



public class Main {

    static HashMap<Character, String> result = new HashMap<>();

    public static void main (String args[]) throws IOException {
        final String str = new String(Files.readAllBytes(
                Paths.get("./src/example.txt")));
        HashMap<Character,Integer> mapOfCount = getMapOfCount(str);
        haffman(mapOfCount);
    }

    public static HashMap<Character, Integer> getMapOfCount(String str){
        HashMap<Character,Integer> map = new HashMap<>();

        for(char c : str.toCharArray())
        {
            if(!map.containsKey(c))
                map.put(c, 1);
            else
            {
                int value = map.get(c);
                map.put(c, ++value);
            }
        }

        return map;
    }

    public static void haffman(HashMap<Character, Integer> map) {
        ArrayList<Node> nodes = new ArrayList<>();

        for(Map.Entry<Character, Integer> en : map.entrySet()){
            nodes.add(new Node(en.getKey(),en.getValue()));
        }

        Collections.sort(nodes, new Comparator<Node>() {
            @Override
            public int compare(Node n1, Node n2) {
                return n1.getData()-n2.getData();
            }
        });

        for (Node node:nodes) {
            System.out.println(node.getLabel() + "--" + node.getData());
        }
        Node root = createTree(nodes);

        setBinaryCodes(root,"");
        for(Map.Entry<Character,String> ent : result.entrySet()){
            System.out.println(ent.getKey()+"---"+ent.getValue());
        }
    }

    private static void setBinaryCodes(Node root,String binaryCode) {
        if (root.getLeft()==null&&root.getRight()==null){
            result.put(root.getLabel(),binaryCode);
        }
        else {
            String binaryLeft = binaryCode + "0";
            String binaryRight = binaryCode + "1";
            setBinaryCodes(root.getLeft(),binaryLeft);
            setBinaryCodes(root.getRight(),binaryRight);
        }
    }

    public static Node createTree(ArrayList<Node> nodes){
        Node root;
        if (nodes.size()>1){
            Node node1 = nodes.get(0);
            Node node2 = nodes.get(1);
            Node node = new Node(node1.getData()+node2.getData());
            node.setLeft(node1);
            node.setRight(node2);
            nodes.remove(0);
            nodes.remove(0);
            nodes.add(node);
            Collections.sort(nodes, new Comparator<Node>() {
                @Override
                public int compare(Node n1, Node n2) {
                    return n1.getData()-n2.getData();
                }
            });
            root = createTree(nodes);
        }
        else
            root = nodes.get(0);
        return root;
    }
}