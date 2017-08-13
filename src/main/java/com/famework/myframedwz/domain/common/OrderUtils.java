package com.famework.myframedwz.domain.common;

import java.util.Date;
import java.util.Random;

import com.famework.myframedwz.utils.DateUtil;

public class OrderUtils {
	/**
	 * 生成正常订单16位
	 * 
	 * @return 订单号(20140530154243033740)
	 */
	public static String getOrderNO() {
		String orderNO = DateUtil.toStringDH(new Date())
				+ getRandomInt(2);
		return orderNO;
	}
	/**
	 * 获取随机整数(0~9).
	 * @param 随机整数的长度
	 * @return 随机整数
	 */
	public static String getRandomInt(int len) {
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < len; i++) {
			int c = random.nextInt(10);
			String strRand=Integer.toString(c);
			sb.append(strRand);
		}
		return sb.toString();
	}
	
	public static String getMoneyForMinFormat(Double money,int length){
		StringBuffer moneyStr = new StringBuffer();
		moneyStr.setLength(0);
		int moneyOne = (int)(money*100);
		moneyStr.append(moneyOne);
		int mstr = moneyStr.length();
		if(mstr<length){
			moneyStr.setLength(0);
			moneyStr.append(String.format("%0"+length+"d", moneyOne));
		}
		//System.out.println(moneyStr+"---"+moneyStr.length());
		return moneyStr.toString();
	}
	
	public static void main(String[] args) {
		OrderUtils.getMoneyForMinFormat(24.52, 12);
	}
}
