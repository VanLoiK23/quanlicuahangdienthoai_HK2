package PDF;

import com.itextpdf.text.Chunk;
import java.awt.Desktop;
import java.awt.FileDialog;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import BUS.DungLuongRamBUS;
import BUS.DungLuongRomBUS;
import BUS.MauSacBUS;
import DAO.Khachhang_DAO;
import DAO.Nhacungcap_DAO;
import DAO.Nhanvien_DAO;
import DAO.PhieuNhap_DAO;
import DAO.PhieuXuat_DAO;
import DAO.Phieubansp_DAO;
import DAO.Sanpham_DAO;
import DAO.ct_phieunhap_DAO;
import DAO.ctphieuxuat_DAO;
import Model.ChitietPhieuNhap;
import Model.ChitietPhieuXuat;
import Model.PhieuNhap;
import Model.PhieuXuat;
import Model.Phieubansanpham;
import Model.Sanpham;

import java.util.Date;
import java.util.Random;

public class WriteFile_pdf {

    DecimalFormat formatter = new DecimalFormat("###,###,###");
    SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/YYYY HH:mm");
    Document document = new Document();
    FileOutputStream file;
    JFrame jf = new JFrame();
    FileDialog fd = new FileDialog(jf, "Xuất pdf", FileDialog.SAVE);
    JFrame jf1 = new JFrame();
    FileDialog fd1 = new FileDialog(jf1, "Xuất pdf", FileDialog.SAVE);
    Font fontNormal10;
    Font fontBold15;
    Font fontBold25;
    Font fontBoldItalic15;

    DungLuongRomBUS romBus = new DungLuongRomBUS();
    DungLuongRamBUS ramBus = new DungLuongRamBUS();
    MauSacBUS mausacBus = new MauSacBUS();
    
    double phanTramThue = 0.1; 

    private String generateRandomMST() {
        Random rand = new Random();
        StringBuilder mst = new StringBuilder();
        for (int i = 0; i < 10; i++) { // MST gồm 10 chữ số
            int digit = rand.nextInt(10); // Lấy số ngẫu nhiên từ 0 đến 9
            mst.append(digit);
        }
        return mst.toString();
    }

