$(function () {
    $('#myTab li:eq(1) a').tab('show');
    table();
    dataTimeRange();
    dataTimeRange1();
    dataTimeRange2();
})

function table() {
    $("#table").bootstrapTable({// 对应table标签的id
        method: 'post',
        url: "/index/selectPersonalProblemByCondition", // 获取表格数据的url
        contentType: "application/x-www-form-urlencoded",//一种编码。好像在post请求的时候需要用到。这里用的get请求，注释掉这句话也能拿到数据
        dataField: "data",//这是返回的json数组的key.默认是"rows".这里只有前后端约定好就行
        cache: false, // 设置为 false 禁用 AJAX 数据缓存， 默认为true
        striped: true,  //表格显示条纹，默认为false
        pagination: true, // 在表格底部显示分页组件，默认false
        pageList: [10, 20, 50, 100, 200, 500], // 如果设置了分页，设置可供选择的页面数据条数。设置为All 则显示所有记录。
        pageSize: 10, // 页面数据条数
        pageNumber: 1, // 初始化加载第一页，默认第一页
        sidePagination: 'server', // 设置为服务器端分页     客户端：client
        search: false,
        toolbar: '#toolbar',//指定工具栏
        searchOnEnterKey: true, //设置为 true时，按回车触发搜索方法，否则自动触发搜索方法
        undefinedText: '---', //当数据为 undefined 时显示的字符
        singleSelect: false,//设置True 将禁止多选
        clickToSelect: true,//设置true 将在点击行时，自动选择rediobox 和 checkbox
        height: 700,     //定义表格的高度。
        searchTimeOut: 500,// 默认500  设置搜索超时时间。
        toolbarAlign: 'right',// 指定 toolbar 水平方向的位置。'left' 或 'right'
        paginationDetailHAlign: 'left',//指定 分页详细信息 在水平方向的位置。'left' 或 'right'。
        showHeader: true,//是否显示列头。
        trimOnSearch: true,//设置为 true 将自动去掉搜索字符的前后空格。
        rowStyle: function (row, index) {
            var style = {
                // css: { 'height': '10px', 'font-size': 'small', 'classes':'danger'}
            };

            return style;
        },
        queryParams: function (params) { // 请求服务器数据时发送的参数，可以在这里添加额外的查询参数，返回false则终止请求
            return {
                pageSize: params.limit, // 每页要显示的数据条数
                pageNum: params.offset / params.limit + 1, // 每页显示数据的开始行号
                sort: params.sort, // 要排序的字段
                sortOrder: params.order, // 排序规则
                search: params.search, //搜索框内容
                beginTime: $("#date").val(),
                endTime: $("#date1").val(),
                name: $("#name").val(),
            }
        },
        sortName: 'id', // 要排序的字段
        sortOrder: 'desc', // 排序规则
        columns: [
            {
                field: 'id', // 要显示数据的字段名称，可以理解为json对象里的key
                title: '序号', // 表格表头显示文字
                align: 'center', // 左右居中
                valign: 'middle', // 上下居中
                visible: true
            }, {
                field: 'name',
                title: '名称',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'fixedNumber',
                title: '固编',
                align: 'center',      //设置单元格数据的左右对齐方式， 可选择的值有：’left’, ‘right’, ‘center’
                valign: 'middle'   //设置单元格数据的上下对齐方式， 可选择的值有：’top’, ‘middle’, ‘bottom’
            }, {
                field: 'subordinateTeam',
                title: "所属班组",
                align: 'center',
                valign: 'middle',
            }, {
                field: 'checkingContent', // 要显示数据的字段名称，可以理解为json对象里的key
                title: '检查内容', // 表格表头显示文字
                align: 'center', // 左右居中
                valign: 'middle', // 上下居中
                visible: true
            }, {
                field: 'date', // 要显示数据的字段名称，可以理解为json对象里的key
                title: '日期', // 表格表头显示文字
                align: 'center', // 左右居中
                valign: 'middle', // 上下居中
                visible: true
            }, {
                field: 'trainNumber', // 要显示数据的字段名称，可以理解为json对象里的key
                title: '车次', // 表格表头显示文字
                align: 'center', // 左右居中
                valign: 'middle', // 上下居中
                visible: true
            }, {
                field: 'locomotive', // 要显示数据的字段名称，可以理解为json对象里的key
                title: '机车', // 表格表头显示文字
                align: 'center', // 左右居中
                valign: 'middle', // 上下居中
                visible: true
            }, {
                field: 'existingProblems', // 要显示数据的字段名称，可以理解为json对象里的key
                title: '存在问题', // 表格表头显示文字
                align: 'center', // 左右居中
                valign: 'middle', // 上下居中
                visible: true
            }, {
                field: 'measures', // 要显示数据的字段名称，可以理解为json对象里的key
                title: '帮教措施', // 表格表头显示文字
                align: 'center', // 左右居中
                valign: 'middle', // 上下居中
                visible: true
            }, {
                field: 'remake', // 要显示数据的字段名称，可以理解为json对象里的key
                title: '备注', // 表格表头显示文字
                align: 'center', // 左右居中
                valign: 'middle', // 上下居中
                visible: true
            }
        ],
        onLoadSuccess: function (result) {  //加载成功时执行
            console.info("加载成功");
        },
        onLoadError: function () {  //加载失败时执行
            console.info("加载数据失败");
        }

    })
}

