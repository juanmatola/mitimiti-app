let url = new URL(document.URL);

if (!(url.search === '')) {
    
    let err = url.searchParams.get("err");

    if (err != null) {
        let errorModalBody = document.getElementById("ErrorModalMesagge");
        let errModal = new bootstrap.Modal(document.getElementById("ErrorModal"));
        errorModalBody.innerHTML = "<p>"+ err + "</p>";
        errModal.show();
    }

}