(function(win){
  win.$getParams = function(url){
    url = url || window.location.href || '';
    var param = {};
    var hrefs = url.split('?'),endIndex = hrefs.length-1;
    if(endIndex>-1){
      var params = hrefs[endIndex].split('&');
      for(var i=0,len=params.length;i<len;i++){
        var tmpParams = params[i].split('=');
        if(tmpParams.length>1)param[tmpParams[0]] = tmpParams[1];
      }
    }
    return param;
  }
})(window);