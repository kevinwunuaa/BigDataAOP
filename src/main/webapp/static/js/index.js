function traverse(root) {
    var path = root.resname;
    if (root.children && root.children.length > 0) {
        for (var i = 0; i < root.children.length; i++) {
            root.children[i].resname = path + "->>" + root.children[i].resname;
            traverse(root.children[i]);
        }
    }
}

function findNode(root, id) {
    if (id == root.resid) {
        return root;
    }
    if (root.children && root.children.length > 0) {
        for (var i = 0; i < root.children.length; i++) {
            var node = findNode(root.children[i], id);
            if (node) {
                return node;
            }
        }
    }

    return null;
}

function addTab(id, name, url) {
    var root = window.appContext.user.tree.root;
    var node = findNode(root, id);
    if (node) {
        name = node.resname.replace(root.resname + "->>", "");
    }

    $(".sidebar-menu li a").removeClass("btn btn-primary");
    $("#menuitem_" + id + " a").addClass("btn btn-primary");

    var title = '<span class="label label-primary">' + name + '</span>';

    $(".content-header").html(title);

    var path = url.split(".")[0];
    var scriptPath = "/jsp/" + path + ".js?q=" + Math.random();
    var className = path.replace(new RegExp("/","gm"), ".");

    $.get(url,
    function(data) {
        $.getScript(ctxPath + scriptPath,
        function() {
            $(".content-body").empty();
            $(".content-body").html(data);
            var myClass = createClass(className, {});
            myClass.init();
        });
    });
}

function modifyPwd() { // 密码修改
    addTab('modify-pwd', '修改密码', 'modifyPwdPage.action');
}

function logout() {
    if (confirm('确认退出吗?')) {
        $.post("logout.action", {},
        function(result) {
            if (result.success) {
                window.top.location = "loginPage.action";
            } else {
                alert(result.msg);
            }
        },
        "json");
    }
}

function closeAll() {
    $(".custom-nav-tabs > li:gt(0)").remove();
    $(".custom-tab-content > .tab-pane:gt(0)").remove();

    $(".custom-nav-tabs > li:first").addClass("active");
    $(".custom-tab-content > .tab-pane:first").addClass("active");
}

function closeLeft() {
    var curtab = $(".custom-nav-tabs > li.active");

    var prevtab = $(curtab).prev("li");

    var temptab = null;

    while (prevtab) {
        if ($(prevtab).attr("id") == "tab_seed_0") {
            break;
        }

        temptab = prevtab;
        prevtab = $(temptab).prev("li");
        $(temptab).remove();
    }

    var curcontent = $(".custom-tab-content > .tab-pane.active");
    var prevcontent = $(curcontent).prev(".tab-pane");
    var tempcontent = null;

    while (prevcontent) {
        if ($(prevcontent).attr("id") == "tab_container_0") {
            break;
        }

        tempcontent = prevcontent;
        prevcontent = $(tempcontent).prev(".tab-pane");
        $(tempcontent).remove();
    }
}

function closeRight() {
    $(".custom-nav-tabs > li.active").nextAll().remove();
    $(".custom-tab-content > .tab-pane.active").nextAll().remove();
}

function switchRank() {
    $("#myModalLabel").text("切换身份");
    $('#myModal').modal();
}

function setRank(target) {
    $("#myRank").html($(target).html());
    $('#myModal').modal("hide");
}

//日历显示
function myCalendar() {
    $('#myCalendarModal').modal();
    goToday();
}

//计算器
function myCalculator() {
    $('#myCalculatorModal').modal();
}

function inputBtn(el) {
    var reg = /^\d+(\.*\d{0,2})([+*/-\\%]\d+(\.*\d{0,2}))+$/
    var expr = $("#calResult").html();
    var inStr = $(el).html();

    switch (inStr) {
    case "AC":
        $("#calResult").html("0");
        return;
    case "RT":
        if (expr.length > 1) {
            $("#calResult").html(expr.substr(0, expr.length - 1));
        } else {
            $("#calResult").html("0");
        }
        return;
    case "+/-":
        return;
    case "=":
        $("#calMessage").empty();
        if (reg.test(expr)) {
            eval("var result = " + expr);
            $("#calResult").html(result);
        } else {
            $("#calMessage").html('<span class="label label-warning">算式不符合规范，请检查！</span>');
        }
        return;
    default:
        break;
    }

    var tempResult = expr + inStr;

    if (expr == "0") {
        tempResult = inStr;
    }

    $("#calResult").html(tempResult);
}

//判断闰年
function runNian(_year) {
    if (_year % 400 === 0 || (_year % 4 === 0 && _year % 100 !== 0)) {
        return true;
    } else {
        return false;
    }
}

