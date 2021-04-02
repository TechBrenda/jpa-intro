import React from 'react';
import {Route, Switch} from 'react-router-dom';
import Header from './common/Header';
import Home from './Home';
import About from './About';
import Employee from './Employee';
import ManageEmployee from './Employee/ManageEmployee';
import PageNotFound from './PageNotFound';

const App = () => (
    <div className={'container-fluid'}>
        <Header />
        <Switch>
            <Route exact path={'/'} component={Home} />
            <Route path={'/about'} component={About} />
            <Route path={'/employees'} component={Employee} />
            <Route path={'/employee/:id'} component={ManageEmployee} />
            <Route path={'/employee'} component={ManageEmployee} />
            <Route component={PageNotFound} />
        </Switch>
    </div>
);

export default App;