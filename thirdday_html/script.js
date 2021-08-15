function over(){
    document.getElementById("img").src="image/1.jpg";
}
function out(){
    document.getElementById("img").src="image/5.jpg";
}
function askopt(msg){
    var dap = "";
    if(confirm(msg)){ //true==>확인
        dap = "찬성합니다"
    }else{ //false-->취소
        dap="반대합니다"
    }
    return dap;
}