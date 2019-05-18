package com.txr.spbbasic.demo.jframe;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public class JFrameDemo extends JFrame{


    JPanel panel;
    GridBagLayout gbl;
    GridBagConstraints gbc;
    JLabel lalArray[];
    JTextField txtArray[];
    String sProp[] =
            {
                    "path.separator",
                    "file.separator",
                    "file.encoding",
                    "java.vm.version",
                    "java.class.version",
                    "os.arch",
                    "sun.cpu.isalist",
                    "os.name",
                    "os.version",
                    "user.name",
                    "user.home",
                    "user.dir",
                    "java.class.path"
            };

    String sLbl[] =
            {
                    "路径分割符",
                    "文件分隔符",
                    "文件编码格式",
                    "虚拟机版本",
                    "类文件版本",
                    "操作系统架构",
                    "CPU相关信息",
                    "操作系统名称",
                    "操作系统版本",
                    "登录用户名称",
                    "登录用户目录",
                    "系统当前目录",
                    "类搜索路径"
            };

    /*
    启动窗口
     */
    public JFrameDemo(){
        super("JFrameDemo 窗口，使用网格袋布局");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //点击关闭按钮程序结束
        //setSize(640, 480);
        panel = new JPanel();
        gbl = new GridBagLayout();

        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTHWEST;  //靠左上方向对齐
        gbc.fill = GridBagConstraints.NONE;   //不采用拉伸

        panel.setLayout(gbl);

        lalArray = new JLabel[13];
        txtArray = new JTextField[13];

        for (int i = 0; i <= 12; i++) {
            lalArray[i] = new JLabel(sLbl[i]);
            addControls(i + 1, 1, lalArray[i]);
            txtArray[i] = new JTextField(System.getProperty(sProp[i]), 40);
            addControls(i + 1, 2, txtArray[i]);
        }
        getContentPane().add(panel);
        pack();
        setVisible(true);  //
    }

    public void addControls(int iRow, int iCol, Component c){
        gbc.gridy = iRow;
        gbc.gridx = iCol;
        gbl.setConstraints(c, gbc);
        panel.add(c);
    }


    public static void main(String[] args) {
        JFrameDemo jf = new JFrameDemo();
    }

}
