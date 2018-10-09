/**
 * Created by lihan on 2018/10/7.
 */
// ***** 变量定义 ****** //
var YearSt = 1950;			//可选择的开始年份
var YearEnd = 2050;			//可选择的结束年份
var dateFomat;
var inputObj;
var DateSplit = "-";		//日期的分隔符号
var DateTimeSplit = " ";    //日期与时间的分隔符
var TimeSplit = ":";     	//时间的分隔符号
var isShowTime = "display:none";//是否显示时间


// ***** 控件界面 ****** //
function HS_calender(){
    var style = "";
    style +="<style type='text/css'>";
    style +=".calender { width:170px; height:auto; font-size:11px; margin-right:14px; background-color:#F9F9F9;; border:1px solid #397EAE; padding:1px}";
    style +=".calender .calenderTitle {color:#FFCC00; background-color:#2650A6; text-align:center;height:20px; line-height:20px; clear:both}";
    style +=".calender .calenderTitle a {margin:0 1px;color:#FFCC00;text-decoration:none;}";
    style +=".calender .calenderTitle span a {margin:0 1px;color:#FFCC00;font-weight:bold;text-decoration:none;}";
    style +=".calender .calenderTitleEnter {background-color:#2650A6; text-align:center;height:20px; line-height:20px;}";
    style +=".calender ul {list-style-type:none; margin:-1; padding:0;}";
    style +=".calender .day {color:#FFF; background-color:#2650A6; height:20px;}";//日一二三四五六
    style +=".calender .day li{ float:left; width:14%; height:20px; line-height:20px; text-align:center}";
    style +=".calender .date li{ float:left; width:14%; height:20px; line-height:20px; text-align:center}";
    style +=".calender li a { color:#5daac0;text-decoration:none; font-family:Tahoma; font-size:11px; }";
    style +=".calender li a:hover { background-color:#EEE;padding:3px;}";
    style +=".selected { background-color:#DBDBDB; padding:2px}";
    style +=".lastMonthDate, .nextMonthDate {color:#bbb;font-size:11px}";
    style +=".calender .LastMonth, .calender .NextMonth{ text-decoration:none; color:#000; font-size:18px; font-weight:bold; line-height:16px;}";
    style +=".calender .LastMonth { float:left;}";
    style +=".calender .NextMonth { float:right;}";
    style +=".calenderBottom {clear:both; border-top:1px solid #ddd; padding: 3px 0; text-align:right}";
    style +=".calenderBottom a {text-decoration:none; margin:3px; font-size:11px; color:#2650A6}";
    style +="</style>";

    var h="0",m="0";//时间
    var thisDate;
    if (typeof(arguments[0])=="string"){
        var DateValueTemp = inputObj.value.replace(DateSplit, "").replace(DateSplit, "").replace(DateSplit, "").replace(DateTimeSplit, "").replace(TimeSplit, "");
        var year = DateValueTemp.substr(0,4);
        var month = parseInt(DateValueTemp.substr(4,2))-1+"";
        var date = DateValueTemp.substr(6,2);	//alert(year+" "+month+" "+date);
        if(DateValueTemp.substr(8,2).toString().length>0) {h=DateValueTemp.substr(8,2);}
        if(DateValueTemp.substr(10,2).toString().length>0){m=DateValueTemp.substr(10,2);}
        thisDate = new Date(year,month,date);
        thisDate.setHours(parseInt(h), parseInt(m));
    }else if (typeof(arguments[0])=="object"){
        thisDate = arguments[0];
    }
    var lastMonthEndDate = HS_DateAdd("d","-1",thisDate.getFullYear()+"-"+thisDate.getMonth()+"-01").getDate();
    var lastMonthDate = WeekDay(thisDate.getFullYear()+"-"+thisDate.getMonth()+"-01");
    var thisMonthLastDate = HS_DateAdd("d","-1",thisDate.getFullYear()+"-"+(parseInt(thisDate.getMonth())+1).toString()+"-01");
    var thisMonthEndDate = thisMonthLastDate.getDate();
    var thisMonthEndDay = thisMonthLastDate.getDay();
    var thisDateString = thisDate.getFullYear()+"-"+thisDate.getMonth()+"-"+thisDate.getDate();

    var CalenderTitle = "";
    CalenderTitle += "<span style='color:#FFCC00;'><a href='#' onclick='showSelectYear(this)' title='双击选择年份' >"+thisDate.getFullYear()+"</a>年</span>"
    CalenderTitle += "<span style='display:none;'><select style='width:54px;' onChange='HS_calender(new Date(parseInt(this.value),parseInt("+thisDate.getMonth()+"),1),this.parentNode);'>";
    for(var i=YearSt;i <= YearEnd;i ++){
        if (i==thisDate.getFullYear()){
            CalenderTitle += "<option value="+i+" selected>"+i+"</option>";
        }else{
            CalenderTitle += "<option value="+i+">"+i+"</option>";
        }
    }CalenderTitle += "</select></span>";
    CalenderTitle += "<span style='color:#FFCC00;'><a href='#' onclick='showSelectMonth(this)' title='双击选择月份'>"+(parseInt(thisDate.getMonth())+1)+"</a>月</span> ";
    CalenderTitle += "<span style='display:none;'><select style='width:40px;' onChange='HS_calender(new Date(parseInt("+thisDate.getFullYear()+"),parseInt(this.value)-1,1),this.parentNode);'>";
    for(var i=1;i <= 12;i ++){
        if (i==(thisDate.getMonth()+1)){
            CalenderTitle += "<option value="+i+" selected>"+i+"</option>";
        }else{
            CalenderTitle += "<option value="+i+">"+i+"</option>";
        }
    }CalenderTitle += "</select></span>";
    CalenderTitle += "<span style='color:#FFCC00;"+isShowTime+"'><a href='#' onclick='showSelectHour(this)' title='双击选择时间'>"+(parseInt(thisDate.getHours()))+"</a>时</span> ";
    CalenderTitle += "<span style='display:none;'><select style='width:40px;' onChange='selectHour(this);'>";
    for(var i=0;i <=12;i ++){
        if (i==(thisDate.getHours())){
            if(i<10) {CalenderTitle += "<option value="+i+" selected>0"+i+"时</option>";}
            else {CalenderTitle += "<option value="+i+" selected>"+i+"时</option>";}
        }else{
            if(i<10) {CalenderTitle += "<option value="+i+" >0"+i+"时</option>";}
            else {CalenderTitle += "<option value="+i+" >"+i+"时</option>";}
        }
    }CalenderTitle += "</select></span>";
    CalenderTitle += "<span style='color:#FFCC00;margin:0 -2px;"+isShowTime+"'><a href='#' onclick='showSelectMinute(this)' title='双击选择时间'>"+0+"</a>分</span> ";
    CalenderTitle += "<span style='display:none;'><select style='width:40px;' onChange='selectMinute(this);'>";
    for(var i=0;i <=59;i ++){
        if(i<10) {CalenderTitle += "<option value="+i+">0"+i+"分</option>";}
        else {CalenderTitle += "<option value="+i+">"+i+"分</option>";}
    }CalenderTitle += "</select></span>";
    CalenderTitle += "<br>";
    CalenderTitle += "<a href='#' id='turnDate' onclick=HS_calender(HS_DateAdd('y',-1,'"+thisDate.getFullYear()+"-"+thisDate.getMonth()+"-"+thisDate.getDate()+"'),this) title='上一年'>年↑ </a>";
    CalenderTitle += "<a href='#' onclick=HS_calender(HS_DateAdd('y',1,'"+thisDate.getFullYear()+"-"+thisDate.getMonth()+"-"+thisDate.getDate()+"'),this) title='下一年'>年↓ </a> ";
    CalenderTitle += "<a href='#' onclick=HS_calender(HS_DateAdd('m',-1,'"+thisDate.getFullYear()+"-"+thisDate.getMonth()+"-"+thisDate.getDate()+"'),this) title='上一月'>月↑ </a>";
    CalenderTitle += "<a href='#' onclick=HS_calender(HS_DateAdd('m',1,'"+thisDate.getFullYear()+"-"+thisDate.getMonth()+"-"+thisDate.getDate()+"'),this) title='下一月'>月↓</a>";

    var lis = "";
    for (i=0; i<lastMonthDate; i++){  // Last Month's Date
        lis = "<li class='lastMonthDate'>"+lastMonthEndDate+"</li>" + lis;
        lastMonthEndDate--;
    }
    for (i=1; i<=thisMonthEndDate; i++){ // Current Month's Date
        if(thisDateString == thisDate.getFullYear()+"-"+thisDate.getMonth()+"-"+i){
            var momth_length=(parseInt(thisDate.getMonth())+1).toString();
            if(momth_length.length=="1" && i<10){
                lis += "<li><a href=javascript:void(0) class='selected' onclick='selectThisDay(this)' title='"+thisDate.getFullYear()+DateSplit+"0"+(parseInt(thisDate.getMonth())+1)+DateSplit+"0"+i+"'>"+i+"</a></li>";
            }else if(momth_length.length=="1" && i>9){
                lis += "<li><a href=javascript:void(0) class='selected' onclick='selectThisDay(this)' title='"+thisDate.getFullYear()+DateSplit+"0"+(parseInt(thisDate.getMonth())+1)+DateSplit+i+"'>"+i+"</a></li>";
            }else if(momth_length.length=="2" && i<10){
                lis += "<li><a href=javascript:void(0) class='selected' onclick='selectThisDay(this)' title='"+thisDate.getFullYear()+DateSplit+(parseInt(thisDate.getMonth())+1)+DateSplit+"0"+i+"'>"+i+"</a></li>";
            }else if(momth_length.length=="2" && i>9){
                lis += "<li><a href=javascript:void(0) class='selected' onclick='selectThisDay(this)' title='"+thisDate.getFullYear()+DateSplit+(parseInt(thisDate.getMonth())+1)+DateSplit+i+"'>"+i+"</a></li>";
            }
        }else{
            var momth_length=(parseInt(thisDate.getMonth())+1).toString();
            if(momth_length.length=="1" && i<10){
                lis += "<li><a href=javascript:void(0) onclick='selectThisDay(this)' title='"+thisDate.getFullYear()+DateSplit+"0"+(parseInt(thisDate.getMonth())+1)+DateSplit+"0"+i+"'>"+i+"</a></li>";
            }else if(momth_length.length=="1" && i>9){
                lis += "<li><a href=javascript:void(0) onclick='selectThisDay(this)' title='"+thisDate.getFullYear()+DateSplit+"0"+(parseInt(thisDate.getMonth())+1)+DateSplit+i+"'>"+i+"</a></li>";
            }else if(momth_length.length=="2" && i<10){
                lis += "<li><a href=javascript:void(0) onclick='selectThisDay(this)' title='"+thisDate.getFullYear()+DateSplit+(parseInt(thisDate.getMonth())+1)+DateSplit+"0"+i+"'>"+i+"</a></li>";
            }else if(momth_length.length=="2" && i>9){
                lis += "<li><a href=javascript:void(0) onclick='selectThisDay(this)' title='"+thisDate.getFullYear()+DateSplit+(parseInt(thisDate.getMonth())+1)+DateSplit+i+"'>"+i+"</a></li>";
            }
        }
    }
    var j=1;
    for (i=thisMonthEndDay; i<6; i++){  // Next Month's Date
        lis += "<li class='nextMonthDate'>"+j+"</li>";
        j++;
    }

    if (arguments.length>1){//arguments[0]-时间,arguments[1]-元素标签
        arguments[1].parentNode.parentNode.getElementsByTagName("ul")[1].innerHTML = lis;
        arguments[1].parentNode.innerHTML = CalenderTitle;
    }else{
        var CalenderBox = style+"" + //"<br>" +
            "<div class='calender'>" +
            "<div class='calenderTitle'>"+CalenderTitle+"</div>"+
            "<div class='calenderTitleEnter'>"+""+"</div>"+
            "<div class='calenderBody'>" +
            "<ul class='day'>" +
            "<li>日</li>" +
            "<li>一</li>" +
            "<li>二</li>" +
            "<li>三</li>" +
            "<li>四</li>" +
            "<li>五</li>" +
            "<li>六</li>" +
            "</ul>" +
            "<ul class='date' id='thisMonthDate'>"+lis+"</ul>" +
            "</div>" +
            "<div class='calenderBottom'>" +
            "<a href='#' onclick='closeCalender(this)'>关闭 </a>" +
            "<a href='#' onclick='clearCalender(this)'>清空 </a>" +
            "<span><span><a href='#' onclick='selectThisDay(this)' title='"+getToday()+"'>今天 </a></span></span>" +
            "</div>" +
            "</div>";
        return CalenderBox;
    }
}


