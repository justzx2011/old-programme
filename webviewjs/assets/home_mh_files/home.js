var characterArr="";
//var orderArr=["","","","","","","","","",""];//���ڴ洢�û�����˳��
var orderArr=[[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[]];
var numPerLine=6;
var width_offset=8;
var height_offset=6;
var temp='';
//var path="../mh";
document.write('<script type="text/javascript" src="prototype.js"></script>');
document.write('<script type="text/javascript" src="wz_jsgraphics.js"></script>');
document.write('<script type="text/javascript" src="click_mh.js"></script>');
//document.write('<script type="text/javascript" src="' + path + '/onresize_click.js"></script>');
//alert(characterArr);
test();
	
function test()
{
	//var str = window.external.getResources();
	
	//var tnum=window.external.getDebugPath();
	//temp=str.substring(tnum+1);
	//temp='info.txt';
	//alert(temp);
	//$.ajax({
		//	type:'POST',
			//url:temp,
			//success:function(msg){
			//	characterArr=msg;
			//	//alert(characterArr);
			//}
		//});
		characterArr=window.demo.getString();
		alert("home.js---"+characterArr);
}
/*
justzx001��Ҫѧϰ��ֻ�ǣ�
1 html��ǩ
2 js
3 ajax
*/