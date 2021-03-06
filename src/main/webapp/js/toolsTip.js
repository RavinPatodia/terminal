$.fn.extend({
	hoverTips : function (){
		var self = $(this);
		var sw = self.get(0).switch;
		if( !sw ) {
			sw = true;
			var content = self.attr("tooltips");
			var htmlDom = $("<div class='tooltips'>")
					.addClass("yellow")
					.html("<p class='content'></p>"
							+ "<p class='triangle-front'></p>"
							+ "<p class='triangle-back'></p>");
			htmlDom.find("p.content").html( content );
		}
		self.on("mouseover",function(){
			$("body").append( htmlDom );
			var left = self.offset().left - htmlDom.outerWidth()/2 + self.outerWidth()/2;
			var top = self.offset().top + htmlDom.outerHeight()/2 - 23 + parseInt(htmlDom.find(".triangle-front").css("border-width"));
			htmlDom.css({"left":left,"top":top - 10,"display":"block"});
			htmlDom.stop().animate({ "top" : top ,"opacity" : 1},300);
		});
		self.on("mouseout",function(){
			var top = parseInt(htmlDom.css("top"));
			htmlDom.stop().animate({ "top" : top - 30 ,"opacity" : 0},300,function(){
				htmlDom.remove();
				sw = false;
			});
		});
	},
});