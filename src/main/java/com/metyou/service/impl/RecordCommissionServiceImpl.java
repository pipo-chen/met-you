package com.metyou.service.impl;

import com.metyou.common.ServerResponse;
import com.metyou.dao.CommissionRecordMapper;
import com.metyou.dao.StaffMapper;
import com.metyou.pojo.Staff;
import com.metyou.pojo.User;
import com.metyou.service.IRecordCommissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service("iRecordCommissionService")
public class RecordCommissionServiceImpl implements IRecordCommissionService {
    @Autowired
    private CommissionRecordMapper commissionRecordMapper;

    @Autowired
    private StaffMapper staffMapper;

    /**
     * 已结算
     * @param staffName
     * @return
     */
    @Override
    public ServerResponse<String> totalPayed(String staffName) {
        BigDecimal res;
        Integer staffId = staffMapper.selectByUsername(staffName);
        res = commissionRecordMapper.payedCalculate(staffId, "sub");
        if(res == null) {
            return ServerResponse.createBySuccess("0");
        }
        return ServerResponse.createBySuccess(res.toString());

    }

    /**
     * 待结算
     * @param staffName
     * @return
     */
    @Override
    public ServerResponse<String> totalUnPayed(String staffName) {
        Integer staffId = staffMapper.selectByUsername(staffName);
        BigDecimal res;
        res = commissionRecordMapper.payedCalculate(staffId, "add");
        if(res == null) {
            return ServerResponse.createBySuccess("0");
        }
        BigDecimal sub = commissionRecordMapper.payedCalculate(staffId, "sub");
        res = res.subtract(sub);
        return ServerResponse.createBySuccess(res.toString());

    }
}
