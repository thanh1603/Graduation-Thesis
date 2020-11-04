package vnua.khoaluan.common;

public class PargingInfo {
    public  int maxPage = 10;
    public  int maxItemView = 2;
    public  int total = 10;
    public  int pageCurrent  = 1;

    public int getMaxPage() {
        return maxPage;
    }

    public void setMaxPage(int maxPage) {
        this.maxPage = maxPage;
    }

    public int getMaxItemView() {
        return maxItemView;
    }

    public void setMaxItemView(int maxItemView) {
        this.maxItemView = maxItemView;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageCurrent() {
        return pageCurrent;
    }

    public void setPageCurrent(int pageCurrent) {
        this.pageCurrent = pageCurrent;
    }
}
