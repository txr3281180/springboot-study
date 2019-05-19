package com.txr.spbbasic.dom4j;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class XmlUtils {

    public static void generateXml() {

        //1.第一种 创建文档及设置根元素节点的方式
        //创建文档的根节点
        Document document = DocumentHelper.createDocument();
        //创建文档的 根元素节点
        Element root = DocumentHelper.createElement("configuration");
        document.setRootElement(root);

        //2.第二种 创建文档及设置根元素节点的方式
        //Element root = DocumentHelper.createElement("Person");
        //Document document = DocumentHelper.createDocument(root);
        //给根节点添加属性
        //root.addAttribute("学校", "南大").addAttribute("位置", "江西");

        //给根节点添加孩子节点
        Element client = root.addElement("client");
        client.addElement("appName").addText("BondMobility");
        client.addElement("appType").addText("1");
        client.addElement("appGroup").addText("1");
        client.addElement("uuid").addText("BondMobility");
        client.addElement("auth").addText("test");
        client.addElement("appName").addText("test");

        Element serviceList = client.addElement("serviceList");

        Element subscribeList = client.addElement("subscribeList");
        subscribeList.addElement("topic").addText("1009778689");
        subscribeList.addElement("topic").addText("1009778690");

        Element serverGroup = root.addElement("serverGroup");
        serverGroup.addElement("server").addAttribute("IP", "192.168.1.242")
                .addAttribute("port","6666");

        Element heartbeat = root.addElement("heartbeat");
        heartbeat.addElement("maxHeartBeatTick").addText("5");
        heartbeat.addElement("hearbeatinterval").addText("5000");
        heartbeat.addElement("brokeninterval").addText("3000");
        heartbeat.addElement("switch").addText("1");

        Element zlib = root.addElement("zlib");
        zlib.addElement("switch").addText("1");
        zlib.addElement("threshold").addText("1024");

        root.addElement("connection").addElement("timeout").addText("6000");

        Element performance = root.addElement("performance");
        performance.addElement("threadNum").addText("4");
        performance.addElement("buffersize").addText("100000");
        performance.addElement("recvqueuesize").addText("10000");
        performance.addElement("sendqueuesize").addText("10000");

        Element log = root.addElement("log");
        log.addElement("level").addText("1");
        log.addElement("filename").addText("log");
        log.addElement("report").addText("0");
        log.addElement("reportlevel").addText("3");

        //把生成的xml文档存放在硬盘上  true代表是否换行
        OutputFormat format = new OutputFormat("    ", true);
        format.setEncoding("UTF-8");//设置编码格式
        XMLWriter xmlWriter = null;

        try{
            xmlWriter = new XMLWriter(new FileOutputStream("clientcfg1.xml"), format);
            xmlWriter.write(document);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                xmlWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    public static void parseXml(){
        //创建SAXReader对象
        SAXReader reader = new SAXReader();
        //读取文件 转换成Document
        Document document = null;
        try {
            document = reader.read(new File("clientcfg.xml"));
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        //第二种
//        String xmlStr = "<students>......</students>";
//        Document document = DocumentHelper.parseText(xmlStr);

        //获取根节点元素对象
        Element root = document.getRootElement();

        listNodes(root);

    }


    //遍历当前节点下的所有节点
    private static void listNodes(Element node){
        System.out.println("当前节点的名称：" + node.getName());
        //首先获取当前节点的所有属性节点
        List<Attribute> list = node.attributes();
        //遍历属性节点
        for(Attribute attribute : list){
            System.out.println("属性"+attribute.getName() +":" + attribute.getValue());
        }
        //如果当前节点内容不为空，则输出
        if(!(node.getTextTrim().equals(""))){
            System.out.println( node.getName() + "：" + node.getText());
        }
        //同时迭代当前节点下面的所有子节点
        //使用递归
        Iterator<Element> iterator = node.elementIterator();
        while(iterator.hasNext()){
            Element e = iterator.next();
            listNodes(e);
        }
    }
}