function HS_DateAdd(interval,number,date){
    number = parseInt(number);
    if (typeof(date)=="object"){var date = date}
    if (typeof(date)=="string"){var date = new Date(date.split("-")[0],date.split("-")[1],date.split("-")[2])}
    switch(interval){
        case "y":return new Date(date.getFullYear()+number,date.getMonth(),date.getDate()); break;
        case "m":return new Date(date.getFullYear(),date.getMonth()+number,checkDate(date.getFullYear(),date.getMonth()+number,date.getDate())); break;
        case "d":return new Date(date.getFullYear(),date.getMonth(),date.getDate()+number); break;
    }
}
function checkDate(year,month,date){
    var enddate = ["31","28","31","30","31","30","31","31","30","31","30","31"];
    var returnDate = "";
    if (year%4==0){enddate[1]="29"}
    if (date>enddate[month]){returnDate = enddate[month]}else{returnDate = date}
    return returnDate;
}

function WeekDay(date){
    var theDate;
    if (typeof(date)=="object"){theDate = date}
    if (typeof(date)=="string"){theDate = new Date(date.split("-")[0],date.split("-")[1],date.split("-")[2]);}
    return theDate.getDay();
}

function setDateFomat(dateFmt){
    if(dateFmt==null) { isShowTime = "display:none"; }
    DateSplit = "-";		//日期的分隔符号
    DateTimeSplit = " ";    //日期与时间的分隔符
    TimeSplit = ":";     	//时间的分隔符号
    if( dateFmt.toString().charAt(4)=='M' || dateFmt.toString().charAt(4)=='m' ) {DateSplit = "";}
    if( dateFmt.toString().charAt(4)!='M' && dateFmt.toString().charAt(4)!='m' ) {DateSplit = dateFmt.toString().charAt(4);}
    if( dateFmt.replace(DateSplit, "").replace(DateSplit, "").replace(DateSplit, "").substr(8).length < 3){
        isShowTime = "display:none";//不显示时间
    }
    if( dateFmt.replace(DateSplit, "").replace(DateSplit, "").replace(DateSplit, "").substr(8).length > 4){
        var time = dateFmt.replace(DateSplit, "").replace(DateSplit, "").replace(DateSplit, "").substr(8);
        DateTimeSplit = time.charAt(time.toString().toLowerCase().indexOf('h')-1);
        TimeSplit = time.charAt(time.toString().toLowerCase().lastIndexOf('h')+1);
        isShowTime = "";
    }
}
function setDate(Obj,dateFmt){   //*****!主调函数!******//
    if(dateFmt==null) { isShowTime = "display:none"; }
    if(dateFmt!=null) { setDateFomat(dateFmt); }
    inputObj = Obj;
    var DateValue = new Date();
    var calenderObj = document.createElement("span");
    if(inputObj.value.length>1){	calenderObj.innerHTML = HS_calender(inputObj.value);}
    else{	calenderObj.innerHTML = HS_calender(DateValue);}
    calenderObj.style.position = "absolute";
    calenderObj.targetObj = inputObj;
    inputObj.parentNode.insertBefore(calenderObj,inputObj);//inputObj.nextSibling
}
function closeCalender(d){
    var boxObj = d.parentNode.parentNode.parentNode;
    boxObj.parentNode.removeChild(boxObj);
}
function clearCalender(d){
    inputObj.value="";
    var boxObj = d.parentNode.parentNode.parentNode;
    boxObj.parentNode.removeChild(boxObj);
}

