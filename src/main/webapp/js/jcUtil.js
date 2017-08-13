/**
 * 存放基本工具类
 */
/**结合artDialog整合的工具Alert
 * lk:是否开启锁屏
 * zzdb:双击遮罩是否自动关闭
 * cel:是否显示右上角关闭按钮
 * ct:内容
 * --注：默认三秒后关闭
 */
function dAlert(lk,zzdb,cel,ct){
	if(cel==true)cel = null;
	var diaLog = $.dialog({
		id:'smgdia',
		title:'提示',
		lock:lk,
		dblclick_hide:zzdb,
		cancel:cel,
		content:ct
	}).title('提示:3秒后关闭...').time(3);
	return diaLog;
}
/**结合artDialog整合的工具Alert,带有确定和取消按钮
 * lk:是否开启锁屏
 * zzdb:双击遮罩是否自动关闭
 * ct:内容
 */
function okAndCancelAlert(lk,zzdb,ct){
	var diaLog = $.dialog({
		id:'smg2dia',
		title:'提示',
		lock:lk,
		dblclick_hide:zzdb,
		content:ct,
		ok: function () {
	        return false;
	    },
	    cancelVal: '取消',
	    cancel: true //为true等价于function(){}
	}).show();
	return diaLog;
}