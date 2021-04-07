package com.nexus.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Page {
    // 开始，从第几条开始，默认start从1开始
    private int start = 1;
    // 默认每页显示5条
    private int limit = 5;
    // 结束，每页到第几个结束，默认end从5开始
    private int end = 5;
    //总个数
    private int total;
    // 总页数
    private Integer rows;
    // 所有页数
    private List<Integer> pages = new ArrayList<>();

    private Integer currentPage;

    private String tag;

    // isHasPrevious 判断是否有前一页
    // boolean默认值是false
    public boolean isHasPrevious(){
        return start != 1;
//        if(start==1)
//            return false;
//        return true;
    }

    // isHasNext 判断是否有后一页
    public boolean isHasNext(){
        return start != getLast();
//        if(start==getLast())
//            return false;
//        return true;
    }

    // getTotalPage 根据 每页显示的数量count以及总共有多少条数据total，计算出总共有多少页
    public int getTotalPage(){
        int totalPage;
        // 假设总数是50，是能够被5整除的，那么就有10页
        if (0 == total % limit)
            totalPage = total /limit;
            // 假设总数是51，不能够被5整除的，那么就有11页
        else
            totalPage = total / limit + 1;
        if(0==totalPage)
            totalPage = 1;
        return totalPage;
    }


    // getLast 计算出最后一页开始的数值是多少，用于判断是否有最后一页
    public int getLast(){
        int last;
        // 假设总数是10，是能够被5整除的，那么最后一页的开始就是6
        if (0 == total % limit)
            last = total - limit + 1;
            // 假设总数是11，不能够被5整除的，那么最后一页的开始就是6
        else
            last = total - total % limit + 1;
        last = Math.max(last, 0);
        return last;
    }

    public List<Integer> getPages() {
        for (int i = 1; i <= getRows(); i++) {
            pages.add(i);
        }
        return pages;
    }

    /**
     * 获取起始页码
     *
     * @return
     */
    public int getFrom() {
        int from = currentPage - 2;
        return Math.max(from, 1);
    }

    /**
     * 获取结束页码
     *
     * @return
     */
    public int getTo() {
        // 46
//        System.out.println("start"+start);
        // 46
//        System.out.println("getLast"+getLast());

        // 如果start==getLast-5，即代表只剩下1页。
        if(start==(getLast()-5)){
            int to = currentPage + 1;
            int total = getTotal();
            return Math.min(to, total);
        }

        // 如果start!=getLast，即代表至少剩下2页及以上。
        if(start!=getLast()){
            int to = currentPage + 2;
            int total = getTotal();
            return Math.min(to, total);
        }

        int to = currentPage + 1;
        return to > currentPage ? currentPage : to;
    }
}
