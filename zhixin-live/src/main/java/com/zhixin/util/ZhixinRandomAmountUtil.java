package com.zhixin.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

public class ZhixinRandomAmountUtil {

    public static Map<String,Object> getRandomAmount(BigDecimal money, BigDecimal maxMoney, BigDecimal minMoney, String num){
        Map<String,Object> resultMap = new HashMap<>();
        List<BigDecimal> resultList = new ArrayList<>();
        money = keepDecimals(money,2);
        maxMoney = keepDecimals(maxMoney,2);
        minMoney = keepDecimals(minMoney,2);
        int min = minMoney.compareTo(new BigDecimal("0.01"));
        if(min == -1){
            resultMap.put("state",false);
            resultMap.put("message","最小金额不能小于0.01");
            return resultMap;
        }
        int mi = money.compareTo(minMoney.multiply(new BigDecimal(num)));
        if(mi == -1){
            resultMap.put("state",false);
            resultMap.put("message","最小金额设置过大");
            return resultMap;
        }else if(mi == 0){
            resultMap.put("state",true);
            int n = Integer.parseInt(num);
            for (int i = 0 ; i<n ; i++){
                resultList.add(minMoney);
            }
            resultMap.put("data",resultList);
            return resultMap;
        }
        int mx = money.compareTo(maxMoney.multiply(new BigDecimal(num)));
        if(mx == 1){
            resultMap.put("state",false);
            resultMap.put("message","最大金额设置过小");
            return resultMap;
        }else if(mx == 0){
            resultMap.put("state",true);
            int n = Integer.parseInt(num);
            for (int i = 0 ; i<n ; i++){
                resultList.add(maxMoney);
            }
            resultMap.put("data",resultList);
            return resultMap;
        }
        String k = String.valueOf(Integer.valueOf(num) - 1);
        BigDecimal max = maxMoney.add(new BigDecimal(k).multiply(minMoney));
        if(max.compareTo(money) == 1){
            resultMap.put("state",false);
            resultMap.put("message","最大金额设置过大");
            return resultMap;
        }
        resultMap.put("state",true);
        resultMap.put("data",generateRandom(money,maxMoney,minMoney,num));
        return resultMap;
    }


    public static List<BigDecimal> generateRandom(BigDecimal money, BigDecimal maxMoney, BigDecimal minMoney, String num){
        List<BigDecimal> list = new ArrayList<>();
        Integer n = Integer.valueOf(num);
        for ( ; n > 0 ; n--){
            if(money.compareTo(minMoney.multiply(new BigDecimal(num))) == 0) {
                list.add(minMoney);
            }else if(money.compareTo(maxMoney.multiply(new BigDecimal(num))) == 0){
                list.add(maxMoney);
            }else {
                BigDecimal hb = getRandom(money,maxMoney,minMoney,String.valueOf(n));
                list.add(hb);
                money = money.subtract(hb);
            }
        }
        return list;
    }

    public static BigDecimal getRandom(BigDecimal money, BigDecimal maxMoney, BigDecimal minMoney ,String num){
        Random r = new Random();
        DecimalFormat format = new DecimalFormat(".##");
        if(num.equals("1")){
            return money;
        }
        num = String.valueOf(Integer.parseInt(num) - 1);
        while(true){
            String hb = format.format(r.nextDouble()*money.doubleValue());
            BigDecimal bb = new BigDecimal(hb);
            BigDecimal sy =  money.subtract(bb);
            int sMinMoney = sy.compareTo(minMoney.multiply(new BigDecimal(num)));
            int sMaxMoney = sy.compareTo(maxMoney.multiply(new BigDecimal(num)));
            if(bb.compareTo(maxMoney) < 1 && bb.compareTo(minMoney) == 1 && sMinMoney == 1 && sMaxMoney == -1){
                return bb;
            }
        }
    }

    public static BigDecimal keepDecimals(BigDecimal value,int num){
        return value.setScale(num,BigDecimal.ROUND_HALF_UP);
    }

    public static void main(String[] args) {
        System.out.println(getRandomAmount(new BigDecimal("1000"),new BigDecimal("500"),new BigDecimal("10"),"10").toString());
    }
}
