package com.txr.spbbasic.model.composite;

import java.util.List;

//文件类
public class File implements IFile {

    private String name;

    public File(String name){
        this.name = name;
    }

    @Override
    public void display() {
        System.out.println(name);
    }

    @Override
    public boolean add(IFile file) {
        return false;
    }

    @Override
    public boolean remove(IFile file) {
        return false;
    }

    @Override
    public List<IFile> getChild() {
        return null;
    }
}
