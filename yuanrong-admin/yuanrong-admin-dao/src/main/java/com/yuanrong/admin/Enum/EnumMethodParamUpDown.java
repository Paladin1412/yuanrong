package com.yuanrong.admin.Enum;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 基本枚举类型，表示信息的状态
 * @author Administrator
 *
 */
public enum EnumMethodParamUpDown implements IntegerValuedEnum  {
	上架("上架", 1, "上架"),下架("下架", 0, "下架"),获取错误("获取错误",3,"获取错误"),获取对象("获取对象",4,"获取对象"),
	全选("全选",1,"全选"),设为代表作("设为代表作",5,"设为代表作"),取消代表作("取消代表作",6,"取消代表作"),删除("删除参数",1,"删除参数")
	;


	// 成员变量
	private String name;

	private int index;

	private String description;

	//构造方法
	private EnumMethodParamUpDown(String name, int index, String description) {
		this.name = name;
		this.index = index;
		this.description = description;
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
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static List<Map<String , Object>> getMapInfo(){
		List<Map<String , Object>> result = new ArrayList();

		for(EnumMethodParamUpDown type : EnumMethodParamUpDown.values()){
			Map<String , Object> ele = new TreeMap<String, Object>();
			ele.put("id" , type.index);
			ele.put("name" , type.name);
			result.add(ele);
		}
		return result;
	}
}