    public WriteFile_pdf() {
        try {
            fontNormal10 = new Font(BaseFont.createFont("lib/SVN-Times New Roman.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 12, Font.NORMAL);
            fontBold25 = new Font(BaseFont.createFont("lib/SVN-Times New Roman Bold.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 25, Font.NORMAL);
            fontBold15 = new Font(BaseFont.createFont("lib/SVN-Times New Roman Bold.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 15, Font.NORMAL);
            fontBoldItalic15 = new Font(BaseFont.createFont("lib/SVN-Times New Roman Bold Italic.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 15, Font.NORMAL);
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            Logger.getLogger(WriteFile_pdf.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void chooseURL(String url) {
        try {
            document.close();
            document = new Document();
            file = new FileOutputStream(url);
            PdfWriter writer = PdfWriter.getInstance(document, file);
            document.open();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Khong tim thay duong dan file " + url);
        } catch (DocumentException ex) {
            JOptionPane.showMessageDialog(null, "Khong goi duoc document !");
        }
    }

    public void setTitle(String title) {
        try {
            Paragraph pdfTitle = new Paragraph(new Phrase(title, fontBold25));
            pdfTitle.setAlignment(Element.ALIGN_CENTER);
            document.add(pdfTitle);
            document.add(Chunk.NEWLINE);
        } catch (DocumentException ex) {
            ex.printStackTrace();
        }
    }
    public String getFile(String name) {
        SwingWorker<String, Void> worker = new SwingWorker<String, Void>() {
            @Override
            protected String doInBackground() throws Exception {
                fd.pack();
                fd.setSize(800, 600);
                fd.validate();
                Rectangle rect = jf.getContentPane().getBounds();
                double width = fd.getBounds().getWidth();
                double height = fd.getBounds().getHeight();
                double x = rect.getCenterX() - (width / 2);
                double y = rect.getCenterY() - (height / 2);
                Point leftCorner = new Point();
                leftCorner.setLocation(x, y);
                fd.setLocation(leftCorner);
                fd.setVisible(true);
                String url = fd.getDirectory() + fd.getFile();
                if (url.equals("null")) {
                    return null;
                }
                return url;
            }

            @Override
            protected void done() {
                try {
                    String filePath = get();
                    if (filePath != null) {
                        System.out.println("Đường dẫn tệp tin đã chọn: " + filePath);
                    } else {
                        System.out.println("Không có tệp tin nào được chọn.");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };

        worker.execute();

        try {
            return worker.get();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public String getFile1(String name) {
        SwingWorker<String, Void> worker = new SwingWorker<String, Void>() {
            @Override
            protected String doInBackground() throws Exception {
                fd1.pack();
                fd1.setSize(800, 600);
                fd1.validate();
                Rectangle rect = jf1.getContentPane().getBounds();
                double width = fd1.getBounds().getWidth();
                double height = fd1.getBounds().getHeight();
                double x = rect.getCenterX() - (width / 2);
                double y = rect.getCenterY() - (height / 2);
                Point leftCorner = new Point();
                leftCorner.setLocation(x, y);
                fd1.setLocation(leftCorner);
                fd1.setVisible(true);
                String url = fd1.getDirectory() + fd1.getFile();
                if (url.equals("null")) {
                    return null;
                }
                return url;
            }

            @Override
            protected void done() {
                try {
                    String filePath = get();
                    if (filePath != null) {
                        System.out.println("Đường dẫn tệp tin đã chọn: " + filePath);
                    } else {
                        System.out.println("Không có tệp tin nào được chọn.");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };

        worker.execute();

        try {
            return worker.get();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static String generateRandomPdfFileName() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int length = 5; 

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }

        return sb.toString() + ".pdf";
    }

    private void openFile(String file) {
        try {
            File path = new File(file);
            Desktop.getDesktop().open(path);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static Chunk createWhiteSpace(int length) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(" ");
        }
        return new Chunk(builder.toString());
    }

    public void writePN(int maphieu) {
        String url = "";
        try {
            fd.setTitle("In phiếu nhập");
            fd.setLocationRelativeTo(null);
            url = generateRandomPdfFileName();
            if (url.equals("nullnull")) {
                return;
            }
            file = new FileOutputStream(url);
            document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, file);
            document.open();

            Paragraph company = new Paragraph("Hệ thống quản lý điện thoại ", fontBold15);
            company.add(new Chunk(createWhiteSpace(20)));
            Date today = new Date(System.currentTimeMillis());
            company.add(new Chunk("Thời gian in phiếu: " + formatDate.format(today), fontNormal10));
            company.setAlignment(Element.ALIGN_LEFT);
            document.add(company);
            document.add(Chunk.NEWLINE);

            Paragraph header = new Paragraph("THÔNG TIN PHIẾU NHẬP", fontBold25);
            header.setAlignment(Element.ALIGN_CENTER);
            document.add(header);
            document.add(Chunk.NEWLINE);

            // Thêm MST vào PDF
            String maSoThue = generateRandomMST();
            Paragraph taxCode = new Paragraph("Mã số thuế: " + maSoThue, fontNormal10);
            taxCode.setAlignment(Element.ALIGN_LEFT);
            document.add(taxCode);
            document.add(Chunk.NEWLINE);

            PhieuNhap pn = PhieuNhap_DAO.getInstance().selectById(maphieu + "");
            Paragraph paragraph1 = new Paragraph("Mã phiếu: PN-" + pn.getMaphieu(), fontNormal10);
            String ncc = Nhacungcap_DAO.getInstance().selectById(pn.getManhacungcap() + "").getTenncc();
            Paragraph paragraph2 = new Paragraph("Nhà cung cấp: " + ncc, fontNormal10);
            paragraph2.add(new Chunk(createWhiteSpace(5)));
            paragraph2.add(new Chunk("-"));
            paragraph2.add(new Chunk(createWhiteSpace(5)));
            String diachincc = Nhacungcap_DAO.getInstance().selectById(pn.getManhacungcap() + "").getDiachi();
            paragraph2.add(new Chunk(diachincc, fontNormal10));
            String ngtao = Nhanvien_DAO.getInstance().selectById(pn.getManguoitao() + "");
            Paragraph paragraph3 = new Paragraph("Người thực hiện: " + ngtao, fontNormal10);
            paragraph3.add(new Chunk(createWhiteSpace(5)));
            paragraph3.add(new Chunk("-"));
            paragraph3.add(new Chunk(createWhiteSpace(5)));
            paragraph3.add(new Chunk("Mã nhân viên: " + pn.getManguoitao(), fontNormal10));
            Paragraph paragraph4 = new Paragraph("Thời gian nhập: " + formatDate.format(pn.getThoigiantao()), fontNormal10);
            document.add(paragraph1);
            document.add(paragraph2);
            document.add(paragraph3);
            document.add(paragraph4);
            document.add(Chunk.NEWLINE);

            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);
            table.setWidths(new float[]{30f, 35f, 20f, 15f, 20f});
            PdfPCell cell;

            table.addCell(new PdfPCell(new Phrase("Tên sản phẩm", fontBold15)));
            table.addCell(new PdfPCell(new Phrase("Phiên bản", fontBold15)));
            table.addCell(new PdfPCell(new Phrase("Giá", fontBold15)));
            table.addCell(new PdfPCell(new Phrase("Số lượng", fontBold15)));
            table.addCell(new PdfPCell(new Phrase("Tổng tiền", fontBold15)));
            for (int i = 0; i < 5; i++) {
                cell = new PdfPCell(new Phrase(""));
                table.addCell(cell);
            }

            for (ChitietPhieuNhap ctp : ct_phieunhap_DAO.getInstance().select(maphieu + "")) {
                Sanpham sp = new Sanpham_DAO().selectByPhienBan(ctp.getMaphienbansp() + "");
                table.addCell(new PdfPCell(new Phrase(sp.getTensp(), fontNormal10)));
                Phieubansanpham pbsp = new Phieubansp_DAO().selectById(ctp.getMaphienbansp());
                table.addCell(new PdfPCell(new Phrase(romBus.getKichThuocById(pbsp.getRom()) + "GB - "
                        + ramBus.getKichThuocById(pbsp.getRam()) + "GB - " + mausacBus.getTenMau(pbsp.getMausac()), fontNormal10)));
                table.addCell(new PdfPCell(new Phrase(formatter.format(ctp.getDongia()) + "đ", fontNormal10)));
                table.addCell(new PdfPCell(new Phrase(String.valueOf(ctp.getSoluong()), fontNormal10)));
                table.addCell(new PdfPCell(new Phrase(formatter.format(ctp.getSoluong() * ctp.getDongia()) + "đ", fontNormal10)));
            }

            document.add(table);
            document.add(Chunk.NEWLINE);

            // Tính toán thuế và tổng tiền sau thuế
            double tongThanhTien = pn.getTongTien();
            double tienThue = tongThanhTien * phanTramThue;
            double tongTienSauThue = tongThanhTien + tienThue;

            Paragraph paraTongThanhToan = new Paragraph(new Phrase("Tổng thành tiền: " + formatter.format(tongThanhTien) + "đ", fontBold15));
            Paragraph paraThue = new Paragraph(new Phrase("Thuế (10%): " + formatter.format(tienThue) + "đ", fontBold15));
            Paragraph paraTongTienSauThue = new Paragraph(new Phrase("Tổng tiền sau thuế: " + formatter.format(tongTienSauThue) + "đ", fontBold15));
            paraTongThanhToan.setIndentationLeft(300);
            paraThue.setIndentationLeft(300);
            paraTongTienSauThue.setIndentationLeft(300);

            document.add(paraTongThanhToan);
            document.add(paraThue);
            document.add(paraTongTienSauThue);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);

            Paragraph paragraph = new Paragraph();
            paragraph.setIndentationLeft(22);
            paragraph.add(new Chunk("Người lập phiếu", fontBoldItalic15));
            paragraph.add(new Chunk(createWhiteSpace(30)));
            paragraph.add(new Chunk("Nhân viên nhận", fontBoldItalic15));
            paragraph.add(new Chunk(createWhiteSpace(30)));
            paragraph.add(new Chunk("Nhà cung cấp", fontBoldItalic15));

            Paragraph sign = new Paragraph();
            sign.setIndentationLeft(23);
            sign.add(new Chunk("(Ký và ghi rõ họ tên)", fontNormal10));
            sign.add(new Chunk(createWhiteSpace(30)));
            sign.add(new Chunk("(Ký và ghi rõ họ tên)", fontNormal10));
            sign.add(new Chunk(createWhiteSpace(28)));
            sign.add(new Chunk("(Ký và ghi rõ họ tên)", fontNormal10));
            document.add(paragraph);
            document.add(sign);

            document.close();
            writer.close();
            openFile(url);

        } catch (DocumentException | FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi khi ghi file " + url);
        }
    }


    public void writePX(int maphieu) {
        String url = "";
        try {
            fd1.setTitle("In phiếu xuất");
            fd1.setLocationRelativeTo(null);
            url = generateRandomPdfFileName();
            if (url.equals("nullnull")) {
                return;
            }
            file = new FileOutputStream(url);
            document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, file);
            document.open();

            Paragraph company = new Paragraph("Hệ thống quản lý điện thoại ", fontBold15);
            company.add(new Chunk(createWhiteSpace(20)));
            Date today = new Date(System.currentTimeMillis());
            company.add(new Chunk("Thời gian in phiếu: " + formatDate.format(today), fontNormal10));
            company.setAlignment(Element.ALIGN_LEFT);
            document.add(company);
            document.add(Chunk.NEWLINE);

            Paragraph header = new Paragraph("THÔNG TIN PHIẾU XUẤT", fontBold25);
            header.setAlignment(Element.ALIGN_CENTER);
            document.add(header);
            document.add(Chunk.NEWLINE);

            // Thêm MST vào PDF
            String maSoThue = generateRandomMST();
            Paragraph taxCode = new Paragraph("Mã số thuế: " + maSoThue, fontNormal10);
            taxCode.setAlignment(Element.ALIGN_LEFT);
            document.add(taxCode);
            document.add(Chunk.NEWLINE);

            PhieuXuat px = PhieuXuat_DAO.getInstance().selectById(maphieu + "");
            Paragraph paragraph1 = new Paragraph("Mã phiếu: PX-" + px.getMaphieu(), fontNormal10);
            String ngnhan = Khachhang_DAO.getInstance().selectById(px.getMakh() + "").getHoten();
            Paragraph paragraph2 = new Paragraph("Người nhận: " + ngnhan, fontNormal10);
            paragraph2.add(new Chunk(createWhiteSpace(5)));
            paragraph2.add(new Chunk("-"));
            paragraph2.add(new Chunk(createWhiteSpace(5)));
            String diachi = Khachhang_DAO.getInstance().selectById(px.getMakh() + "").getDiachi();
            paragraph2.add(new Chunk(diachi, fontNormal10));
            String ngtao = Nhanvien_DAO.getInstance().selectById(px.getManguoitao() + "");
            Paragraph paragraph3 = new Paragraph("Người thực hiện: " + ngtao, fontNormal10);
            paragraph3.add(new Chunk(createWhiteSpace(5)));
            paragraph3.add(new Chunk("-"));
            paragraph3.add(new Chunk(createWhiteSpace(5)));
            paragraph3.add(new Chunk("Mã nhân viên: " + px.getManguoitao(), fontNormal10));
            Paragraph paragraph4 = new Paragraph("Thời gian xuất: " + formatDate.format(px.getThoigiantao()), fontNormal10);
            document.add(paragraph1);
            document.add(paragraph2);
            document.add(paragraph3);
            document.add(paragraph4);
            document.add(Chunk.NEWLINE);

            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);
            table.setWidths(new float[]{30f, 35f, 20f, 15f, 20f});
            PdfPCell cell;

            table.addCell(new PdfPCell(new Phrase("Tên sản phẩm", fontBold15)));
            table.addCell(new PdfPCell(new Phrase("Phiên bản", fontBold15)));
            table.addCell(new PdfPCell(new Phrase("Giá", fontBold15)));
            table.addCell(new PdfPCell(new Phrase("Số lượng", fontBold15)));
            table.addCell(new PdfPCell(new Phrase("Tổng tiền", fontBold15)));
            for (int i = 0; i < 5; i++) {
                cell = new PdfPCell(new Phrase(""));
                table.addCell(cell);
            }

            for (ChitietPhieuXuat ctx : ctphieuxuat_DAO.getInstance().select(maphieu + "")) {
                Sanpham sp = new Sanpham_DAO().selectByPhienBan(ctx.getMaphienbansp() + "");
                table.addCell(new PdfPCell(new Phrase(sp.getTensp(), fontNormal10)));
                Phieubansanpham pbsp = new Phieubansp_DAO().selectById(ctx.getMaphienbansp());
                table.addCell(new PdfPCell(new Phrase(romBus.getKichThuocById(pbsp.getRom()) + "GB - "
                        + ramBus.getKichThuocById(pbsp.getRam()) + "GB - " + mausacBus.getTenMau(pbsp.getMausac()), fontNormal10)));
                table.addCell(new PdfPCell(new Phrase(formatter.format(ctx.getDongia()) + "đ", fontNormal10)));
                table.addCell(new PdfPCell(new Phrase(String.valueOf(ctx.getSoluong()), fontNormal10)));
                table.addCell(new PdfPCell(new Phrase(formatter.format(ctx.getSoluong() * ctx.getDongia()) + "đ", fontNormal10)));
            }

            document.add(table);
            document.add(Chunk.NEWLINE);

            double tongThanhTien = px.getTongTien();
            double tienThue = tongThanhTien * phanTramThue;
            double tongTienSauThue = tongThanhTien + tienThue;

            Paragraph paraTongThanhToan = new Paragraph(new Phrase("Tổng thành tiền: " + formatter.format(tongThanhTien) + "đ", fontBold15));
            Paragraph paraThue = new Paragraph(new Phrase("Thuế (10%): " + formatter.format(tienThue) + "đ", fontBold15));
            Paragraph paraTongTienSauThue = new Paragraph(new Phrase("Tổng tiền sau thuế: " + formatter.format(tongTienSauThue) + "đ", fontBold15));
            paraTongThanhToan.setIndentationLeft(300);
            paraThue.setIndentationLeft(300);
            paraTongTienSauThue.setIndentationLeft(300);

            document.add(paraTongThanhToan);
            document.add(paraThue);
            document.add(paraTongTienSauThue);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);

            Paragraph paragraph = new Paragraph();
            paragraph.setIndentationLeft(22);
            paragraph.add(new Chunk("Người lập phiếu", fontBoldItalic15));
            paragraph.add(new Chunk(createWhiteSpace(30)));
            paragraph.add(new Chunk("Nhân viên giao hàng", fontBoldItalic15));
            paragraph.add(new Chunk(createWhiteSpace(30)));
            paragraph.add(new Chunk("Khách hàng", fontBoldItalic15));

            Paragraph sign = new Paragraph();
            sign.setIndentationLeft(23);
            sign.add(new Chunk("(Ký và ghi rõ họ tên)", fontNormal10));
            sign.add(new Chunk(createWhiteSpace(30)));
            sign.add(new Chunk("(Ký và ghi rõ họ tên)", fontNormal10));
            sign.add(new Chunk(createWhiteSpace(28)));
            sign.add(new Chunk("(Ký và ghi rõ họ tên)", fontNormal10));
            document.add(paragraph);
            document.add(sign);

            document.close();
            writer.close();
            openFile(url);

        } catch (DocumentException | FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi khi ghi file " + url);
        }
    }


}