//查询
$('#submit').click(function () {
    // table();
    //查询之后重新从第一页算起
    $('#table').bootstrapTable('refreshOptions', {pageNumber: 1, pageSize: 10});
    //刷新
    $('#isDisplay').css("display","");
    $('#personProblem').css("display","none");
    //  $('#table').bootstrapTable('refresh');
});

//查询
$('#submit1').click(function () {
    $.ajax({
        type: "POST",
        url: "/index/selectNotice",
        data: {beginTime:$("#date2").val(), endTime:$("#date3").val(),notice:$("#notice").val()},
        dataType: "json",
        success: function(data){
            if(data.code == 200){
                $('#resText').empty();   //清空resText里面的所有内容
                var html = '';
                for(var i=0;i<data.data.length;i++){
                    html +='<li style="display: inline-block"> <span style="font-family:Microsoft YaHei;font-weight:bold">'+data.data[i].date+'</span> '+"："+data.data[i].notice+'</li>';
                }
                $('#study').css("display","none");
                $('#vocationalStudy').html(html);
            }
        }
    });
});
//日期选择
function dataTimeRange() {//日期范围
    $('#start').datetimepicker({
        format: 'YYYY-MM-DD',
        locale: moment.locale('zh-cn')
    }).on('changeDate', function (e) {
        var startTime = e.date;
        $('#end').datepicker('setStartDate', startTime);
    });

    //结束时间
    $('#end').datetimepicker({
        format: 'YYYY-MM-DD',
        locale: moment.locale('zh-cn')
    }).on('changeDate', function (e) {
        var endTime = e.date;
        $('#start').datepicker('setEndDate', endTime);
    });
}

//日期选择
function dataTimeRange1() {//日期范围
    $('#start1').datetimepicker({
        format: 'YYYY-MM-DD',
        locale: moment.locale('zh-cn')
    }).on('changeDate', function (e) {
        var startTime = e.date;
        $('#end1').datepicker('setStartDate', startTime);
    });

    //结束时间
    $('#end1').datetimepicker({
        format: 'YYYY-MM-DD',
        locale: moment.locale('zh-cn')
    }).on('changeDate', function (e) {
        var endTime = e.date;
        $('#start1').datepicker('setEndDate', endTime);
    });
}

//日期选择
function dataTimeRange2() {//日期范围
    $('#start2').datetimepicker({
        format: 'YYYY-MM-DD',
        locale: moment.locale('zh-cn')
    }).on('changeDate', function (e) {
        var startTime = e.date;
        $('#end2').datepicker('setStartDate', startTime);
    });

    //结束时间
    $('#end2').datetimepicker({
        format: 'YYYY-MM-DD',
        locale: moment.locale('zh-cn')
    }).on('changeDate', function (e) {
        var endTime = e.date;
        $('#start2').datepicker('setEndDate', endTime);
    });
}

//初始化Excel导入的文件  个人问题导入
initUpload("excelFile", "/index/importPersonalProblem");
function initUpload(ctrlName, uploadUrl) {
    var control = $("#" + ctrlName);
    control.fileinput({
        language: "zh",//设置语言
        uploadUrl: uploadUrl,//上传的地址
        uploadAsync: true,//默认异步上传
        showCaption: true,//是否显示标题
        showUpload: true,//是否显示上传按钮
        browseClass: "btn btn-primary",//按钮样式
        allowedFileExtensions: ["xls", "xlsx"], //接收的文件后缀
        maxFileCount: 1,//最大上传文件数限制
        previewFileIcon: '<i class="glyphicon glyphcion-file"></i>',
        showPreview: true, //是否显示预览
// 									allowPreviewTypes:null,//是否显示预览
        previewFileIconSettings: {
            'docx': '<i class="glyphicon glyphcion-file"></i>',
            'xlsx': '<i class="glyphicon glyphcion-file"></i>',
            'pptx': '<i class="glyphicon glyphcion-file"></i>',
            'jpg': '<i class="glyphicon glyphcion-picture"></i>',
            'pdf': '<i class="glyphicon glyphcion-file"></i>',
            'zip': '<i class="glyphicon glyphcion-file"></i>',
        },
        uploadExtraData: function () {
            var extraValue = "test";
            return {"excelType": extraValue};
        }
    });
}
$("#excelFile").on("fileuploaded", function (exevt, data, previewId, index) {
    if (data.response.code == 200) {
        alert("导入成功！");
        $("#excelFile").fileinput("clear");
        $("#excelFile").fileinput("reset");
        $("#excelFile").fileinput("refresh");
        $("#excelFile").fileinput("enable");
        $(".close").click();
        $("#reload").click();
    } else {
        alert("导入失败:" + data.response.message);
        $("#excelFile").fileinput("clear");
        $("#excelFile").fileinput("reset");
        $("#excelFile").fileinput("refresh");
        $("#excelFile").fileinput("enable");
    }
});



