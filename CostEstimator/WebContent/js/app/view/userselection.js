/**
 * 
 */


var user_selection_view=Backbone.View.extend(
{
	

	tagName: 'li',
	events:{
		'click': 'toggleService'
	},

	initialize: function(){
		
		this.listenTo(this.model, 'change', this.render);
		if(this.model.get('price')==null&&this.model.get('percent')==null&&this.model.get('dcprice')==null)
		this.noofpersons();
	},

	render: function(){

		// Create the HTML

		if(this.model.get('price')!=undefined)
		{
		this.$el.html('<input type="checkbox" value="1" name="' + this.model.get('title') + '" /> ' + this.model.get('title') + '<span>$' + this.model.get('price') + '</span>');
		
		}
		else if(this.model.get('percent')!=undefined)
			{
			
			this.$el.html('<input type="checkbox" value="1" name="' + this.model.get('title') + '" /> ' + this.model.get('title') + '<span>' + this.model.get('percent') + '%</span>');
			
			}	
		else if(this.model.get('dcprice')!=undefined)	
		{
			this.$el.html('<input type="checkbox" value="1" name="' + this.model.get('title') + '" /> ' + this.model.get('title') + '<span>$' + this.model.get('dcprice') + '</span>');
		
			
		}			
		
		this.$('input').prop('checked', this.model.get('checked'));
		return this;
	},
	noofpersons:function()
	{
		
		var dropdowndata=this.model.get('dropdowndata');
		var data="";
		for(var i=0;i<dropdowndata.length;i++)
			{
			data+="<option value='"+dropdowndata[i]+"'>";
			data+=dropdowndata[i];
			data+="</option>";
		}
		this.$el.html('<div>'+this.model.get('title') +'<span><select name= "persons" id="persons">'+data+'</select></span></div>');
		this.$('input').prop('checked', this.model.get('checked'));
			
	},
	

	
	
	
	toggleService: function(){
		
		this.model.toggle();
	}
});




