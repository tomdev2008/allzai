<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
        <title>AJAX测试</title>
        
        <script type="text/javascript" src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
        
<script type="text/javascript">
    var xmlHttp;
    
    function GetXmlHttpObject() {
        try {
            xmlHttp = new XMLHttpRequest();
        } catch (e) {
            try {
                xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
            } catch (e) {
	            xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
            }
        }
    }
    
    function refresh() {
    
    
    
        GetXmlHttpObject();
        xmlHttp.onreadystatechange = handle;
        url = "http://www.appannie.com/apps/google-play/top-table/20140317-SG-2/?p=2-&h=8&iap=undefined";
        xmlHttp.open("POST", url, true);
        
        try{
         	xmlHttp.setRequestHeader("Access-Control-Allow-Origin", "*");  
         	xmlHttp.setRequestHeader("Access-Control-Allow-Headers", "X-Requested-With");  
         	xmlHttp.setRequestHeader("X-Requested-With", "XMLHttpRequest");  
	        xmlHttp.setRequestHeader("Referer", "http://www.appannie.com/apps/google-play/top/singapore/game/");  
			xmlHttp.setRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:27.0) Gecko/20100101 Firefox/27.0");
	        xmlHttp.setRequestHeader("Cookie", "__qca=P0-1782521221-1394793409159; hsfirstvisit=http%3A%2F%2Fwww.appannie.com%2Fapps%2Fgoogle-play%2Ftop%2Fsingapore%2F||1394793409933; __hstc=45742459.904fd984ce505c48c41bde3a5560c8a4.1394793409937.1395037142374.1395039424164.6; hubspotutk=904fd984ce505c48c41bde3a5560c8a4; django_language=zh-cn; csrftoken=8cd76ec2a4108d284446c5964b39e5fc; sessionId=\".eJxNzbFKA0EQxvH1EmM4CXpW9jbaHD6DqTRoERywEJa53SFZss7d3swqEQRtRJ_Et_JZJBDF7s8HP77X4iXtnEIlJBJa7qiXIEqsHzDZblYUe50ZGEfkRcYF3RXGGMcwsZh1abNQb4O_ejsuDBz8KmJsIvlZAUOU4G9gX1uxufOo5FPxCUf_dINuRezh_IkaZIxrDU5qdK7NrPUUhS5ZiCVoeKTr1lO82IpDjNSrdUtyK6vhgdzmYBPlX6QBlHvfg2pcnYy-7l231ufSwu20TMOzedp9n6dRrn8AsoFd2g:1WPSjn:6p6vD6Cp4IONpk-DvX3sKl__jkk\"; __utma=143309285.867841777.1394793409.1395034924.1395044316.5; __utmb=143309285.4.8.1395044316; __utmc=143309285; __utmz=143309285.1394793409.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); __utmv=143309285.|2=UserID=137345=1^3=Account%20Type=Publisher%20-%20Game=1^4=Account%20Type=50%20to%20150%20employees=1; __atuvc=1%7C11%2C30%7C12; __ar_v4=G5KB2UD2SZE5XBYOIK5SFG%3A20140316%3A1%7C4IJGT2ZDGJFZLAACNH4TVF%3A20140316%3A31%7CPSAAGT4MFBB2ROIM33X7L2%3A20140316%3A31%7CT55U6UJPX5AUHHNTMJPY57%3A20140316%3A30");
        } catch(ex) {
        	alert(ex);
        }

        xmlHttp.send(null); 
    }
    
    function handle() {
        if (xmlHttp.readyState == 4) {
        	if(xmlHttp.status == 404) {
        		alert("暂时没有数据");
        	} else {
        		var str = xmlHttp.responseText;
	            alert("响应内容:" + str);
	            var s = document.getElementById("result")
	            s.innerHTML = str;
        	}
        }
    }
    
    $(function(){
    
    /* $.ajax({
		url:"http://www.appannie.com/apps/google-play/top-table/20140317-SG-2/?p=2-&h=8&iap=undefined",
		dataType:"jsonp",
		success:function(data){
			console.log(data);
		},
		headers:{
			"Access-Control-Allow-Origin":"*",
			"User_Agent":"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:27.0) Gecko/20100101 Firefox/27.0",
			"Access-Control-Allow-Headers":"X-Requested-With"
		}
	}); */
    
/* 		$.getJSON("http://www.appannie.com/apps/google-play/top-table/20140317-SG-2/?p=2-&h=8&iap=undefined", function(data){
	
		headers: {
            "Access-Control-Allow-Origin":"*",
            "Access-Control-Allow-Headers":"X-Requested-With",
            "User_Agent":"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:27.0) Gecko/20100101 Firefox/27.0",
            "Reffer":"http://www.appannie.com/apps/google-play/top/singapore/game/",
            "Host":"www.appannie.com"
        }
	
		console.log(data);
	}); */
	})
    
</script>
    </head>

    <body onload="refresh()">

    
        <h3>
           自动加载数据
        </h3>
        结果:
        <div id="result" style="width: 200px">
        </div>
    </body>
</html>
