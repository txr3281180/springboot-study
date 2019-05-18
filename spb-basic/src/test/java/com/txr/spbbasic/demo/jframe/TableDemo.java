package com.txr.spbbasic.demo.jframe;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class TableDemo {
    public static void main(String[] args) {
        JFrame frame = new JFrame("用对象数组构建表格");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        frame.getContentPane().add(panel);
        String titleName[] = {"学号","姓名","出生日期"};
        Object data[][] = {
                {"S_11111111101","卡卡西","1979-01-15"},
                {"L_22234111111","自来也","1968-10-23"},
                {"S_11111112321","鼬","1985-12-16"},
                {"A_12239999999","凯","1978-06-23"}
        };
        JTable table = new JTable(data, titleName);
        JScrollPane pane = new JScrollPane(table);
        panel.add(pane);
        frame.setSize(480, 160);
        frame.setVisible(true);
    }
}
