var formatJson = function(json, options) {
	var reg = null, formatted = '', pad = 0, PADDING = '    '; // one can also
	json = removeBlankLine(json);
	// use '\t' or a
	// different
	// number of
	// spaces

	// optional settings
	options = options || {};
	// remove newline where '{' or '[' follows ':'
	options.newlineAfterColonIfBeforeBraceOrBracket = (options.newlineAfterColonIfBeforeBraceOrBracket === true) ? true
			: false;
	// use a space after a colon
	options.spaceAfterColon = (options.spaceAfterColon === true) ? true : false;

	// use a newline after a semicolon
	options.semicolonAfterNewLine = (options.semicolonAfterNewLine === true) ? true
			: false;
	options.quotationMarksCurlyBracesRemoveNewLine = (options.quotationMarksCurlyBracesRemoveNewLine === true) ? true
			: false;

	// begin formatting...

	// make sure we start with the JSON as a string
	// if (typeof json !== 'string') {
	// json = JSON.stringify(json);
	// }
	// // parse and stringify in order to remove extra whitespace
	// json = JSON.parse(json);
	// json = JSON.stringify(json);

	// add newline before and after curly braces
	reg = /([\{\}])/g;
	json = json.replace(reg, '\r\n$1\r\n');

	// add newline before and after square brackets
//	reg = /([\[\]])/g;
//	json = json.replace(reg, '\r\n$1\r\n');

	// add newline after comma
	reg = /(\,)/g;
	json = json.replace(reg, '$1\r\n');

	// remove multiple newlines
	reg = /(\r\n\r\n)/g;
	json = json.replace(reg, '\r\n');

	// remove newlines before commas
	reg = /\r\n\,/g;
	json = json.replace(reg, ',');

	// optional formatting...
	if (!options.newlineAfterColonIfBeforeBraceOrBracket) {
		reg = /\:\r\n\{/g;
		json = json.replace(reg, ':{');
		reg = /\:\r\n\[/g;
		json = json.replace(reg, ':[');
	}
	if (options.spaceAfterColon) {
		reg = /\:/g;
		json = json.replace(reg, ': ');
	}
	if (options.semicolonAfterNewLine) {
		reg = /\;/g;
		json = json.replace(reg, ';\r\n');

		reg = /\r\n\s*\r\n\s*}/g;
		json = json.replace(reg, '\r\n}');
	}
	if (options.quotationMarksCurlyBracesRemoveNewLine) {
		reg = /"\r\n\s*\{/g;
		json = json.replace(reg, '"{');

		reg = /\}\r\n\s*"/g;
		json = json.replace(reg, '}"');

	}
	reg = /\s+\:\s+'/g;
	json = json.replace(reg, ':\'');

	$.each(json.split('\r\n'), function(index, node) {
		var i = 0, indent = 0, padding = '';
		node = $.trim(node);

		if (node.match(/\{$/) || node.match(/\[$/)) {
			indent = 1;
		} else if (node.match(/\}/) || node.match(/\]/)) {
			if (pad !== 0) {
				pad -= 1;
			}
		} else {
			indent = 0;
		}

		for (i = 0; i < pad; i++) {
			padding += PADDING;
		}

		formatted += padding + node + '\r\n';
		pad += indent;
	});
	formatted = removeDynamicNewLine(formatted);
	formatted = removeEnter(formatted);
	return formatted;
};

function removeEnter(formatted) {
    
    var retrunValue = '';
    
    for ( var index in formatted) {
        if(index == 0 && (formatted[index] == '\n' || formatted[index] == '\r')) {
            continue;
        }
        if(index == 1 && (formatted[index] == '\n' || formatted[index] == '\r')) {
            continue;
        }
        if(index == (formatted.length - 2) && (formatted[index] == '\n' || formatted[index] == '\r')) {
            continue;
        }
        if(index == (formatted.length - 1) && (formatted[index] == '\n' || formatted[index] == '\r')) {
            continue;
        }
        retrunValue += formatted[index];
    }
    return retrunValue;
    
}

function removeBlankLine(formatted) {
	var _tf = '';
	for ( var ch in formatted) {
		if (formatted[ch] == '\n' || formatted[ch] == '\r') {
			continue;
		}
		_tf += formatted[ch];
	}
	return _tf;
}

function removeDynamicNewLine(formatted) {
	var _tf = '', indent = 0, _begin = false;
	for ( var ch in formatted) {
		if (formatted[ch] == '$') {
			_begin = true
		} else if (_begin) {
			if (formatted[ch] == '{') {
				_begin = false;
			}
			if (formatted[ch] == '\n' || formatted[ch] == '\r' || formatted[ch] == ' ') {
				continue;
			}
		}
		_tf += formatted[ch];
	}
	return _tf;
}