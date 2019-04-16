var ctxPath = "/BigDataAOP";
var nameSpace = function() {
    var arg = arguments[0];
    var arr = arg.split('.');
    var context = window;
    for (var i = 0; i < arr.length; i++) {
        var str = arr[i];
        if (!context[str]) {
            context[str] = {};
        }
        context = context[str];
    };
};

var enumerables = ['hasOwnProperty', 'valueOf', 'isPrototypeOf',
		'propertyIsEnumerable', 'toLocaleString', 'toString', 'constructor'];

var apply = function(object, config) {
	if(!config){
		config = {};
	}
	
	if (object && config && typeof config === 'object') {
		var i, j, k;

		for (i in config) {
			object[i] = config[i];
		}

		if (enumerables) {
			for (j = enumerables.length; j--;) {
				k = enumerables[j];
				if (config.hasOwnProperty(k)) {
					object[k] = config[k];
				}
			}
		}
		return object;
	}
}

var createClass = function(className, cfg) {
	var myCfg = {};
	if(cfg){
		apply(myCfg,cfg);
	}
	
	var module = eval("new " + className + "(myCfg)");
	return module;
}