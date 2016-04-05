var DAO = (function () {
    var baseUrl = '/todoitems';

    return {
        findAll: function () {
            return new Promise(function (resolve, reject) {
                request({
                    url: baseUrl,
                    method: 'GET',
                    json: true
                }, function (err, response, body) {
                    err ? reject(err) : resolve(body);
                })
            })
        },

        save: function (props) {
            return new Promise(function (resolve, reject) {
                request(
                    {
                        url: baseUrl,
                        method: 'POST',
                        json: true,
                        body: {description: props.description, importance: props.important}
                    },
                    function (err, response, body) {
                        err ? reject(err) : resolve(body);
                    }
                )
            })
        },
        
        remove: function (id) {
            return new Promise(function (resolve, reject) {
                request(
                    {
                        url: baseUrl + '/' + id,
                        method: 'DELETE'
                    },
                    function (err) {
                        err ? reject(err) : resolve(true);
                    }
                );
            });
        },
        update: function (id, props) {
            // TODO fix the following hack
            if (props.hasOwnProperty('important')) {
                props.importance = props.important;
                delete props.important;
            }

            return new Promise(function (resolve, reject) {
                request(
                    {
                        url: baseUrl + '/' + id,
                        method: 'PATCH',
                        json: true,
                        body: props
                    },
                    function (err, response, body) {
                        err ? reject(err) : resolve(body);
                    }
                );
            });
        }
    }
})();