//统计
function tj(){
	console.log('统计');
    //cnzz tongji http://new.cnzz.com/v1/login.php?siteid=1255513477

    // var snzzScript = document.createElement('script');
    // snzzScript.src = 'https://s4.cnzz.com/z_stat.php?id=1255513477&web_id=1255513477';
    // snzzScript.async = true;
    // document.head.appendChild(snzzScript)

    document.writeln("<script async src=\'https://s4.cnzz.com/z_stat.php?id=1255513477&web_id=1255513477\' language=\'JavaScript\'></script>");

    //baidu tongji
    document.writeln("<script>");
    document.writeln("var _hmt = _hmt || [];");
    document.writeln("(function() {");
    document.writeln("  var hm = document.createElement(\'script\');");
    document.writeln("  hm.src = \'https://hm.baidu.com/hm.js?22975875995c9a95b1ec706190b79317\';");
    document.writeln("  hm.async = true;");
    document.writeln("  hm.charset = \'utf-8\';");
    document.writeln("  var s = document.getElementsByTagName(\'script\')[0]; ");
    document.writeln("  s.parentNode.insertBefore(hm, s);");
    document.writeln("})();");
    document.writeln("</script>");
}

tj();
