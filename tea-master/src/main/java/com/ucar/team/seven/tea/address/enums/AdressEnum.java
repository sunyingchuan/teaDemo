package com.ucar.team.seven.tea.address.enums;


/**
 * @描述
 * @参数 $
 * @返回值 $
 * @创建人 yufeng.lin@ucarinc.com
 * @创建时间 $
 * @修改人和其它信息
 */
public enum  AdressEnum {

    OPRATE_SUCCESS_DEFAULT("操作成功返回默认值Int值",1),
    OPRATE_FAIL_DEFAULT("操作失败返回默认值Int值",0),
    USER_MAX_ADRESS_NUM("用户最大收货地址数",5),
    INSERT_SUCCESS_VAL("地址插入成功返回值",1),
    INSER_FAIL_VAL("地址插入失败返回值",0),
    INSER_TO_MAV_VAL_FAIL("地址插入达到最大数目失败返回值",2),
    PARM_NOT_COMPLETE("传入参数不完整",-1),
    NOT_DEFAULT_ADDR_SIGN("非默认地址的标识",0),
    DEFAULT_ADDR_SIGN("默认地址的标识",1);


    // 成员变量
    private String name;
    private int index;

    // 构造方法
    private AdressEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }
    // 根据数值获取对应名称
    public static String getName(int index) {
        for (AdressEnum c : AdressEnum.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }

        return null;
    }

    // 根据名字获取对应数值
    public static int getIndex(String name) {
        for (AdressEnum c : AdressEnum.values()) {
            if (name.equals(c.getName())) {
                return c.index;
            }
        }

        return -1;
    }


    // get set 方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

}
