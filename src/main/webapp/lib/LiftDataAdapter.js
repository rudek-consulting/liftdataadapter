var LiftDataAdapter = DS.Adapter.extend({

    find: function(store, type, id) {
        return serverFuncs.save({type: type, id:id} );
    },

    createRecord: function(store, type, record) {
        return serverFuncs.createRecord({type: type, record:record} );
    },

    updateRecord: function(store, type, record) {
        return serverFuncs.updateRecord({type: type, record:record} );
    },

    deleteRecord: function(store, type, record) {
        return serverFuncs.deleteRecord({type: type, record:record} );
    },

    findAll: function(store, type, sinceToken) {
        return serverFuncs.findAll({type: type, sinceToken:sinceToken} );
    },

    findQuery: function(store, type, query) {
        return serverFuncs.findQuery({type: type, query:query} );
    }

});