(function() {
	var keys_arr = [];
	var _idx = 0;
	var commandKey = 91, shiftKey = 16, fKey = 70, altKey = 18;
	var keydown = function(e, target, callback) {
		var event = e || window.event;
		var keyCode = event.keyCode;
		keys_arr[_idx++] = keyCode;
		checkCompositeKey(callback, target);
	};

	var checkCompositeKey = function(callback, target) {
		var flag = (find(commandKey) || find(altKey)) && find(shiftKey) && find(fKey);
		if (flag) {
			callback(target);
		}
	};

	var find = function(keyCode) {
		for (var it = 0; it < _idx; it++) {
			var k = keys_arr[it];
			if (k == keyCode) {
				return true;
			}
		}
		return false;
	};

	var keyUp = function() {
		_idx = 0;
	};

	window.keydownFn = keydown;
	window.keyUpFn = keyUp;
})();