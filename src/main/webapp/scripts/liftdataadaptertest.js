App = Ember.Application.create();

App.ApplicationAdapter = MyAdapter;

App.Router.map(function() {
    this.resource('exampleView', { path: '/'});
});

App.ExampleView = DS.Model.extend({
  title: DS.attr('string'),
  isCompleted: DS.attr('boolean')
});

App.ExampleViewRoute = Ember.Route.extend({
  model: function() {
    return this.store.find('exampleView');
  }
});
