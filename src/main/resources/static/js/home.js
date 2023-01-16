
let userSeq = document.getElementById('userSeq');


let goToAccountBook = ()=>{
    if(userSeq.value =='' || userSeq.value== null){
        alert('로그인이 필요한 서비스입니다.')
        location.replace("/payhere/");
        return false;
    }

    location.replace("/payhere/accountBook");
}



let logOutBtn =()=>{
    $.ajax({
        url:"/api/member/logOut"
        ,type:'post'
        ,success:function (){
            location.replace("/payhere/")
        }
    })
}