const NotFound = { template: '<p>Page not found</p>' }
const Home = { template: '<p>home page</p>' }
const About = { template: '<p>about page</p>' }
const routes = {
  '/': Home,
  '/about': About
}

var restApiPeople = {
	findAll: {
		method: 'GET', 
		url:'people/findAll'
	}
};

var restResourcePeople = Vue.resource('people', {}, restApiPeople);

Vue.component('demo-grid', {
  template: '#grid-template',
  replace: true,
  props: {
    data: Array,
    columns: Array,
    filterKey: String
  },
  data: function () {
    var sortOrders = {}
    this.columns.forEach(function (key) {
      sortOrders[key] = 1
    })
    return {
      sortKey: '',
      sortOrders: sortOrders
    }
  },
  computed: {
    filteredData: function () {
      var sortKey = this.sortKey;
      var filterKey = this.filterKey && this.filterKey.toLowerCase();
      var order = this.sortOrders[sortKey] || 1;
      var data = this.data;
      if (filterKey) {
        data = data.filter(function (row) {
          return Object.keys(row).some(function (key) {
            return String(row[key]).toLowerCase().indexOf(filterKey) > -1;
          })
        })
      }
      if (sortKey) {
        data = data.slice().sort(function (a, b) {
          a = a[sortKey];
          b = b[sortKey];
          return (a === b ? 0 : a > b ? 1 : -1) * order;
        })
      }
      return data;
    }
  },
  filters: {
    capitalize: function (str) {
      return str.charAt(0).toUpperCase() + str.slice(1);
    }
  },
  methods: {
    sortBy: function (key) {
      this.sortKey = key
      this.sortOrders[key] = this.sortOrders[key] * -1;
    }
  }
})

var demo = new Vue({
  el: '#demo',
  data: {
    searchQuery: '',
    gridColumns: ['uuid', 'firstName', 'middleName', 'lastName'],
    gridData: []
  },
  methods: {
	  findAll: function() {
		  restResourcePeople.findAll().then(
			  (response) => {
				  this.gridData = response.data;
				  console.log('response: ', response.data);
			  },
			  (response) => {
				  console.log('error: ', response);
			  }
		  );
  	}
  },
  mounted: function() {
	  this.findAll();
  }
})
