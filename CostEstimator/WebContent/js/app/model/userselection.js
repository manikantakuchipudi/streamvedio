/**
 * 
 */


var user_selection=Backbone.Model.extend(
{
	

	// Will contain three attributes.
	// These are their default values

	defaults:{
		title: 'My service',
		price: 100,
		checked: false
	},

	// Helper function for checking/unchecking a service
	toggle: function(){
		this.set('checked', !this.get('checked'));
	}}		


);


var user_selection_healthy=Backbone.Model.extend(
		{
			

			// Will contain three attributes.
			// These are their default values

			defaults:{
				title: 'My service',
				percent: 100,
				checked: false
			},

			// Helper function for checking/unchecking a service
			toggle: function(){
				this.set('checked', !this.get('checked'));
			}}		


		);



var user_select_customers=Backbone.Model.extend(
		
{
	defaults:{
		title: 'NoOfPersons',
		dropdowndata:['1']
		
	},

	// Helper function for checking/unchecking a service
	toggle: function(){
		this.set('checked', !this.get('checked'));
	}
	}

		
);

var user_select_decolation=Backbone.Model.extend(
		
		{
			defaults:{
				title: 'NoOfPersons',
				id:'dec',
				dcprice:100,
				costperperson:100,
				checked: false
			},
			toggle: function(){
				if(this==usdn)
					{
					
						usdn.set('checked',true);
						uspn.set('checked',false);
					}
				else if(this==uspn)
					{
					
						usdn.set('checked',false);
						uspn.set('checked',true);
					}
		       }
			});





var user_selectionList = Backbone.Collection.extend({
	model: user_selection,
	getChecked: function(){
		return this.where({checked:true});
	}
});

var usdn=new user_select_decolation({ title: 'Normal decorating fee',id:'ND', dcprice: 30,costperperson:7.5,checked:false});
var uspn=new user_select_decolation({ title: 'Premium decorating fee',id:'PD', dcprice: 50,costperperson:15,checked:false});
var usfood=new user_selection({ title: 'Food Cost Per Person', price: 25});
var usdrink=new user_selection({ title: 'Drink Cost', price: 20});
var ushealthy=new user_selection_healthy({ title: 'Healthy', percent: -5});
var uscustomers=new user_select_customers({ title: 'NoOfPersons',dropdowndata:['1','2','3','4','5','10','15','20','25','50']});


var user_selectiondata = new user_selectionList([uscustomers,usdn,uspn,usfood,usdrink,ushealthy]);