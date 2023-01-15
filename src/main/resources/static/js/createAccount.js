let reValiPw = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,}$/;
let reValiNickName =  /^[\w\Wㄱ-ㅎㅏ-ㅣ가-힣]{2,20}$/;

let ccreatAccountForm = document.getElementById('ccreatAccountForm');
let creatAccountBtn = document.getElementById('creatAccountBtn');
let reqeustEmailValidationBtn = document.getElementById('reqeustEmailValidationBtn');
reqeustEmailValidationBtn.addEventListener("click", reqeustEmailValidation)
creatAccountBtn.addEventListener("click", validation)

let emailId = document.getElementById('userEmailId');
let email = document.getElementById('userEmail');
let emailAddress = document.getElementById('emailAddress');
let pw = document.getElementById('userPw');
let name = document.getElementById('userName');
let validationCode = document.getElementById('emailValidationCode');
let validationCodeHidden = document.getElementById('emailValidationCodeHidden');
let icon = document.getElementById('checkIcon');





function validation(){
    let userEmailId = emailId.value;
    let userEmailAddress = emailAddress.value;
    let userPw = pw.value;
    let userName = name.value;
    let emailValidationCode = validationCode.value;
    let emailValidationCodeHidden =validationCodeHidden.value
    email.value = userEmailId + "@" +userEmailAddress

    console.log(userEmailId);
    console.log(userEmailAddress);
    console.log(userPw);
    console.log(userName);
    console.log(emailValidationCode);
    console.log(emailValidationCodeHidden);
    console.log(email.value)


    if(userEmail ==""|| userPw ==""|| userName ==""|| emailValidationCode ==""|| emailValidationCodeHidden ==""){
        alert("빈칸없이 입력해주세요")
        return false;
    }

    if(emailValidationCode != emailValidationCodeHidden){
        alert("인증번호가 일치하지 않습니다.")
        icon.classList.add('unChecked')
        return false;
    }else{

        icon.classList.add('checked')
    }
    if(!reTest(reValiPw,userPw)){
        alert("8자리 이상의 영문,숫자,특수문자 조합의 비밀번호를 입력해주세요")
        return false;
    }

    if(!reTest(reValiNickName,userName)){
        alert("2자 이상의 이름을 입력해주세요")
        return false;
    }
    ccreatAccountForm.submit();
}

function reqeustEmailValidation(){
    let userEmailId = emailId.value;
    let userEmailAddress = emailAddress.value;

    if(emailAddress.value == "address" || emailId.value ==""){
        alert("이메일을 입력해주세요")
        return false;
    }
    console.log(emailId.value +"@"+ emailAddress.value);
    $.ajax({
        url:'/api/member/reqeustEmailValidation'
        ,data:{userEmail:emailId.value +"@"+emailAddress.value}
        ,type:'post'
        ,success:function (rp){
            if(rp.response == "duplicatedEmail"){
                alert("이미 가입한 이메일입니다.")
            }else{
                validationCodeHidden.value = rp.response
                console.log("rp.value :: "+rp.response)
                console.log("validationCodeHidden.value :: "+validationCodeHidden.value)
            }
        }
    })

}



function reTest(re,what){
    if (re.test(what)) {
        return true;
    }
    what!=""
}