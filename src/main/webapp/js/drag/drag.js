function DragCamera(id) {//原生拖放函数
		            var Sect = function (flag) {
		                return document.getElementById(flag);
		            }
		            Sect(id).onmousedown = function (e) {
		                var d = document;
		                dv = e.target;
		                var page = {
		                    event: function (evt) {
		                        var ev = evt || window.event;
		                        return ev;
		                    },
		                    pageX: function (evt) {
		                        var e = this.event(evt);
		                        return e.pageX || (e.clientX + document.body.scrollLeft - document.body.clientLeft);
		                    },
		                    pageY: function (evt) {
		                        var e = this.event(evt);
		                        return e.pageY || (e.clientY + document.body.scrollTop - document.body.clientTop);

		                    },
		                    layerX: function (evt) {
		                        var e = this.event(evt);
		                        return e.layerX || e.offsetX;
		                    },
		                    layerY: function (evt) {
		                        var e = this.event(evt);
		                        return e.layerY || e.offsetY;
		                    }
		                }             
		                var x = page.layerX(e);
		                var y = page.layerY(e);        
		                if (dv.setCapture) {
		                    dv.setCapture();
		                }
		                else if (window.captureEvents) {
		                    window.captureEvents(Event.MOUSEMOVE | Event.MOUSEUP);
		                }
		                d.onmousemove = function (e) {                    
		                    var tx = page.pageX(e) - x;
		                    var ty = page.pageY(e) - y;
		                    var wrap = $('#drop');
		                    var wrap_left = parseInt(wrap.css('left').slice(0,-2));
		                    var wrap_right = parseInt(wrap.css('right').slice(0,-2));
		                    var wrap_top = parseInt(wrap.css('top').slice(0,-2));
		                    var wrap_bottom = parseInt(wrap.css('bottom').slice(0,-2));
		                    var width = parseInt(wrap.css('width').slice(0,-2));
		                    var height = parseInt(wrap.css('height').slice(0,-2));
		                     tx = tx - wrap_left;
		                     ty = ty - wrap_top;
		                     console.log('x='+tx+",y="+ty);
		                     if(tx < 0)
		                     	tx = 0;
		                     if(tx >= width)
		                     	tx = width-60;
		                     if(ty < 0)
		                     	ty = 0;
		                     if(ty >= height)
		                     	ty = height-60;
		                    dv.style.left = tx + "px";
		                    dv.style.top = ty + "px";
		                }
		                d.onmouseup = function () {
		                    if (dv.releaseCapture) {
		                        dv.releaseCapture();
		                    }
		                    else if (window.releaseEvents) {
		                        window.releaseEvents(Event.MOUSEMOVE | Event.MOUSEUP);
		                    }
		                    d.onmousemove = null;
		                    d.onmouseup = null;
		       }
		   }
	}
function DragPsp(id) {//原生拖放函数
    var Sect = function (flag) {
        return document.getElementById(flag);
    }
    Sect(id).onmousedown = function (e) {
        var d = document;
        dv = e.target;
        var page = {
            event: function (evt) {
                var ev = evt || window.event;
                return ev;
            },
            pageX: function (evt) {
                var e = this.event(evt);
                return e.pageX || (e.clientX + document.body.scrollLeft - document.body.clientLeft);
            },
            pageY: function (evt) {
                var e = this.event(evt);
                return e.pageY || (e.clientY + document.body.scrollTop - document.body.clientTop);

            },
            layerX: function (evt) {
                var e = this.event(evt);
                return e.layerX || e.offsetX;
            },
            layerY: function (evt) {
                var e = this.event(evt);
                return e.layerY || e.offsetY;
            }
        }             
        var x = page.layerX(e);
        var y = page.layerY(e);        
        if (dv.setCapture) {
            dv.setCapture();
        }
        else if (window.captureEvents) {
            window.captureEvents(Event.MOUSEMOVE | Event.MOUSEUP);
        }
        d.onmousemove = function (e) {                    
            var tx = page.pageX(e) - x;
            var ty = page.pageY(e) - y;
            var wrap = $('#drop');
            var wrap_left = parseInt(wrap.css('left').slice(0,-2));
            var wrap_right = parseInt(wrap.css('right').slice(0,-2));
            var wrap_top = parseInt(wrap.css('top').slice(0,-2));
            var wrap_bottom = parseInt(wrap.css('bottom').slice(0,-2));
            var width = parseInt(wrap.css('width').slice(0,-2));
            var height = parseInt(wrap.css('height').slice(0,-2));
             tx = tx - wrap_left;
             ty = ty - wrap_top;
             console.log('x='+tx+",y="+ty);
             if(tx < 0)
             	tx = 0;
             if(tx >= width)
             	tx = width-60;
             if(ty < 0)
             	ty = 0;
             if(ty >= height)
             	ty = height-60;
            dv.style.left = tx + "px";
            dv.style.top = ty + "px";
        }
        d.onmouseup = function () {
            if (dv.releaseCapture) {
                dv.releaseCapture();
            }
            else if (window.releaseEvents) {
                window.releaseEvents(Event.MOUSEMOVE | Event.MOUSEUP);
            }
            d.onmousemove = null;
            d.onmouseup = null;
        }
    }
}
