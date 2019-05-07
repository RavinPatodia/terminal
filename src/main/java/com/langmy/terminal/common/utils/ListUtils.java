package com.langmy.terminal.common.utils;

import java.util.List;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;


/**
 * @author lzy
 * 使用 Guava实现的一些工具方法
 */
public class ListUtils {
	
	/**
	 * 1, 2, 3, 4, 5->5,4,3,2,1
	 * 
	 */
	public static <T> List<T> reverse(List<T> list){
		return Lists.reverse(list);
	}
	
	/**
	 * {{1,2}, {3,4}, {5}}
	 * 
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> partition(List<T> list,int size){
		return (List<T>) Lists.partition(list,size);
	}
	
	public static <T> boolean isNullOrEmpty(List<T> list){
		boolean flag = false;
		if(list==null){
			flag = true;
			return flag;
		}
		if(list.isEmpty()){
			flag = true;
			return flag;
		}
		return flag;
		
	}
	
	public static <T> boolean notNullAndEmpty(List<T> list){
		if(list==null||list.isEmpty())
			return false;
		return true;
	}
	
	public static <T> List<T> newAndReplace(final List<T> list,T sourceObj,T targetObj){
		if(ListUtils.isNullOrEmpty(list)){
			return null;
		}
		List<T> result = Lists.newArrayList(list.iterator());
		if(!result.remove(sourceObj)){
//			throw new Exception("List中移除对象异常");
			return null;
		}
		if(!result.add(targetObj)){
//			throw new Exception("List中添加对象失败");
			return null;
		}
		return result;
	} 
	
	/**
	 * 将List分割成字符串，用","隔开
	 * @param list 列表
	 * @return
	 */
	public static <T> String splite(final List<T> list){
		String result = Joiner.on(",").join(list);
		return result;
	}
	
	
	public static void main(String[] args) {
//		List<String> lists = Lists.newArrayList();
//		lists.add("123");
//		ListUtils.newAndReplace(lists, "1", "12");
		
//		List<String> list = new ArrayList<String>();
//		  list.add("爱护地球");        //向列表中添加数据
//		  list.add("保护环境");       //向列表中添加数据
//		  list.add("从我做起");       //向列表中添加数据
//		  List<String> list1 = new ArrayList<String>();
//		  list1.add("保护环境");        //向列表中添加数据
//		  list1.add("爱护地球");       //向列表中添加数据
//		  boolean ret = list.removeAll(list1);    //从list中移除与list1相同的元素
//		  Iterator<String> it = list.iterator();   //创建迭代器
//		  while (it.hasNext()) {       //循环遍历迭代器
//		   System.out.println(it.next());    //输出集合中元素
//		  }
		List<String> list = Lists.newArrayList();
		list.add("1");
		System.out.println(notNullAndEmpty(list));
	}
	
}
