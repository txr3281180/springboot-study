package com.txr.spbbasic.demo.jframe;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuDemo {

    //属性定义部分
    JFrame frame;   //框架窗口
    JPanel panel;   //面板（空间的容器）
    JMenuBar mb;    //菜单栏
    JMenu mFile, mEdit, mSearch, mHelp;  //父菜单: 文件、 编辑 、 搜索、 帮助
    //“文件” 子菜单： 新建， 打开， 保存， 另存为，退出
    JMenuItem mNew, mOpen, mSave, mSaveAs, mQuit;
    //"编辑" 子菜单： 撤销，剪切，复制，粘贴， 删除
    JMenuItem mUndo, mCut, mCopy, mPaste, mDelete;
    JMenuItem mFind, mFindNext;  //"搜索" 子菜单： 查找，查找下一个
    JMenuItem mTitle, mAbout;   //"帮助" 子菜单： 帮助主题，关于记事本
    JTextArea areal;    //定义一个文本区域

    //构造方法
    public MenuDemo(){
        //创建框架窗口
        frame = new JFrame("菜单相应演示程序");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setVisible(true);
        //创建并添加一个面板
        panel = new JPanel();
        frame.getContentPane().add(panel);
        BorderLayout bl = new BorderLayout();
        panel.setLayout(bl);
        //创建菜单栏
        mb = new JMenuBar();
        frame.setJMenuBar(mb);

        /*创建菜单部分*/
        //创建父菜单
        mFile = new JMenu("文件（F）");
        mEdit =  new JMenu("编辑（E）");
        mSearch =  new JMenu("搜索（S）");
        mHelp =  new JMenu("帮助（H）");

        //创建子菜单
        mNew = new JMenuItem("新建（N）");
        mOpen = new JMenuItem("打开（O）");
        mSave = new JMenuItem("保存（S）");
        mSaveAs = new JMenuItem("另存为（A）");
        mQuit = new JMenuItem("退出（Q）");

        mUndo = new JMenuItem("撤销（U）");
        mCut = new JMenuItem("剪切（T）");
        mCopy = new JMenuItem("赋值（C）");
        mPaste = new JMenuItem("粘贴（P）");
        mDelete = new JMenuItem("删除（L）");

        mFind = new JMenuItem("查找（F）");
        mFindNext = new JMenuItem("查找下一个（N）");

        mTitle = new JMenuItem("帮助主题（H）");
        mAbout = new JMenuItem("关于记事本（A）");

        /*设置菜单的快捷键代码*/
        mFile.setMnemonic('F');
        mEdit.setMnemonic('E');
        mSearch.setMnemonic('S');
        mHelp.setMnemonic('H');
        mNew.setMnemonic('N');
        mOpen.setMnemonic('O');
        mSave.setMnemonic('S');
        mSaveAs.setMnemonic('A');
        mQuit.setMnemonic('Q');

        /*添加菜单*/
        //父菜单
        mb.add(mFile);
        mb.add(mEdit);
        mb.add(mSearch);
        mb.add(mHelp);
        //添加 mFile 的子菜单
        mFile.add(mNew);
        mFile.add(mOpen);
        mFile.add(mSave);
        mFile.add(mSaveAs);
        mFile.addSeparator();  //分隔符
        mFile.add(mQuit);
        //添加 mEdit 的子菜单
        mEdit.add(mUndo);
        mEdit.addSeparator();
        mEdit.add(mCut);
        mEdit.add(mCopy);
        mEdit.add(mPaste);
        mEdit.add(mDelete);
        //添加 mSearch 的子菜单
        mSearch.add(mFind);
        mSearch.add(mFindNext);
        //添加 mHelp 的子菜单
        mHelp.add(mTitle);
        mHelp.addSeparator();
        mHelp.add(mAbout);

        //创建并添加一个文本区域
        String orignalText = "这是按钮响应的演示程序。 \n" +
                "你可以点击文件菜单下的子菜单，以查看效果。";

        areal = new JTextArea(orignalText, 10, 30);
        JScrollPane scroll = new JScrollPane(areal);
        panel.add("Center", scroll);

        MenuListener mListener = new MenuListener();
        mNew.addActionListener(mListener);
        mOpen.addActionListener(mListener);
        mSave.addActionListener(mListener);
        mSaveAs.addActionListener(mListener);
        mQuit.addActionListener(mListener);
        mUndo.addActionListener(mListener);
        mCut.addActionListener(mListener);
        mCopy.addActionListener(mListener);
        mPaste.addActionListener(mListener);
        mDelete.addActionListener(mListener);
        mFindNext.addActionListener(mListener);
        mFind.addActionListener(mListener);
        mTitle.addActionListener(mListener);
        mAbout.addActionListener(mListener);
        frame.pack(); //压缩整理窗口上的控件
    }

    class MenuListener implements ActionListener{

        public void showMsg(String sMsg){
            JOptionPane.showMessageDialog(null,sMsg);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String actionCommand = e.getActionCommand();
            if("新建（N）".equals(actionCommand)){
                showMsg("新建按钮被点击！");
                return;
            }
            if("打开（O）".equals(actionCommand)){
                showMsg("打开按钮被点击！");
                return;
            }
            if("保存（S）".equals(actionCommand)){
                showMsg("保存按钮被点击！");
                return;
            }
            if("另存为（A）".equals(actionCommand)){
                showMsg("另存为按钮被点击！");
                return;
            }
            if("退出（Q）".equals(actionCommand)){
                showMsg("退出按钮被点击！");
                return;
            }

            showMsg(actionCommand + "按钮被点击！");
        }
    }

    public static void main(String[] args) {
        MenuDemo md = new MenuDemo();
    }

}
