package com.ruoyi.system.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DiscoveryPageManagement implements Serializable {

    private Integer id;   //菜单id
    private String menunumber;//菜单编号
    private String menuname;//菜单名称
    private String menutype;//菜单类型
    private String typepicture;//菜单图片
    private String typelink;//菜单连接
    private String typewordage;//菜单文字
    private String typeshuffling;//菜单轮播
    private Integer modulechoice;//模块选择ID
    private Integer state;//状态开关
    private List<DiscoveryPageManagement> list = new ArrayList<>();


    public DiscoveryPageManagement() {
    }

    public DiscoveryPageManagement(Integer id, String menunumber, String menuname, String menutype, String typepicture, String typelink, String typewordage, String typeshuffling, Integer modulechoice, Integer state, List<DiscoveryPageManagement> list) {
        this.id = id;
        this.menunumber = menunumber;
        this.menuname = menuname;
        this.menutype = menutype;
        this.typepicture = typepicture;
        this.typelink = typelink;
        this.typewordage = typewordage;
        this.typeshuffling = typeshuffling;
        this.modulechoice = modulechoice;
        this.state = state;
        this.list = list;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMenunumber() {
        return menunumber;
    }

    public void setMenunumber(String menunumber) {
        this.menunumber = menunumber;
    }

    public String getMenuname() {
        return menuname;
    }

    public void setMenuname(String menuname) {
        this.menuname = menuname;
    }

    public String getMenutype() {
        return menutype;
    }

    public void setMenutype(String menutype) {
        this.menutype = menutype;
    }

    public String getTypepicture() {
        return typepicture;
    }

    public void setTypepicture(String typepicture) {
        this.typepicture = typepicture;
    }

    public String getTypelink() {
        return typelink;
    }

    public void setTypelink(String typelink) {
        this.typelink = typelink;
    }

    public String getTypewordage() {
        return typewordage;
    }

    public void setTypewordage(String typewordage) {
        this.typewordage = typewordage;
    }

    public String getTypeshuffling() {
        return typeshuffling;
    }

    public void setTypeshuffling(String typeshuffling) {
        this.typeshuffling = typeshuffling;
    }

    public Integer getModulechoice() {
        return modulechoice;
    }

    public void setModulechoice(Integer modulechoice) {
        this.modulechoice = modulechoice;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public List<DiscoveryPageManagement> getList() {
        return list;
    }

    public void setList(List<DiscoveryPageManagement> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "DiscoveryPageManagement{" +
                "id=" + id +
                ", menunumber='" + menunumber + '\'' +
                ", menuname='" + menuname + '\'' +
                ", menutype='" + menutype + '\'' +
                ", typepicture='" + typepicture + '\'' +
                ", typelink='" + typelink + '\'' +
                ", typewordage='" + typewordage + '\'' +
                ", typeshuffling='" + typeshuffling + '\'' +
                ", modulechoice=" + modulechoice +
                ", state=" + state +
                ", list=" + list +
                '}';
    }
}
