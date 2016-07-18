package com.kennedy.springdemo.beans.menu;

import java.io.Serializable;
import java.util.List;

import com.kennedy.springdemo.common.BaseData;

public class Menu extends BaseData implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer parentId;

    private Integer level;

    private String text;

    private String icon;

    private String selectedIcon;

    private String color;

    private String backColor;

    private String href;

    private Boolean selectTable;

    private String tags;

    private List<Menu> nodes;

    public String getText() {
        return text;
    }

    public String getIcon() {
        return icon;
    }

    public String getSelectedIcon() {
        return selectedIcon;
    }

    public String getHref() {
        return href;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setSelectedIcon(String selectedIcon) {
        this.selectedIcon = selectedIcon;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public List<Menu> getNodes() {
        return nodes;
    }

    public void setNodes(List<Menu> nodes) {
        this.nodes = nodes;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getLevel() {
        return level;
    }

    public String getColor() {
        return color;
    }

    public String getBackColor() {
        return backColor;
    }

    public String getTags() {
        return tags;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setBackColor(String backColor) {
        this.backColor = backColor;
    }

    public Boolean getSelectTable() {
        return selectTable;
    }

    public void setSelectTable(Boolean selectTable) {
        this.selectTable = selectTable;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}
