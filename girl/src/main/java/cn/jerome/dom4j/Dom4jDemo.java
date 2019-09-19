/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: Dom4jDemo
 * Author:   JG
 * Date:     2019/9/11 19:56
 * Description: Dom4j Api 实现简单的 常见xml和解析xml
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package cn.jerome.dom4j;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.Executors;

/**
 * 〈一句话功能简述〉<br> 
 * 〈Dom4j Api 实现简单的 常见xml和解析xml〉
 *
 * @author JG
 * @create 2019/9/11
 * @since 1.0.0
 */

public class Dom4jDemo {
    public  static void readXml(File file){
        /*
            1 文件流读取xml  Document
                SAXReader reader = new SAXReader()
                Document document = reader.read(new File(String path))
            2 解析字符串获取 Document
                DocumentHelper.parseText(String xmlString)
         */
        Document doc=null;
        SAXReader reader = new SAXReader();
        try{
            doc = reader.read(file);
            Element rootElement = doc.getRootElement();
            System.out.println("根节点：" + rootElement.getName()); // 拿到根节点的名称
            // 获取根节点下的子节点head
            Element recordEle;
           for (Iterator iter = rootElement.elementIterator("head");iter.hasNext();) {
                recordEle = (Element) iter.next();
                String title = recordEle.elementTextTrim("title"); // 拿到head节点下的子节点title值
                System.out.println("title:" + title);
               // 获取子节点head下的子节点script
                Element itemEle;
                for(Iterator iters=recordEle.elementIterator("script");iters.hasNext();){
                    itemEle = (Element) iters.next();
                    String username = itemEle.elementTextTrim("username"); // 拿到head下的子节点script下的字节点username的值
                    String password = itemEle.elementTextTrim("password");

                    System.out.println("username:" + username);
                    System.out.println("password:" + password);
                }
            }
            // 获取根节点下的子节点body
            // 遍历body节点
                 Element recordEless;
            for (Iterator iterss = rootElement.elementIterator("body");iterss.hasNext();) {
                recordEless= (Element) iterss.next();
                String result = recordEless.elementTextTrim("result"); // 拿到body节点下的子节点result值
                System.out.println("result:" + result);


                Element itemEles;
                for (Iterator itersElIterator = recordEless.elementIterator("form");itersElIterator.hasNext();) {
                    itemEles = (Element) itersElIterator.next();

                    //String banlce = itemEles.elementTextTrim("banlce"); // 拿到body下的子节点form下的字节点banlce的值
                    //String subID = itemEles.elementTextTrim("subID");

                    // 获取子节点body下的子节点form
                    Element underform;
                    for (Iterator underformIterator = itemEles.elementIterator();underformIterator.hasNext();) {
                        underform = (Element) underformIterator.next();
                        if("banlce".equals(underform.getName())){
                            System.out.println("banlce:" + underform.getText());
                        }else if("subID".equals(underform.getName())){
                            System.out.println("subID:" + underform.getText());
                        }
                        if (underform.attributes().size() > 0) {
                            Attribute attribute;
                            for (Iterator<Attribute> attr = underform.attributeIterator(); attr.hasNext(); ) {
                                attribute = attr.next();
                                String attributeName = attribute.getName();
                                if (attributeName.equals("country")) {
                                    System.out.println(underform.getName()+" attr country is:" + attribute.getValue());
                                } else {
                                    System.out.println(underform.getName()+" attr unit is:" + attribute.getValue());
                                }

                            }
                        }

                    }


                }
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public static void writeXml(File file){
        /*
            Document document = DocumentHelper.createDocument();
                addElement
                setText
            OutputFormat format = OutputFormat.createPrettyPrint();// 创建文件输出的时候，自动缩进的格式
            format.setEncoding("UTF-8");
            XMLWriter output = new XMLWriter(new FileWriter(file));
         */
        Document document = DocumentHelper.createDocument();
        Element rootElement = document.addElement("students");
        Element studentElement = rootElement.addElement("student");
        studentElement.addAttribute("email", "5@5.com");
        Element idElement = studentElement.addElement("stuId");
        idElement.setText("55");
        Element nameElement = studentElement.addElement("stuName");
                studentElement.addAttribute("sex","男");
        nameElement.setText("55Name");
        Element ageElement = studentElement.addElement("age");
        ageElement.setText("55");

        try {
            OutputFormat format = OutputFormat.createPrettyPrint();// 创建文件输出的时候，自动缩进的格式
            format.setEncoding("UTF-8");
            XMLWriter output = new XMLWriter(new FileWriter(file),format);
            output.write(document);
            output.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
