/*
	loadBox 0.1
	
	
*/



wopu.loadBox = wopu.BaseClass.extend({
	_options : {
		showOnStartup : false,
		wrapBorders   : true,
		cssClass	  : "loadBox",
		animateBG	  : true,
		loadingImage  : "../../src/loadBox/imgs/loader_a.gif",
		"z-index"	  : 100,
		debug 		  : false
	},
	construct : function(el, o){
		this.options = {};
		$.extend(this.options, this._options );
		$.extend(this.options, o || {});
		
		this.el = $(el);
		
		this.box = $("<div>")
		  .addClass(this.options.cssClass)
		  .hide()
		  .css({
			  "z-index" : this.options["z-index"]
		  })
		  .appendTo(this.el.offsetParent());
		
		if(this.options.showOnStartup) this.show();
	},
	
	
	show : function(){
		if(this.displayed) return;
		this.displayed = true;
		
		var self = this;
		var el = this.el;
		var parent = el.offsetParent();
		var pos = el.position();
		
		var box = this.box;
		box.show();
		
		var h = this.options.wrapBorders ? el.outerHeight() : el.innerHeight();
		var w = this.options.wrapBorders ? el.outerWidth() : el.innerWidth();
		var t = el.position().top  + (this.options.wrapBorders ? 0 : parseInt(el.css("border-top-width"), 10));
		var l = el.position().left + (this.options.wrapBorders ? 0 : parseInt(el.css("border-left-width"), 10));
		box.css({
			height : h,
			width  : w,
			top	   : t,
			left   : l
		});
		
		if(this.options.loadingImage){
			this.image = $("<img>")
				.appendTo(parent)
				.css({
					  	position  : "absolute",
						"z-index" : this.options["z-index"]-1
				  })
				.attr("src", this.options.loadingImage)
				.hide()
				.load(function(){
					var img = $(this);
					img.show();
					var ih = img.height();
					var iw = img.width();
					
					if(ih>h){
						ih = h;
						img.attr("height", h);
					}
					if(iw>w){
						iw = w;
						img.attr("width", w);
					}
					
					img.css({
						top  : t + ((h-ih)/2),
						left : l + ((w-iw)/2)
					});
					
				})
		}
		

		var cycleAnimation = function(){
			if(!self.displayed) return;
			box.fadeTo(2000, 0.45)
			   .fadeTo(2000, 0.80, cycleAnimation);
		};
		if(this.options.animateBG){
			cycleAnimation();
		}

	},

	hide : function(){
		if(!this.displayed) return;
		this.displayed = false;
		
		this.box.stop();
		this.box.hide();
		if(this.image) this.image.hide();
	}
	
});
