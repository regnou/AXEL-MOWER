/*
	WOPU - WayOfSpark Utils
*/

wopu = {};

wopu.BaseClass = function(o){};
wopu.BaseClass.prototype.construct = function(){};
wopu.BaseClass.extend = function(def) {
  var classDef = function() {
      if (arguments[0] !== wopu.BaseClass) { this.construct.apply(this, arguments); }
  };
  var proto = new this(wopu.BaseClass);
  
  var superClass = this.prototype;
  for (var n in def) {
      var item = def[n];
      proto[n] = item;
  }
  classDef.prototype = proto;
  classDef.extend = this.extend;      
  return classDef;
};