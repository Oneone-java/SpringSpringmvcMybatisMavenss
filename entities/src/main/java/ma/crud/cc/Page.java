package ma.crud.cc;

import java.util.ArrayList;
import java.util.List;

public class Page<T> {

    private int pageSize = 3;
    private int pageNo = 1;
    private int pageCount = 0;
    private int sum;
    private int p;

    public int getP() {
        return p;
    }

    public void setP(int p) {
        this.p = p;
    }

    private List<T> list = new ArrayList<T>();

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageCount() {
        if (sum % pageSize == 0) {
            return sum / pageSize;
        } else {
            return (sum / pageSize) + 1;
        }
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }


}
