document.addEventListener('DOMContentLoaded', function () {
  document.getElementById('btn').addEventListener('click', function (event) {
    var scriptElement = document.createElement('script')
    var callbackName = 'jsonp_callback_' + Math.round(100000 * Math.random())

    window[callbackName] = function(responseJson) {
      document.head.removeChild(scriptElement)
      delete window[callbackName]

      alert(responseJson.msg)
    }

    scriptElement.src = 'http://127.0.0.1:8080/cors-jsonp-sample/JSONPRequestHandler?callback=' + callbackName

    document.head.appendChild(scriptElement)
  })
})
