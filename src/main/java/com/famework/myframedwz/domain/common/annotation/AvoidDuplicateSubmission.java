/**
* 标题: AvoidDuplicateSubmission.java
* 包名： com.yuanjing.myweb.domain.common.annotation
* 功能描述：TODO
* 作者： john
* 创建时间： 2014年8月2日 下午11:50:44
* @version V1.0   
*/
package com.famework.myframedwz.domain.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
*项目名称：myweb
*包名： com.yuanjing.myweb.domain.common.annotation
*类名称：AvoidDuplicateSubmission
*类描述：防止重复提交注解，用于方法上
*1.在新建页面方法上，设置needSaveToken()为true，此时拦截器会在Session中保存一个token，
*同时需要在新建的页面中添加
*<input type="hidden" name="token" value="${token}">
*2.保存方法需要验证重复提交的，设置needRemoveToken为true
*此时会在拦截器中验证是否重复提交
*创建人：张斌龙
*创建时间：2014年8月2日下午11:53:01
*修改人：张斌龙
*修改时间：2014年8月2日下午11:53:01
*修改备注：
*@version
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AvoidDuplicateSubmission {
	boolean needSaveToken() default false;
    boolean needRemoveToken() default false;
}
