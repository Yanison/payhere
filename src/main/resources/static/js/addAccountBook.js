let addAccountBookBtn = document.getElementById('addAccountBookBtn');
addAccountBookBtn.addEventListener("click", addAccountBook)

/**
 * 가계부 내역 추가
 */
function addAccountBook(){
    let htmlStr ="";

    $.ajax({
        url:'/api/accountbook/create'
        ,data:{userSeq : userSeq.value}
        ,type:'post'
        ,success:function (seq){
            console.log("create abRow :: abSqe_" + seq)
            htmlStr +=
                `
                <input class="abSeq" type="hidden" name="abRowSeq" value="`+seq+`">
                <input class="abSeq"type="hidden" value="`+seq+`">
                <td><input class="abChk" type="checkbox"></td>
                <td class="type">
                    <input class="abInp" type="text" id="type" name="type" placeholder="내역" autocomplete="off">
                </td>
                <td class="price"><input class="abInp" type="text" id="price" name="price" placeholder="금액" ><i class="fa-solid fa-won-sign"></i></td>
                <td class="contents"><input class="abInp" type="text" id="content" name="content" placeholder="메모" ></td>
                <td class="timestamp">방금전</td>
                <td name="deleteAccountBookBtn" ><button id="deleteAccountBookBtn" class="deleteAccountBookBtn" name="deleteAccountBookBtn"  value="`+seq+`"><i class="fa-solid fa-x" style="color: red"></i></button></td>
            `

            let tr = document.createElement('tr');
            tbody.querySelector('tbody').prepend(tr);
            let firstTr = tbody.querySelector('tbody').children[0];
            firstTr.insertAdjacentHTML('afterbegin',htmlStr);
        }
    })
}