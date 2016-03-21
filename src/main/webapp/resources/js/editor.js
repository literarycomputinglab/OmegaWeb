(function ($) {

    if ($.cleditor === undefined) {
        return;
    }

    $.ajax({
        async: false,
        dataType: "json",
        url: "/traduco/servlet/editorServlet",
        success: setup
    });

    function setup(json) {

        $.cleditor.defaultOptions.useCSS = false;
        $.cleditor.defaultOptions.docCSSFile = "/traduco/resources/css/editor.css";
        $.cleditor.defaultOptions.bodyStyle= "margin:4px; font:13px sans-serif; cursor:text";

        $.cleditor.buttons.smallcaps = {
            name: "smallcaps",
            css: {
                'background-image': 'url(/traduco/resources/image/smallcaps.png)'
            },
            title: "Smallcaps",
            command: "inserthtml",
            popupName: "smallcaps",
            popupClass: "cleditorPrompt",
            popupContent: "",
            buttonClick: onSmallcapsClick
        };

        $.cleditor.buttons.note = {
            name: "note",
            css: {
                'background-image': 'url(/traduco/resources/image/note.png)'
            },
            title: "Note",
            command: "inserthtml",
            popupName: "note",
            popupClass: "cleditorPrompt",
            popupContent: buildNoteMenu(json.notes),
            buttonClick: onNoteClick
        };

        $.cleditor.buttons.glossary = {
            name: "glossary",
            css: {
                'background-image': 'url(/traduco/resources/image/glossary.png)'
            },
            title: "Glossary",
            command: "inserthtml",
            popupName: "glossary",
            popupClass: "cleditorPrompt",
            popupContent: buildGlossaryMenu(json.glossaries),
            buttonClick: onGlossaryClick
        };

        $.cleditor.buttons.specialChars = {
            name: "specialChars",
            css: {
                'background-image': 'url(/traduco/resources/image/specialChars.png)'
            },
            title: "Special character",
            command: "inserthtml",
            popupName: "specialChars",
            popupClass: "cleditorPrompt",
            popupContent: buildSpecialCharsMenu(json.specialChars),
            buttonClick: onSpecialCharsClick
        };

        $.cleditor.buttons.accentedChars = {
            name: "accentedChars",
            css: {
                'background-image': 'url(/traduco/resources/image/accentedChars.png)'
            },
            title: "Accented character",
            command: "inserthtml",
            popupName: "accentedChars",
            popupClass: "cleditorPrompt",
            popupContent: buildAccentedCharsMenu(),
            buttonClick: onSpecialCharsClick
        };
    }

    function buildNoteMenu(list) {
        var menu = '<div class="editorPopupMenu">';
        for (var index in list) {
            var entry = list[index];
            menu += '<div rel="' + entry.color + ' ' + entry.symbol + '">';
            menu += '<span class="colorCircle" style="background-color:' + entry.color + '; margin-right: 4px"/>';
            menu += entry.name + '</div>';
        }
        menu += "</div>";
        return menu;
    }

    function buildGlossaryMenu(list) {
        var menu = '<div class="editorPopupMenu">';
        for (var index in list) {
            var entry = list[index];
            menu += '<div rel="' + entry.color + '">';
            menu += '<span class="colorCircle" style="background-color:' + entry.color + '; margin-right: 4px"/>';
            menu += entry.name + '</div>';
        }
        menu += "</div>";
        return menu;
    }

    function buildSpecialCharsMenu(list) {
        var menu = '<div class="editorCharsPopupMenu">';
        for (var index in list) {
            var entry = list[index];
            menu += '<div rel="' + entry.value + '">';
            menu += '<div>' + entry.key + '</div>';
            menu += '</div>';
        }
        menu += "</div>";
        return menu;
    }

    function buildAccentedCharsMenu() {
        var menu = '<div class="editorCharsPopupMenu editorAccentedCharsPopupMenu">';
        menu += '<div rel="à"><div style="width: 12px">à</div></div>';
        menu += '<div rel="è"><div style="width: 12px">è</div></div>';
        menu += '<div rel="ì"><div style="width: 12px">ì</div></div>';
        menu += '<div rel="ò"><div style="width: 12px">ò</div></div>';
        menu += '<div rel="ù"><div style="width: 12px">ù</div></div>';
        menu += '<div rel="á"><div style="width: 12px">á</div></div>';
        menu += '<div rel="é"><div style="width: 12px">é</div></div>';
        menu += '<div rel="í"><div style="width: 12px">í</div></div>';
        menu += '<div rel="ó"><div style="width: 12px">ó</div></div>';
        menu += '<div rel="ú"><div style="width: 12px">ú</div></div>';
        menu += "</div>";
        menu += '<div class="editorCharsPopupMenu editorAccentedCharsPopupMenu">';
        menu += '<div rel="À"><div style="width: 12px">À</div></div>';
        menu += '<div rel="È"><div style="width: 12px">È</div></div>';
        menu += '<div rel="Ì"><div style="width: 12px">Ì</div></div>';
        menu += '<div rel="Ò"><div style="width: 12px">Ò</div></div>';
        menu += '<div rel="Ù"><div style="width: 12px">Ù</div></div>';
        menu += '<div rel="Á"><div style="width: 12px">Á</div></div>';
        menu += '<div rel="É"><div style="width: 12px">É</div></div>';
        menu += '<div rel="Í"><div style="width: 12px">Í</div></div>';
        menu += '<div rel="Ó"><div style="width: 12px">Ó</div></div>';
        menu += '<div rel="Ú"><div style="width: 12px">Ú</div></div>';
        menu += "</div>";
        return menu;
    }

    function buildQuoteMenu(list, size) {
        if (size === undefined) {
            size = 10;
        }
        var menu = '<select size="' + size + '" class="editorQuotePopupMenu">';
        for (var index in list) {
            var entry = list[index];
            menu += '<option value="' + entry.value + '">' + entry.key + '</option>';
        }
        menu += '</select>';
        return menu;
    }

    function onSmallcapsClick(e, data) {
        var editor = data.editor;
        var selectedHtml = editor.selectedHTML();
        if (selectedHtml) {
            var html = '<span class="smallcaps">' + selectedHtml + '</span>';
            editor.execCommand(data.command, html, false, data.button);
            editor.hidePopups();
            editor.focus();
        }
    }

    function onNoteClick(e, data) {
        $(data.popup).find('.editorPopupMenu > div').unbind("click").click(function (e) {
            var editor = data.editor;
            var color = $(this).attr('rel').split(" ")[0];
            var symbol = $(this).attr('rel').split(" ")[1];
            var html = '<span class="note" style="background-color: ' + color + '" rel="?">' + symbol + '</span> ';
            editor.execCommand(data.command, html, false, data.button);
            editor.hidePopups();
            editor.focus();
        });
    }


    function onGlossaryClick(e, data) {
        $(data.popup).find('.editorPopupMenu > div').unbind("click").click(function (e) {
            var editor = data.editor;
            var selectedHtml = editor.selectedHTML();
            var color = $(this).attr('rel');
            var html = '<span class="glossary" style="background-color: ' + color + '" rel="?">' + selectedHtml + '</span>';
            editor.execCommand(data.command, html, false, data.button);
            editor.hidePopups();
            editor.focus();
        });
    }

    function onSpecialCharsClick(e, data) {
        $(data.popup).find('.editorCharsPopupMenu > div').unbind("click").click(function (e) {
            var editor = data.editor;
            var html = $(this).text();
            editor.execCommand(data.command, html, false, data.button);
            editor.hidePopups();
            editor.focus();
        });
    }

    function onQuoteClick(e, data) {
        $(data.popup).find('.editorQuotePopupMenu > option').unbind("click").click(function (e) {
            var editor = data.editor;
            var html = $(this).attr('value');
            editor.execCommand(data.command, html, false, data.button);
            editor.hidePopups();
            editor.focus();
        });
    }

})(jQuery);

function inslallClick(inplaceId, editorWidget) {
    var inplaceElement = $(document.getElementById(inplaceId));
    var events = $._data(inplaceElement[0], 'events');
    if (events !== undefined && events.click !== undefined) {
        console.debug("click is already installed");
    } else {
        console.debug("instal click");
        inplaceElement.click(function () {
            inplaceElement.unbind("click");
            inplaceClick(editorWidget);
        });
    }
}

function inplaceClick(editorWidget) {
    console.debug("click action");
    if (!editorWidget.render()) {
        console.debug("editor is not ready, wait 100");
        setTimeout(function () {
            inplaceClick(editorWidget);
        }, 100);
    }
}