function getToday(){	//返回今天日期
    var todayObj = new Date();
    var todayString = ""+todayObj.getFullYear();
    if((parseInt(todayObj.getMonth())+1).toString().length=="1"){ todayString += DateSplit+"0"+(parseInt(todayObj.getMonth())+1);}
    if((parseInt(todayObj.getMonth())+1).toString().length=="2"){ todayString += DateSplit+(parseInt(todayObj.getMonth())+1);}
    if( todayObj.getDate().toString().length=="1"){ todayString += DateSplit+"0"+todayObj.getDate();}
    if( todayObj.getDate().toString().length=="2"){ todayString += DateSplit+todayObj.getDate();}
    return todayString;
}

function selectThisDay(d){
    var _h = d.parentNode.parentNode.parentNode.parentNode.getElementsByTagName('div')[0].getElementsByTagName('span')[4].getElementsByTagName('a')[0].innerHTML;
    var _m = d.parentNode.parentNode.parentNode.parentNode.getElementsByTagName('div')[0].getElementsByTagName('span')[6].getElementsByTagName('a')[0].innerHTML;
    var _time = "";
    if(isShowTime != "display:none"){
        if(_h.toString().length<2) { _h="0"+_h;}
        if(_m.toString().length<2) { _m="0"+_m;}
        _time += DateTimeSplit + _h + TimeSplit + _m;
    }
    var boxObj = d.parentNode.parentNode.parentNode.parentNode.parentNode;
    boxObj.targetObj.value = d.title+_time;
    boxObj.parentNode.removeChild(boxObj);
}

