document.addEventListener('DOMContentLoaded', function () {
  document.getElementById('btn').addEventListener('click', function (event) {
    var xhr = new XMLHttpRequest()
    xhr.open('get', 'http://127.0.0.1:8080/cors-jsonp-sample/CredentialedRequestHandler')
    xhr.withCredentials = true
    xhr.responseType = 'json'
    xhr.addEventListener('load', function (event) {
      var responseJson = event.target.response

      alert(responseJson.msg)
      if (responseJson.receviedUserCookie) {
        alert(responseJson.receviedUserCookie.name)
        alert(responseJson.receviedUserCookie.value)
      }
    })
    xhr.addEventListener('error', function (event) {
      alert('error')
    })
    xhr.send()
  })
})
