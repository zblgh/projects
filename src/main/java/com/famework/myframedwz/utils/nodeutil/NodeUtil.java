/**
* 标题: NodeUtil.java
* 包名： com.famework.myframedwz.utils.NodeUtil
* 功能描述：TODO
* 作者： tech
* 创建时间： 2014年9月2日 下午3:53:27
* @version V1.0   
*/
package com.famework.myframedwz.utils.nodeutil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.famework.myframedwz.domain.model.SysPermission;
import com.famework.myframedwz.utils.BaseCodeParam;

/**
 * ------------------------------------------------------------------
 * 版权所有 Copyright (C) 2014 杭州热讯电子商务有限公司
 *
 * 项目名称：myframedwz
 *
 * 包名：com.famework.myframedwz.utils.NodeUtil
 *
 * 类名称：NodeUtil
 *
 * 文件名：NodeUtil.java
 * 
 * 创建者： 张斌龙（zblvip123@163.com）
 * 
 * 创建时间：2014年9月2日-下午3:53:27
 * 
 * 描述：TODO
 *-------------------------------------------------------------------
 * 修改人：
 *
 * 修改时间：
 *
 * 修改备注：
 *-------------------------------------------------------------------
 */

public class NodeUtil {
	private static StringBuffer sb = new StringBuffer();
	private static JSONArray jsonArray = new JSONArray();
    /**
     * 根据父节点的ID获取所有子节点作为左边菜单
     * @param list 分类表
     * @param typeId 传入的父节点ID
     * @return String
     */
    public static String getChildNodes(List<SysPermission> list) {
        if(list == null) return "";
        StringBuffer nodesStr = new StringBuffer();
        nodesStr.setLength(0);
        List<SysPermission> oneNodeList = new ArrayList<SysPermission>();
        for (Iterator<SysPermission> iterator = list.iterator(); iterator.hasNext();) {
        	SysPermission node = (SysPermission) iterator.next();
        	if ("0".equals(node.getParendid())) {
        		oneNodeList.add(node);//添加所有一级节点
			}
        }
        Collections.sort(oneNodeList);//执行排序
        for (Iterator<SysPermission> iterator = oneNodeList.iterator(); iterator.hasNext();) {
        	SysPermission node = (SysPermission) iterator.next();
        	sb.append("<div class=\"accordionHeader\">\n")
        	.append("<h2><span>Folder</span>"+node.getPermissionName()+"</h2>\n")
        	.append("</div>\n");
        	sb.append("<div class=\"accordionContent\">\n")
        	.append("<ul class=\"tree treeFolder\">\n");
        	ddd(list, node);//一级节点的所有子节点
            sb.append("</ul>\n")
            .append("</div>\n");
        }
        nodesStr.append(sb);
        sb.setLength(0);
		return nodesStr.toString();
    }
    private static void ddd(List<SysPermission> list, SysPermission node){
    	List<SysPermission> childList = getChildList(list, node);// 得到node节点的子节点列表
        if (hasChild(childList.size())) {// 判断是否有子节点
            Iterator<SysPermission> it = childList.iterator();
            while (it.hasNext()) {
            	SysPermission n = (SysPermission) it.next();
            	sb.append("<li>\n");
            	if ("".equals(n.getPageUrl())) {
            		sb.append("<a id=\""+n.getPid()+"\" >"+n.getPermissionName()+"</a>\n");
				}else {
					sb.append("<a id=\""+n.getPid()+"\" href=\""+n.getPageUrl()+"\" target=\"navTab\" rel=\""+n.getPid()+"\" >"+n.getPermissionName()+"</a>\n");
				}
            	recursionFn(list, n);
                sb.append("</li>\n");
            }
        }
    } 
    private static void recursionFn(List<SysPermission> list, SysPermission node) {
        List<SysPermission> childList = getChildList(list, node);// 得到node节点的子节点列表
        if (hasChild(childList.size())) {// 判断是否有子节点
            Iterator<SysPermission> it = childList.iterator();
            sb.append("<ul>\n");
            while (it.hasNext()) {
            	SysPermission n = (SysPermission) it.next();
					sb.append("<li>\n");
                    if ("".equals(n.getPageUrl())) {
	            		sb.append("<a id=\""+n.getPid()+"\" >"+n.getPermissionName()+"</a>\n");
					}else {
						sb.append("<a id=\""+n.getPid()+"\" href=\""+n.getPageUrl()+"\" target=\"navTab\" rel=\""+n.getPid()+"\" >"+n.getPermissionName()+"</a>\n");
					}
                    recursionFn(list, n);
					sb.append("</li>\n");
            }
            sb.append("</ul>\n");
        }
    }
     
