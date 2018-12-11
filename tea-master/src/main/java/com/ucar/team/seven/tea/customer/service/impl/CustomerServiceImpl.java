package com.ucar.team.seven.tea.customer.service.impl;

import com.ucar.team.seven.tea.common.constant.CommonConstant;
import com.ucar.team.seven.tea.common.dao.BaseDao;
import com.ucar.team.seven.tea.common.enums.BalanceRecordType;
import com.ucar.team.seven.tea.common.enums.UserStatus;
import com.ucar.team.seven.tea.common.exception.ServiceException;
import com.ucar.team.seven.tea.common.service.impl.BaseService;
import com.ucar.team.seven.tea.common.util.DateUtil;
import com.ucar.team.seven.tea.common.util.Md5Util;
import com.ucar.team.seven.tea.common.web.Result;
import com.ucar.team.seven.tea.customer.dao.BalanceRecordDao;
import com.ucar.team.seven.tea.customer.dao.CustomerDao;
import com.ucar.team.seven.tea.customer.entity.BalanceRecord;
import com.ucar.team.seven.tea.customer.entity.Customer;
import com.ucar.team.seven.tea.customer.service.CustomerService;
import com.ucar.team.seven.tea.customer.vo.CustomerVo;
import com.ucar.team.seven.tea.customer.vo.params.CustomerParamVo;
import com.ucar.team.seven.tea.order.entity.Order;
import com.ucar.team.seven.tea.system.controller.SysRoleController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @Description 客户service类
 * @Author Grant(gang.zeng @ ucarinc.com)
 * @Version v1.0
 * @Date 2018/10/9 8:44
 */
