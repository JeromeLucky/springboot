/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: TestPDFDemo1
 * Author:   JG
 * Date:     2019/9/11 22:42
 * Description: itext PDF demo
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package cn.jerome.itextpdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * 〈一句话功能简述〉<br> 
 * 〈itext PDF demo 〉
 *
 * @author JG
 * @create 2019/9/11
 * @since 1.0.0
 */

public class TestPDFDemo1 {
    public static void main(String[] args) throws FileNotFoundException, DocumentException {

        // 1.新建document对象
        Document document = new Document();

        // 2.建立一个书写器(Writer)与document对象关联，通过书写器(Writer)可以将文档写入到磁盘中。
        try {
            // 创建 PdfWriter 对象 第一个参数是对文档对象的引用，第二个参数是文件的实际名称，在该名称中还会给出其输出路径。
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("F:/Ideal/pdfdemo/test.pdf"));
            // 3.打开文档
            document.open();

            // 4.添加一个内容段落
            document.add(new Paragraph("Hello World!"));
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        // 5.关闭文档
        document.close();

    }
}
