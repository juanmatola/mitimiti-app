let url = new URL(document.URL);


export default function errorHandler(){
	if (!(url.search === '')) {
    
	    let err = url.searchParams.get("err");
	
	    if (err != null) {
	        let errorModalBody = document.getElementById("ErrorModalMesagge");
	        let errModal = new bootstrap.Modal(document.getElementById("ErrorModal"));
	        errorModalBody.innerHTML = "<p>"+ err + "</p>";
	        errModal.show();
	    }
		
		$(document).on('hide.bs.modal', '.modal', function () {
	        if ($(".modal-backdrop").length > 1) {
	            $(".modal-backdrop").remove();
	        }
    	});
	}
}

errorHandler();