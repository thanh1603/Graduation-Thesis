package vnua.khoaluan.service.impl;

import com.itextpdf.layout.element.Cell;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vnua.khoaluan.bean.Cart;
import vnua.khoaluan.common.StringUtil;
import vnua.khoaluan.entities.Product;
import vnua.khoaluan.entities.User;
import vnua.khoaluan.bean.Result;
import vnua.khoaluan.common.Constant;
import vnua.khoaluan.entities.Order;
import vnua.khoaluan.service.IConfigService;
import vnua.khoaluan.service.IOrderService;
import vnua.khoaluan.service.IUserService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements IOrderService {
    private static final Logger logger = Logger.getLogger(OrderServiceImpl.class);

    @Autowired(required = true)
    private MongoTemplate mongoTemplate;

    @Autowired
    private IUserService iUserService;

    @Autowired
    private IConfigService iConfigService;

    DateFormat dateFormat = new SimpleDateFormat(Constant.DATE_FORMAT.ORDER_FORMAT);

    public Result saveOrder(User userInfo, Order order) {
        Result result = new Result();
        result.setStatus(Constant.STATUS.OK);
        try{
            // TODO validate
            DateFormat dateFormat = new SimpleDateFormat(Constant.DATE_FORMAT.ORDER_CODE_FORMAT);
            order.setCreateDate(this.dateFormat.format(new Date()));
            order.setCode(dateFormat.format(new Date()));
            order.setUserID(userInfo.getEmail());
            order.setStatus(Constant.ORDER_STATUS.INIT);
            userInfo.getOrders().add(order);
            this.mongoTemplate.save(userInfo);
        }catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            result.setStatus(Constant.STATUS.ERROR);
        }
        return  result;
    }

    public Result updateStatusOrder(String userId, String orderCode, int status) {
        Result result = new Result();
        result.setStatus(Constant.STATUS.OK);
        try {
            User user = this.iUserService.findByEmail(userId);
            if(user != null) {
                for(Order order: user.getOrders()) {
                    if(order.getCode().equals(orderCode)) {
                        order.setStatus(status);
                        order.setUpdateDate(this.dateFormat.format(new Date()));
                        this.mongoTemplate.save(user);
                        break;
                    }
                }
            }
        }catch (Exception ex ) {
            logger.error(ex.getMessage(), ex);
            result.setStatus(Constant.STATUS.ERROR);
        }
        return  result;
    }

    public Order findOrderByOrderCode(String userID, String orderCode) {
       try{
            User user = this.iUserService.findByEmail(userID);
            if(user != null) {
                for(Order order: user.getOrders()) {
                    if(order.getCode().equals(orderCode) && order.getUserID().equals(userID)) {
                        return order;
                    }
                }
            }
       }catch (Exception ex) {
           logger.error(ex.getMessage(), ex);
       }
       return null;
    }

    public Result printOrder(String userName, String orderCode) {
        Result result = new Result();
        result.setStatus(Constant.STATUS.OK);
        try{
            User user = this.iUserService.findByEmail(userName);
            Order order = null;
            if(user != null) {
                for(Order item: user.getOrders()) {
                    if(item.getCode().equals(orderCode)) {
                        order = item;
                        break;
                    }
                }
                if(order != null) {
                    String dir = Constant.ROOT_CART.concat(orderCode);
                    File file = new File(dir);
                    file.mkdirs();
                    FileUtils.cleanDirectory(file);
                    DateFormat dateFormat = new SimpleDateFormat(Constant.DATE_FORMAT.ORDER_CODE_FORMAT);
                    String fileName =  dir.concat(File.separator)
                                      .concat(orderCode)
                                      .concat(".pdf");
                    result.setFileName(fileName);
                    Document document = new Document();
                    PdfWriter priPdfWriter = PdfWriter.getInstance(document, new FileOutputStream(fileName));
                    document.open();
                    Paragraph paragraph;
                    BaseFont courier = BaseFont.createFont(BaseFont.TIMES_ROMAN, BaseFont.CP1252, BaseFont.EMBEDDED);
                    PdfPTable table;
                    Font font = new Font();
                    font.setSize(20);
                    // In Thong tin hoa don

                    table = new PdfPTable(2);
//                    table.setWidths(new int[] {50, 50});
                    table.setWidthPercentage(100);
                    PdfPCell cell;
                    table.addCell(emptyCell(null));


                    font = new Font();
                    font.setSize(15);
                    font.setStyle(Font.BOLD);
                    cell = emptyCell(new Paragraph("INVOICE - " + orderCode, font));
                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    table.addCell(cell);
                    // Header
                    //------

                    table.addCell(emptyCell(null));
                    table.addCell(emptyCell(null));
                    table.addCell(emptyCell(null));
                    table.addCell(emptyCell(null));
                    //
                    font = new Font(courier, 13, Font.BOLD);
                    cell = this.emptyCell(new Paragraph("From: ", font));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);

                    cell = this.emptyCell(new Paragraph("To: ", font));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);

                    // In dia chi
                    font = new Font(courier, 10, Font.NORMAL);
                    StringBuilder addrFrom = new StringBuilder("");
                    addrFrom.append("Vũ Thị Hoài Linh\n\n");
                    addrFrom.append("Học Viện Nông Nghiệp Việt Nam \n\n");
                    addrFrom.append("0352923968");
                    paragraph = new Paragraph(addrFrom.toString(), font);
                    paragraph.setSpacingBefore(50);
                    paragraph.setSpacingAfter(50);
                    cell =  emptyCell(paragraph);
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);

                    StringBuilder addrTo = new StringBuilder("");
                    addrTo.append(user.getFullName() + "\n\n");
                    addrTo.append(user.getAddress() + "\n\n");
                    addrTo.append(user.getPhone());
                    paragraph = new Paragraph(addrTo.toString(), font);
                    paragraph.setSpacingBefore(50);
                    paragraph.setSpacingAfter(50);
                    cell =  emptyCell(paragraph);
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);

                    table.addCell(cell);
                    document.add(table);

                    ///==========================
                    //=========================================
                    //IProduduct
                    PdfPTable tableProduct = new PdfPTable(4);
                    tableProduct.setWidthPercentage(100);
                    tableProduct.setSpacingBefore(30);
                    font = new Font();
                    font.setSize(12);
                    font.setStyle(Font.BOLD);
                    font.setColor(BaseColor.WHITE);
                    BaseColor baseColor  = new BaseColor(59, 163, 219);
                    PdfPCell headCell = this.emptyCellProduct(new Paragraph("Name", font), 1);
                    headCell.setBackgroundColor(baseColor);
                    tableProduct.addCell(headCell);

                    headCell = this.emptyCellProduct(new Paragraph("Price(VND)", font), 2);
                    headCell.setBackgroundColor(baseColor);
                    tableProduct.addCell(headCell);

                    headCell = this.emptyCellProduct(new Paragraph("Amount", font), 3);
                    headCell.setBackgroundColor(baseColor);
                    tableProduct.addCell(headCell);

