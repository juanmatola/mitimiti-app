let url = new URL(document.URL);

if (!(url.search === '')) {
  let action = url.searchParams.get('action');
  if (action === 'login') {
      let singInModal = new bootstrap.Modal(document.getElementById('ModalInicio'));
      singInModal.show();
  } else if (action === 'singup') {
      let singUpModal = new bootstrap.Modal(document.getElementById('ModalRegistro'));
      singUpModal.show();
  }
}