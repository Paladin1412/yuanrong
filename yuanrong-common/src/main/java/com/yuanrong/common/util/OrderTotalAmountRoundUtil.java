package com.yuanrong.common.util;

import java.math.BigDecimal;

/**
 * Created by zhonghang on 2018/09/25.
 * 计算
 *
 * 小于10向上整取两位小数 						8.492 → 8.50
 * 大于等于10小于100向上整取一位小数			18.73 → 18.80
 * 大于等于100小于1000向上取整到个位			163.2 → 164.00
 * 大于等于1000小于10000向上取整到十位 		    5011.74 → 5020.00
 * 大于等于10000向上取整到百位					26938.54 → 27000
 */
public class OrderTotalAmountRoundUtil {
    public static BigDecimal getRoundAmount(BigDecimal money){
        BigDecimal result;
        if(money.compareTo(new BigDecimal(10)) < 0){
            result = money.setScale(2,BigDecimal.ROUND_UP);
        }else if(money.compareTo(new BigDecimal(100)) < 0){
            result = money.setScale(1,BigDecimal.ROUND_UP);
        }else if(money.compareTo(new BigDecimal(1000)) < 0){
            result = money.setScale(0,BigDecimal.ROUND_UP);
        }else if(money.compareTo(new BigDecimal(10000)) < 0){
            result = money.setScale(-1,BigDecimal.ROUND_UP);
        }else{
            result = money.setScale(-2,BigDecimal.ROUND_UP);
        }
        return result.setScale(2);
    }

    public static void main(String[] args) {
        System.out.println(OrderTotalAmountRoundUtil.getRoundAmount(new BigDecimal(8.492)));
        System.out.println(OrderTotalAmountRoundUtil.getRoundAmount(new BigDecimal(18.73)));
        System.out.println(OrderTotalAmountRoundUtil.getRoundAmount(new BigDecimal(163.2)));
        System.out.println(OrderTotalAmountRoundUtil.getRoundAmount(new BigDecimal(5011.74)));
        System.out.println(OrderTotalAmountRoundUtil.getRoundAmount(new BigDecimal(26800.00)));
    }
}
