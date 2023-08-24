package lk.ijse.HelthCare.bo.custom.impl;

import lk.ijse.HelthCare.bo.custom.BillBo;
import lk.ijse.HelthCare.dao.DaoFactory;
import lk.ijse.HelthCare.dao.custom.BillDao;
import lk.ijse.HelthCare.dto.BillDto;
import lk.ijse.HelthCare.entity.Bill;

import java.sql.SQLException;
import java.util.ArrayList;

public class BillBoImpl implements BillBo {

    private final BillDao dao = DaoFactory.getInstance().getDao(DaoFactory.DaoType.BILL);
    @Override
    public boolean saveBill(BillDto dto) throws SQLException, ClassNotFoundException {

        return dao.save(new Bill(
                dto.getBId(),
                dto.getDId(),
                dto.getPId(),
                dto.getAmount(),
                dto.getDate()
        ));
    }

    @Override
    public ArrayList<BillDto> search(String text) throws SQLException, ClassNotFoundException {
        ArrayList<BillDto> list = new ArrayList<>();
        for (Bill bill:dao.search(text)){
            list.add(
                    new BillDto(
                            bill.getBId(),
                            bill.getDId(),
                            bill.getPId(),
                            bill.getAmount(),
                            bill.getDate()
                    )
            );
        }
        return list;
    }

    @Override
    public ArrayList<BillDto> getAllBills() throws SQLException, ClassNotFoundException {
        ArrayList<BillDto> list = new ArrayList<>();
        for (Bill bill : dao.getAll()){
            list.add(new BillDto(
                    bill.getBId(),
                    bill.getDId(),
                    bill.getPId(),
                    bill.getAmount(),
                    bill.getDate()

            ));
        }
        return list;
    }

    @Override
    public boolean deleteBill(String billId) throws SQLException, ClassNotFoundException {
        return dao.delete(billId);
    }
}