    // 得到子节点列表
    private static List<SysPermission> getChildList(List<SysPermission> list, SysPermission node) {
        List<SysPermission> nodeList = new ArrayList<SysPermission>();
        Iterator<SysPermission> it = list.iterator();
        while (it.hasNext()) {
        	SysPermission n = (SysPermission) it.next();
            if (n.getParendid().equals(node.getPid())) {
                nodeList.add(n);
            }
        }
        Collections.sort(nodeList);//执行排序
        return nodeList;
    }
    //判断某个菜单是否有子菜单
    public static boolean isChildNodes(List<SysPermission> list, String pid) {
    	boolean isChildNodes = false;
        Iterator<SysPermission> it = list.iterator();
        while (it.hasNext()) {
        	SysPermission n = (SysPermission) it.next();
            if (n.getParendid().equals(pid)) {
            	isChildNodes = true;
            	break;
            }
        }
        return isChildNodes;
    }
    //获得子菜单代码以及本菜单
    public static String getMenuPidArray(List<SysPermission> list, String pid){
    	String pidsStr = "";
    	getChildArray(list, pid);
    	pidsStr = sb.append(pid).toString();
    	sb.setLength(0);
    	return pidsStr;
    }
    // 得到子节点array
    private static void getChildArray(List<SysPermission> list, String pid) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
        	SysPermission n = (SysPermission) list.get(i);
            if (n.getParendid().equals(pid)) {
            	sb.append(n.getPid()).append("`");
            	 getChildArray(list,n.getPid());
            }
		}
    }
    // 判断是否有子节点
    private static boolean hasChild(int size) {
        return size > 0 ? true : false;
    }
    /**
     * 
    * @Title: getAllChildNodes
    * @Description: TODO(得到用户权限菜单列表)
    * @param @param allSplist
    * @param @param map
    * @param @return    设定文件
    * @return String    返回类型
    * @throws
     */
    public static String getAllChildNodes(List<SysPermission> allSplist,Map<String, String> map) {
    	if(allSplist == null) return "";
    	StringBuffer nodesStr = new StringBuffer();
        nodesStr.setLength(0);
        JSONObject jsonObject = null;
        for (Iterator<SysPermission> iterator = allSplist.iterator(); iterator.hasNext();) {
        	SysPermission node = (SysPermission) iterator.next();
            // 二、遍历所有的父节点下的所有子节点
            if ("0".equals(node.getParendid())) {//一级节点
            	jsonObject = new JSONObject();
            	jsonObject.put("id", node.getPid());
				jsonObject.put("pId", node.getParendid());
				jsonObject.put("name", node.getPermissionName());
				if (map.containsKey(node.getPid())) {
					jsonObject.put("checked", true);
				}
				jsonArray.add(jsonObject);
				//List<SysPermission> childList = getChildList(allSplist, node);// 得到node节点的子节点列表
				//子节点递归
				recursionMenu(allSplist,node,map);
            }
        }
        nodesStr.append(jsonArray.toJSONString());
        jsonArray.clear();
        return nodesStr.toString();
    }
    //子节点递归
    private static void recursionMenu(List<SysPermission> list, SysPermission node,Map<String, String> map) {
    	List<SysPermission> childList = getChildList(list, node);// 得到node节点的子节点列表
        if (hasChild(childList.size())) {// 判断是否有子节点
            Iterator<SysPermission> it = childList.iterator();
            JSONObject jsonObject = null;
            while (it.hasNext()) {
            	SysPermission n = (SysPermission) it.next();
            	jsonObject = new JSONObject();
            	jsonObject.put("id", n.getPid());
				jsonObject.put("pId", n.getParendid());
				jsonObject.put("name", n.getPermissionName());
				if (map.containsKey(n.getPid())) {
					jsonObject.put("checked", true);
				}
				jsonArray.add(jsonObject);
                recursionMenu(list, n, map);
            }
        }
    }
    
    //菜单管理：查询所有菜单
    public static String getAllNodes(List<SysPermission> allSplist){
    	if(allSplist == null) return "";
    	StringBuffer nodesStr = new StringBuffer();
        nodesStr.setLength(0);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", BaseCodeParam.rootPid);
        jsonObject.put("pId", BaseCodeParam.rootParendid);
        jsonObject.put("name", BaseCodeParam.permissionName);
        jsonObject.put("url", BaseCodeParam.rootMenuUrl);
        jsonObject.put("target", BaseCodeParam.target);
        jsonObject.put("open", true);
        jsonArray.add(jsonObject);
        for (Iterator<SysPermission> iterator = allSplist.iterator(); iterator.hasNext();) {
        	SysPermission node = (SysPermission) iterator.next();
            // 二、遍历所有的父节点下的所有子节点
            if ("0".equals(node.getParendid())) {//一级节点
            	jsonObject = new JSONObject();
            	jsonObject.put("id", node.getPid());
				jsonObject.put("pId", node.getParendid());
				jsonObject.put("name", node.getPermissionName());
				jsonObject.put("url", BaseCodeParam.menuUrl+"?pid="+node.getPid());
		        jsonObject.put("target", BaseCodeParam.target);
		        jsonObject.put("open", true);
				jsonArray.add(jsonObject);
				//List<SysPermission> childList = getChildList(allSplist, node);// 得到node节点的子节点列表
				//子节点递归
				childMenu(allSplist,node);
            }
        }
        nodesStr.append(jsonArray.toJSONString());
        jsonArray.clear();
        return nodesStr.toString();
    }
   //子菜单递归
    private static void childMenu(List<SysPermission> list, SysPermission node) {
    	List<SysPermission> childList = getChildList(list, node);// 得到node节点的子节点列表
        if (hasChild(childList.size())) {// 判断是否有子节点
            Iterator<SysPermission> it = childList.iterator();
            JSONObject jsonObject = null;
            while (it.hasNext()) {
            	SysPermission n = (SysPermission) it.next();
            	jsonObject = new JSONObject();
            	jsonObject.put("id", n.getPid());
				jsonObject.put("pId", n.getParendid());
				jsonObject.put("name", n.getPermissionName());
				jsonObject.put("url", BaseCodeParam.menuUrl+"?pid="+n.getPid());
		        jsonObject.put("target", BaseCodeParam.target);
		        jsonObject.put("open", true);
				jsonArray.add(jsonObject);
				childMenu(list, n);
            }
        }
    }
}
