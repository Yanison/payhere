let userSeq = document.getElementById('userSeq')
let tbody = document.getElementById('tbody');
let addAccountBookBtn = document.getElementById('addAccountBookBtn');
addAccountBookBtn.addEventListener("click", addAccountBook)
let deleteAccountBookChekedAllBtn = document.getElementById('deleteAccountBookChekedAllBtn');
deleteAccountBookChekedAllBtn.addEventListener("click", deleteAccountBookChekedAll);

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

tbody.addEventListener("keyup", (e)=>{
    if(e.target !== e.currentTarget){
        let shKey = e.target.name;
        let shValue = e.target.value;
        let abSeq = e.target.parentNode.parentNode.querySelector('button').value;
        let thisNod = e.target;

        if(shKey =="price" || shKey =="contents"){
            if(shKey =="price"){
                thisNod.value = localString(shValue)
            }
            updateAccountBook(shKey,shValue,abSeq)
        }else if(shKey == "type"){
            selectTypeList(thisNod)
        }
    }
    e.stopPropagation()
})
tbody.addEventListener("click", (e)=>{
    if(e.target !== e.currentTarget){

        let shKey = e.target.name;
        let shValue = e.target.value;
        let thisNod = e.target;

        if(shKey == "type"){
            selectTypeList(thisNod)
        }else if(shKey =="deleteAccountBookBtn"){
            console.log(thisNod)
            deleteAccountBook(thisNod)
        }else if(shKey === "typeName"){
            let findNod = thisNod.parentNode.parentNode
            console.log(findNod)
                //= thisNod.value
        }
    }
    e.stopPropagation()
})
tbody.addEventListener("mouseover", (e)=>{
    if(e.target !== e.currentTarget){
        let thisNod = e.target;

        let thisNodName = thisNod.getAttribute('name');

        if(thisNodName === "typeName"){
            let typeIno = thisNod.parentNode.parentNode.querySelector('input[name="type"]');
            typeIno.value = thisNod.getAttribute('value');
        }
    }
    e.stopPropagation()
})

tbody.addEventListener("focusout", (e)=>{
    if(e.target !== e.currentTarget){
        let shKey = e.target.name;
        let shValue = e.target.value;
        let abSeq = e.target.parentNode.parentNode.querySelector('button').value;
        let thisNod = e.target;
        if(shKey == "type"){
            updateAccountBook(shKey,shValue,abSeq,thisNod)

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

            for(let i = 0; i < n; i ++){
                let span =  `<span class="typeName" name="typeName" value="`+typeList[i].typeName+`">`+typeList[i].typeName+`</span>`;
                nod.parentNode.querySelector('div').innerHTML=span;
            }
        }
    })
}

function updateAccountBook(shKey,shValue,abSeq){

    if(shKey == "price"){
        console.log(shKey)
        localString(shValue)
    }

    $.ajax({
        url:'/api/accountbook/update'
        ,data:{
            shKey : shKey
            ,shValue : shValue
            ,abSeq : abSeq
        }
        ,type:'post'
        ,success:function (rp){
        }
    })
}

function deleteAccountBook(nod){
    let abSeq = nod.value;
    $.ajax({
        url:'/api/accountbook/delete'
        ,data:{ abSeq : abSeq}
        ,type:'post'
        ,success:function (rp){
            let parentTr = nod.parentNode.parentNode
            parentTr.remove();
        }
    })
}
function deleteAccountBookChekedAll(){
    console.log('del all')
}
function localString(e){
    let value = e.replace(/[^0-9]/g, "");

    let fillted = value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    console.log("fillted :: "+fillted);
    return fillted;
}

