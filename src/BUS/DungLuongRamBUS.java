package BUS;

import DAO.ram_DAO;
import java.util.ArrayList;

import AtttributeSanPham.Ram;

public class DungLuongRamBUS {
    private final ram_DAO dlramDAO = new ram_DAO();
    private ArrayList<Ram> listDLRam = new ArrayList<>();

    public DungLuongRamBUS getInstance() {
        return new DungLuongRamBUS();
    }
    
    public DungLuongRamBUS() {
        listDLRam =  dlramDAO.select();
    }

    
    public int getIndexById(int madlrom) {
        int i = 0;
        int vitri = -1;
        while (i < this.listDLRam.size() && vitri == -1) {
            if (listDLRam.get(i).getMadl() == madlrom) {
                vitri = i;
            } else {
                i++;
            }
        }
        return vitri;
    }
    public int getKichThuocById(int madlrom) {
        return this.listDLRam.get(this.getIndexById(madlrom)).getDungluongRam();
    }
    public ArrayList<Ram> getAll() {
        return this.listDLRam;
    }

    public Ram getByIndex(int index) {
        return this.listDLRam.get(index);
    }

    public int getIndexByMaRam(int maram) {
        int i = 0;
        int vitri = -1;
        while (i < this.listDLRam.size() && vitri == -1) {
            if (listDLRam.get(i).getMadl()== maram) {
                vitri = i;
            } else {
                i++;
            }
        }
        return vitri;
    }
    
    public String[] getArrKichThuoc() {
        String[] result = new String[listDLRam.size()];
        for(int i = 0; i < listDLRam.size(); i++) {
            result[i] = Integer.toString(listDLRam.get(i).getDungluongRam())+"GB";
        }
        return result;
    }
}