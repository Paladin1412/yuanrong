package com.yuanrong.admin.Enum;

/**
 * Created by zhonghang on 2018/4/11.
 */
public enum  EnumYesOrNo implements IntegerValuedEnum  {
        NORMAL("是", 1, "是"),FORBIDDEN("否", 0, "否");

        // 成员变量
        private String name;
        private int index;
        private String description;

        //构造方法
	    private EnumYesOrNo(String name, int index, String description) {
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
}
