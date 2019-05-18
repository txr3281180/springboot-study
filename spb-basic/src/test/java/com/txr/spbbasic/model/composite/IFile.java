package com.txr.spbbasic.model.composite;

import java.util.List;

/*
  抽象类
 */
public interface IFile {

    void display();     //显示文件 或者文件 名称

    boolean add(IFile file);    //添加

    boolean remove(IFile file); //移除

    List<IFile> getChild();   //获得子节点

}
