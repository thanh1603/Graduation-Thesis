package vnua.khoaluan.common;

import java.io.File;

public class Constant {
    public static class TEMPLATE_VIEW {
        // Trang chu
        public static final String INDEX = "index";
        // Trang tat ca san pham
        public static final String PRODUCT = "product";
        // Gio hang
        public static final String CARD = "card";
        // Trang chu admin
        public static final String ADMIN = "admin";
        // Chi tiet mot san pham
        public static final String PRODUCT_DETAIL = "productDetail";
        // trang tin tuc
        public static final String INFOMATION = "information";
        // dang nhap
        public static final String LOGIN = "login";
        // dang ki
        public static final String REGISTER = "register";
        // dat hang
        public static final String CHECKOUT = "checkout";
        // lien he
        public static final String CONTACT = "contact";
        //gioi thieu
        public static final String INTRODUCE = "introduce";

        public static final String LIST_ORDER = "listOrder";

        public static final String ORDER_DETAIL = "orderDetail";

        // ------------------ Phan cho admin ----------------------
        public static final String ADMIN_PRODUCT = "admin/product";
        public static final String ADMIN_ADD_PRODUCT = "admin/addProduct";
        public static final String ADMIN_EDIT_PRODUCT = "admin/editProduct";
        public static final String ADMIN_UPDATE_PRODUCT = "admin/editProduct";
        public static final String ADMIN_CATALOG = "admin/catalog";
        public static final String ADMIN_ADD_CATALOG = "admin/addCatalog";
        public static final String ADMIN_EDIT_CATALOG = "admin/editCatalog";
        public static final String ADMIN_LIST_ORDER = "admin/listOrder";
        public static final String ADMIN_ORDER_DETAIL = "admin/orderDetail";
        public static final String ACCESS_DENIED = "acessdenied";

    }

    public static class ROLE {
        public static final String ADMIN = "ADMIN";
        public static final String USER = "USER";
    }

    public  static  class STATUS {
        public  static  final  int OK = 1;
        public  static final  int ERROR = 0;
    }

    public  static  class MSG_CODE {
        public static final String MSG_001 = "MSG_001";
        public static final String MSG_002 = "MSG_002";
        public static final String MSG_003 = "MSG_003";
        public static final String MSG_004 = "MSG_004";
        public static final String MSG_005 = "MSG_005";
        public static final String MSG_006 = "MSG_006";
        public static final String MSG_007 = "MSG_007";
        public static final String MSG_008 = "MSG_008";
        public static final String MSG_009 = "MSG_009";
        public static final String MSG_010 = "MSG_010";
        public static final String MSG_011 = "MSG_010";
        public static final String MSG_012= "MSG_012";
        public static final String MSG_013 = "MSG_013";
        public static final String MSG_014 = "MSG_014";
        public static final String MSG_015 = "MSG_015";
        public static final String MSG_016 = "MSG_016";
        public static final String MSG_017 = "MSG_017";
        public static final String MSG_018 = "MSG_018";
        public static final String MSG_019 = "MSG_019";
        public static final String MSG_020 = "MSG_020";
        public static final String MSG_021 = "MSG_021";
        public static final String MSG_022 = "MSG_022";

    }

    public static final String ROOT_IMAGE_URL = "D:\\KhoaLuan\\images\\" ;
    public static final String ROOT_CART = "D:\\KhoaLuan\\carts\\";

    public static final String BLANK = "";

    public  static class PAGING {
        public  int MAX_VIEW_PAGE = Constant.MAX_VIEW_PAGE;
        public  int MAX_SHOW = Constant.MAX_SHOW;
    }

    public static final int MAX_VIEW_PAGE = 10;
    public static final int MAX_SHOW = 10;

    public static class  FLAG_CODE {
        public static int INSERT = 1;
        public  static int UPDATE = 2;
        public  static int DELETE = 3;
        public  static int DELETE_ERROR = 30;
    }

    public static class  CART_CODE {
        public static int  ADD = 1;
        public  static int UPDATE = 2;
        public  static int DELETE = 3;
        public  static int DELETE_ERROR = 30;
    }

    public  class  REQUEST_CODE {
        public  static  final  String CATATLOG_CODE = "catalogCode";
        public  static  final  String FLAG = "flag";
    }

    public  static class  SESSION_CODE {
        public  static  final  String AD_CATALOG_CODE = "AD_CATALOG_CODE";
        public  static  final  String AD_TEXT_SEARCH = "AD_TEXT_SEARCH";
        public  static  final  String AD_PAGE_CURRENT = "AD_PAGE_CURRENT";
        public  static  final  String AD_FLAG = "AD_FLAG";
        public  static  final  String ORDER_FLAG = "ORDER_FLAG";
        public  static  final  String CART = "CART";
        public  static  final  String CART_CHECKOUT_REDIRIECT = "CART_CHECKOUT_REDIRIECT";

        //Order
        public  static  final  String AD_ORDER_TEXT_SEARCH = "AD_ORDER_TEXT_SEARCH";
        public  static  final  String AD_ORDER_FROM_DATE = "AD_ORDER_FROM_DATE";
        public  static  final  String AD_ORDER_TO_DATE = "AD_ORDER_TO_DATE";
        public  static  final  String AD_ORDER_STATUS = "AD_ORDER_STATUS";
        public  static  final  String AD_ORDER_FLAG = "AD_ORDER_FLAG";
    }

    public  static class CONFIG {
        public  static  final  String FEE_TRANSFER = "FEE_TRANSFER";
    }

    public static class DATE_FORMAT {
        public  static  final  String ORDER_FORMAT = "HH:mm:yy dd/MM/yyyy";
        public  static  final  String ORDER_CODE_FORMAT = "yyyyMMddHHmmss";
    }

    public  static  class ORDER_STATUS {
        // Trang thi vua dat hang xong
        public  static  final int INIT = 1;
        // Trang thai da tiep nhan don hang
        public  static  final int RECIER = 2;
        // Trang thai dang van chuyen
        public  static  final int TRANSFER = 3;

        // Trang thai huy don hang
        public  static  final int CANCEL = -99;
        // Trang thai da giao hang xong
        public  static  final int END = 100;
    }

    public static  long FEE_TRANSFER_DEFAULT = 35000;

}
