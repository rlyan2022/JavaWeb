package com.sanqing.fenye;

public class Test {
    public static void main(String[] args) {
        Page page = PageUtil.createPage(5, 34, 7);
        System.out.println(page.getBeginIndex());
        System.out.println(page.getCurrentPage());
        System.out.println(page.getEveryPage());
        System.out.println(page.getTotalCount());
        System.out.println(page.getTotalPage());
        System.out.println(page.isHasNextPage());
        System.out.println(page.isHasPrePage());
    }
}
