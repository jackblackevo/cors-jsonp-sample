document.addEventListener('DOMContentLoaded', function () {
  document.getElementById('btn').addEventListener('click', function (event) {
    var xhr = new XMLHttpRequest()
    xhr.open('get', 'http://127.0.0.1:8080/cors-jsonp-sample/SimpleRequestHandler')
    xhr.responseType = 'json'
    xhr.addEventListener('load', function (event) {
      var responseJson = event.target.response

      alert(responseJson.msg)
    })
    xhr.addEventListener('error', function (event) {
      alert('error')
    })
    xhr.send()
  })
})
