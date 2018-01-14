/*!
 * Image (upload) dialog Local Server for Editor.md
 *
 * @file        image-dialog.js
 * @author      newflydd@189.cn
 * @version     1.0.0
 * @updateTime  2017-08-18
 * {@link       http://www.hexcode.cn}
 * @license     MIT
 */

(function() {

  var factory = function(exports) {

    var pluginName = "image-dialog";

    exports.fn.imageDialog = function() {

      var _this = this;
      var cm = this.cm;
      var lang = this.lang;
      var editor = this.editor;
      var settings = this.settings;
      var cursor = cm.getCursor();
      var selection = cm.getSelection();
      var imageLang = lang.dialog.image;
      var classPrefix = this.classPrefix;
      var dialogName = classPrefix + pluginName, dialog;

      cm.focus();

      var loading = function(show) {
        var _loading = dialog.find("." + classPrefix + "dialog-mask");
        _loading[(show) ? "show" : "hide"]();
      };

      if (editor.find("." + dialogName).length < 1) {
        var guid = (new Date).getTime();
        var action = settings.imageUploadURL + (settings.imageUploadURL.indexOf("?") >= 0 ? "&" : "?") + "guid=" + guid + '&' + settings.csrf_key + '=' + settings.csrf_value;

        var dialogContent = ((settings.imageUpload) ? "<form id=\"localUploadForm\" method=\"post\" enctype=\"multipart/form-data\" class=\"" + classPrefix + "form\" onsubmit=\"return false;\">" : "<div class=\"" + classPrefix + "form\">") +
          "<label>文件地址</label>" +
          "<input type=\"text\" data-url />" +
          ((settings.imageUpload) ? "<div class=\"" + classPrefix + "file-input\">" + "<input type=\"file\" name=\"file\" accept=\"*\" />" + "<input type=\"submit\" value=\"本地上传\"/>" + "</div>" : "") +
          "<br/>" +
          "<label>" + imageLang.alt + "</label>" +
          "<input type=\"text\" value=\"" + selection + "\" data-alt />" +
          "<br/>" +
          "<label>" + imageLang.link + "</label>" +
          "<input type=\"text\" value=\"http://\" data-link />" +
          "<br/>" +
          ((settings.imageUpload) ? "</form>" : "</div>");

        dialog = this.createDialog({
          title: imageLang.title,
          width: (settings.imageUpload) ? 465 : 380,
          height: 254,
          name: dialogName,
          content: dialogContent,
          mask: settings.dialogShowMask,
          drag: settings.dialogDraggable,
          lockScreen: settings.dialogLockScreen,
          maskStyle: {
            opacity: settings.dialogMaskOpacity,
            backgroundColor: settings.dialogMaskBgColor
          },
          buttons: {
            enter: [lang.buttons.enter, function() {
              var url = this.find("[data-url]").val();
              var alt = this.find("[data-alt]").val();
              var link = this.find("[data-link]").val();

              if (url === "") {
                alert(imageLang.imageURLEmpty);
                return false;
              }

              var altAttr = (alt !== "") ? " \"" + alt + "\"" : "";

              if (link === "" || link === "http://") {
                cm.replaceSelection("![" + alt + "](" + url + altAttr + ")");
              } else {
                cm.replaceSelection("[![" + alt + "](" + url + altAttr + ")](" + link + altAttr + ")");
              }

              if (alt === "") {
                cm.setCursor(cursor.line, cursor.ch + 2);
              }

              this.hide().lockScreen(false).hideMask();

              return false;
            }],

            cancel: [lang.buttons.cancel, function() {
              this.hide().lockScreen(false).hideMask();

              return false;
            }]
          }
        });

        dialog.attr("id", classPrefix + "image-dialog-" + guid);

        if (!settings.imageUpload) {
          return;
        }

        var fileInput = dialog.find("[name=\"file\"]");

        var ajaxData = {};

        var submitHandler = function() {
          var formData = new FormData($("#localUploadForm")[0]);
          $.ajax({
            url: action,
            type: 'POST',
            data: formData,
            dataType: "json",
            beforeSend: function() { loading(true); },
            cache: false,
            contentType: false,
            processData: false,
            timeout: 30000,
            success: function(result) {
              dialog.find("[data-url]").val(result.url);
            },
            error: function() { alert("上传超时"); },
            complete: function() { loading(false); }
          });
        };

        dialog.find("[type=\"submit\"]").bind("click", submitHandler);

        fileInput.bind("change", function() {
          var fileName = fileInput.val();

          if (fileName === "") {
            alert(imageLang.uploadFileEmpty);

            return false;
          }

          dialog.find("[type=\"submit\"]").trigger("click");
        });
      }

      dialog = editor.find("." + dialogName);
      dialog.find("[type=\"text\"]").val("");
      dialog.find("[type=\"file\"]").val("");
      dialog.find("[data-link]").val("http://");

      this.dialogShowMask(dialog);
      this.dialogLockScreen();
      dialog.show();
    };

  };

  // CommonJS/Node.js
  if (typeof require === "function" && typeof exports === "object" && typeof module === "object") {
    module.exports = factory;
  } else if (typeof define === "function") // AMD/CMD/Sea.js
  {
    if (define.amd) { // for Require.js

      define(["editormd"], function(editormd) {
        factory(editormd);
      });

    } else { // for Sea.js
      define(function(require) {
        var editormd = require("./../../editormd");
        factory(editormd);
      });
    }
  } else {
    factory(window.editormd);
  }

})();