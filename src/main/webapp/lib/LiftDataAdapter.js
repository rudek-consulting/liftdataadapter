var LiftDataAdapter = DS.Adapter.extend({
    find: function(store, type, id) {
        return liftDataService.save({type: type.typeKey, id:id} );
    },

    createRecord: function(store, type, record) {
        return liftDataService.createRecord({type: type.typeKey, record:record} );
    },

    updateRecord: function(store, type, record) {
        return liftDataService.updateRecord({type: type.typeKey, record:record} );
    },

    deleteRecord: function(store, type, record) {
        return liftDataService.deleteRecord({type: type.typeKey, record:record} );
    },

    findAll: function(store, type, sinceToken) {
        return liftDataService.findAll({type: type.typeKey, sinceToken: sinceToken} );
    },

    findQuery: function(store, type, query) {
        return liftDataService.findQuery({type: type.typeKey, query:query} );
    }

});