//初始化Excel导入的文件  通知导入
initUpload("excelFile1", "/index/importNotice");
function initUpload(ctrlName, uploadUrl) {
    var control = $("#" + ctrlName);
    control.fileinput({
        language: "zh",//设置语言
        uploadUrl: uploadUrl,//上传的地址
        uploadAsync: true,//默认异步上传
        showCaption: true,//是否显示标题
        showUpload: true,//是否显示上传按钮
        browseClass: "btn btn-primary",//按钮样式
        allowedFileExtensions: ["xls", "xlsx"], //接收的文件后缀
        maxFileCount: 1,//最大上传文件数限制
        previewFileIcon: '<i class="glyphicon glyphcion-file"></i>',
        showPreview: true, //是否显示预览
// 									allowPreviewTypes:null,//是否显示预览
        previewFileIconSettings: {
            'docx': '<i class="glyphicon glyphcion-file"></i>',
            'xlsx': '<i class="glyphicon glyphcion-file"></i>',
            'pptx': '<i class="glyphicon glyphcion-file"></i>',
            'jpg': '<i class="glyphicon glyphcion-picture"></i>',
            'pdf': '<i class="glyphicon glyphcion-file"></i>',
            'zip': '<i class="glyphicon glyphcion-file"></i>',
        },
        uploadExtraData: function () {
            var extraValue = "test";
            return {"excelType": extraValue};
        }
    });
}
$("#excelFile1").on("fileuploaded", function (exevt, data, previewId, index) {
    if (data.response.code == 200) {
        alert("导入成功！");
        $("#excelFile1").fileinput("clear");
        $("#excelFile1").fileinput("reset");
        $("#excelFile1").fileinput("refresh");
        $("#excelFile1").fileinput("enable");
        $(".close").click();
        $("#reload").click();
    } else {
        alert("导入失败:" + data.response.message);
        $("#excelFile1").fileinput("clear");
        $("#excelFile1").fileinput("reset");
        $("#excelFile1").fileinput("refresh");
        $("#excelFile1").fileinput("enable");
    }
});

//初始化Excel导入的文件  通知导入
initUpload("excelFile2", "/index/importNotice");
function initUpload(ctrlName, uploadUrl) {
    var control = $("#" + ctrlName);
    control.fileinput({
        language: "zh",//设置语言
        uploadUrl: uploadUrl,//上传的地址
        uploadAsync: true,//默认异步上传
        showCaption: true,//是否显示标题
        showUpload: true,//是否显示上传按钮
        browseClass: "btn btn-primary",//按钮样式
        allowedFileExtensions: ["xls", "xlsx"], //接收的文件后缀
        maxFileCount: 1,//最大上传文件数限制
        previewFileIcon: '<i class="glyphicon glyphcion-file"></i>',
        showPreview: true, //是否显示预览
// 									allowPreviewTypes:null,//是否显示预览
        previewFileIconSettings: {
            'docx': '<i class="glyphicon glyphcion-file"></i>',
            'xlsx': '<i class="glyphicon glyphcion-file"></i>',
            'pptx': '<i class="glyphicon glyphcion-file"></i>',
            'jpg': '<i class="glyphicon glyphcion-picture"></i>',
            'pdf': '<i class="glyphicon glyphcion-file"></i>',
            'zip': '<i class="glyphicon glyphcion-file"></i>',
        },
        uploadExtraData: function () {
            var extraValue = "test";
            return {"excelType": extraValue};
        }
    });
}
$("#excelFile2").on("fileuploaded", function (exevt, data, previewId, index) {
    if (data.response.code == 200) {
        alert("导入成功！");
        $("#excelFile2").fileinput("clear");
        $("#excelFile2").fileinput("reset");
        $("#excelFile2").fileinput("refresh");
        $("#excelFile2").fileinput("enable");
        $(".close").click();
        $("#reload").click();
    } else {
        alert("导入失败:" + data.response.message);
        $("#excelFile2").fileinput("clear");
        $("#excelFile2").fileinput("reset");
        $("#excelFile2").fileinput("refresh");
        $("#excelFile2").fileinput("enable");
    }
});