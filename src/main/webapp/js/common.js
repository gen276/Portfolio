/**
 * ボタン押下時のポップアップ表示
 */
 
 /**
 * 年月日の取得と表示画面への値の受け渡し
 */
 
    function LoadProc() {
	  var now = new Date();
      var target = document.getElementById("DateTimeDisp");

      var Month = now.getMonth()+1;

      target.innerHTML = Month + "月";
    }
    
 /**登録,ログイン画面
 * ユーザー名,ID,パスワード未入力時のポップアップ表示
 */
    function Check() {
	if(document.form.name.value==""){
	alert("ユーザー名を入力してください。");
	return false;
	}
	if(document.form.id.value==""){
	alert("IDを入力してください。");
	return false;
	}
	if(document.form.pass.value==""){
	alert("パスワードを入力してください。");
	return false;
	}
	return true;
    }