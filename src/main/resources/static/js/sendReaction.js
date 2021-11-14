
let sendResumeButton = document.getElementById("sendResumeButton");

let sendLoadderFunction = () => {
    sendResumeButton.style.display = 'none';
    let sendLoadder= document.getElementById('sendLoadder');
    sendLoadder.classList.remove("d-none");
}

sendResumeButton.addEventListener("click", sendLoadderFunction);
