//listen to button and send data to server for calc
function buttonListener(){
    document.getElementById("btn").addEventListener("click",()=>{
        let amount = getValueById("amount");
        let fromDen = getValueById("fromDenominator");
        let toDen = getValueById("toDenominator");
        calculateOnServer(amount,fromDen,toDen)
    });
}
////listen to inputEvent and send data to server for calc
function inputListener() {
    document.getElementById("amount").addEventListener("input", () => {
        let amount = getValueById("amount");
        let fromDen = getValueById("fromDenominator");
        let toDen = getValueById("toDenominator");
        calculateOnServer(amount, fromDen, toDen)
    });
}
//sends data to server and gets result as response and sets data to result input;
function calculateOnServer(amount,fromDen,toDen){
    let xhttp = new XMLHttpRequest();
    xhttp.open("POST","/api/calculate",true);
    xhttp.setRequestHeader('X-Requested-With', 'XMLHttpRequest');
    xhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    xhttp.send("amount="+ amount +"&" + "fromDen=" + fromDen + "&" + "toDen=" +toDen)
    xhttp.onreadystatechange = function () {
        let DONE = 4; // readyState 4 means the request is done.
        let OK = 200; // status 200 is a successful return.
        if (xhttp.readyState === DONE) {
            if (xhttp.status === OK) {
                setValueById("result",xhttp.responseText);
            } else {
                console.log('Error: ' + xhttp.status); // An error occurred during the request.
            }
        }
    };

}
//gets elements value by ID
function getValueById(id){
    return document.getElementById(id).value;
}
//sets elements value by ID
function setValueById(id,value){
    document.getElementById(id).value = value;
}

buttonListener();
inputListener();