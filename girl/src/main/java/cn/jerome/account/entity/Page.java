/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: Page
 * Author:   JG
 * Date:     2019/8/25 14:21
 * Description: 分页信息
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package cn.jerome.account.entity;

/**
 * 〈一句话功能简述〉<br> 
 * 〈分页信息〉
 *
 * @author JG
 * @create 2019/8/25
 * @since 1.0.0
 */

public class Page {
    private  Integer page;
    private  Integer size;
    private  String  sort;
    private  String  sdix;

    public String getSdix() {
        return sdix;
    }

    public void setSdix(String sdix) {
        this.sdix = sdix;
    }

    public Page() {
        super();
    }
    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        if("1".equals(sort)){
            this.sort ="ASC";
        }else if("0".equals(sort)){
            this.sort ="DESC";
        }else{
            this.sort ="DESC";
        }
    }

}
