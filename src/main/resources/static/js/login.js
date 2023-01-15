
let loginBtn = document.getElementById('loginBtn')
loginBtn.addEventListener("click", () =>{
    requestLogin();
})

function requestLogin(){
    let userEmail = document.getElementById('userEmail');
    let userPw = document.getElementById('userPw');
    console.log(userEmail.value + "//" + userPw.value);

    if(!userEmail.value || !userPw.value){
        alert("아이디 혹은 비밀번호를 확인해주세요")
        return false
    }

    $.ajax({
        url:"/api/member/login"
        ,data:{
            userEmail : userEmail.value
            ,userPw : userPw.value
        }
        ,type:'post'
        ,success:function (response){
            //setCookie("Authorization",token);
            console.log(response)
            if(response.message == "ok"){
                location.replace("/payhere/")
            }else{
                alert("아이디 혹은 비밀번호를 확인해주세요")
            }

        }
    })
}

var setCookie = function(name, value) {
    document.cookie = name + '=' + value + ';';
};