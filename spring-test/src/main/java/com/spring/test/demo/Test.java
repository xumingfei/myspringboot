package com.spring.test.demo;

import java.util.*;

/**
 * @author: Xiaofei
 * @DATE: 2020/12/21 14:09
 */
public class   Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        System.out.println("输入字符串");
        List<String[]> nodeList = new LinkedList<>();
        Map<Integer,Node> map = new HashMap<>();
        Node root = null;
        if(scanner.hasNextLine()){
//            System.out.println(scanner.nextLine());
            int n = scanner.nextInt();
            for (int m = 0; m <= n; m++) {
                String str  = scanner.nextLine();
                String[] arr = str.split(" ");
                if(arr.length==3){
                    nodeList.add(0,arr);
//                    System.out.println(arr[0]+"==="+arr[1]+"==="+arr[2]);
                }
                /*for (String s: str.split(" ")){
                    System.out.println("===="+s);
                }*/
            }

            for(int i=1;i<=nodeList.size();i++){
                String[] a = nodeList.get(i-1);
                Node node = new Node();
                if(!"0".equals(a[0])){
                    node.setData(Integer.parseInt(a[0]));
                }
                if("0".equals(a[1])){
                    node.setLeft(null);
                }else{
                    node.setLeft(map.get(Integer.parseInt(a[1])));
                }
                if("0".equals(a[2])){
                    node.setRight(null);
                }else{
                    node.setRight(map.get(Integer.parseInt(a[2])));
                }
                map.put(Integer.parseInt(a[0]),node);
                if(i==nodeList.size()){
                    root  = node;
                }
            }

            Test test = new Test();
            test.thePostOrderTraversal(root);
        }
    }

    public void thePostOrderTraversal(Node root) {  //后序遍历
        if (root.getLeft() != null) {
            thePostOrderTraversal(root.getLeft());
        }
        if(root.getRight() != null) {
            thePostOrderTraversal(root.getRight());
        }
        printNode(root);
    }

    public void printNode(Node node){
        System.out.print(node.getData()+"#");
    }
}

class Node {
    private int data;
    private Node left;
    private Node right;

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public Node(int data, Node left, Node right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public Node() {
    }
}
