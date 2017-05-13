package com.sanqing.common;


import java.util.ArrayList;
import java.util.Iterator;

/**
 * Struts分页显示逻辑Bean
 */
public class PageBean {

    int currentPage = 1;// 当前页

    public int totalPages = 0;// 总页数

    int pageRecorders = 10;// 每页10条数据

    int totalRows = 0;// 总数据数

    int pageStartRow = 0;// 每页的起始数

    int pageEndRow = 0;// 每页显示数据的终止数

    boolean hasNextPage = false; // 是否有下一页

    boolean hasPreviousPage = false; // 是否有前一页

    ArrayList arrayList;

    Iterator it;

    public String sort_id;

    public PageBean() {
    }

    public PageBean(ArrayList arrayList, String pass_sort_id) {
        this.sort_id = pass_sort_id;
        this.arrayList = arrayList;
        totalRows = arrayList.size();
        it = arrayList.iterator();
        hasPreviousPage = false;
        currentPage = 1;
        if ((totalRows % pageRecorders) == 0) {
            totalPages = totalRows / pageRecorders;
        } else {
            totalPages = totalRows / pageRecorders + 1;
        }

        if (currentPage >= totalPages) {
            hasNextPage = false;
        } else {
            hasNextPage = true;
        }

        if (totalRows < pageRecorders) {
            this.pageStartRow = 0;
            this.pageEndRow = totalRows;
        } else {
            this.pageStartRow = 0;
            this.pageEndRow = pageRecorders;
        }

    }

    public String getSort_id() {
        return sort_id;
    }

    public void setSort_id(String sort_id) {
        this.sort_id = sort_id;
    }

    /**
     * @return Returns the currentPage.
     */
    public String getCurrentPage() {
        return this.toString(currentPage);
    }

    /**
     * @param currentPage The currentPage to set.
     */
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    /**
     * @return Returns the pageRecorders.
     */
    public int getPageRecorders() {
        return pageRecorders;
    }

    /**
     * @param pageRecorders The pageRecorders to set.
     */
    public void setPageRecorders(int pageRecorders) {
        this.pageRecorders = pageRecorders;
    }

    /**
     * @return Returns the pageEndRow.
     */
    public int getPageEndRow() {
        return pageEndRow;
    }

    /**
     * @return Returns the pageStartRow.
     */
    public int getPageStartRow() {
        return pageStartRow;
    }

    /**
     * @return Returns the totalPages.
     */
    public String getTotalPages() {

        return this.toString(totalPages);
    }

    /**
     * @return Returns the totalRows.
     */
    public String getTotalRows() {
        return this.toString(totalRows);
    }

    /**
     * @return Returns the hasNextPage.
     */
    public boolean isHasNextPage() {
        return hasNextPage;
    }

    /**
     * @param hasNextPage The hasNextPage to set.
     */
    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    /**
     * @return Returns the hasPreviousPage.
     */
    public boolean isHasPreviousPage() {
        return hasPreviousPage;
    }

    /**
     * @param hasPreviousPage The hasPreviousPage to set.
     */
    public void setHasPreviousPage(boolean hasPreviousPage) {
        this.hasPreviousPage = hasPreviousPage;
    }

/*	public NewsList[] getNextPage() {

		currentPage = currentPage + 1;

		if ((currentPage - 1) > 0) {
			hasPreviousPage = true;
		} else {
			hasPreviousPage = false;
		}
		if (currentPage >= totalPages) {
			hasNextPage = false;
		} else {
			hasNextPage = true;
		}

		NewsList[] nsList = getNsList();
		this.description();
		return nsList;
	}*/

	/*public NewsList[] getPreviouspage() {
        currentPage = currentPage - 1;

		if (currentPage == 0) {
			currentPage = 1;
		}

		if (currentPage >= totalPages) {
			hasNextPage = false;
		} else {
			hasNextPage = true;
		}
		if ((currentPage - 1) > 0) {
			hasPreviousPage = true;
		} else {
			hasPreviousPage = false;
		}
		NewsList[] nsList = getNsList();
		this.description();
		return nsList;
	}

	public NewsList[] getNsList() {
		System.out.println("pageBean.getBooks（）开始执行；");
		if (currentPage * pageRecorders < totalRows) {// 判断是否为最后一页
			pageEndRow = currentPage * pageRecorders;
			pageStartRow = pageEndRow - pageRecorders;
		} else {
			pageEndRow = totalRows;
			pageStartRow = pageRecorders * (totalPages - 1);
		}
		NewsList[] books = new NewsList[pageEndRow - pageStartRow + 1];

		System.out.println("pageStartRow=" + pageStartRow);
		System.out.println("pageEndRow=" + pageEndRow);
		int j = 0;
		for (int i = pageStartRow; i < pageEndRow; i++) {
			NewsList book = (NewsList) arrayList.get(i);
			books[j++] = book;

		}
		System.out.println("要显示的页面数据已经封装,具体信息如下：");
		this.description();
		return books;
	}*/

    public String toString(int temp) {
        String str = Integer.toString(temp);
        return str;
    }

    public void description() {
        String description = "共有数据数:" + this.getTotalRows() + "共有页数: "
                + this.getTotalPages() + "当前页数为:" + this.getCurrentPage()
                + " 是否有前一页: " + this.isHasPreviousPage() + " 是否有下一页:"
                + this.isHasNextPage() + " 开始行数:" + this.getPageStartRow()
                + " 终止行数:" + this.getPageEndRow();
        System.out.println(description);
    }
}
