var charInfoArr=[];//存储所有字的数据
var minification=7;//缩小倍率
var preBHNo=-1;
var preHZNo=-1;
var jie2TimeObj=null;
var width=0;
var height=0;
var tipDraw=new jsGraphics("tipDiv");
var curId="";
var strokeInfo=[];
//alert(czbhProxyUrl);



startFun();

function startFun(){
    //charInfoArr=[];
    var divObj=document.getElementById("jie2Disp");
	//alert(divObj);
    var posi=Position.cumulativeOffset(divObj);
	//alert(strokeInfo);
    width=posi[0]+width_offset;//245,37
    height=posi[1]+height_offset;
    divObj=null;
	getCharInfoByArray();
	//alert("split"+ "      "+strokeInfo);
	echohello();
    getCharInfoFun();
}


function ajaxx(i)
{
    
	jQuery.ajax({
	    url:"info.txt",
		//url:i+".txt",
		type:"POST",
		success:function(msg){
			alert(msg);
		}
	});
}

//得到所有汉字的数据，处理后存于charInfoArr数组中
function getCharInfoFun(){
    var nLen=characterArr.length;
	//alert(characterArr);
	var tempArr=[];
	//alert(nLen);
	//alert("getCharInfoFun()");
	var tag=1;
	var m,n
	//alert(strokeInfo);
    for(n=0,m=0; n<nLen; n++){
			//对中文字符进行处理
			//alert(characterArr);
			//alert(characterArr.substr(n,1));window.external.isChinese
			//if(window.external.isChinese(characterArr.substr(n,1)))
			var charInfoStr=strokeInfo[n];
			//alert(charInfoStr);
			
			if(1){
				
				tag=1;
				if(charInfoStr=="" || charInfoStr=="######"){
					//document.getElementById("jie2BH2").innerHTML="<font color='red'>暂无该字</font>";
					//return;
				}
				else if(charInfoStr=="Exception"){
					//document.getElementById("jie2BH2").innerHTML="<font color='red'>连接错误，请重新查询！</font>";
					//return;
				}
				else{
					tempArr=charInfoStr.split("###");
					//alter(tempArr);
					if(typeof(tempArr[1])!="undefined" && typeof(tempArr[2])!="undefined"){//有坐标
						if(tag==1)//如果是汉字
						{
							var strokeArr=drawChar(tempArr[1], m);//汉字形体坐标分析及画字
							var charArr=dealChar(tempArr[2]);//汉字描红坐标分析,数据存入数据库后使用
							charInfoArr.push([charArr, strokeArr, tempArr[0]]);
							m++;
						}
					}
					tempArr=null;
				}
				width+=123;
				//换行
				if(n!=0 && n%numPerLine==(numPerLine-1)){
					height+=123;
					width-=123*numPerLine;
				}
			}
			else
			{
				var objId="jie2Char_"+n;
				createDivObj(objId);
				var divObj=document.getElementById(objId);
				divObj.style.color="#aaa";
				divObj.style.top=height+25.75;
				divObj.style.left=width+35.75;
				divObj.style.position="absolute";
				divObj.style.fontSize="72px";
				divObj.style.textAlign="center";
				document.getElementById(objId).innerHTML=characterArr.substr(n,1);
				width+=123;
				
				//换行
				if(n!=0 && n%numPerLine==(numPerLine-1)){
					height+=123;
					width-=123*numPerLine;
				}
				tag=0;
			}
			//对非中文字符进行处理
    }
}



function drawSpecialWord(specialWord)
{

}


function GetHeader(src) { 
 alert("GetHeader");
 var ForReading=1; 
 var fso=new ActiveXObject("Scripting.FileSystemObject"); 
 var f=fso.OpenTextFile(src,ForReading); 
 return(f.ReadAll()); 
} 

function echohello()
{
	var hello=window.demo.getString()
}

//查字笔画的笔画及坐标的查询
function getCharInfoByArray(){
    //strokeInfo=GetHeader("D:\\Debug\\info.txt").split("*****");
	var strokeInfo1;
	strokeInfo1=window.demo.getString();
	alert("click_mh.js---"+strokeInfo);
	strokeInfo= strokeInfo1.split("*****");
	//alert(strokeInfo);
}

/**
 分解字型坐标串，画出汉字
 coordinateStr:坐标串
 no:表示第几个汉字
 i:表示第几笔笔画
*/
function drawChar(coordinateStr, no){
    var Arr=coordinateStr.split("#");//按笔画切分
    var iLen=Arr.length;
    var strokeArr=[];//存储单字字型数据	        	   				
    for(var i=iLen-1;i>=0;i--){
        var tempArr=Arr[i].split(";");//以坐标点对切分
        var jLen=tempArr.length;    			
        var xArr=[],yArr=[];  			
        for(var j=0;j<jLen;j++){
            var str=tempArr[j];
            var arrStr=str.split(",");//切分为x、y轴
            xArr.push(Math.floor(arrStr[0]/minification)+width);
            yArr.push(Math.floor(arrStr[1]/minification)+height);
        }
        var objId="jie2Char"+no+i;
        createDivObj(objId);
        divObj=document.getElementById(objId);
        if(document.all){
            eval("divObj"+".onmouseover=function(){setTip('red','"+i+"','"+no+"', event);}");
            eval("divObj"+".onmouseout=function(){clearTip(event);}");
        }
        else{
            eval("divObj"+".setAttribute(\"onmouseover\",\"setTip('red','"+i+"','"+no+"',event)\")");
            eval("divObj"+".setAttribute(\"onmouseout\",\"clearTip(event)\")");
        }
        var draw=new jsGraphics(objId);
        draw.setColor("#F2C7CB");
        draw.onclickStr=" onclick=\"drawStroke('red','"+i+"','"+no+"')\" ";
        draw.fillPolygon(xArr, yArr);
        draw.paint();
        draw.onclickStr="";
        draw=null;
        strokeArr.unshift([xArr,yArr]);
    }
    return strokeArr;
}



