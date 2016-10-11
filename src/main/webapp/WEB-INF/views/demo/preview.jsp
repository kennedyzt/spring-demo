<%@ page language="java"
	import="java.util.*,com.zhuozhengsoft.pageoffice.*,com.zhuozhengsoft.pageoffice.wordwriter.*"
	pageEncoding="gb2312"%>
<%@ taglib uri="http://java.pageoffice.cn" prefix="po"%>
<%
//******************************卓正PageOffice组件的使用*******************************
	//设置PageOffice服务器组件
	PageOfficeCtrl poCtrl1 = new PageOfficeCtrl(request);
	poCtrl1.setServerPage(request.getContextPath()+"/poserver.zz"); //此行必须
	
	//隐藏Office工具条
	poCtrl1.setOfficeToolbars(false);
	//隐藏自定义工具栏
	poCtrl1.setCustomToolbar(false);
	//设置页面的显示标题
	poCtrl1.setCaption("演示：最简单的以只读模式打开Word文档");
	
	//打开文件
	//poCtrl1.webOpen("doc/template.doc", OpenModeType.docNormalEdit, "张三");
	poCtrl1.webOpen("doc/template.doc", OpenModeType.docReadOnly, "张三");
	poCtrl1.setTagId("PageOfficeCtrl1"); //此行必须	
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>   
    <title>最简单的以只读模式打开Word文档</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <div style=" width:auto; height:700px;">
    <po:PageOfficeCtrl id="PageOfficeCtrl1"></po:PageOfficeCtrl>
    </div>
  </body>
</html>
