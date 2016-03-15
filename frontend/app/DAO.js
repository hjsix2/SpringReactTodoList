var DAO = (function () {
    var db = [
        {
            id: 1,
            description: "Get a life",
            important: false
        },
        {
            id: 2,
            description: "Get a better bike",
            important: true
        },
        {
            id: 3,
            description: "Get a wife",
            important: true
        }
    ];
    var nextId = db.length + 1;

    return {
        findAll: function () {
            return Promise.resolve(db);
        },
        save: function (props) {
            var newItem = {description: props.description, important: props.important, id: nextId++};
            db.unshift(newItem);
            return Promise.resolve(newItem);
        },
        remove: function (id) {
            db = _.filter(db, function (item) {
                return item.id != id;
            });
            return Promise.resolve(true); // ?
        },
        update: function (id, props) { // if undefined then no update
            var item = _.find(db, function (item) {
                return item.id == id;
            });
            
            if (props.description !== undefined) {
                item.description = props.description;
            }
            if (props.important !== undefined) {
                item.important = props.important;
            }
            return Promise.resolve(true); // ?
        }
    }
})();