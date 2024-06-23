package Controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import DAO.Thongke_sp_DAO;

public class Thongkesp {
public  Thongkesp(){
	Thongke_sp_DAO tk=new Thongke_sp_DAO();
	tk.updateThongKeTongQuan();
	try {
	    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/quan_ly_ban_hang", "root", "binvaloi123");

	    String query = "SELECT SUM(soluong) AS total FROM thongke_sp";
        PreparedStatement pstmt = conn.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();

        double total = 0;
        if (rs.next()) {
            total = rs.getDouble("total"); 
        }
        DefaultPieDataset barDataset = new DefaultPieDataset( );
       
        String componentQuery = "SELECT tensanpham, soluong FROM thongke_sp";
        PreparedStatement componentStmt = conn.prepareStatement(componentQuery);
        ResultSet componentRs = componentStmt.executeQuery();
        double totalPercentage = 0;
       
        int i=0;
        while (componentRs.next()) {
        	if(i<=3) {
            String componentName = componentRs.getString("tensanpham");
            double quantity = componentRs.getDouble("soluong");

            double percentage = (quantity / total) * 100; 
           
            DecimalFormat df = new DecimalFormat("#,###"); 
         String formattedPercentage = df.format(percentage);
         double num1 = Double.parseDouble(formattedPercentage);               
            barDataset.setValue(componentName, num1); 
            i++;
        	}
        	else {
                double quantity = componentRs.getDouble("soluong");

                double percentage = (quantity / total) * 100; 
               
                DecimalFormat df = new DecimalFormat("#,###"); 
                String formattedPercentage = df.format(percentage);
                double num1 = Double.parseDouble(formattedPercentage);               
                barDataset.setValue("other", num1); 	
        	}
           
        }
        
	    JFreeChart piechart = ChartFactory.createPieChart("Thống kê các sản phẩm được ưa chuộng nhất", barDataset, false, true, false);

	    // Tạo Panel để hiển thị biểu đồ
	    ChartPanel panelBarChart = new ChartPanel(piechart);
	  
	    panelBarChart.setPreferredSize(new java.awt.Dimension(800, 600));

	    // Tạo Frame để hiển thị biểu đồ
	    JFrame frame = new JFrame("Biểu đồ thống kê các sản phẩm được ưa chuộng nhất");
	    ImageIcon image1 = new ImageIcon("D://bank.jpg");
	    frame.setIconImage(image1.getImage());
	    frame.setLocation(450, 20);
	    frame.setSize(360, 1800);
	    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    frame.add(panelBarChart);
	    frame.pack();
	    frame.setVisible(true);

	    conn.close();
	} catch (Exception e) {
	    e.printStackTrace();
	}

     
    }

   
    public static void main(String[] args) {
        
    	Thongkesp  show=new Thongkesp();
    }
}