package com.ucar.team.seven.tea.address.service.impl;

import com.ucar.team.seven.tea.address.dao.AdressDao;
import com.ucar.team.seven.tea.address.entity.Adress;
import com.ucar.team.seven.tea.address.enums.AdressEnum;
import com.ucar.team.seven.tea.address.service.AdressService;
import com.ucar.team.seven.tea.common.dao.BaseDao;
import com.ucar.team.seven.tea.common.service.impl.BaseService;
import com.ucar.team.seven.tea.common.util.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static cn.hutool.json.XMLTokener.entity;

/**
 * @描述
 * @参数 $
 * @返回值 $
 * @创建人 yufeng.lin@ucarinc.com
 * @创建时间 $
 * @修改人和其它信息
 */
@Service("adressService")
public class AdressServiceImpl extends BaseService<Adress> implements AdressService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdressServiceImpl.class);

    @Autowired
    AdressDao adressDao;

    @Override
    public BaseDao getDao() {
        return adressDao;
    }

    @Override
    public List<Adress> getAdressesByUserId(Long id) {
        if (id == null || id == 0) {
            LOGGER.info("用户id为空,{}", id);
            return null;
        }
        List<Adress> list = adressDao.getAdressesByUserId(id);
        return list;
    }

    @Override
    public Adress getUserDefaultReceveAdrr(Long id) {
        if (id == null || id == 0) {
            LOGGER.info("用户id为空,{}", id);
            return null;
        }
        return adressDao.getUserDefaultReceveAdrr(id);
    }

    @Override
    public Integer insert(Adress entity) {
        //1.校验
        if (!vaildateInsertParm(entity)) {
            LOGGER.info("传入参数不完整，{}", entity);
            return AdressEnum.PARM_NOT_COMPLETE.getIndex();
        }
        //2.先决业务条件
        int count = adressDao.countAdressTotalByUserId(entity.getCustomerId());
        if (count > AdressEnum.USER_MAX_ADRESS_NUM.getIndex()) {
            LOGGER.info("达到收货地址最大值，{}", count);
            return AdressEnum.INSER_TO_MAV_VAL_FAIL.getIndex();
        }
        //3.执行插入
        int res = adressDao.insert(entity);
        if (res > 0) {
            LOGGER.info("插入成功，{}", entity);
            return AdressEnum.INSERT_SUCCESS_VAL.getIndex();
        }
        return AdressEnum.INSER_FAIL_VAL.getIndex();
    }

    /**
     * @return java.lang.Boolean
     * @Author yufeng.lim@ucarinc.com
     * @Description 参数校验 通过返回true 失败返回false
     * @Date 11:01 2018/10/16
     * @Param [entity]
     **/
    private Boolean vaildateInsertParm(Adress entity) {
        if (entity.getCustomerId() == null || entity.getCustomerId() == 0) {
            return false;
        }

        if (StringUtils.isEmpty(entity.getCity())) {
            return false;
        }
        if (StringUtil.isEmpty(entity.getDetailAddress())) {
            return false;
        }
        if (StringUtil.isEmpty(entity.getMobile())) {
            return false;
        }
        if (StringUtil.isEmpty(entity.getProvince())) {
            return false;
        }
        return true;
    }

    @Override
    public Integer update(Adress entity) {
        if (entity.getId() == null || entity.getId() == 0 || !vaildateInsertParm(entity)) {
            LOGGER.info("更新传入参数不完整，{}", entity);
            return AdressEnum.PARM_NOT_COMPLETE.getIndex();
        }
        int res = adressDao.update(entity);
        if (res > 0) {
            LOGGER.info("更新成功，{}", entity);
            return AdressEnum.OPRATE_FAIL_DEFAULT.getIndex();
        }
        return AdressEnum.OPRATE_SUCCESS_DEFAULT.getIndex();
    }

    @Override
    public Integer deleteBatch(String delIds) {
        if (StringUtil.isEmpty(delIds)) {
            LOGGER.info("批量删除传入参数不完整，{}", delIds);
            return AdressEnum.PARM_NOT_COMPLETE.getIndex();
        }
        //去掉单双引号
        delIds = delIds.replace("\"", "");
        delIds = delIds.replace("\'", "");
        String idArrs[] = delIds.split(",");
        int res = adressDao.deleteBatch(idArrs);
        if (res > 0) {
            LOGGER.info("插入成功，{}", entity);
            return AdressEnum.OPRATE_FAIL_DEFAULT.getIndex();
        }
        return AdressEnum.OPRATE_SUCCESS_DEFAULT.getIndex();
    }

    @Override
    public Integer countAdressTotalByUserId(Long id) {
        if (id == null || id == 0) {
            LOGGER.info("用户id为空,{}", id);
            return null;
        }
        return adressDao.countAdressTotalByUserId(id);
    }

    @Override
    public Integer setDefaultAddr(Long id, Long customerId) {
        if (id == null || id == 0 || customerId == null || customerId == 0) {
            LOGGER.info("修改默认地址传入参数出错，id:{},customerId:{}", id, customerId);
            return AdressEnum.OPRATE_FAIL_DEFAULT.getIndex();
        }
        //取消原来默认地址如果存在
        int c = adressDao.cancleOldDefaultAddr(customerId,AdressEnum.NOT_DEFAULT_ADDR_SIGN.getIndex());
        LOGGER.info("原来的默认地址数目：{}",c);
        //设置新的地址
        int res = adressDao.setDefaultAddrById(id,AdressEnum.DEFAULT_ADDR_SIGN.getIndex());
        if (res > 0) {
            LOGGER.info("设置默认地址成功，{}", entity);
            return AdressEnum.OPRATE_FAIL_DEFAULT.getIndex();
        }
        return AdressEnum.OPRATE_SUCCESS_DEFAULT.getIndex();
    }

    @Override
    public Integer deleteAddrById(Long id) {
        if (id == null || id == 0) {
            LOGGER.info("删除传入参数出错，id:{}", id);
            return AdressEnum.OPRATE_FAIL_DEFAULT.getIndex();
        }
        int res = adressDao.deleteAddrById(id);
        if (res > 0) {
            LOGGER.info("删除成功");
            return AdressEnum.OPRATE_SUCCESS_DEFAULT.getIndex();
        }
        return AdressEnum.OPRATE_FAIL_DEFAULT.getIndex();
    }


}
