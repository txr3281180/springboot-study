package com.txr.spbbasic.model.composite;

import java.util.List;

public class MainClass {

    public static void main(String[] args) {
        //C盘
        Folder rootFolder = new Folder("C:");
        //System 目录
        Folder initFolder = new Folder("System");
        //init.txt文件
        File intiFile = new File("init.txt");

        rootFolder.add(initFolder);
        rootFolder.add(intiFile);

        //二级目录
        Folder childFolder = new Folder("window");
        File confFile = new File("conf.txt");
        initFolder.add(childFolder);
        initFolder.add(confFile);


        //三级目录 (添加到二级目录中)
        Folder downFolder = new Folder("down");
        File settingFile = new File("setting.txt");
        childFolder.add(downFolder);
        childFolder.add(settingFile);

        displayTree(rootFolder,0);
    }


    //用于显示
    public static void displayTree(IFile rootFolder, int deep){
        for(int i = 0; i < deep; i++) {
            System.out.print("--");
        }
        //显示自身的名称
        rootFolder.display();
        //获得子树
        List<IFile> children = rootFolder.getChild();
        //遍历子树
        for(IFile file : children) {
            if(file instanceof File) {  //如果是文件则显示
                for(int i = 0; i <= deep; i++) {
                    System.out.print("--");
                }
                file.display();
            } else {
                displayTree(file,deep + 1); //如果是文件夹则递归调用
            }
        }
    }
}
