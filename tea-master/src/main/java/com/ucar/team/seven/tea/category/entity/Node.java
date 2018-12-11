package com.ucar.team.seven.tea.category.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @Author Kyrie(lingyi.huang @ ucarinc.com)
 * @Date 2018/10/12 14:07
 * @Version 1.0
 * @Description 描述
 */
@Data
public class Node {
    private Long id;

    private Long pid;

    private String text;

    private List<Node> nodes=null;

    /*public Node(int id,int pid, String text, List<Node> node)
    {
        this.id=id;
        this.pid=pid;
        this.text = text;
        this.nodes = node;
    }*/

    @Override
    public String toString() {
        if(nodes.isEmpty() ) {
            return "Node{" +
                    "id=" + id +
                    ", pid=" + pid +
                    ", text='" + text + '\'' +
                    '}';
        }else{
            return "Node{" +
                    "id=" + id +
                    ", pid=" + pid +
                    ", text='" + text + '\'' +
                    ", nodes=" + nodes +
                    '}';
        }
    }



}
