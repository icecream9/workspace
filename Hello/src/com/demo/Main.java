package com.demo;

import java.util.Scanner;

public class Main{
    @SuppressWarnings("null")
	public static void main(String[]args){

        TreeNode root = null;
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        
        String line=sc.nextLine();
        root.val=sc.nextInt();
        TreeNode node=root.next;
        node.pre=root;
        for(int i=1;i<n;++i){
            TreeNode temp = null;
            temp.pre=node;
            node.next=temp;
            temp.val=sc.nextInt();
            node=node.next;
        }
        
        int q=0;
        int p=0;
        while(q<n-1){
            if(root.val>node.val){
                int val1=node.val;
                node=node.pre;
                node.val=val1+node.val;
                ++p;
                ++q;
            }else if(root.val<node.val){
                int val1=root.val;
                root=root.next;
                root.val=val1+root.val;
                ++p;
                ++q;
            }else{
                root=root.next;
                node=node.pre;
                q=q+2;
            }
        }
       System.out.println(p);
    }
}
class TreeNode {
    int val = 0;
    TreeNode pre = null;
    TreeNode next = null;

    public TreeNode(int val) {
        this.val = val;

    }
}