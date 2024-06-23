package Controller;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.ui.RectangleEdge;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RectangleInsets;

import DAO.Thongke_doanhthu_DAO;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class Thongke_doanhthu {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new JFrame("Biểu đồ Số Tiền Vốn và Doanh Thu Theo Từng Tháng");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            DefaultCategoryDataset dataset = createDataset();

            JFreeChart chart = createGroupedBarChart(dataset);
            
            // Thêm chú thích màu sắc
            LegendTitle legend = chart.getLegend();
            legend.setItemFont(new Font("Arial", Font.PLAIN, 12));
            legend.setPosition(RectangleEdge.BOTTOM);

            
            
            ChartPanel chartPanel = new ChartPanel(chart);
            frame.setLocation(400, 30);
            frame.getContentPane().add(chartPanel);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    private static JFreeChart createGroupedBarChart(DefaultCategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createBarChart(
                "Số Tiền Vốn và Doanh Thu", // Tiêu đề biểu đồ
                "Tháng", // Nhãn trục x
                "Số tiền (VNĐ)", // Nhãn trục y
                dataset, // Tập dữ liệu
                PlotOrientation.VERTICAL, // Hướng biểu đồ
                true, // Hiển thị chú thích
                true, // Hiển thị tooltips
                false // Không hiển thị URLs
        );

        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        CategoryAxis xAxis = plot.getDomainAxis();
        xAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);

        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);
        
        renderer.setSeriesPaint(0, new Color(79, 129, 189)); // Màu của số vốn
        renderer.setSeriesPaint(1, new Color(155, 187, 89)); // Màu của số doanh thu

        return chart;
    }

    private static DefaultCategoryDataset createDataset() {
    	Thongke_doanhthu_DAO t=new Thongke_doanhthu_DAO();
    	t.updateData();
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/quan_ly_ban_hang", "root", "binvaloi123");
            String query = "SELECT month, sotienvon, sotienthuvao FROM thongke_doanhthutheothang";
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int month = resultSet.getInt("month");
                double capital = resultSet.getDouble("sotienvon");
                double revenue = resultSet.getDouble("sotienthuvao");
                dataset.addValue(capital, "Số tiền vốn", "Tháng " + month);
                dataset.addValue(revenue, "Số tiền doanh thu", "Tháng " + month);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return dataset;
    }
}