@Service("customerService")
public class CustomerServiceImpl extends BaseService<Customer> implements CustomerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SysRoleController.class);

    @Resource
    CustomerDao customerDao;

    @Resource
    BalanceRecordDao balanceRecordDao;

    @Override
    public BaseDao<Customer> getDao() {
        return customerDao;
    }

    @Override
    public Result insertCustomer(Customer customer) {

        if (customer.getPassword().length() < 6 || customer.getPassword().length() > 20) {
            return Result.failResult("创建失败！密码长度应为6-20位！");
        }
        if (getByUserName(customer) != null) {
            return Result.failResult("创建失败！该用户名已存在");
        }

        if (getEmp() != null) {
            customer.setCreateEmp(getEmp());
            customer.setModifyEmp(getEmp());
        } else {
            customer.setCreateEmp(CommonConstant.AUTO_SYS_USER_ID);
            customer.setModifyEmp(CommonConstant.AUTO_SYS_USER_ID);
        }
        String pwd = Md5Util.getMD5(customer.getPassword());
        customer.setPassword(pwd);
        customerDao.insert(customer);
        return Result.successResult();
    }

    @Override
    public void updateStatus(Long id, Integer status) {
        Integer newStatus;
        if (status == UserStatus.INVALID.getValue()) {
            newStatus = UserStatus.VALID.getValue();
        } else {
            newStatus = UserStatus.INVALID.getValue();
        }
        Customer customer = getById(id);
        customer.setStatus(newStatus);
        if (getEmp() != null) {
            customer.setModifyEmp(getEmp());
        }
        customerDao.update(customer);
    }

    @Override
    public List<CustomerVo> findPage(CustomerParamVo customerParamVo) {

        Long total = getCount(customerParamVo);
        customerParamVo.setRowCount(total);
        customerParamVo.setStartIndex((customerParamVo.getPageCurrent() - 1) * customerParamVo.getPageSize());

        return customerDao.findPage(customerParamVo);
    }

    @Override
    public Long getCount(CustomerParamVo customerParamVo) {
        return customerDao.getCount(customerParamVo);
    }

    @Override
    public void resetPwd(Long id) {
        Customer customer = customerDao.getById(id);
        String pwd = "123456";
        customer.setPassword(Md5Util.getMD5(pwd));

        if (getEmp() != null) {
            customer.setModifyEmp(getEmp());
        } else {
            customer.setModifyEmp(CommonConstant.AUTO_SYS_USER_ID);
        }

        customerDao.resetPwd(customer);
    }

    @Override
    public Customer getByUserName(Customer customer) {
        return customerDao.getByUserName(customer);
    }

    @Override
    public Customer getByWxId(Customer customer) {
        return customerDao.getByWxId(customer);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result recharge(Long customerId, BigDecimal figure) throws ServiceException {

        //生成新的余额变动记录
        BalanceRecord balanceRecord = new BalanceRecord();
        balanceRecord.setCustomerId(customerId);
        balanceRecord.setAmount(figure);
        balanceRecord.setType(BalanceRecordType.RECHARGE.getValue());
        balanceRecord.setOrderNumber("RC" + DateUtil.getNowFormatTime("yyyyMMddHHmmss"));
        balanceRecord.setCreateEmp(getEmp());
        balanceRecord.setModifyEmp(getEmp());

        Customer customer = customerDao.getById(customerId);
        balanceRecord.setBalanceOld(customer.getBalance());

        BigDecimal newBalance = customer.getBalance().add(figure);
        customer.setBalance(newBalance);
        balanceRecord.setBalanceNew(newBalance);
        if(customerDao.update(customer) <= 0){
            throw new ServiceException("数据出错，充值失败！");
        }
        if(balanceRecordDao.insert(balanceRecord) <= 0){
            throw new ServiceException("数据出错，充值失败！");
        }

        return Result.successResult();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result expend(Order order) throws ServiceException{

        //生成新的余额变动记录
        BalanceRecord balanceRecord = new BalanceRecord();
        balanceRecord.setCustomerId(order.getCustomerId());
        balanceRecord.setAmount(order.getPrice());
        balanceRecord.setType(BalanceRecordType.EXPEND.getValue());
        balanceRecord.setOrderNumber(order.getOrderNumber());
        balanceRecord.setCreateEmp(getEmp());
        balanceRecord.setModifyEmp(getEmp());

        Customer customer = customerDao.getById(order.getCustomerId());

        balanceRecord.setBalanceOld(customer.getBalance());
        BigDecimal newBalance = customer.getBalance().subtract(order.getPrice());
        if (newBalance.compareTo(BigDecimal.valueOf(0)) < 0) {
            return Result.failResult("余额不足，操作失败!");
        }

        balanceRecord.setBalanceNew(newBalance);
        customer.setBalance(newBalance);
        if(customerDao.update(customer) <= 0){
            throw new ServiceException("数据出错，扣款失败！");
        }
        if(balanceRecordDao.insert(balanceRecord) <= 0){
            throw new ServiceException("数据出错，扣款失败！");
        }

        return Result.successResult();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result refund(Order order) throws ServiceException{
        //生成新的余额变动记录
        BalanceRecord balanceRecord = new BalanceRecord();
        balanceRecord.setCustomerId(order.getCustomerId());
        balanceRecord.setAmount(order.getPrice());
        balanceRecord.setType(BalanceRecordType.REFUND.getValue());
        balanceRecord.setOrderNumber(order.getOrderNumber());
        balanceRecord.setCreateEmp(getEmp());
        balanceRecord.setModifyEmp(getEmp());
        //余额增加
        Customer customer = customerDao.getById(order.getCustomerId());
        balanceRecord.setBalanceOld(customer.getBalance());
        BigDecimal newBalance = customer.getBalance().add(order.getPrice());
        customer.setBalance(newBalance);

        balanceRecord.setBalanceNew(newBalance);
        if(customerDao.update(customer) <= 0){
            throw new ServiceException("数据出错，退款失败！");
        }
        if(balanceRecordDao.insert(balanceRecord) <= 0){
            throw new ServiceException("数据出错，退款失败！");
        }
        return Result.successResult();
    }

    @Override
    public Result pointAdd(Long customerId, Integer figure) {
        try {
            Customer customer = customerDao.getById(customerId);
            Integer newPoint = customer.getPoint() + figure;
            if (newPoint < 0) {
                return Result.failResult("积分不足，扣除失败!");
            }
            customer.setPoint(newPoint);
            customer.setModifyEmp(getEmp());
            customerDao.update(customer);
        } catch (Exception e) {
            LOGGER.info("增加积分出错：" + e.getMessage());
            return Result.failResult("业务出错，操作失败!");
        }
        return Result.successResult();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void pointAdd(Long[] customerIdArray, Integer figure) throws ServiceException {
        for (Long customerId : customerIdArray) {
            Customer customer = customerDao.getById(customerId);
            Integer newPoint = customer.getPoint() + figure;
            if (newPoint < 0) {
                throw new ServiceException("积分不足，扣除失败!");
            }
            customer.setPoint(newPoint);
            customer.setModifyEmp(getEmp());
            customerDao.update(customer);
        }
    }

    @Override
    public Result pointSub(Long customerId, Integer figure) {
        try {
            Customer customer = customerDao.getById(customerId);
            Integer newPoint = customer.getPoint() - figure;
            if (newPoint < 0) {
                return Result.failResult("积分不足，扣除失败!");
            }
            customer.setPoint(newPoint);
            customer.setModifyEmp(getEmp());
            customerDao.update(customer);
        } catch (Exception e) {
            LOGGER.info("扣除积分出错：" + e.getMessage());
            return Result.failResult("业务出错，操作失败!");
        }
        return Result.successResult();
    }
}
