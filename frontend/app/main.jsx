var TodoItem = React.createClass({
    mixins: [React.addons.LinkedStateMixin],

    getInitialState: function () {
        return {editModeActive: false, userDescription: this.props.description};
    },

    remove: function () {
        this.props.remove();
    },

    updateDescription: function () {
        this.props.update({description: this.state.userDescription});
        this.setState({editModeActive: false});
    },

    toggleImportant: function () {
        this.props.update({important: !this.props.important});
    },

    switchToEditMode: function () {
        this.setState({editModeActive: true});
    },

    render: function () {
        var tdContent, tdButtons;

        if (this.state.editModeActive) {
            tdContent = (
                <td>
                    <input type="text" valueLink={this.linkState('userDescription')}/>
                </td>
            );
            tdButtons = (
                <td>
                    <button className="btn btn-default" onClick={this.updateDescription}>Update</button>
                </td>
            )
        } else {
            tdContent = (
                <td> {this.props.description}
                </td>
            );
            tdButtons = (
                <td>
                    <div className="btn-group">
                        <button className="btn btn-default" onClick={this.switchToEditMode}>Edit...</button>
                        <button className="btn btn-default" onClick={this.toggleImportant}>Toggle</button>
                        <button className="btn btn-danger" onClick={this.remove}>Delete</button>
                    </div>
                </td>
            );
        }

        return (
            <tr className={this.props.important ? 'success' : ''}>
                {tdContent}
                {tdButtons}
            </tr>
        );
    }
});

var NewItemRow = React.createClass({
    mixins: [React.addons.LinkedStateMixin],

    getInitialState: function () {
        return {userInput: '', important: false}
    },

    save: function (e) {
        e.preventDefault();
        this.props.save({description: this.state.userInput, important: this.state.important});
    },

    render: function () {
        return (
            <tr>
                <td>
                    <form className="form-inline">
                        <input
                            type="text"
                            placeholder="Create new item..."
                            className="form-control"
                            valueLink={this.linkState('userInput')}
                        />
                    </form>
                </td>
                <td>
                    <div class="checkbox ">
                        <label class="checkbox-inline">
                            <input type="checkbox" checkedLink={this.linkState('important')}>Important</input>
                        </label>
                    </div>
                    <button className="btn btn-primary" onClick={this.save}>Save</button>
                </td>
            </tr>
        )
    }
});


var TodoList = React.createClass({
    getInitialState: function () {
        return {items: []};
    },

    componentDidMount: function () {
        this.refreshData();
    },

    refreshData: function () {
        DAO.findAll().then(items => {
            this.setState({items: items});
        });
    },

    save: function (props) {
        DAO.save(props).then(this.refreshData);
    },

    remove: function (id) {
        DAO.remove(id).then(this.refreshData);
    },

    update: function (id, props) {
        DAO.update(id, props).then(this.refreshData);
    },

    render: function () {
        var items = this.state.items.map((item) =>
            <TodoItem
                description={item.description}
                important={item.importance}
                key={item.id}
                remove={this.remove.bind(this, item.id)}
                update={this.update.bind(this, item.id)}
            />
        );

        return (
            <div className="row">
                <div className="col-md-6 col-md-push-3">
                    <h1 className="text-center">
                        My Todo List
                        <small> I suck at frontend
                            <small> and I loathe it
                                <small> backend ftw</small>
                            </small>
                        </small>
                    </h1>

                    <table className="table table-bordered">
                        <tbody>
                        <NewItemRow save={this.save}/>
                        {items}
                        </tbody>
                    </table>
                </div>
            </div>
        )
    }
});


ReactDOM.render(<TodoList/>, document.getElementById('react-target'));