//                    headCell = this.emptyCellProduct(new Paragraph("Subtotal", font), 4);
//                    headCell.setBackgroundColor(baseColor);
//                    tableProduct.addCell(headCell);

                    headCell = this.emptyCellProduct(new Paragraph("Total(VND)", font), 5);
                    headCell.setBackgroundColor(baseColor);
                    tableProduct.addCell(headCell);

                    long price = 0;
                    for(Cart cart: order.getCarts()) {
                        price += this.addRows(tableProduct, cart);
                    }

                    String pattern = "###,###.###";
                    DecimalFormat decimalFormat = new DecimalFormat(pattern);
                    addRowsFooterCart(tableProduct,"Subtotal", (decimalFormat.format(price)));
                    Long taxShip = Long.getLong((this.iConfigService.getConfigByKey(Constant.CONFIG.FEE_TRANSFER)));
                    if(taxShip == null) {
                        taxShip = Constant.FEE_TRANSFER_DEFAULT;
                    }

                    addRowsFooterCart(tableProduct, "Shipping", decimalFormat.format(taxShip.longValue() ));

                    addRowsFooterCart(tableProduct, "Total due", decimalFormat.format(taxShip.longValue() + price));

                    document.add(tableProduct);
                    onEndPage(priPdfWriter, document);

                    document.close();
                }

            }
       }catch (Exception ex) {
           logger.error(ex.getMessage(), ex);
           result.setStatus(Constant.STATUS.ERROR);
       }
        return  result;
    }

    public Result getOrders(String textSearch, String fromDate, String toDate, int status, int offset,int limit) {
        Result result = new Result();
        result.setStatus(Constant.STATUS.OK);
        try{
            List<User> users = this.iUserService.findALL();
            List<Order> orders = new LinkedList<Order>();
            for(User user: users) {
                orders.addAll(user.getOrders());
            }

            // Sap xep order
            Collections.sort(orders, new Comparator<Order>() {
                public int compare(Order o1, Order o2) {
                    return o1.getCreateDate().compareTo(o2.getCreateDate());
                }
            });

            // Tim kiem order
            List<Order> orderSearch = new LinkedList<Order>();
            if(StringUtil.isEmptyWithTrim(textSearch)) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                if(StringUtil.isEmptyWithTrim(fromDate)) {
                    fromDate =  dateFormat.format(new Date());
                }

                Calendar createDate = Calendar.getInstance();
                createDate.setTime(dateFormat.parse(fromDate));
                createDate.set(Calendar.HOUR, 0);
                createDate.set(Calendar.MINUTE, 0);
                createDate.set(Calendar.SECOND, 0);

                Calendar calToDate = Calendar.getInstance();

                Calendar calendarOrder = Calendar.getInstance();
                for(Order order: orders) {
                    calendarOrder.setTime(dateFormat.parse(order.getCreateDate().substring(8).trim()));
                    calendarOrder.set(Calendar.HOUR, 0);
                    calendarOrder.set(Calendar.MINUTE, 0);
                    calendarOrder.set(Calendar.SECOND, 0);
                    if(!StringUtil.isEmptyWithTrim(toDate)) {
                        calToDate.setTime(dateFormat.parse(toDate));
                        if((calendarOrder.after(createDate) || createDate.equals(calendarOrder) )
                                && (calendarOrder.before(calToDate) || calendarOrder.equals(calToDate) )) {
                            if(status == -1) {
                                orderSearch.add(order);
                            }else{
                                if(order.getStatus() == status) {
                                    orderSearch.add(order);
                                }
                            }
                        }
                    }else{
                        if(createDate.after(calendarOrder) || createDate.equals(calendarOrder)) {
                            if(status == -1) {
                                orderSearch.add(order);
                            }else{
                                if(order.getStatus() == status) {
                                    orderSearch.add(order);
                                }
                            }
                        }
                    }
                }
            }else{ // truong hop ma don hang khac null, tim kiem theo ma don hang
                for(Order order: orders) {
                    if((order.getCode().toLowerCase()).indexOf(textSearch.toLowerCase().trim()) != -1) {
                        orderSearch.add(order);
                    }
                }
            }

            // sort
            Collections.sort(orderSearch, new Comparator<Order>() {
                public int compare(Order o1, Order o2) {
                    return o1.getCode().compareTo(o2.getCode());
                }
            });

            List<Order> orderResult;

            if(offset == 0 && orderSearch.size() < offset + limit) {
                limit = orderSearch.size();
            }else if(offset > orderSearch.size()) {
                offset = orderSearch.size() - 1;
                limit = 0;
            } else if(orderSearch.size() < offset + limit) {
                limit = orderSearch.size() - offset;
            }

            result.setTotal(orderSearch.size());
            orderResult = new ArrayList<Order>(orderSearch).subList(offset, offset + limit);
            result.getOrders().addAll(orderResult);
            result.setStatus(Constant.STATUS.OK);
            return  result;
        }catch (Exception ex) {
            result.setStatus(Constant.STATUS.ERROR);
            logger.error(ex.getMessage(), ex);
        }
        return result;
    }

    private PdfPCell emptyCell(Paragraph paragraph) {
        PdfPCell cell;
        if(paragraph != null) {
            cell =new PdfPCell(paragraph);
        } else{
            cell =new PdfPCell();
        }
        cell.setUseVariableBorders(true);
        cell.setBorderColorTop(BaseColor.WHITE);
        cell.setBorderColorBottom(BaseColor.WHITE);
        cell.setBorderWidth(0);
        cell.setPadding(5);
       return  cell;
    }

    private PdfPCell emptyCellProduct(Paragraph paragraph, int index) {
        PdfPCell cell = new PdfPCell(paragraph);
        cell.setUseVariableBorders(true);
        if(index >= 2) {
            cell.setBorderWidthLeft(0);
        }
        cell.setBorderColorTop(BaseColor.BLACK);
        cell.setBorderColorBottom(BaseColor.BLACK);
        cell.setBorderWidth(1);
        cell.setPadding(5);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        return  cell;
    }

    private long addRows(PdfPTable table, Cart cart) throws IOException, DocumentException {
        String pattern = "###,###.###";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        BaseFont courier = BaseFont.createFont(BaseFont.TIMES_ROMAN, BaseFont.CP1252, BaseFont.EMBEDDED);
        Font font = new Font(courier, 10);
        // Name
        Paragraph paragraph = new Paragraph(cart.getProduct().getName(), font);
        font.setColor(BaseColor.BLACK);
        paragraph.setFont(font);
        PdfPCell pdfPCell = emptyCellProduct(paragraph, 1);
        pdfPCell.setBorderColorTop(BaseColor.WHITE);
        pdfPCell.setBorderWidthTop(0);
        table.addCell(pdfPCell);

        long price = cart.getProduct().getPrice() - (cart.getProduct().getPrice()* cart.getProduct().getDiscount()/100);

        paragraph = new Paragraph(decimalFormat.format(price), font);
        pdfPCell = emptyCellProduct(paragraph, 2);
        pdfPCell.setBorderColorTop(BaseColor.WHITE);
        pdfPCell.setBorderWidthTop(0);
        pdfPCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.addCell(pdfPCell);

        paragraph = new Paragraph(cart.getAmount() + "", font);
        pdfPCell = emptyCellProduct(paragraph, 3);
        pdfPCell.setBorderColorTop(BaseColor.WHITE);
        pdfPCell.setBorderWidthTop(0);
        pdfPCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.addCell(pdfPCell);

        paragraph = new Paragraph(decimalFormat.format(cart.getAmount() * price), font);
        pdfPCell = emptyCellProduct(paragraph, 4);
        pdfPCell.setBorderColorTop(BaseColor.WHITE);
        pdfPCell.setBorderWidthTop(0);
        pdfPCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.addCell(pdfPCell);
        return cart.getAmount() * price;
    }

    private void addRowsFooterCart(PdfPTable tableProduct,String title,  String text) throws IOException, DocumentException {
        BaseFont courier = BaseFont.createFont(BaseFont.TIMES_ROMAN, BaseFont.CP1252, BaseFont.EMBEDDED);
        Font font  = new Font(courier, 10 , Font.BOLD);
        font.setSize(10);
        font.setStyle(Font.BOLD);
        font.setColor(BaseColor.BLACK);
        Paragraph   paragraph = new Paragraph(title, font);
        PdfPCell cell1 = this.emptyCellProduct(null, 1);
        cell1.setBorderColor(BaseColor.WHITE);
        cell1.setBorderWidth(0);
        cell1.setBorderColorTop(BaseColor.WHITE);
        cell1.setBorderColorBottom(BaseColor.WHITE);
        tableProduct.addCell(cell1);
        tableProduct.addCell(cell1);
        cell1 = this.emptyCellProduct(paragraph, 1);
        cell1.setBorderColorTop(BaseColor.WHITE);
        cell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tableProduct.addCell(cell1);
        cell1 = this.emptyCellProduct(new Paragraph(text, font), 4);
        cell1.setBorderColorTop(BaseColor.WHITE);
        cell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tableProduct.addCell(cell1);
    }

    public void onEndPage(PdfWriter writer, Document document) {
        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase("http://localhost:8080/DoAn"), 110, 30, 0);
        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase("page - " + document.getPageNumber()), 550, 30, 0);
    }
}
