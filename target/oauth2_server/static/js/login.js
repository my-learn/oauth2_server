(function(win){
  var loginBtn = null;

  function initTogglePwdType() {
    var pwdField = $('.input-pwd-field');
    var togglePwdBtn = $('.toggle-pwd-btn');
    var pwdType = 'pwd';
    togglePwdBtn.on('click', function () {
      pwdType = (pwdType == 'pwd' ? 'txt' : 'pwd');
      togglePwdBtn.find('.input-pwd')[pwdType == 'pwd' ? 'show' : 'hide']();
      togglePwdBtn.find('.input-txt')[pwdType != 'pwd' ? 'show' : 'hide']();
      pwdField.attr('type', pwdType == 'pwd' ? 'password' : 'text');
    });
  }

  function initFormValidate() {
    $validator.on({
      elSelector: '.user-phone',
      rules: [{
        require: true,
        errMsg: '账号不能为空'
      }, {
        nospace: true,
        errMsg: '账号不能有空字符'
      },{
        min: 5,
        max: 14,
        errMsg: '账号为5-14个字符'
      }]
    }).on({
      elSelector: '.user-pwd',
      rules: [{
        require: true,
        errMsg: '密码不能为空'
      }, {
        nospace: true,
        errMsg: '密码不能有空字符'
      },{
        min: 6,
        max: 18,
        errMsg: '密码为6-18位'
      }]
    }).addValidateListener(function(value){
      loginBtn[(value?'remove':'add')+'Class']('btn-disabled');
    }.bind(this));
  }

  if(win.$){
    initTogglePwdType();
    initFormValidate();
        
    var errAjaxTip = $('.err-ajax-tip');

    var mailSuffix = '@qeveworld.com';
    var userPhone = $('.user-phone');
    var phoneVal = userPhone.val();
    if(phoneVal && phoneVal.substr(phoneVal.length-mailSuffix.length)==mailSuffix)userPhone.val(phoneVal.substr(0,phoneVal.length-mailSuffix.length));
    loginBtn = $('.login-btn');
    loginBtn.on('click',function(){
      var validated = $validator.validateAll();      
      if(validated){
        var oPhone = userPhone.val();
        userPhone.val(oPhone + mailSuffix);
      }
      return validated;
    })
  }else throw new UserException("Please load zepto.js libary.");  
})(window);