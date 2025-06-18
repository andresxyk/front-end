(function($){
    $.fn.validationEngineLanguage = function(){
    };
    $.validationEngineLanguage = {
        newLang: function(){
            $.validationEngineLanguage.allRules = {
                "required": { // Add your regex rules here, you can take telephone as an example
                    "regex": "none",
                    "alertText": "* Tato položka je povinn&aacute;",
                    "alertTextCheckboxMultiple": "* Pros&iacute;m vyberte jednu možnost",
                    "alertTextCheckboxe": "* Tato položka je povinn&aacute;"
                },
                 "requiredInFunction": { 
                    "func": function(field, rules, i, options){
                        return (field.val() == "test") ? true : false;
                    },
                    "alertText": "* Pole se mus&iacute; rovnat test"
                },
                "minSize": {
                    "regex": "none",
                    "alertText": "* Minim&aacute;lně ",
                    "alertText2": " znaky"
                },
                "maxSize": {
                    "regex": "none",
                    "alertText": "* Maxim&aacute;lně ",
                    "alertText2": " znaky"
                },
				"groupRequired": {
                    "regex": "none",
                    "alertText": "* Mus&iacute;te zadat jedno z nasleduj&iacute;c&iacute;ch pol&iacute;"
                },
                "min": {
                    "regex": "none",
                    "alertText": "* Minim&aacute;ln&iacute; hodnota je "
                },
                "max": {
                    "regex": "none",
                    "alertText": "* Maxim&aacute;ln&iacute; hodnota je "
                },
                "past": {
                    "regex": "none",
                    "alertText": "* Datum před "
                },
                "future": {
                    "regex": "none",
                    "alertText": "* Datum po "
                },	
                "maxCheckbox": {
                    "regex": "none",
                    "alertText": "* Počet vybraných položek přes&aacute;hl limit"
                },
                "minCheckbox": {
                    "regex": "none",
                    "alertText": "* Pros&iacute;m vyberte ",
                    "alertText2": " volbu"
                },
                "equals": {
                    "regex": "none",
                    "alertText": "* Pole se neshoduj&iacute;"
                },
                "creditCard": {
                    "regex": "none",
                    "alertText": "* Neplatn&eacute; č&iacute;slo kreditn&iacute; karty"
                },
                "CZphone": {
                    // telefon&iacute; č&iacute;slo
                    "regex": /^([\+][0-9]{1,3}[ \.\-])([0-9]{3}[\-][0-9]{3}[\-][0-9]{3})$/,
                    "alertText": "* Neplatn&eacute; telefon&iacute; č&iacute;slo, zadejte ve form&aacute;tu +420 598-598-895"
                },
                "phone": {
                    // credit: jquery.h5validate.js / orefalo
                    "regex": /^([\+][0-9]{1,3}[ \.\-])?([\(]{1}[0-9]{2,6}[\)])?([0-9 \.\-\/]{3,20})((x|ext|extension)[ ]?[0-9]{1,4})?$/,
                    "alertText": "* Neplatn&eacute; telefon&iacute; č&iacute;slo"
                },
                "email": {
                    // Shamelessly lifted from Scott Gonzalez via the Bassistance Validation plugin http://projects.scottsplayground.com/email_address_validation/
                    "regex": /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i,
                    "alertText": "* Neplatn&aacute; emailov&aacute; adresa"
                },
                "integer": {
                    "regex": /^[\-\+]?\d+$/,
                    "alertText": "* Zadejte pouze č&iacute;sla"
                },
                "number": {
                    // Number, including positive, negative, and floating decimal. credit: orefalo
                    "regex": /^[\-\+]?((([0-9]{1,3})([,][0-9]{3})*)|([0-9]+))?([\.]([0-9]+))?$/,
                    "alertText": "* Neplatn&eacute; č&iacute;slo"
                },
                "CZdate": {
                    // datum ve form&aacute;tu jak se použ&iacute;v&aacute; v čr
                    "regex": /^(0[1-9]|[12][0-9]|3[01])[. /.](0[1-9]|1[012])[. /.](19|20)\d{2}$/,
                    "alertText": "* Neplatn&eacute; datum, datum mus&iacute; být ve form&aacute;tu den.měs&iacute;c.rok (dd.mm.rrrr)"
                },
                "date": {
                    // Date in ISO format. Credit: bassistance
                    "regex": /^\d{4}[\/\-](0?[1-9]|1[012])[\/\-](0?[1-9]|[12][0-9]|3[01])$/,
                    "alertText": "* Neplatn&eacute; datum, datum mus&iacute; být ve form&aacute;tu YYYY-MM-DD"
                },
                "ipv4": {
                    "regex": /^((([01]?[0-9]{1,2})|(2[0-4][0-9])|(25[0-5]))[.]){3}(([0-1]?[0-9]{1,2})|(2[0-4][0-9])|(25[0-5]))$/,
                    "alertText": "* Neplatn&aacute; IP adresa"
                },
                //česk&aacute; syntaxe pro rodn&eacute; č&iacute;slo
                "rc": {
                    "regex": /^\d{2}((0[1-9]|1[012])|(5[1-9]|6[012]))(0[1-9]|[12][0-9]|3[01])\/([0-9]{2,4})$/,
                    "alertText": "* Neplatn&eacute; rodn&eacute; č&iacute;slo, tvar mus&iacute; být 895431/4567"
                },
                //poštovn&iacute; směrovac&iacute; č&iacute;slo
                "psc": {
                    "regex": /^\d{3}[ \.\-]\d{2}$/,
                    "alertText": "* Neplatn&eacute; poštovn&iacute; směrovac&iacute; č&iacute;slo, tvar mus&iacute; být 456 45"
                },
                "url": {
                    "regex": /^(https?|ftp):\/\/(((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:)*@)?(((\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5]))|((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?)(:\d*)?)(\/((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)+(\/(([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)*)*)?)?(\?((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|[\uE000-\uF8FF]|\/|\?)*)?(\#((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|\/|\?)*)?$/i,
                    "alertText": "* Neplatný odkaz"
                },
                "onlyNumberSp": {
                    "regex": /^[0-9\ ]+$/,
                    "alertText": "* Pouze č&iacute;sla"
                },
                "onlyLetterSp": {
                    "regex": /^[a-zA-Z\ \']+$/,
                    "alertText": "* Pouze p&iacute;smena"
                },
                "onlyLetterNumber": {
                    "regex": /^[0-9a-zA-Z]+$/,
                    "alertText": "* Pouze p&iacute;smena a č&iacute;slice"
                },
                // --- CUSTOM RULES -- Those are specific to the demos, they can be removed or changed to your likings
                "ajaxUserCall": {
                    "url": "ajaxValidateFieldUser",
                    // you may want to pass extra data on the ajax call
                    "extraData": "name=eric",
                    "alertText": "* Uživatelsk&eacute; jm&eacute;no je již použito",
                    "alertTextLoad": "* Ověřov&aacute;n&iacute;, pros&iacute;m čekejte"
                },
                "ajaxNameCall": {
                    // remote json service location
                    "url": "ajaxValidateFieldName",
                    // error
                    "alertText": "* Uživatelsk&eacute; jm&eacute;no je již použito",
                    // if you provide an "alertTextOk", it will show as a green prompt when the field validates
                    "alertTextOk": "* Toto jm&eacute;no je k dispozici",
                    // speaks by itself
                    "alertTextLoad": "* Ověřov&aacute;n&iacute;, pros&iacute;m čekejte"
                },
                "validate2fields": {
                    "alertText": "* Pros&iacute;m napište HELLO"
                }
            };
            
        }
    };
    $.validationEngineLanguage.newLang();
})(jQuery);
