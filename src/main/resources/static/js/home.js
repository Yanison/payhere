let logOutBtn = document.getElementById('logOutBtn');
logOutBtn.addEventListener("click", ()=>{
    $.ajax({
        url:"/api/member/logOut"
        ,type:'post'
        ,success:function (){
            location.replace("/payhere/")
        }
    })
})