//判断某年某月的1号是星期几
function getFirstDay(_year, _month) {
    var allDay = 0,
    y = _year - 1,
    i = 1;
    allDay = y + Math.floor(y / 4) - Math.floor(y / 100) + Math.floor(y / 400) + 1;
    for (; i < _month; i++) {
        switch (i) {
        case 1:
            allDay += 31;
            break;
        case 2:
            if (runNian(_year)) {
                allDay += 29;
            } else {
                allDay += 28;
            }
            break;
        case 3:
            allDay += 31;
            break;
        case 4:
            allDay += 30;
            break;
        case 5:
            allDay += 31;
            break;
        case 6:
            allDay += 30;
            break;
        case 7:
            allDay += 31;
            break;
        case 8:
            allDay += 31;
            break;
        case 9:
            allDay += 30;
            break;
        case 10:
            allDay += 31;
            break;
        case 11:
            allDay += 30;
            break;
        case 12:
            allDay += 31;
            break;
        }
    }
    return allDay % 7;
}

//清除内容
function clearBody() {
    $("#myCalendar tbody").empty();
}

//显示日历
function showCalendar(_year, _month, _day, firstDay) {
    clearBody();
    var i = 0,
    monthDay = 0,
    showStr = "",
    _classname = "",
    today = new Date();
    //月份的天数
    switch (_month) {
    case 1:
        monthDay = 31;
        break;
    case 2:
        if (runNian(_year)) {
            monthDay = 29;
        } else {
            monthDay = 28;
        }
        break;
    case 3:
        monthDay = 31;
        break;
    case 4:
        monthDay = 30;
        break;
    case 5:
        monthDay = 31;
        break;
    case 6:
        monthDay = 30;
        break;
    case 7:
        monthDay = 31;
        break;
    case 8:
        monthDay = 31;
        break;
    case 9:
        monthDay = 30;
        break;
    case 10:
        monthDay = 31;
        break;
    case 11:
        monthDay = 30;
        break;
    case 12:
        monthDay = 31;
        break;
    }

    //输出日历表格，这部分因结构而异
    showStr = "<tr>";
    //当月第一天前的空格
    for (i = 1; i <= firstDay; i++) {
        showStr += "<td></td>";
    }
    //显示当前月的天数
    for (i = 1; i <= monthDay; i++) {
        //debugger;
        //当日的日期
        if (_year === today.getFullYear() && _month === today.getMonth() + 1 && i === today.getDate()) {
            _classname = "info";
        }
        //当日之前的日期（这个判断是因为我有工作需求，就是要求之前的日期不能点击）
        else if (_year < today.getFullYear() || (_year === today.getFullYear() && _month <= today.getMonth()) || (_year === today.getFullYear() && _month === today.getMonth() + 1 && i < today.getDate())) {
            _classname = "cld-old";
        }
        //其他普通的日期
        else {
            _classname = "cld-day";
        }
        //其他大于当月的月份的相同日期（为了让点击下一月的时候，相同的日期增加cld-cur类）
        if (_day === i && (_year > today.getFullYear() || _month > today.getMonth() + 1)) {
            _classname = "cld-cur";
        }
        //把日期存在对应的value       
        showStr += "<td class=" + _classname + " value='" + _year + "-" + _month + "-" + i + "'>" + i + "</td>";

        firstDay = (firstDay + 1) % 7;
        if (firstDay === 0 && i !== monthDay) {
            showStr += "</tr><tr>";
        }
    }

    //剩余的空格
    if (firstDay !== 0) {
        for (i = firstDay; i < 7; i++) {
            showStr += "<td></td>";
        }
    }

    showStr += "</tr>";
    //插入calendar的页面结构里
    $("#myCalendar tbody").html($(showStr));
}

//显示年月日
function showDate(_year, _month, _day) {
    var date = "",
    curDate = "",
    firstDay = getFirstDay(_year, _month, _day);
    if (_day !== 0) {
        date = _year + "年" + _month + "月" + _day + "日";
        curDate = _year + "-" + _month + "-" + _day;
    } else {
        date = "No Choose.";
    }
    $("#curDate").val(curDate);
    $("#showDate").html(date); //日历头部显示
    showCalendar(_year, _month, _day, firstDay); //调用日历显示函数
}

//今天
function goToday() {
    var today = new Date();
    var _year = today.getFullYear(),
    _month = today.getMonth() + 1,
    _day = today.getDate();

    showDate(_year, _month, _day)
}

//上一月
function preMonth() {
    var dateArr = $("#curDate").val().split("-");
    var _year = dateArr[0] - 0,
    _month = dateArr[1] - 0,
    _day = dateArr[2] - 0;
    if (_month == 1) {
        showDate(_year - 1, 12, _day);
    } else {
        showDate(_year, _month - 1, _day);
    }
}
//下一月
function nextMonth() {
    var dateArr = $("#curDate").val().split("-");
    var _year = dateArr[0] - 0,
    _month = dateArr[1] - 0,
    _day = dateArr[2] - 0;
    if (_month == 12) {
        showDate(_year + 1, 1, _day);
    } else {
        showDate(_year, _month + 1, _day);
    }
}