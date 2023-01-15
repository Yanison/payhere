let userSeq = document.getElementById('userSeq');
let tbody = document.getElementById('tbody');

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
tbody.addEventListener("click", async (e)=>{
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
            deleteType(thisNod)
        }else if(thisNodName === "typeName"){
            let typInp = thisNod.parentNode.parentNode.querySelector('input[name="type"]');
            typInp.value = thisNod.getAttribute('value');
            insertType(typInp);
        }else if(thisNodName == "abCheckAllBtn"){
            console.log("chkEvent")
            checkAll();
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
        // console.log("nod_attr_Name :: "+thisNodName + " // nod_value ::" + thisNod.value)
    }
    e.stopPropagation()
})

/**
 * 가계부 테이블에 등록된 가계부 focusout 이벤트
 * -내역 input에서 focusout되면 불러온 내역타입 container nod 지우기
 */
tbody.addEventListener("focusout", async (e)=>{
    if(e.target !== e.currentTarget){
        let shKey = e.target.name;
        let shValue = e.target.value;
        let abSeq = e.target.parentNode.parentNode.querySelector('button').value;
        let thisNod = e.target;
        if(shKey == "type"){
            insertType(thisNod)

            let divs =await thisNod.parentNode.querySelectorAll('div')

            for(let i = 0 ; i < divs.length;i++){
                setTimeout(()=>{
                    divs[i].remove()
                },"300")
            }
        }
    }
    e.stopPropagation()
})

/**
 * 가계부 row 수정
 */
function updateAccountBook(node){
    let shKey = node.name
    let shValue = node.value
    let apiUrl ='/api/accountbook/update';
    let userSeq = document.getElementById('userSeq').value;
    let abSeq = node.parentNode.parentNode.querySelector('button').value;

    if(shKey =="type"){
        apiUrl = '/api/accountbook/insertType'
    }
    console.log(
        "userSeq :: "+userSeq+
        "abSeq :: "+abSeq+
        "shKey :: "+shKey+
        ", shValue ::"+shValue+
        ", userSeq :: " + userSeq);

    $.ajax({
        url: '/api/accountbook/update'
        ,data:{
            shKey : shKey
            ,shValue : shValue
            ,abSeq : abSeq
        }
        ,type:'post'
        ,success:function (rp){
            console.log(rp);
        }
    })
}

function insertType(node){
    console.log("focusOut insertType")
    let shKey = node.name
    let typeName = node.value
    let apiUrl ='/api/accountbook/update';
    let userSeq = document.getElementById('userSeq').value;
    let abSeq = node.parentNode.parentNode.querySelector('button').value;

    console.log(
        "userSeq :: "+userSeq+
        "abSeq :: "+abSeq+
        "shKey :: "+shKey+
        ", typeName ::"+typeName+
        ", userSeq :: " + userSeq);

    if(typeName == ''){return false}

    $.ajax({
        url: '/api/accountbook/insertType'
        ,data:{
            typeName : typeName
            ,userSeq : userSeq
            ,abSeq : abSeq
        }
        ,type:'post'
        ,success:function (rp){
            console.log(rp);
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

                nod.parentNode.querySelector('div').innerHTML+=span;
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


/**
 * 가계부 row 삭제 or 복구
 */
let endPoint = document.getElementById('thead').querySelector('tr td:last-child').getAttribute('name');
let delNyValue = document.getElementById('thead').querySelector('tr td:last-child').getAttribute('value');;
function deleteAccountBook(nod){
    let arr = []
    let abSeq = nod.value;
    arr.push(abSeq);
    console.log("endPoint :: "+ endPoint);
    $.ajax({
        url:'/api/accountbook/'+endPoint
        ,data:{
            checkList : JSON.stringify(arr)
        }
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

const checkAll = (target) => {
    const isChecked = target.checked;
    document.querySelectorAll('input[name="abRowCheckBox"]')
        .forEach(el => {
            el.checked = isChecked;
        });
}

let delOrRestoreBtn= document.querySelector('button.delOrRestoreBtn');
delOrRestoreBtn.addEventListener("click", deleteOrRestore);

function deleteOrRestore(){
    let arr =[];
    let howManyAreThey = 0;
    let checkList = document.querySelectorAll('input[name="abRowCheckBox"]')
    for(let i =0 ; i < checkList.length ; i ++){
        if(checkList[i].checked == true){
            let abRowSeq = checkList[i].parentNode.parentNode.querySelector('input[name="abRowSeq"]');
            arr.push(abRowSeq.value);
            howManyAreThey ++;
        }
    }
    console.log(arr);
    $.ajax({
        url:'/api/accountbook/'+endPoint
        ,data:{
            checkList : JSON.stringify(arr)
        }
        ,type:'post'
        ,success:function (rp){
            removeRows(checkList);
        }
    })
}

let permanentlyDeleteBtn = document.getElementById('permanentlyDeleteBtn')
permanentlyDeleteBtn.addEventListener('click',permanentlyDelete)
function permanentlyDelete(){
    let arr =[];
    let howManyAreThey = 0;
    let checkList = document.querySelectorAll('input[name="abRowCheckBox"]')
    for(let i =0 ; i < checkList.length ; i ++){
        if(checkList[i].checked == true){
            let abRowSeq = checkList[i].parentNode.parentNode.querySelector('input[name="abRowSeq"]');
            arr.push(abRowSeq.value);
            howManyAreThey ++;
        }
    }
    console.log(arr);
    $.ajax({
        url:'/api/accountbook/permanentlyDelete'
        ,data:{
            checkList : JSON.stringify(arr)
        }
        ,type:'post'
        ,success:function (rp){
            removeRows(checkList);
        }
    })
}
function localString(e){
    let value = e.replace(/[^0-9]/g, "");

    let fillted = value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    console.log("fillted :: "+fillted);
    return fillted;
}


function removeRows(nodeList){
    for(let i =0 ; i < nodeList.length ; i ++){
        nodeList[i].remove()
    }
}


