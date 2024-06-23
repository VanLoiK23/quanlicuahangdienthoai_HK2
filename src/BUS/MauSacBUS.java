package BUS;

import DAO.color_DAO;
import java.util.ArrayList;

import AtttributeSanPham.Color;

public class MauSacBUS {

    private color_DAO mausacDAO = new color_DAO();
    private ArrayList<Color> listMauSac = new ArrayList<>();

    public MauSacBUS() {
        this.listMauSac =  mausacDAO.select();
    }

    public ArrayList<Color> getAll() {
        return this.listMauSac;
    }

    public String[] getArrTenMauSac() {
        String[] result = new String[listMauSac.size()];
        for (int i = 0; i < listMauSac.size(); i++) {
            result[i] = listMauSac.get(i).getColor();
        }
        return result;
    }

    public Color getByIndex(int index) {
        return this.listMauSac.get(index);
    }

    public int getIndexByMaMau(int mamau) {
        int i = 0;
        int vitri = -1;System.out.println(this.listMauSac.size());
        while (i < this.listMauSac.size() && vitri == -1) {
            if (listMauSac.get(i).getMamau() == mamau) {
                vitri = i;
            } else {
                i++;
            }
        }
        return vitri;
    }

    public String getTenMau(int mamau) {
        int index = this.getIndexByMaMau(mamau);
        System.out.println(index);
        return this.listMauSac.get(index).getColor();
    }
  

        public boolean checkDup(String name) {
        boolean check = true;
        int i = 0;
        while (i <= this.listMauSac.size() && check == true) {
            if (this.listMauSac.get(i).getColor().toLowerCase().contains(name.toLowerCase())) {
                check = false;
            } else {
                i++;
            }
        }
        return check;
    }

}