import request from 'request';

const baseUrl = '/todoitems';

const DAO = {
    findAll() {
        return new Promise((resolve, reject) => {
            request({
                    url: baseUrl,
                    method: 'GET',
                    json: true
                },
                (err, response, body) => err ? reject(err) : resolve(body)
            )
        })
    },

    save(props) {
        return new Promise((resolve, reject) => {
            request({
                    url: baseUrl,
                    method: 'POST',
                    json: true,
                    body: {description: props.description, important: props.important}
                },
                (err, response, body) => err ? reject(err) : resolve(body)
            )
        })
    },

    remove(id) {
        return new Promise((resolve, reject) => {
            request({
                    url: baseUrl + '/' + id,
                    method: 'DELETE'
                },
                (err) => err ? reject(err) : resolve(true)
            );
        });
    },
    update(id, props) {
        return new Promise((resolve, reject) => {
            request({
                    url: baseUrl + '/' + id,
                    method: 'PATCH',
                    json: true,
                    body: props
                },
                (err, response, body) => err ? reject(err) : resolve(body)
            );
        });
    }
};

export default DAO;