function showSelectYear(obj){
    var spanDateTag = obj.parentNode.parentNode.parentNode.getElementsByTagName('span');
    spanDateTag[0].style.display = "none";
    spanDateTag[1].style.display = "";
    spanDateTag[2].style.display = "";
    spanDateTag[3].style.display = "none";
    if(isShowTime == "display:none"){
        spanDateTag[4].style.display = "none";
        spanDateTag[5].style.display = "none";
        spanDateTag[6].style.display = "none";
        spanDateTag[7].style.display = "none";
    }else{
        spanDateTag[4].style.display = "";
        spanDateTag[5].style.display = "none";
        spanDateTag[6].style.display = "";
        spanDateTag[7].style.display = "none";
    }
}
function showSelectMonth(obj){
    var spanDateTag = obj.parentNode.parentNode.parentNode.getElementsByTagName('span');
    spanDateTag[0].style.display = "";
    spanDateTag[1].style.display = "none";
    spanDateTag[2].style.display = "none";
    spanDateTag[3].style.display = "";;
    if(isShowTime == "display:none"){
        spanDateTag[4].style.display = "none";
        spanDateTag[5].style.display = "none";
        spanDateTag[6].style.display = "none";
        spanDateTag[7].style.display = "none";
    }else{
        spanDateTag[4].style.display = "";
        spanDateTag[5].style.display = "none";
        spanDateTag[6].style.display = "";
        spanDateTag[7].style.display = "none";
    }
}
function showSelectHour(obj){
    var spanDateTag = obj.parentNode.parentNode.parentNode.getElementsByTagName('span');
    spanDateTag[0].style.display = "";
    spanDateTag[1].style.display = "none";
    spanDateTag[2].style.display = "";
    spanDateTag[3].style.display = "none";;
    if(isShowTime == "display:none"){
        spanDateTag[4].style.display = "none";
        spanDateTag[5].style.display = "none";
        spanDateTag[6].style.display = "none";
        spanDateTag[7].style.display = "none";
    }else{
        spanDateTag[4].style.display = "none";
        spanDateTag[5].style.display = "";
        spanDateTag[6].style.display = "";
        spanDateTag[7].style.display = "none";
    }
}
function showSelectMinute(obj){
    var spanDateTag = obj.parentNode.parentNode.parentNode.getElementsByTagName('span');
    spanDateTag[0].style.display = "";
    spanDateTag[1].style.display = "none";
    spanDateTag[2].style.display = "";
    spanDateTag[3].style.display = "none";;
    if(isShowTime == "display:none"){
        spanDateTag[4].style.display = "none";
        spanDateTag[5].style.display = "none";
        spanDateTag[6].style.display = "none";
        spanDateTag[7].style.display = "none";
    }else{
        spanDateTag[4].style.display = "";
        spanDateTag[5].style.display = "none";
        spanDateTag[6].style.display = "none";
        spanDateTag[7].style.display = "";
    }
}
function selectHour(obj){
    var spanDateTag = obj.parentNode.parentNode.parentNode.getElementsByTagName('span');
    spanDateTag[4].getElementsByTagName("a")[0].innerHTML = obj.value;
    spanDateTag[4].style.display = "";
    spanDateTag[5].style.display = "none";
}
function selectMinute(obj){
    var spanDateTag = obj.parentNode.parentNode.parentNode.getElementsByTagName('span');
    spanDateTag[6].getElementsByTagName("a")[0].innerHTML = obj.value;
    spanDateTag[6].style.display = "";
    spanDateTag[7].style.display = "none";
}

