let userSeq = document.getElementById('userSeq')
let tbody = document.getElementById('tbody');

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
/**
 * 가계부 테이블에 등록된 가계부 keyup 이벤트
 * - 가계부 row 업데이트 updateAccountBook(키값,밸류값,가계부Index)
 * - 내역타입 리스트 불러오기 selectTypeList(노드객체)
 */
tbody.addEventListener("keyup", (e)=>{
    if(e.target !== e.currentTarget){
        let shKey = e.target.name;
        let shValue = e.target.value;
        let abSeq = e.target.parentNode.parentNode.querySelector('button').value;
        let thisNod = e.target;
        console.log(shKey)
        if(shKey =="price" || shKey =="contents"){
            if(shKey =="price"){
                thisNod.value = localString(shValue)
            }
            updateAccountBook(thisNod)
        }else if(shKey == "type"){
            selectTypeList(thisNod)
        }
    }
    e.stopPropagation()
})

/**
 * 가계부 테이블에 등록된 가계부 click 이벤트
 * - 내역타입 리스트 불러오기 selectTypeList(노드객체)
 * - 가계부 row 삭제하기 deleteAccountBook(노드객체)
 */
tbody.addEventListener("click", (e)=>{
    if(e.target !== e.currentTarget){

        let shKey = e.target.name;
        let shValue = e.target.value;
        let thisNod = e.target;
        let thisNodName = thisNod.getAttribute('name');
        if(shKey == "type"){
            selectTypeList(thisNod)
        }else if(shKey =="deleteAccountBookBtn"){
            console.log(thisNod)
            deleteAccountBook(thisNod)
        }else if(thisNodName === "deleteType"){
            console.log("del_Type")
            deleteType(thisNod)
        }else if(thisNodName === "typeName"){
            let typeIno = thisNod.parentNode.parentNode.querySelector('input[name="type"]');
            typeIno.value = thisNod.getAttribute('value');
        }
    }
    e.stopPropagation()
})

/**
 * 가계부 테이블에 등록된 가계부 mouseover 이벤트
 * - 불러온 내역타입 리스트중 선택한 값 할당하기
 */
tbody.addEventListener("mouseover", (e)=>{
    if(e.target !== e.currentTarget){
        let thisNod = e.target;
        let thisNodName = thisNod.getAttribute('name');
        console.log("nod_attr_Name"+thisNodName + " // nod_value " + thisNod.value)
    }
    e.stopPropagation()
})

/**
 * 가계부 테이블에 등록된 가계부 focusout 이벤트
 * -내역 input에서 focusout되면 불러온 내역타입 container nod 지우기
 */
tbody.addEventListener("focusout", (e)=>{
    if(e.target !== e.currentTarget){
        let shKey = e.target.name;
        let shValue = e.target.value;
        let abSeq = e.target.parentNode.parentNode.querySelector('button').value;
        let thisNod = e.target;
        if(shKey == "type"){
            updateAccountBook(thisNod)

            let divs = thisNod.parentNode.querySelectorAll('div')
            for(let i = 0 ; i < divs.length;i++){
                setTimeout(()=>{
                    divs[i].remove()
                },"100")
            }
        }
    }
    e.stopPropagation()
})

/**
 * 가계부 row 수정
 */
function updateAccountBook(node){
    let seq;
    let shKey = node.name
    let shValue = node.value
    let apiUrl ='/api/accountbook/update';

    seq = node.parentNode.parentNode.querySelector('button').value;

    if(shKey =="type"){
        seq = userSeq.value;
        apiUrl = '/api/accountbook/insertType'
    }


    console.log(seq);

    console.log("shKey :: "+shKey+", shValue ::"+shValue+", seq :: " + seq);

    if(shKey == "price"){
        console.log(shKey)
        localString(shValue)
    }

    $.ajax({
        url: apiUrl
        ,data:{
            shKey : shKey
            ,shValue : shValue
            ,abSeq : seq
        }
        ,type:'post'
        ,success:function (rp){
            console.log(rp);
        }
    })
}

/**
 * 가계부 row 삭제
 */
function deleteAccountBook(nod){
    let abSeq = nod.value;
    $.ajax({
        url:'/api/accountbook/delete'
        ,data:{ abSeq : abSeq}
        ,type:'post'
        ,success:function (rp){
            console.log(rp);
            if(rp == 1){
                let parentTr = nod.parentNode.parentNode
                parentTr.remove();
            }
        }
    })
}

/**
 * 가계부 내역타입 불러오기
 */
function selectTypeList(nod){
    let divs = nod.parentNode.querySelectorAll('div')
    for(let i = 0 ; i < divs.length;i++){
        divs[i].remove()
    }

    let div =  document.createElement('div')
    nod.parentNode.appendChild(div)
    let userSeq = document.getElementById('userSeq').value;

    $.ajax({
        url:'/api/accountbook/selectTypeList'
        ,data:{
            userSeq : userSeq
            ,shValue : nod.value
        }
        ,type:'get'
        ,success:function (rp){

            let typeList = rp.list;
            let n = typeList.length;
            console.log(typeList)
            for(let i = 0; i < n; i ++){
                let span =  `<span class="typeName" name="typeName" value="`+typeList[i].typeName+`">`+typeList[i].typeName+`<button name="deleteType" class="deleteType" value="`+typeList[i].typeSeq+`">X</button></span>`;
                console.log(span)
                nod.parentNode.querySelector('div').innerHTML=span;
            }
        }
    })
}
function deleteType(nod){
    console.log("seq :: "+ nod.value);
    $.ajax({
        url:'/api/accountbook/deleteType'
        ,data:{ typeSeq : nod.value}
        ,type:'post'
        ,success:function (rp){
            console.log(rp);
            if(rp == 1){
                nod.parentNode.querySelector('button').remove();
            }
        }
    })
}
function checkAll(nodList){
}
function deleteAccountBookChekedAll(nod){
    console.log('del all')
}

function localString(e){
    let value = e.replace(/[^0-9]/g, "");

    let fillted = value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    console.log("fillted :: "+fillted);
    return fillted;
}

