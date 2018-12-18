function snowFlow(left,height,src)
{
    var container=document.createElement('div');
    var pic=document.createElement('img');
    var snowFlow=document.getElementById('snowFlow');
    pic.className='pic';
    container.className='container';
    snowFlow.appendChild(container);
    container.appendChild(pic);
    container.style.left=left+'px';
    container.style.height=height+'px';
    pic.src=src; setTimeout(function(){
        snowFlow.removeChild(container);
    },5000);
}