function createDivObj(objId){
    var divObj=document.createElement('div');
    divObj.id=objId;
    document.getElementById("jie2Char").appendChild(divObj);
    divObj=null;
}

//分解用于描红的坐标串
function dealChar(coordinateStr){
    var Arr=[];
    var charArr=[];//存储单字描红数据
    var txtConArr=coordinateStr.split("#");
    var iLen=txtConArr.length;
    for(var i=0; i<iLen; i++){//每一笔
        if(txtConArr[i]!=""){
            var Arr=txtConArr[i].split(";");
            var kLen=Arr.length;
            var charArr_mh_temp=[];
            for(var k=0; k<kLen; k++){//每一笔由;切分的段组成        
                var tempArr=Arr[k].split(" ");
                var jLen=tempArr.length;
                for(var j=0;j<jLen;j+=4){
                    var xArr=[],yArr=[];
	                for(var m=0; m<8; m+=2){
                        xArr.push(Math.floor(tempArr[j+m]/minification)+width);
                        yArr.push(Math.floor(tempArr[j+m+1]/minification)+height);
                    }		                
                    charArr_mh_temp.push([xArr,yArr]);
                    if(j+8==jLen){
                        break;   
                    }
                }
            }//一笔的所有坐标点
            charArr.push(charArr_mh_temp);
            charArr_mh_temp=null;
        }
    }
    return charArr;
}

/**
描红
color：用于描红的颜色
n1:表示要描第n1个汉字
n2:表示要描第n2笔笔画
*/
function drawStroke(color, n2, n1){
    tipDraw.clear();
    var strokeArr=[];//存储单字字型数据
    var charArr=[];//存储单字描红数据
    var charInfoArrTemp=charInfoArr[n1];    
    charArr=charInfoArrTemp[0];
    //var orderStr=charInfoArrTemp[2].replace(/\//g,"");
    if(!(preBHNo==n2 && preHZNo==n1)){
        //orderArr[n1]+=orderStr.charAt(n2);
        orderArr[n1].push(n2);
        if(preHZNo!=-1 && preBHNo!=-1){
            var objId="jie2Char"+preHZNo+preBHNo;
            var reObjId=objId+"_";
            createDivObj(reObjId);
            if(jie2TimeObj!=null && jie2TimeObj!=""){//前一笔还没描完
                strokeArr=charInfoArr[preHZNo][1];       
                endPreBH(strokeArr[preBHNo], color, reObjId);
            }
            else{
                strokeArr=charInfoArr[preHZNo][1];
                reDrawBH(strokeArr[preBHNo], color, reObjId);
            }         
            deleteDiv(objId);
        }
        preBHNo=n2;
        preHZNo=n1;
    }
    var draw=new jsGraphics("jie2Char"+n1+n2);
    draw.setColor(color);  
    jie2TimeObj=setInterval(function(){dealDraw(charArr[n2],draw);}, 10);
}

//清除原有的内容
function deleteDiv(objId){
    var div=document.getElementById(objId);
    div.parentNode.removeChild(div);
}
//结束正在描的笔画
function endPreBH(dataArr, color, objId){
    clearInterval(jie2TimeObj);
    jie2TimeObj=null;
    var draw=new jsGraphics(objId);
    draw.setColor(color);  
    draw.fillPolygon(dataArr[0],dataArr[1]); 
    draw.paint();
    draw=null;
    dataArr=null;
}
//重画前一笔
function reDrawBH(dataArr, color, objId){
    var draw=new jsGraphics(objId);
    draw.setColor(color);  
    draw.fillPolygon(dataArr[0],dataArr[1]); 
    draw.paint();
    draw=null;
    dataArr=null;
}

//描一笔
function dealDraw(dataArr,draw){
    if(dataArr.length>0){
        var tempArr=dataArr.shift();
        draw.fillPolygon(tempArr[0], tempArr[1]);
	    draw.paint();
	    tempArr=null;
    }
    else{
        clearInterval(jie2TimeObj);
        jie2TimeObj=null;
        dataArr=null;
        draw=null;
    }
}

if(typeof(HTMLElement) != "undefined"){
    HTMLElement.prototype.contains = function(obj) {
        while(obj != null &&  typeof(obj.tagName) != "undefind") {
            if(obj == this){
                return true;
            }
            obj = obj.parentNode;
        }    
        return false;   
    }  
}

//提示鼠标指向哪一笔
function setTip(color, n2, n1, event){
    tipDraw.clear();
    curId="jie2Char"+n1+n2;
    var dataArr=charInfoArr[n1][1][n2];
    tipDraw.setColor(color);
    tipDraw.setStroke(1); 
    tipDraw.onclickStr=" onclick=\"drawStroke('red','"+n2+"','"+n1+"')\" ";
    tipDraw.drawPolygon(dataArr[0],dataArr[1]); 
    tipDraw.paint();
    tipDraw.onclickStr="";
    dataArr=null;
}

function clearTip(event){
    if(!document.all){
        event=event||window.event;
        var curObj=document.getElementById(curId);
        var objTip=document.getElementById("tipDiv");
        if( !(curObj.contains(event.relatedTarget) || objTip.contains(event.relatedTarget)) ){
            tipDraw.clear();
        }
    }
    else{
        tipDraw.clear();
    }
}
