/*
* Usage:
*
*/

!function (name, context, definition) {
    if (typeof module != 'undefined' && module.exports) module.exports = definition();
    else if (typeof define == 'function' && define.amd) define(definition);
    else {
      var prev = context[name],
        self = definition();
      self.noConflict = function () {
        context[name] = prev;
        return self;
      };
      context[name] = self;
    }
  }('msledger', this, function () {
    if (typeof window === 'undefined') {
      throw new Error("This script is only for use in browser environment");
    }
    var win =  window,
      doc = win.document,
      counter = 0,
      head,
      config = {};
  
    function load(url, pfnError) {
      var script = doc.createElement('script'),
        done = false;
      script.src = url;
      script.async = true;
  
      var errorHandler = pfnError || config.error;
      if (typeof errorHandler === 'function') {
        script.onerror = function (ex) {
          errorHandler({url: url, event: ex});
        };
      }
  
      script.onload = script.onreadystatechange = function () {
        if (!done && (!this.readyState || this.readyState === "loaded" || this.readyState === "complete")) {
          done = true;
          script.onload = script.onreadystatechange = null;
          if (script && script.parentNode) {
            script.parentNode.removeChild(script);
          }
        }
      };
  
      if (!head) {
        head = doc.getElementsByTagName('head')[0];
      }
      
      try{
        head.appendChild(script);
      }catch(ex){
        if (typeof errorHandler === 'function') {
          errorHandler({url: url, event: ex});
        }
      }
      
    }
  
    function encode(str) {
      return encodeURIComponent(str);
    }
  
    function jsonp(url, params, callback, callbackName) {
      var query = (url || '').indexOf('?') === -1 ? '?' : '&',
        key;
  
      if (typeof params === 'function') {
        callback = params;
        params = {};
      }
  
      callbackName = callbackName || config.callbackName ||'callback';
      var uniqueName = callbackName + "_json" + (++counter);
  
      params = params || {};
      for (key in params) {
        if (params.hasOwnProperty(key)) {
          query += encode(key) + "=" + encode(params[key]) + "&";
        }
      }
  
      win[uniqueName] = function(data) {
        callback(data);
        try {
          win[uniqueName] = null;
          delete win[uniqueName];
        } catch (e) {}
      };
  
      load(url + query + callbackName + '=' + uniqueName);
      return uniqueName;
    }

    function reward(token,fnCallBackHandler,fnErrorHandler){
        var ENDPOINT = '//missionledger.net/front/rest/charge/callback/mission';
        //var ENDPOINT = '//thecoke.tk:8080/resources/mission.json';
        var obj = {
            p: token
        }

        if (typeof fnErrorHandler === 'function') {
            config.error = fnErrorHandler;
        }
        
        jsonp(ENDPOINT ,obj,function(data){
            console.log(data);
            if (typeof fnCallBackHandler === 'function') {
               fnCallBackHandler(data);
            }
        });
    }
  
    function setDefaults(obj) {
      config = obj;
    }
    
    return {
      // get: jsonp,
      reward : reward,
      init: setDefaults
    };
  });
  