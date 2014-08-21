
function element(name,content){
    
    var xml;
    if (!content){
        xml='<' + name +'/>';
    }
    else {
        xml='<' + name  + '>' + content + '</'+name+'>';
    }
    return xml;
}
function constructXML(value){
    return ('<?xml version="1.0" encoding="utf-8"?>'+value);
}