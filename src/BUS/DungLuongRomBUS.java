package BUS;

import DAO.ram_DAO;
import DAO.rom_DAO;

import java.util.ArrayList;

import AtttributeSanPham.Ram;
import AtttributeSanPham.Rom;

public class DungLuongRomBUS {
    private final rom_DAO dlromDAO = new rom_DAO();
    private ArrayList<Rom> listDLRom = new ArrayList<>();

    public DungLuongRomBUS getInstance() {
        return new DungLuongRomBUS();
    }
    
    public DungLuongRomBUS() {
        listDLRom =  dlromDAO.select();
    }

    public int getIndexById(int madlrom) {
        int i = 0;
        int vitri = -1;
        while (i < this.listDLRom.size() && vitri == -1) {
            if (listDLRom.get(i).getMadl() == madlrom) {
                vitri = i;
            } else {
                i++;
            }
        }
        return vitri;
    }
    public int getKichThuocById(int madlrom) {
        return this.listDLRom.get(this.getIndexById(madlrom)).getDungluongRom();
    }
    public ArrayList<Rom> getAll() {
        return this.listDLRom;
    }

    public Rom getByIndex(int index) {
        return this.listDLRom.get(index);
    }

    public int getIndexByMaRom(int maram) {
        int i = 0;
        int vitri = -1;
        while (i < this.listDLRom.size() && vitri == -1) {
            if (listDLRom.get(i).getMadl()== maram) {
                vitri = i;
            } else {
                i++;
            }
        }
        return vitri;
    }
    
    public String[] getArrKichThuoc() {
        String[] result = new String[listDLRom.size()];
        for(int i = 0; i < listDLRom.size(); i++) {
            result[i] = Integer.toString(listDLRom.get(i).getDungluongRom())+"GB";
        }
        return result;
    }
}