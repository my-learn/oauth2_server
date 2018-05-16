(function(win){
  if(!win.$)throw new UserException("Please load zepto.js libary.");  
  var params = $getParams(window.location.href);
  var themeMaps = {
    '1001':{
      'css': 'green',
      'icon': 'default.png'
    },
    '1002':{
      'css': 'red',
      'icon': 'default-1.png'
    }
  }
  var appId = params.appId,appTheme = null;
  if(appId && (appTheme = themeMaps[appId])){
    $("#theme-href").attr('href','./style/' + appTheme.css + '.css');
    $(".platform-icon").attr('src','./img/' + appTheme.icon);
  }  
})(window);