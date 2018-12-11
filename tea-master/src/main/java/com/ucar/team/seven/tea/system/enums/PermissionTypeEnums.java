package com.ucar.team.seven.tea.system.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum PermissionTypeEnums {

    DIR_TYPE("目录",1),
    MENU_TYPE("菜单",2),
    BTN_TYPE("按钮",3);

    // 成员变量
    private String name;
    private int index;

    // 构造方法
    private PermissionTypeEnums(String name, int index) {
        this.name = name;
        this.index = index;
    }
    // 根据数值获取对应名称
    public static String getName(int index) {
        for (PermissionTypeEnums c : PermissionTypeEnums.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }

        return null;
    }

    // 根据名字获取对应数值
    public static int getIndex(String name) {
        for (PermissionTypeEnums c : PermissionTypeEnums.values()) {
            if (name.equals(c.getName())) {
                return c.index;
            }
        }

        return -1;
    }

    public static List<Map<Object, Object>> getKeyValueList(){
        List<Map<Object,Object>> list = new ArrayList<>();
        for (PermissionTypeEnums c : PermissionTypeEnums.values()) {
            Map map = new HashMap();
            map.put("name",c.getName());
            map.put("index",c.getIndex());
            list.add(map);
        }
        return list;
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
