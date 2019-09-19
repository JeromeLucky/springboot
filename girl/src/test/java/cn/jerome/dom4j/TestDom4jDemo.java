/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: TestDom4jDemo
 * Author:   JG
 * Date:     2019/9/11 20:42
 * Description: 测试类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package cn.jerome.dom4j;

import org.junit.Test;
import java.io.File;

/**
 * 〈一句话功能简述〉<br> 
 * 〈测试类〉
 *
 * @author JG
 * @create 2019/9/11
 * @since 1.0.0
 */

public class TestDom4jDemo {
    @Test
    public void readxml() {
        File file= new File(TestDom4jDemo.class.getResource("/testReadXml.xml").getPath());
        try{
            if(file.exists()){
                Dom4jDemo.readXml(file);
            }else{

                file.createNewFile();
                Dom4jDemo.readXml(file);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
    @Test
    public void writexml() {
        File file= new File(TestDom4jDemo.class.getResource("/testWriteXml.xml").getPath());
        try{
            if(file.exists()){
                Dom4jDemo.writeXml(file);
            }else{

                file.createNewFile();
                Dom4jDemo.writeXml(file);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
